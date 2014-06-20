package org.durcframework.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author thc 2011-10-22
 */
public class ClassUtil {

	private ClassUtil() {
	};

	private static String classPath = "";

	private static ClassUtil classUtil = new ClassUtil();


	/**
	 * 返回定义类时的泛型参数的类型. <br/>
	 * 如:定义一个BookManager类<br>
	 * <code>{@literal public BookManager extends GenricManager<Book,Address>}{...} </code><br>
	 * 调用getSuperClassGenricType(getClass(),0)将返回Book的Class类型<br>
	 * 调用getSuperClassGenricType(getClass(),1)将返回Address的Class类型
	 * 
	 * @param clazz
	 *            从哪个类中获取
	 * @param index
	 *            泛型参数索引,从0开始
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index)
			throws IndexOutOfBoundsException {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	private String _getClassPath() {
		if ("".equals(classPath)) {
			classPath = getClass().getClassLoader().getResource("").getPath();
		}
		return classPath;
	}

	/**
	 * 获取class根目录
	 * 
	 * @return
	 */
	public static String getClassRootPath() {
		return classUtil._getClassPath();
	}
	
	/**
	 * 返回类名并且第一个字母小写
	 * @param clazz
	 * @return
	 * @author hc.tang
	 */
	public static String getClassSimpleName(Class<?> clazz) {
		String className = clazz.getSimpleName();
		return className.substring(0, 1).toLowerCase()
				+ className.substring(1);
	}
	
	public static void main(String[] args) {
		System.out.println(getClassRootPath());
	}

}
