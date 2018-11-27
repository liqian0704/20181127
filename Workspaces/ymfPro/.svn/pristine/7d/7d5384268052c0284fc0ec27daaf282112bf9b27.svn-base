package com.yeepay.g3.facade.laike.exception;

import com.yeepay.g3.facade.foundation.dto.DefaultErrorCode;
import com.yeepay.g3.facade.foundation.dto.ErrorMeta;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.foundation.util.ErrorCodeUtility;

/**
 * 通过错误码系统获取错误码系统<br/>
 * Created by Felix on 4/20/16.
 */
public class SystemErrorCodeTranslator implements ErrorCodeTranslator {


	public static final String LIKER_SYS_CODE = "LK";


	private static final Logger logger = LoggerFactory.getLogger(SystemErrorCodeTranslator.class);

	/**
	 * 单例模式
	 */
	private static class Instance {
		private static SystemErrorCodeTranslator instance = new SystemErrorCodeTranslator();
	}

	private SystemErrorCodeTranslator() {
	}

	public static SystemErrorCodeTranslator getInstance() {
		return Instance.instance;
	}

	/**
	 * Get the high pay error message of the specified error code .<br/>
	 *
	 * @param errorCode the error code whose corresponding message you want to get.
	 * @return return the corresponding error message, if there is no the error code will return
	 * null.
	 */
	@Override
	public String getMessage(String errorCode) {

		if (StringUtils.isNotEmpty(errorCode)) {
			try {
				String message = ErrorCodeUtility.retrieveErrorCodeMsg(LIKER_SYS_CODE, errorCode);
				return message;
			} catch (Exception e) {
				logger.error(
						"[Outer] - [系统异常] - [ConfigErrorCodeFacade.catchSelfErrorCode] - 调用错误码配置系统出现未知异常：errorCode:"
								+ errorCode, e);
			}
		}
		return "";
	}

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
	@Override
	public String translateCode(ErrorCodeSource source, String errorCode, String errorMessage) {
		CheckUtils.notNull(source, "source");
		CheckUtils.notEmpty(errorCode, "errorCode");
		if (StringUtils.isNotEmpty(errorCode)) {
			DefaultErrorCode defaultErrorCode =
					new DefaultErrorCode(LIKER_SYS_CODE, DEFAULT_ERROR_CODE, "系统异常");

			try {
				ErrorMeta errorMeta =
						ErrorCodeUtility.mapErrorMeta(source.getSysCode(), errorCode, errorMessage,
								defaultErrorCode);
				if (errorMeta != null) {
					return errorMeta.getErrorCode();
				}
			} catch (Exception e) {
				logger.error("[系统异常]调用错误码配置系统出现未知异常:sysCode" + source.getSysCode() + ", errorCode:"
						+ errorCode + ", errorMsg:" + errorMessage, e);
			}
		}
		return DEFAULT_ERROR_CODE;
	}
}
