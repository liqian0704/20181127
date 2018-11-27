package com.yeepay.g3.facade.ymf.enumtype.common;

/**
 * 交易类型
 * 订单支付状态
 * 订单清算状态
 * 订单支付通知状态
 * 订单退款状态
 * Created by aoick on 2016/8/28.
 */
public enum TradeStatus {

    INIT {
        @Override
        public String getOrderStatus() {
            return "未支付";
        }

        @Override
        public String getRefundStatus() {
            return "未退款";
        }

        @Override
        public String getPayStatus() {
            return "未支付";
        }

        @Override
        public String getSettleStatus() {
            return "未结算";
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

        @Override
        public String getPayStatus() {
            return "支付中";
        }

        @Override
        public String getSettleStatus() {
            return "支付中";
        }
    },

    CANCEL {
        @Override
        public String getOrderStatus() {
            return "订单取消";
        }

        @Override
        public String getRefundStatus() {
            return "退款取消";
        }

        @Override
        public String getPayStatus() {
            return "支付取消";
        }

        @Override
        public String getSettleStatus() {
            return "订单取消";
        }
    },


    SUCCESS {
        @Override
        public String getOrderStatus() {
            return "订单成功";
        }

        @Override
        public String getRefundStatus() {
            return "退款成功";
        }

        @Override
        public String getPayStatus() {
            return "支付成功";
        }

        @Override
        public String getSettleStatus() {
            return "结算成功";
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

        @Override
        public String getPayStatus() {
            return "支付失败";
        }

        @Override
        public String getSettleStatus() {
            return "结算失败";
        }
    },

    TIMEOUT {
        @Override
        public String getOrderStatus() {
            return null;
        }

        @Override
        public String getRefundStatus() {
            return null;
        }

        @Override
        public String getPayStatus() {
            return null;
        }

        @Override
        public String getSettleStatus() {
            return null;
        }
    };

    // for javabean
    private String orderStatus;
    private String refundStatus;
    private String payStatus;
    private String settleStatus;

    public abstract String getOrderStatus();

    public abstract String getRefundStatus();

    public abstract String getPayStatus();

    public abstract String getSettleStatus();

}
