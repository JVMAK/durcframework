package org.durcframework.controller;

import org.durcframework.dao.BaseDao;
import org.durcframework.entity.BaseEntity;
import org.durcframework.entity.ValidateHolder;
import org.durcframework.exception.DurcException;
import org.durcframework.service.CrudService;
import org.durcframework.util.MyBeanUtil;
import org.durcframework.util.ResultUtil;
import org.springframework.web.servlet.ModelAndView;

/**
 * 增删改查的Controller
 * @author hc.tang
 * 2013年11月14日
 *
 * @param <Entity> 实体类
 * @param <Service> 增删改查的Service
 */
public abstract class CrudController<Entity extends BaseEntity, Service extends CrudService<Entity, ? extends BaseDao<Entity>>>
		extends SearchController<Entity, Service> {

	/**
	 * 新增记录
	 * @param entity
	 * @return
	 */
	public ModelAndView save(Entity entity) {
		ValidateHolder validateHolder = entity.validate();
		if(validateHolder.isSuccess()){
			this.getService().save(entity);
			return ResultUtil.success();
		}
		return ResultUtil.validateError(validateHolder);
	}

	/**
	 * 修改记录
	 * @param entity
	 * @return
	 */
	public ModelAndView update(Entity entity) {
		Entity e = getService().get(entity);
		if (e == null) {
			throw new DurcException("修改失败-该记录不存在");
		} 
		MyBeanUtil.copyProperties(entity, e);
		getService().update(e);
		return ResultUtil.success();
	}

	/**
	 * 删除记录,需要实现Entity.getPk()方法
	 * @param entity
	 * @return
	 */
	public ModelAndView delete(Entity entity) {
		Entity e = getService().get(entity);
		if (e == null) {
			throw new DurcException("删除失败-该记录不存在");
		} else {
			getService().del(entity);
		}
		return ResultUtil.success();
	}
	
}
