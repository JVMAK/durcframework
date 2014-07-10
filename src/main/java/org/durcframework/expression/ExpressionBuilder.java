package org.durcframework.expression;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.durcframework.expression.annotation.AnnoExprStore;
import org.durcframework.expression.annotation.ListField;
import org.durcframework.expression.annotation.ValueField;
import org.durcframework.expression.getter.ExpressionGetter;
import org.durcframework.expression.subexpression.ListExpression;
import org.durcframework.expression.subexpression.ValueExpression;
import org.springframework.util.StringUtils;

/**
 * 从bean中获取Expression
 * 
 * @author thc 2011-10-25
 */
public class ExpressionBuilder {

	private static final String PREFIX_GET = "get";

	// =============内部类=============
	// 构建list查询条件
	private static class ListExpressionGetter implements ExpressionGetter {

		@Override
		public Expression buildExpression(Annotation annotation, String column,
				Object value) {
			if (value == null) {
				return null;
			}
			ListField listValueField = (ListField) annotation;
			String joint = listValueField.joint();
			String equal = listValueField.equal();
			String field = listValueField.column();
			if (StringUtils.hasText(field)) {
				column = field;
			}
			if (value.getClass().isArray()) {
				return new ListExpression(joint, column, equal,
						(Object[]) value);
			}
			if (value instanceof List) {
				return new ListExpression(joint, column, equal, (List<?>) value);
			}
			return null;
		}
	}
	
	// 构建单值查询条件工厂
	private static class ValueExpressionGetter implements ExpressionGetter {

		@Override
		public Expression buildExpression(Annotation annotation, String column,
				Object value) {
			if (value == null) {
				return null;
			}
			if (value instanceof String) {
				if (!StringUtils.hasText((String) value)) {
					return null;
				}
			}
			ValueField valueField = (ValueField) annotation;
			String fieldColumn = valueField.column();
			if (StringUtils.hasText(fieldColumn)) {
				column = fieldColumn;
			}
			return new ValueExpression(valueField.joint(), column,
					valueField.equal(), value);
		}

	}

	static {
		AnnoExprStore.addExpressionGetter(ListField.class,
				new ListExpressionGetter());
		AnnoExprStore.addExpressionGetter(ValueField.class,
				new ValueExpressionGetter());
	}

	/**
	 * 获取条件表达式
	 * @param obj
	 * @return
	 */
	public static List<Expression> buildExpressions(Object obj) {
		if (obj == null) {
			return null;
		}
		List<Expression> expList = new ArrayList<Expression>();
		Method[] methods = obj.getClass().getMethods();
		try {
			for (Method method : methods) {
				String methodName = method.getName();
				Annotation[] annotations = method.getAnnotations();

				if (couldBuildExpression(methodName, annotations)) {
					Object value = method.invoke(obj, new Object[] {});

					String column = buildColumn(methodName);

					expList.addAll(buildExpression(annotations, column, value));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expList;
	}

	private static List<Expression> buildExpression(Annotation[] annotations,
			String column, Object value) {
		List<Expression> expList = new ArrayList<Expression>();

		for (Annotation annotation : annotations) {

			ExpressionGetter expressionGetter = AnnoExprStore.get(annotation);

			Expression expression = expressionGetter.buildExpression(
					annotation, column, value);

			if (expression != null) {
				expList.add(expression);
			}
		}

		return expList;
	}

	// 构建列名
	private static String buildColumn(String methodName) {
		return methodName.substring(3, 4).toLowerCase()
				+ methodName.substring(4);

	}

	// 能否构建表达式
	private static boolean couldBuildExpression(String methodName,
			Annotation[] annotations) {
		return methodName.startsWith(PREFIX_GET)
				&& hasExpressionAnnotation(annotations);
	}

	// 是否有注解
	private static boolean hasExpressionAnnotation(Annotation[] annotations) {
		if (annotations == null || annotations.length == 0) {
			return false;
		}
		for (Annotation annotation : annotations) {
			if (AnnoExprStore.get(annotation) != null) {
				return true;
			}
		}
		return false;
	}

}
