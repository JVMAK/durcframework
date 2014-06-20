package org.durcframework.util;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.durcframework.entity.BaseEntity;
import org.durcframework.entity.ValidateHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

public class ResultUtil {

	private static final String JSON_NAME = "json";
	/**
	 * 返回json数据的jsp页面
	 */
	private static final String S_RESULT_JSP_NAME = "result";

	// 返回到页面的结果:{"success":false,"errorMsg":"错误..."}
	private static final String F_ERROR_MSG = "{\"success\":false,\"errorMsg\":\"%s\"}";
	// 返回到页面的结果:{"success":true,"msg":""}
	private static final String F_SUCCESS_MSG = "{\"success\":true,\"msg\":\"%s\"}";
	// 返回到页面的结果:{"success":false,"validateErrors":["用户名错误","密码不正确"]}
	private static final String F_VALIDATE_ERROR_MSG = "{\"success\":false,\"validateErrors\":%s}";


	/**
	 * 返回json格式字符串
	 * @param json json字符串
	 * @return
	 */
	public static ModelAndView buildModelAndView(String json) {
		if (StringUtils.hasText(json)) {
			return new ModelAndView(S_RESULT_JSP_NAME, JSON_NAME, json);
		}

		return success();
	}

	/**
	 * 返回出错信息
	 * @param msg
	 * @return {"errorMsg":"错误信息."}
	 */
	public static ModelAndView error(String msg) {
		return buildModelAndView(String.format(F_ERROR_MSG, msg));
	}

	/**
	 * 返回成功信息
	 * @return {"success":true,"msg":""}
	 */
	public static ModelAndView success() {
		return success("");
	}
	
	/**
	 * 返回成功信息
	 * @return {"success":true}
	 */
	public static ModelAndView success(String msg) {
		return buildModelAndView(String.format(F_SUCCESS_MSG, msg));
	}
	
	/**
	 * 返回验证后的结果
	 * @param holder
	 * @return 如果验证正确,返回success()
	 */
	public static ModelAndView validateError(ValidateHolder holder){
		if(holder.isSuccess()){
			return success();
		}
		
		String validateErrorJson = buildValidateErrorsJson(holder);
		
		return buildModelAndView(String.format(F_VALIDATE_ERROR_MSG, validateErrorJson));
	}
	
	// 返回格式类似于:["用户名错误","密码不正确"]
	private static String buildValidateErrorsJson(ValidateHolder holder){
		Set<ConstraintViolation<BaseEntity>> set = holder.getConstraintViolations();
		StringBuilder msg = new StringBuilder();
		
		for (ConstraintViolation<BaseEntity> c : set) {
			msg.append(",").append("\"").append(c.getMessage()).append("\"");
		}
		
		return "[" + msg.toString().substring(1) + "]";
		
	}
	
}
