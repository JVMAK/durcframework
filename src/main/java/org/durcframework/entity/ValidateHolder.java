package org.durcframework.entity;

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * 验证结果
 * @author hc.tang
 * 2014年6月19日
 *
 */
public class ValidateHolder {
	private boolean isSuccess;
	private Set<ConstraintViolation<BaseEntity>> constraintViolations;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Set<ConstraintViolation<BaseEntity>> getConstraintViolations() {
		return constraintViolations;
	}

	public void setConstraintViolations(
			Set<ConstraintViolation<BaseEntity>> constraintViolations) {
		this.constraintViolations = constraintViolations;
	}

}
