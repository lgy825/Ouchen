package com.ouchen.core.util;


import javax.servlet.http.HttpServletRequest;


/**
 *
 * 验证码生成器 vg.check(request)
 */
public class ValidCodeUtil {

	public static final String VALIDATE_CODE = "validateCode";
	private static final ValidCodeUtil generator = new ValidCodeUtil();

	private ValidCodeUtil() {
		//
	}

	public static boolean validate(HttpServletRequest request,String validateCode) {
		String code = request.getSession().getAttribute(VALIDATE_CODE).toString();
		return validateCode.toUpperCase().equals(code);
	}


}
