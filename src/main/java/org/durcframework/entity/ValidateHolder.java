package org.durcframework.entity;

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * 验证结果
 * @author hc.tang
 * 2014年6月19日
 * @param <T>
 *
 */
public class ValidateHolder {
	private boolean isSuccess;
	private Set<ConstraintViolation<Object>> constraintViolations;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Set<ConstraintViolation<Object>> getConstraintViolations() {
		return constraintViolations;
	}

	public void setConstraintViolations(
			Set<ConstraintViolation<Object>> constraintViolations) {
		this.constraintViolations = constraintViolations;
	}

}
