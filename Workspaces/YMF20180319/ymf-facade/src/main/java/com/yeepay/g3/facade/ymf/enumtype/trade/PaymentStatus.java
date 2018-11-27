package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * SpayPayment支付状态值
 * @author xiaobin.liu
 *
 */
public enum PaymentStatus {

	SUCCESS {
		@Override
		public String getOrderStatus() {
			return "支付成功";
		}

		@Override
		public String getRefundStatus() {
			return "退款成功";
		}

	},
	FAIL {
		@Override
		public String getOrderStatus() {
			return "支付失败";
		}

		@Override
		public String getRefundStatus() {
			return "退款失败";
		}
	},
	INIT {
		@Override
		public String getOrderStatus() {
			return "未支付";
		}

		@Override
		public String getRefundStatus() {
			return "未退款";
		}
	},
	PROCESS {
		@Override
		public String getOrderStatus() {
			return "支付中";
		}

		@Override
		public String getRefundStatus() {
			return "退款中";
		}
	};

	/**
	 * 订单payment状态
	 * @return
     */
	public abstract String getOrderStatus();

	/**
	 * 退款payment状态
	 * @return
     */
	public abstract String getRefundStatus();

}
