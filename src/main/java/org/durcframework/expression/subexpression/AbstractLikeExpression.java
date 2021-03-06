package org.durcframework.expression.subexpression;

import org.durcframework.expression.SqlContent;

public abstract class AbstractLikeExpression extends ValueExpression {

	public AbstractLikeExpression(String column, Object value) {
		super(column, value);
	}

	public AbstractLikeExpression(String joint, String column, Object value) {
		super(joint, column, SqlContent.LIKE, value);
	}

	@Override
	public String getEqual() {
		return SqlContent.LIKE;
	}

}
