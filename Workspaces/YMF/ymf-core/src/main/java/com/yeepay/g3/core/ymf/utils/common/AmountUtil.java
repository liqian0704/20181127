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

	/**
	 * double型转成BigDecimal的带小数点金额
	 */
	public static BigDecimal formatDoubleAmout(Double amt) {
		if (amt == null) {
			amt = 0d;
		}
		BigDecimal bd = new BigDecimal(amt/1.00);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}


	/**
	 * 交易金额减去费用
	 * @param trxAmt
	 * @param fee
	 * @return
	 */
	public static BigDecimal getSubstractFeeOrderAmount(BigDecimal trxAmt,BigDecimal fee){
		if (trxAmt == null) {
			return null;
		}
		return trxAmt.subtract(fee);
	}

	public static void main(String args[]) {
//		Double amt = 1223.1d;
		Double amt = null;
		System.out.println(formatDoubleAmout(amt));
	}

}
