package com.yeepay.g3.core.ymf.entity.order;

import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderParam() {
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

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberIsNull() {
            addCriterion("CUSTOMER_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberIsNotNull() {
            addCriterion("CUSTOMER_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberEqualTo(String value) {
            addCriterion("CUSTOMER_NUMBER =", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotEqualTo(String value) {
            addCriterion("CUSTOMER_NUMBER <>", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberGreaterThan(String value) {
            addCriterion("CUSTOMER_NUMBER >", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NUMBER >=", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLessThan(String value) {
            addCriterion("CUSTOMER_NUMBER <", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NUMBER <=", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLike(String value) {
            addCriterion("CUSTOMER_NUMBER like", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotLike(String value) {
            addCriterion("CUSTOMER_NUMBER not like", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberIn(List<String> values) {
            addCriterion("CUSTOMER_NUMBER in", values, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotIn(List<String> values) {
            addCriterion("CUSTOMER_NUMBER not in", values, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NUMBER between", value1, value2, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NUMBER not between", value1, value2, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdIsNull() {
            addCriterion("CUSTOMER_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdIsNotNull() {
            addCriterion("CUSTOMER_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdEqualTo(String value) {
            addCriterion("CUSTOMER_ORDER_ID =", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdNotEqualTo(String value) {
            addCriterion("CUSTOMER_ORDER_ID <>", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdGreaterThan(String value) {
            addCriterion("CUSTOMER_ORDER_ID >", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ORDER_ID >=", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdLessThan(String value) {
            addCriterion("CUSTOMER_ORDER_ID <", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ORDER_ID <=", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdLike(String value) {
            addCriterion("CUSTOMER_ORDER_ID like", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdNotLike(String value) {
            addCriterion("CUSTOMER_ORDER_ID not like", value, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdIn(List<String> values) {
            addCriterion("CUSTOMER_ORDER_ID in", values, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdNotIn(List<String> values) {
            addCriterion("CUSTOMER_ORDER_ID not in", values, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ORDER_ID between", value1, value2, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ORDER_ID not between", value1, value2, "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andExternalIdIsNull() {
            addCriterion("EXTERNAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andExternalIdIsNotNull() {
            addCriterion("EXTERNAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExternalIdEqualTo(String value) {
            addCriterion("EXTERNAL_ID =", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotEqualTo(String value) {
            addCriterion("EXTERNAL_ID <>", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdGreaterThan(String value) {
            addCriterion("EXTERNAL_ID >", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXTERNAL_ID >=", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdLessThan(String value) {
            addCriterion("EXTERNAL_ID <", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdLessThanOrEqualTo(String value) {
            addCriterion("EXTERNAL_ID <=", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdLike(String value) {
            addCriterion("EXTERNAL_ID like", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotLike(String value) {
            addCriterion("EXTERNAL_ID not like", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdIn(List<String> values) {
            addCriterion("EXTERNAL_ID in", values, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotIn(List<String> values) {
            addCriterion("EXTERNAL_ID not in", values, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdBetween(String value1, String value2) {
            addCriterion("EXTERNAL_ID between", value1, value2, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotBetween(String value1, String value2) {
            addCriterion("EXTERNAL_ID not between", value1, value2, "externalId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNull() {
            addCriterion("OUT_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNotNull() {
            addCriterion("OUT_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdEqualTo(String value) {
            addCriterion("OUT_ORDER_ID =", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotEqualTo(String value) {
            addCriterion("OUT_ORDER_ID <>", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThan(String value) {
            addCriterion("OUT_ORDER_ID >", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_ORDER_ID >=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThan(String value) {
            addCriterion("OUT_ORDER_ID <", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThanOrEqualTo(String value) {
            addCriterion("OUT_ORDER_ID <=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLike(String value) {
            addCriterion("OUT_ORDER_ID like", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotLike(String value) {
            addCriterion("OUT_ORDER_ID not like", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIn(List<String> values) {
            addCriterion("OUT_ORDER_ID in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotIn(List<String> values) {
            addCriterion("OUT_ORDER_ID not in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdBetween(String value1, String value2) {
            addCriterion("OUT_ORDER_ID between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotBetween(String value1, String value2) {
            addCriterion("OUT_ORDER_ID not between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andSanCodeIsNull() {
            addCriterion("SAN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSanCodeIsNotNull() {
            addCriterion("SAN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSanCodeEqualTo(String value) {
            addCriterion("SAN_CODE =", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeNotEqualTo(String value) {
            addCriterion("SAN_CODE <>", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeGreaterThan(String value) {
            addCriterion("SAN_CODE >", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SAN_CODE >=", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeLessThan(String value) {
            addCriterion("SAN_CODE <", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeLessThanOrEqualTo(String value) {
            addCriterion("SAN_CODE <=", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeLike(String value) {
            addCriterion("SAN_CODE like", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeNotLike(String value) {
            addCriterion("SAN_CODE not like", value, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeIn(List<String> values) {
            addCriterion("SAN_CODE in", values, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeNotIn(List<String> values) {
            addCriterion("SAN_CODE not in", values, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeBetween(String value1, String value2) {
            addCriterion("SAN_CODE between", value1, value2, "sanCode");
            return (Criteria) this;
        }

        public Criteria andSanCodeNotBetween(String value1, String value2) {
            addCriterion("SAN_CODE not between", value1, value2, "sanCode");
            return (Criteria) this;
        }

        public Criteria andTrxAmtIsNull() {
            addCriterion("TRX_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTrxAmtIsNotNull() {
            addCriterion("TRX_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTrxAmtEqualTo(BigDecimal value) {
            addCriterion("TRX_AMT =", value, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtNotEqualTo(BigDecimal value) {
            addCriterion("TRX_AMT <>", value, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtGreaterThan(BigDecimal value) {
            addCriterion("TRX_AMT >", value, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRX_AMT >=", value, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtLessThan(BigDecimal value) {
            addCriterion("TRX_AMT <", value, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRX_AMT <=", value, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtIn(List<BigDecimal> values) {
            addCriterion("TRX_AMT in", values, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtNotIn(List<BigDecimal> values) {
            addCriterion("TRX_AMT not in", values, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRX_AMT between", value1, value2, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andTrxAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRX_AMT not between", value1, value2, "trxAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtIsNull() {
            addCriterion("REAL_AMT is null");
            return (Criteria) this;
        }

        public Criteria andRealAmtIsNotNull() {
            addCriterion("REAL_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andRealAmtEqualTo(BigDecimal value) {
            addCriterion("REAL_AMT =", value, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtNotEqualTo(BigDecimal value) {
            addCriterion("REAL_AMT <>", value, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtGreaterThan(BigDecimal value) {
            addCriterion("REAL_AMT >", value, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REAL_AMT >=", value, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtLessThan(BigDecimal value) {
            addCriterion("REAL_AMT <", value, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REAL_AMT <=", value, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtIn(List<BigDecimal> values) {
            addCriterion("REAL_AMT in", values, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtNotIn(List<BigDecimal> values) {
            addCriterion("REAL_AMT not in", values, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REAL_AMT between", value1, value2, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRealAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REAL_AMT not between", value1, value2, "realAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtIsNull() {
            addCriterion("REFUND_AMT is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmtIsNotNull() {
            addCriterion("REFUND_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmtEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMT =", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtNotEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMT <>", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtGreaterThan(BigDecimal value) {
            addCriterion("REFUND_AMT >", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMT >=", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtLessThan(BigDecimal value) {
            addCriterion("REFUND_AMT <", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMT <=", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMT in", values, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtNotIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMT not in", values, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMT between", value1, value2, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMT not between", value1, value2, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("FEE is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("FEE is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("FEE =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("FEE <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("FEE >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("FEE <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FEE <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("FEE in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("FEE not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FEE not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("RECEIVER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("RECEIVER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("RECEIVER_NAME =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("RECEIVER_NAME <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("RECEIVER_NAME >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_NAME >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("RECEIVER_NAME <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_NAME <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("RECEIVER_NAME like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("RECEIVER_NAME not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("RECEIVER_NAME in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("RECEIVER_NAME not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("RECEIVER_NAME between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_NAME not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIsNull() {
            addCriterion("RECEIVER_TEL is null");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIsNotNull() {
            addCriterion("RECEIVER_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverTelEqualTo(String value) {
            addCriterion("RECEIVER_TEL =", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotEqualTo(String value) {
            addCriterion("RECEIVER_TEL <>", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelGreaterThan(String value) {
            addCriterion("RECEIVER_TEL >", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_TEL >=", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLessThan(String value) {
            addCriterion("RECEIVER_TEL <", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_TEL <=", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLike(String value) {
            addCriterion("RECEIVER_TEL like", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotLike(String value) {
            addCriterion("RECEIVER_TEL not like", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIn(List<String> values) {
            addCriterion("RECEIVER_TEL in", values, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotIn(List<String> values) {
            addCriterion("RECEIVER_TEL not in", values, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelBetween(String value1, String value2) {
            addCriterion("RECEIVER_TEL between", value1, value2, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_TEL not between", value1, value2, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNull() {
            addCriterion("BUSINESS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNotNull() {
            addCriterion("BUSINESS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeEqualTo(BusinessType value) {
            addCriterion("BUSINESS_TYPE =", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotEqualTo(BusinessType value) {
            addCriterion("BUSINESS_TYPE <>", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThan(BusinessType value) {
            addCriterion("BUSINESS_TYPE >", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThanOrEqualTo(BusinessType value) {
            addCriterion("BUSINESS_TYPE >=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThan(BusinessType value) {
            addCriterion("BUSINESS_TYPE <", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThanOrEqualTo(BusinessType value) {
            addCriterion("BUSINESS_TYPE <=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLike(BusinessType value) {
            addCriterion("BUSINESS_TYPE like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotLike(BusinessType value) {
            addCriterion("BUSINESS_TYPE not like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIn(List<BusinessType> values) {
            addCriterion("BUSINESS_TYPE in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotIn(List<BusinessType> values) {
            addCriterion("BUSINESS_TYPE not in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeBetween(BusinessType value1, BusinessType value2) {
            addCriterion("BUSINESS_TYPE between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotBetween(BusinessType value1, BusinessType value2) {
            addCriterion("BUSINESS_TYPE not between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andQueryCountIsNull() {
            addCriterion("QUERY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andQueryCountIsNotNull() {
            addCriterion("QUERY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andQueryCountEqualTo(Integer value) {
            addCriterion("QUERY_COUNT =", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountNotEqualTo(Integer value) {
            addCriterion("QUERY_COUNT <>", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountGreaterThan(Integer value) {
            addCriterion("QUERY_COUNT >", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("QUERY_COUNT >=", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountLessThan(Integer value) {
            addCriterion("QUERY_COUNT <", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountLessThanOrEqualTo(Integer value) {
            addCriterion("QUERY_COUNT <=", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountIn(List<Integer> values) {
            addCriterion("QUERY_COUNT in", values, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountNotIn(List<Integer> values) {
            addCriterion("QUERY_COUNT not in", values, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountBetween(Integer value1, Integer value2) {
            addCriterion("QUERY_COUNT between", value1, value2, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountNotBetween(Integer value1, Integer value2) {
            addCriterion("QUERY_COUNT not between", value1, value2, "queryCount");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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

        public Criteria andCompleteTimeIsNull() {
            addCriterion("COMPLETE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNotNull() {
            addCriterion("COMPLETE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeEqualTo(Date value) {
            addCriterion("COMPLETE_TIME =", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotEqualTo(Date value) {
            addCriterion("COMPLETE_TIME <>", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThan(Date value) {
            addCriterion("COMPLETE_TIME >", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("COMPLETE_TIME >=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThan(Date value) {
            addCriterion("COMPLETE_TIME <", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("COMPLETE_TIME <=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIn(List<Date> values) {
            addCriterion("COMPLETE_TIME in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotIn(List<Date> values) {
            addCriterion("COMPLETE_TIME not in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeBetween(Date value1, Date value2) {
            addCriterion("COMPLETE_TIME between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("COMPLETE_TIME not between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("ORDER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("ORDER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(OrderStatus value) {
            addCriterion("ORDER_STATUS =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(OrderStatus value) {
            addCriterion("ORDER_STATUS <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(OrderStatus value) {
            addCriterion("ORDER_STATUS >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(OrderStatus value) {
            addCriterion("ORDER_STATUS >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(OrderStatus value) {
            addCriterion("ORDER_STATUS <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(OrderStatus value) {
            addCriterion("ORDER_STATUS <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLike(OrderStatus value) {
            addCriterion("ORDER_STATUS like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotLike(OrderStatus value) {
            addCriterion("ORDER_STATUS not like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<OrderStatus> values) {
            addCriterion("ORDER_STATUS in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<OrderStatus> values) {
            addCriterion("ORDER_STATUS not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(OrderStatus value1, OrderStatus value2) {
            addCriterion("ORDER_STATUS between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(OrderStatus value1, OrderStatus value2) {
            addCriterion("ORDER_STATUS not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andPayConfirmIsNull() {
            addCriterion("PAY_CONFIRM is null");
            return (Criteria) this;
        }

        public Criteria andPayConfirmIsNotNull() {
            addCriterion("PAY_CONFIRM is not null");
            return (Criteria) this;
        }

        public Criteria andPayConfirmEqualTo(String value) {
            addCriterion("PAY_CONFIRM =", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmNotEqualTo(String value) {
            addCriterion("PAY_CONFIRM <>", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmGreaterThan(String value) {
            addCriterion("PAY_CONFIRM >", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_CONFIRM >=", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmLessThan(String value) {
            addCriterion("PAY_CONFIRM <", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmLessThanOrEqualTo(String value) {
            addCriterion("PAY_CONFIRM <=", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmLike(String value) {
            addCriterion("PAY_CONFIRM like", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmNotLike(String value) {
            addCriterion("PAY_CONFIRM not like", value, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmIn(List<String> values) {
            addCriterion("PAY_CONFIRM in", values, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmNotIn(List<String> values) {
            addCriterion("PAY_CONFIRM not in", values, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmBetween(String value1, String value2) {
            addCriterion("PAY_CONFIRM between", value1, value2, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andPayConfirmNotBetween(String value1, String value2) {
            addCriterion("PAY_CONFIRM not between", value1, value2, "payConfirm");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_NUMBER) like", value.toUpperCase(), "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderIdLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_ORDER_ID) like", value.toUpperCase(), "customerOrderId");
            return (Criteria) this;
        }

        public Criteria andExternalIdLikeInsensitive(String value) {
            addCriterion("upper(EXTERNAL_ID) like", value.toUpperCase(), "externalId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLikeInsensitive(String value) {
            addCriterion("upper(OUT_ORDER_ID) like", value.toUpperCase(), "outOrderId");
            return (Criteria) this;
        }

        public Criteria andSanCodeLikeInsensitive(String value) {
            addCriterion("upper(SAN_CODE) like", value.toUpperCase(), "sanCode");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLikeInsensitive(String value) {
            addCriterion("upper(RECEIVER_NAME) like", value.toUpperCase(), "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLikeInsensitive(String value) {
            addCriterion("upper(RECEIVER_TEL) like", value.toUpperCase(), "receiverTel");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(REMARK) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andPayConfirmLikeInsensitive(String value) {
            addCriterion("upper(PAY_CONFIRM) like", value.toUpperCase(), "payConfirm");
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