package com.yeepay.g3.ymf.pay.param;


import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.utils.common.json.JSONUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 异步JSON响应数据
 * @author xiaobin.liu
 *
 */
public class JsonResponse implements Serializable {
	private static final long serialVersionUID = 8992436576262574064L;
	private String code ;
	private String msg ;
	private Object data ;

	public JsonResponse() {

	}

	/**
	 * 构造器
	 * @param TrxCode
	 * @param data
	 */
	protected JsonResponse(TrxCode TrxCode, Object data) {
		this.data = data;
	}

	/**
	 * 构造器,重写msg
	 * @param TrxCode
	 * @param data
	 * @param msg
	 */
	protected JsonResponse(TrxCode TrxCode, Object data, String msg) {
		this(TrxCode, data);
		this.msg = msg;
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (data != null)
			map.put("data", this.getData());
		if (msg != null)
			map.put("message", this.getMsg());
		map.put("code", this.getCode());
		return map;
	}

	/**
	 * 成功
	 * @return
	 */
	public static JsonResponse ok() {
		return ok(TrxCode.T00);
	}

	/**
	 * 成功返回数据
	 * @param data
	 * @return
	 */
	public static JsonResponse ok(Object data) {
		return new JsonResponse(TrxCode.T00, data);
	}

	/**
	 * 错误
	 * @param code	错误码
	 * @return
	 */
	public static JsonResponse error(TrxCode code) {
		return new JsonResponse(code,null);
	}

	/**
	 * 错误
	 * @param code	错误码
	 * @param message	自定义错误消息
	 * @return
	 */
	public static JsonResponse error(TrxCode code,String message) {
		return new JsonResponse(code,null,message);
	}

	@Override
	public String toString() {
		return JSONUtils.toJsonString(this);
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
