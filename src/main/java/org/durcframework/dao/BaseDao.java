package org.durcframework.dao;

import java.io.Serializable;
import java.util.List;

import org.durcframework.entity.BaseEntity;
import org.durcframework.expression.ExpressionQuery;

/**
 * DAO类,其它DAO类都要继承该类
 * @author hc.tang
 * 
 * @param <Entity>
 */
public interface BaseDao<Entity extends BaseEntity> {
	// 查询部分
	Entity getById(Serializable id);

	List<Entity> find(ExpressionQuery query);

	Integer findTotalCount(ExpressionQuery query);

	// 修改部分部分
	void save(Entity entity);

	void update(Entity entity);

	void del(Entity entity);
}