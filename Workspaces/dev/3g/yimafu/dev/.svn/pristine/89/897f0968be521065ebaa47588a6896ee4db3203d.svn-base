package com.yeepay.g3.core.ymf.utils.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额处理工具类
 * @author xiaobin.liu
 *
 */
public class AmountUtil {
	
	/**
	 * long型转成BigDecimal的带小数点金额
	 */
	public static BigDecimal formatLongAmout(long amt) {
		BigDecimal bd = new BigDecimal(amt/100.00);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}

}
