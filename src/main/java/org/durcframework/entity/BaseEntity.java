package org.durcframework.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.durcframework.util.ValidateUtil;

/**
 * 基础实体类,后面的实体类都要继承这个类
 * @author thc
 * 2011-10-28
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 3135746068620126911L;

	/**
	 * 用于保存后设置POJO的自增主键
	 * 
	 * @param id
	 */
	public abstract void setPk(Serializable id);

	public abstract Serializable getPk();
	
	/**
	 * 验证当前类
	 * @return ValidateHolder 返回验证结果
	 */
	public ValidateHolder validate(){
		ValidateHolder holder = new ValidateHolder();
		
		if(this.isNeedValidate()){
			Set<ConstraintViolation<BaseEntity>> set = ValidateUtil.validate(this);
			
			
			holder.setConstraintViolations(set);
			holder.setSuccess(set.size() == 0);
			
			return holder;
		}else{
			holder.setSuccess(true);
			holder.setConstraintViolations(Collections.<ConstraintViolation<BaseEntity>> emptySet());
			
			return holder;
		}
	}
	
	/**
	 * 是否需要验证,返回true才开启验证功能,默认不验证
	 * 如果提交表单需要验证的话,子类必须重写该方法,并返回true
	 * @return
	 */
	protected boolean isNeedValidate(){
		return false;
	}
	
}
