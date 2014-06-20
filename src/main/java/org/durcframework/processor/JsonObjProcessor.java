package org.durcframework.processor;

import org.durcframework.entity.BaseEntity;

import com.alibaba.fastjson.JSONObject;

/**
 * 处理JSON对象的接口
 * 
 * @author hc.tang
 * 
 */
public interface JsonObjProcessor<Entity extends BaseEntity> {
	void process(Entity entity, JSONObject jsonObject);
}
