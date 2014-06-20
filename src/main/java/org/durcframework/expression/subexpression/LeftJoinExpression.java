package org.durcframework.expression.subexpression;

import org.durcframework.expression.SqlContent;

/**
 * 左连接条件
 * 
 * @author thc 2011-10-28
 */
public class LeftJoinExpression extends AbstractJoinExpression {

	public LeftJoinExpression(String secondTableName,
			String secondTableTableAlias, String firstTableColumn,
			String secondTableColumn) {
		super(secondTableName, secondTableTableAlias, firstTableColumn,
				secondTableColumn);
	}

	@Override
	protected String getJoinType() {
		return SqlContent.LEFT_JOIN;
	}

}
