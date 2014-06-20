package org.durcframework.expression.subexpression;

import org.durcframework.expression.SqlContent;

/**
 * 右连接条件
 * 
 * @author thc 2011-10-28
 */
public class RightJoinExpression extends AbstractJoinExpression {

	public RightJoinExpression(String secondTableName,
			String secondTableTableAlias, String firstTableColumn,
			String secondTableColumn) {
		super(secondTableName, secondTableTableAlias, firstTableColumn,
				secondTableColumn);
	}

	@Override
	protected String getJoinType() {
		return SqlContent.RIGHT_JOIN;
	}

}
