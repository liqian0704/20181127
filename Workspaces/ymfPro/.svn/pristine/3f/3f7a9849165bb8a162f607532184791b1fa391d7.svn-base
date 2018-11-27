package com.yeepay.g3.facade.laike.exception;


/**
 * 提供对接错误码系统相关服务
 *
 * @author Created by Felix on 11/17/15.
 */
public interface ErrorCodeTranslator {

	/**
	 * default error_code
	 */
	String DEFAULT_ERROR_CODE = com.yeepay.g3.facade.laike.exception.ErrorCode.HESSIAN_UNKNOW_EXCEPTION;

	/**
	 * Get the high pay error message of the specified error code .<br/>
	 *
	 * @param errorCode the error code whose corresponding message you want to get.
	 * @return return the corresponding error message, if there is no the error code will return
	 * null.
	 */
	String getMessage(String errorCode);


	/**
	 * Get the high pay error code from the specified source system, e.g nocard, nobank, auth and so
	 * on. <br/>
	 *
	 * @param source       the source system, <link> ErrorCodeSource </link>
	 * @param errorCode    the source system error code
	 * @param errorMessage the source system error message
	 * @return the transferred error code from no source system, if there is no corresponding error
	 * code will return DEFAULT_ERROR_CODE.
	 */
	String translateCode(ErrorCodeSource source, String errorCode, String errorMessage);
}
