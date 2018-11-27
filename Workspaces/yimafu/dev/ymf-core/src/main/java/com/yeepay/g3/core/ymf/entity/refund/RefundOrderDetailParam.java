package com.yeepay.g3.core.ymf.entity.refund;

import com.yeepay.g3.facade.ymf.enumtype.refund.RefundSource;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundStatus;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RefundOrderDetailParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RefundOrderDetailParam() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdIsNull() {
            addCriterion("REFUNDINFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdIsNotNull() {
            addCriterion("REFUNDINFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdEqualTo(Long value) {
            addCriterion("REFUNDINFO_ID =", value, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdNotEqualTo(Long value) {
            addCriterion("REFUNDINFO_ID <>", value, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdGreaterThan(Long value) {
            addCriterion("REFUNDINFO_ID >", value, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("REFUNDINFO_ID >=", value, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdLessThan(Long value) {
            addCriterion("REFUNDINFO_ID <", value, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdLessThanOrEqualTo(Long value) {
            addCriterion("REFUNDINFO_ID <=", value, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdIn(List<Long> values) {
            addCriterion("REFUNDINFO_ID in", values, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdNotIn(List<Long> values) {
            addCriterion("REFUNDINFO_ID not in", values, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdBetween(Long value1, Long value2) {
            addCriterion("REFUNDINFO_ID between", value1, value2, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundinfoIdNotBetween(Long value1, Long value2) {
            addCriterion("REFUNDINFO_ID not between", value1, value2, "refundinfoId");
            return (Criteria) this;
        }

        public Criteria andRefundSourceIsNull() {
            addCriterion("REFUND_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andRefundSourceIsNotNull() {
            addCriterion("REFUND_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andRefundSourceEqualTo(RefundSource value) {
            addCriterion("REFUND_SOURCE =", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceNotEqualTo(RefundSource value) {
            addCriterion("REFUND_SOURCE <>", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceGreaterThan(RefundSource value) {
            addCriterion("REFUND_SOURCE >", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceGreaterThanOrEqualTo(RefundSource value) {
            addCriterion("REFUND_SOURCE >=", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceLessThan(RefundSource value) {
            addCriterion("REFUND_SOURCE <", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceLessThanOrEqualTo(RefundSource value) {
            addCriterion("REFUND_SOURCE <=", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceLike(RefundSource value) {
            addCriterion("REFUND_SOURCE like", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceNotLike(RefundSource value) {
            addCriterion("REFUND_SOURCE not like", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceIn(List<RefundSource> values) {
            addCriterion("REFUND_SOURCE in", values, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceNotIn(List<RefundSource> values) {
            addCriterion("REFUND_SOURCE not in", values, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceBetween(RefundSource value1, RefundSource value2) {
            addCriterion("REFUND_SOURCE between", value1, value2, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceNotBetween(RefundSource value1, RefundSource value2) {
            addCriterion("REFUND_SOURCE not between", value1, value2, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdIsNull() {
            addCriterion("REFUND_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdIsNotNull() {
            addCriterion("REFUND_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdEqualTo(String value) {
            addCriterion("REFUND_ORDER_ID =", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotEqualTo(String value) {
            addCriterion("REFUND_ORDER_ID <>", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdGreaterThan(String value) {
            addCriterion("REFUND_ORDER_ID >", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("REFUND_ORDER_ID >=", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdLessThan(String value) {
            addCriterion("REFUND_ORDER_ID <", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdLessThanOrEqualTo(String value) {
            addCriterion("REFUND_ORDER_ID <=", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdLike(String value) {
            addCriterion("REFUND_ORDER_ID like", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotLike(String value) {
            addCriterion("REFUND_ORDER_ID not like", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdIn(List<String> values) {
            addCriterion("REFUND_ORDER_ID in", values, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotIn(List<String> values) {
            addCriterion("REFUND_ORDER_ID not in", values, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdBetween(String value1, String value2) {
            addCriterion("REFUND_ORDER_ID between", value1, value2, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotBetween(String value1, String value2) {
            addCriterion("REFUND_ORDER_ID not between", value1, value2, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdIsNull() {
            addCriterion("REFUND_REQUEST_ID is null");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdIsNotNull() {
            addCriterion("REFUND_REQUEST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdEqualTo(String value) {
            addCriterion("REFUND_REQUEST_ID =", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdNotEqualTo(String value) {
            addCriterion("REFUND_REQUEST_ID <>", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdGreaterThan(String value) {
            addCriterion("REFUND_REQUEST_ID >", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdGreaterThanOrEqualTo(String value) {
            addCriterion("REFUND_REQUEST_ID >=", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdLessThan(String value) {
            addCriterion("REFUND_REQUEST_ID <", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdLessThanOrEqualTo(String value) {
            addCriterion("REFUND_REQUEST_ID <=", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdLike(String value) {
            addCriterion("REFUND_REQUEST_ID like", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdNotLike(String value) {
            addCriterion("REFUND_REQUEST_ID not like", value, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdIn(List<String> values) {
            addCriterion("REFUND_REQUEST_ID in", values, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdNotIn(List<String> values) {
            addCriterion("REFUND_REQUEST_ID not in", values, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdBetween(String value1, String value2) {
            addCriterion("REFUND_REQUEST_ID between", value1, value2, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdNotBetween(String value1, String value2) {
            addCriterion("REFUND_REQUEST_ID not between", value1, value2, "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdIsNull() {
            addCriterion("YEEPAY_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdIsNotNull() {
            addCriterion("YEEPAY_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdEqualTo(String value) {
            addCriterion("YEEPAY_ORDER_ID =", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdNotEqualTo(String value) {
            addCriterion("YEEPAY_ORDER_ID <>", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdGreaterThan(String value) {
            addCriterion("YEEPAY_ORDER_ID >", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("YEEPAY_ORDER_ID >=", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdLessThan(String value) {
            addCriterion("YEEPAY_ORDER_ID <", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdLessThanOrEqualTo(String value) {
            addCriterion("YEEPAY_ORDER_ID <=", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdLike(String value) {
            addCriterion("YEEPAY_ORDER_ID like", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdNotLike(String value) {
            addCriterion("YEEPAY_ORDER_ID not like", value, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdIn(List<String> values) {
            addCriterion("YEEPAY_ORDER_ID in", values, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdNotIn(List<String> values) {
            addCriterion("YEEPAY_ORDER_ID not in", values, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdBetween(String value1, String value2) {
            addCriterion("YEEPAY_ORDER_ID between", value1, value2, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdNotBetween(String value1, String value2) {
            addCriterion("YEEPAY_ORDER_ID not between", value1, value2, "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNull() {
            addCriterion("REFUND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNotNull() {
            addCriterion("REFUND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeEqualTo(RefundType value) {
            addCriterion("REFUND_TYPE =", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotEqualTo(RefundType value) {
            addCriterion("REFUND_TYPE <>", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThan(RefundType value) {
            addCriterion("REFUND_TYPE >", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThanOrEqualTo(RefundType value) {
            addCriterion("REFUND_TYPE >=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThan(RefundType value) {
            addCriterion("REFUND_TYPE <", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThanOrEqualTo(RefundType value) {
            addCriterion("REFUND_TYPE <=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLike(RefundType value) {
            addCriterion("REFUND_TYPE like", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotLike(RefundType value) {
            addCriterion("REFUND_TYPE not like", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIn(List<RefundType> values) {
            addCriterion("REFUND_TYPE in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotIn(List<RefundType> values) {
            addCriterion("REFUND_TYPE not in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeBetween(RefundType value1, RefundType value2) {
            addCriterion("REFUND_TYPE between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotBetween(RefundType value1, RefundType value2) {
            addCriterion("REFUND_TYPE not between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andTrxAmountIsNull() {
            addCriterion("TRX_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTrxAmountIsNotNull() {
            addCriterion("TRX_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTrxAmountEqualTo(BigDecimal value) {
            addCriterion("TRX_AMOUNT =", value, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountNotEqualTo(BigDecimal value) {
            addCriterion("TRX_AMOUNT <>", value, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountGreaterThan(BigDecimal value) {
            addCriterion("TRX_AMOUNT >", value, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRX_AMOUNT >=", value, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountLessThan(BigDecimal value) {
            addCriterion("TRX_AMOUNT <", value, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRX_AMOUNT <=", value, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountIn(List<BigDecimal> values) {
            addCriterion("TRX_AMOUNT in", values, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountNotIn(List<BigDecimal> values) {
            addCriterion("TRX_AMOUNT not in", values, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRX_AMOUNT between", value1, value2, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andTrxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRX_AMOUNT not between", value1, value2, "trxAmount");
            return (Criteria) this;
        }

        public Criteria andPaySourceIsNull() {
            addCriterion("PAY_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andPaySourceIsNotNull() {
            addCriterion("PAY_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andPaySourceEqualTo(PaySource value) {
            addCriterion("PAY_SOURCE =", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceNotEqualTo(PaySource value) {
            addCriterion("PAY_SOURCE <>", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceGreaterThan(PaySource value) {
            addCriterion("PAY_SOURCE >", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceGreaterThanOrEqualTo(PaySource value) {
            addCriterion("PAY_SOURCE >=", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceLessThan(PaySource value) {
            addCriterion("PAY_SOURCE <", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceLessThanOrEqualTo(PaySource value) {
            addCriterion("PAY_SOURCE <=", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceLike(PaySource value) {
            addCriterion("PAY_SOURCE like", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceNotLike(PaySource value) {
            addCriterion("PAY_SOURCE not like", value, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceIn(List<PaySource> values) {
            addCriterion("PAY_SOURCE in", values, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceNotIn(List<PaySource> values) {
            addCriterion("PAY_SOURCE not in", values, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceBetween(PaySource value1, PaySource value2) {
            addCriterion("PAY_SOURCE between", value1, value2, "paySource");
            return (Criteria) this;
        }

        public Criteria andPaySourceNotBetween(PaySource value1, PaySource value2) {
            addCriterion("PAY_SOURCE not between", value1, value2, "paySource");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNull() {
            addCriterion("REFUND_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNotNull() {
            addCriterion("REFUND_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT =", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <>", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThan(BigDecimal value) {
            addCriterion("REFUND_AMOUNT >", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT >=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThan(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMOUNT in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMOUNT not in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMOUNT between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMOUNT not between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundFeeIsNull() {
            addCriterion("REFUND_FEE is null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeIsNotNull() {
            addCriterion("REFUND_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeEqualTo(BigDecimal value) {
            addCriterion("REFUND_FEE =", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeNotEqualTo(BigDecimal value) {
            addCriterion("REFUND_FEE <>", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeGreaterThan(BigDecimal value) {
            addCriterion("REFUND_FEE >", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_FEE >=", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeLessThan(BigDecimal value) {
            addCriterion("REFUND_FEE <", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_FEE <=", value, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeIn(List<BigDecimal> values) {
            addCriterion("REFUND_FEE in", values, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeNotIn(List<BigDecimal> values) {
            addCriterion("REFUND_FEE not in", values, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_FEE between", value1, value2, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_FEE not between", value1, value2, "refundFee");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIsNull() {
            addCriterion("REFUND_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIsNotNull() {
            addCriterion("REFUND_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRefundStatusEqualTo(RefundStatus value) {
            addCriterion("REFUND_STATUS =", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotEqualTo(RefundStatus value) {
            addCriterion("REFUND_STATUS <>", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusGreaterThan(RefundStatus value) {
            addCriterion("REFUND_STATUS >", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusGreaterThanOrEqualTo(RefundStatus value) {
            addCriterion("REFUND_STATUS >=", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusLessThan(RefundStatus value) {
            addCriterion("REFUND_STATUS <", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusLessThanOrEqualTo(RefundStatus value) {
            addCriterion("REFUND_STATUS <=", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusLike(RefundStatus value) {
            addCriterion("REFUND_STATUS like", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotLike(RefundStatus value) {
            addCriterion("REFUND_STATUS not like", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIn(List<RefundStatus> values) {
            addCriterion("REFUND_STATUS in", values, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotIn(List<RefundStatus> values) {
            addCriterion("REFUND_STATUS not in", values, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusBetween(RefundStatus value1, RefundStatus value2) {
            addCriterion("REFUND_STATUS between", value1, value2, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotBetween(RefundStatus value1, RefundStatus value2) {
            addCriterion("REFUND_STATUS not between", value1, value2, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andReasonCodeIsNull() {
            addCriterion("REASON_CODE is null");
            return (Criteria) this;
        }

        public Criteria andReasonCodeIsNotNull() {
            addCriterion("REASON_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andReasonCodeEqualTo(String value) {
            addCriterion("REASON_CODE =", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeNotEqualTo(String value) {
            addCriterion("REASON_CODE <>", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeGreaterThan(String value) {
            addCriterion("REASON_CODE >", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REASON_CODE >=", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeLessThan(String value) {
            addCriterion("REASON_CODE <", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeLessThanOrEqualTo(String value) {
            addCriterion("REASON_CODE <=", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeLike(String value) {
            addCriterion("REASON_CODE like", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeNotLike(String value) {
            addCriterion("REASON_CODE not like", value, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeIn(List<String> values) {
            addCriterion("REASON_CODE in", values, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeNotIn(List<String> values) {
            addCriterion("REASON_CODE not in", values, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeBetween(String value1, String value2) {
            addCriterion("REASON_CODE between", value1, value2, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andReasonCodeNotBetween(String value1, String value2) {
            addCriterion("REASON_CODE not between", value1, value2, "reasonCode");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNull() {
            addCriterion("REFUND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNotNull() {
            addCriterion("REFUND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeEqualTo(Date value) {
            addCriterion("REFUND_TIME =", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotEqualTo(Date value) {
            addCriterion("REFUND_TIME <>", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThan(Date value) {
            addCriterion("REFUND_TIME >", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REFUND_TIME >=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThan(Date value) {
            addCriterion("REFUND_TIME <", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThanOrEqualTo(Date value) {
            addCriterion("REFUND_TIME <=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIn(List<Date> values) {
            addCriterion("REFUND_TIME in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotIn(List<Date> values) {
            addCriterion("REFUND_TIME not in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeBetween(Date value1, Date value2) {
            addCriterion("REFUND_TIME between", value1, value2, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotBetween(Date value1, Date value2) {
            addCriterion("REFUND_TIME not between", value1, value2, "refundTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeIsNull() {
            addCriterion("MANUAL_EXEC_TIME is null");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeIsNotNull() {
            addCriterion("MANUAL_EXEC_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeEqualTo(Date value) {
            addCriterion("MANUAL_EXEC_TIME =", value, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeNotEqualTo(Date value) {
            addCriterion("MANUAL_EXEC_TIME <>", value, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeGreaterThan(Date value) {
            addCriterion("MANUAL_EXEC_TIME >", value, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MANUAL_EXEC_TIME >=", value, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeLessThan(Date value) {
            addCriterion("MANUAL_EXEC_TIME <", value, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeLessThanOrEqualTo(Date value) {
            addCriterion("MANUAL_EXEC_TIME <=", value, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeIn(List<Date> values) {
            addCriterion("MANUAL_EXEC_TIME in", values, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeNotIn(List<Date> values) {
            addCriterion("MANUAL_EXEC_TIME not in", values, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeBetween(Date value1, Date value2) {
            addCriterion("MANUAL_EXEC_TIME between", value1, value2, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualExecTimeNotBetween(Date value1, Date value2) {
            addCriterion("MANUAL_EXEC_TIME not between", value1, value2, "manualExecTime");
            return (Criteria) this;
        }

        public Criteria andManualOperatorIsNull() {
            addCriterion("MANUAL_OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andManualOperatorIsNotNull() {
            addCriterion("MANUAL_OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andManualOperatorEqualTo(String value) {
            addCriterion("MANUAL_OPERATOR =", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorNotEqualTo(String value) {
            addCriterion("MANUAL_OPERATOR <>", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorGreaterThan(String value) {
            addCriterion("MANUAL_OPERATOR >", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("MANUAL_OPERATOR >=", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorLessThan(String value) {
            addCriterion("MANUAL_OPERATOR <", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorLessThanOrEqualTo(String value) {
            addCriterion("MANUAL_OPERATOR <=", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorLike(String value) {
            addCriterion("MANUAL_OPERATOR like", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorNotLike(String value) {
            addCriterion("MANUAL_OPERATOR not like", value, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorIn(List<String> values) {
            addCriterion("MANUAL_OPERATOR in", values, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorNotIn(List<String> values) {
            addCriterion("MANUAL_OPERATOR not in", values, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorBetween(String value1, String value2) {
            addCriterion("MANUAL_OPERATOR between", value1, value2, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andManualOperatorNotBetween(String value1, String value2) {
            addCriterion("MANUAL_OPERATOR not between", value1, value2, "manualOperator");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeIsNull() {
            addCriterion("REFUND_HANDLE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeIsNotNull() {
            addCriterion("REFUND_HANDLE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeEqualTo(String value) {
            addCriterion("REFUND_HANDLE_TYPE =", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeNotEqualTo(String value) {
            addCriterion("REFUND_HANDLE_TYPE <>", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeGreaterThan(String value) {
            addCriterion("REFUND_HANDLE_TYPE >", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("REFUND_HANDLE_TYPE >=", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeLessThan(String value) {
            addCriterion("REFUND_HANDLE_TYPE <", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeLessThanOrEqualTo(String value) {
            addCriterion("REFUND_HANDLE_TYPE <=", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeLike(String value) {
            addCriterion("REFUND_HANDLE_TYPE like", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeNotLike(String value) {
            addCriterion("REFUND_HANDLE_TYPE not like", value, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeIn(List<String> values) {
            addCriterion("REFUND_HANDLE_TYPE in", values, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeNotIn(List<String> values) {
            addCriterion("REFUND_HANDLE_TYPE not in", values, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeBetween(String value1, String value2) {
            addCriterion("REFUND_HANDLE_TYPE between", value1, value2, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeNotBetween(String value1, String value2) {
            addCriterion("REFUND_HANDLE_TYPE not between", value1, value2, "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andCauseIsNull() {
            addCriterion("CAUSE is null");
            return (Criteria) this;
        }

        public Criteria andCauseIsNotNull() {
            addCriterion("CAUSE is not null");
            return (Criteria) this;
        }

        public Criteria andCauseEqualTo(String value) {
            addCriterion("CAUSE =", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotEqualTo(String value) {
            addCriterion("CAUSE <>", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThan(String value) {
            addCriterion("CAUSE >", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThanOrEqualTo(String value) {
            addCriterion("CAUSE >=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThan(String value) {
            addCriterion("CAUSE <", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThanOrEqualTo(String value) {
            addCriterion("CAUSE <=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLike(String value) {
            addCriterion("CAUSE like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotLike(String value) {
            addCriterion("CAUSE not like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseIn(List<String> values) {
            addCriterion("CAUSE in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotIn(List<String> values) {
            addCriterion("CAUSE not in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseBetween(String value1, String value2) {
            addCriterion("CAUSE between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotBetween(String value1, String value2) {
            addCriterion("CAUSE not between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("PAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("PAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("PAY_TIME =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("PAY_TIME <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("PAY_TIME >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PAY_TIME >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("PAY_TIME <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("PAY_TIME <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("PAY_TIME in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("PAY_TIME not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("PAY_TIME between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("PAY_TIME not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andReasonMsgIsNull() {
            addCriterion("REASON_MSG is null");
            return (Criteria) this;
        }

        public Criteria andReasonMsgIsNotNull() {
            addCriterion("REASON_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andReasonMsgEqualTo(String value) {
            addCriterion("REASON_MSG =", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgNotEqualTo(String value) {
            addCriterion("REASON_MSG <>", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgGreaterThan(String value) {
            addCriterion("REASON_MSG >", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgGreaterThanOrEqualTo(String value) {
            addCriterion("REASON_MSG >=", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgLessThan(String value) {
            addCriterion("REASON_MSG <", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgLessThanOrEqualTo(String value) {
            addCriterion("REASON_MSG <=", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgLike(String value) {
            addCriterion("REASON_MSG like", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgNotLike(String value) {
            addCriterion("REASON_MSG not like", value, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgIn(List<String> values) {
            addCriterion("REASON_MSG in", values, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgNotIn(List<String> values) {
            addCriterion("REASON_MSG not in", values, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgBetween(String value1, String value2) {
            addCriterion("REASON_MSG between", value1, value2, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andReasonMsgNotBetween(String value1, String value2) {
            addCriterion("REASON_MSG not between", value1, value2, "reasonMsg");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdLikeInsensitive(String value) {
            addCriterion("upper(REFUND_ORDER_ID) like", value.toUpperCase(), "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundRequestIdLikeInsensitive(String value) {
            addCriterion("upper(REFUND_REQUEST_ID) like", value.toUpperCase(), "refundRequestId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdLikeInsensitive(String value) {
            addCriterion("upper(YEEPAY_ORDER_ID) like", value.toUpperCase(), "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andReasonCodeLikeInsensitive(String value) {
            addCriterion("upper(REASON_CODE) like", value.toUpperCase(), "reasonCode");
            return (Criteria) this;
        }

        public Criteria andManualOperatorLikeInsensitive(String value) {
            addCriterion("upper(MANUAL_OPERATOR) like", value.toUpperCase(), "manualOperator");
            return (Criteria) this;
        }

        public Criteria andRefundHandleTypeLikeInsensitive(String value) {
            addCriterion("upper(REFUND_HANDLE_TYPE) like", value.toUpperCase(), "refundHandleType");
            return (Criteria) this;
        }

        public Criteria andCauseLikeInsensitive(String value) {
            addCriterion("upper(CAUSE) like", value.toUpperCase(), "cause");
            return (Criteria) this;
        }

        public Criteria andReasonMsgLikeInsensitive(String value) {
            addCriterion("upper(REASON_MSG) like", value.toUpperCase(), "reasonMsg");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}