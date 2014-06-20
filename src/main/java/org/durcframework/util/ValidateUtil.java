package org.durcframework.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 验证工具类
 * JSR303
 * @author hc.tang
 * 2014年6月5日
 *
 */
public class ValidateUtil {

	private static Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static <T> Set<ConstraintViolation<T>> validate(T paramT) {
		return validator.validate(paramT);
	}

}
