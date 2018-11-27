package com.yeepay.g3.core.ymf.entity.remit;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemittanceParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RemittanceParam() {
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

        public Criteria andBatchNoIsNull() {
            addCriterion("BATCH_NO is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("BATCH_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("BATCH_NO =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("BATCH_NO <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("BATCH_NO >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_NO >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("BATCH_NO <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("BATCH_NO <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("BATCH_NO like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("BATCH_NO not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("BATCH_NO in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("BATCH_NO not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("BATCH_NO between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("BATCH_NO not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("AMOUNT =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("AMOUNT <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("AMOUNT >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("AMOUNT <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("AMOUNT in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("AMOUNT not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andSrcFeeIsNull() {
            addCriterion("SRC_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSrcFeeIsNotNull() {
            addCriterion("SRC_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSrcFeeEqualTo(BigDecimal value) {
            addCriterion("SRC_FEE =", value, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeNotEqualTo(BigDecimal value) {
            addCriterion("SRC_FEE <>", value, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeGreaterThan(BigDecimal value) {
            addCriterion("SRC_FEE >", value, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SRC_FEE >=", value, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeLessThan(BigDecimal value) {
            addCriterion("SRC_FEE <", value, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SRC_FEE <=", value, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeIn(List<BigDecimal> values) {
            addCriterion("SRC_FEE in", values, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeNotIn(List<BigDecimal> values) {
            addCriterion("SRC_FEE not in", values, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SRC_FEE between", value1, value2, "srcFee");
            return (Criteria) this;
        }

        public Criteria andSrcFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SRC_FEE not between", value1, value2, "srcFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeIsNull() {
            addCriterion("TARGET_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTargetFeeIsNotNull() {
            addCriterion("TARGET_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFeeEqualTo(BigDecimal value) {
            addCriterion("TARGET_FEE =", value, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeNotEqualTo(BigDecimal value) {
            addCriterion("TARGET_FEE <>", value, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeGreaterThan(BigDecimal value) {
            addCriterion("TARGET_FEE >", value, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TARGET_FEE >=", value, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeLessThan(BigDecimal value) {
            addCriterion("TARGET_FEE <", value, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TARGET_FEE <=", value, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeIn(List<BigDecimal> values) {
            addCriterion("TARGET_FEE in", values, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeNotIn(List<BigDecimal> values) {
            addCriterion("TARGET_FEE not in", values, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TARGET_FEE between", value1, value2, "targetFee");
            return (Criteria) this;
        }

        public Criteria andTargetFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TARGET_FEE not between", value1, value2, "targetFee");
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

        public Criteria andRemitStatusIsNull() {
            addCriterion("REMIT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRemitStatusIsNotNull() {
            addCriterion("REMIT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRemitStatusEqualTo(RemitStatus value) {
            addCriterion("REMIT_STATUS =", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusNotEqualTo(RemitStatus value) {
            addCriterion("REMIT_STATUS <>", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusGreaterThan(RemitStatus value) {
            addCriterion("REMIT_STATUS >", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusGreaterThanOrEqualTo(RemitStatus value) {
            addCriterion("REMIT_STATUS >=", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusLessThan(RemitStatus value) {
            addCriterion("REMIT_STATUS <", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusLessThanOrEqualTo(RemitStatus value) {
            addCriterion("REMIT_STATUS <=", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusLike(RemitStatus value) {
            addCriterion("REMIT_STATUS like", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusNotLike(RemitStatus value) {
            addCriterion("REMIT_STATUS not like", value, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusIn(List<RemitStatus> values) {
            addCriterion("REMIT_STATUS in", values, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusNotIn(List<RemitStatus> values) {
            addCriterion("REMIT_STATUS not in", values, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusBetween(RemitStatus value1, RemitStatus value2) {
            addCriterion("REMIT_STATUS between", value1, value2, "remitStatus");
            return (Criteria) this;
        }

        public Criteria andRemitStatusNotBetween(RemitStatus value1, RemitStatus value2) {
            addCriterion("REMIT_STATUS not between", value1, value2, "remitStatus");
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

        public Criteria andRequestTimeIsNull() {
            addCriterion("REQUEST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("REQUEST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("REQUEST_TIME =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("REQUEST_TIME <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("REQUEST_TIME >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REQUEST_TIME >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("REQUEST_TIME <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("REQUEST_TIME <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("REQUEST_TIME in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("REQUEST_TIME not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("REQUEST_TIME between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("REQUEST_TIME not between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeIsNull() {
            addCriterion("CALLBACK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeIsNotNull() {
            addCriterion("CALLBACK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeEqualTo(Date value) {
            addCriterion("CALLBACK_TIME =", value, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeNotEqualTo(Date value) {
            addCriterion("CALLBACK_TIME <>", value, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeGreaterThan(Date value) {
            addCriterion("CALLBACK_TIME >", value, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CALLBACK_TIME >=", value, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeLessThan(Date value) {
            addCriterion("CALLBACK_TIME <", value, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeLessThanOrEqualTo(Date value) {
            addCriterion("CALLBACK_TIME <=", value, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeIn(List<Date> values) {
            addCriterion("CALLBACK_TIME in", values, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeNotIn(List<Date> values) {
            addCriterion("CALLBACK_TIME not in", values, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeBetween(Date value1, Date value2) {
            addCriterion("CALLBACK_TIME between", value1, value2, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andCallbackTimeNotBetween(Date value1, Date value2) {
            addCriterion("CALLBACK_TIME not between", value1, value2, "callbackTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeIsNull() {
            addCriterion("LAST_NOTIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeIsNotNull() {
            addCriterion("LAST_NOTIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeEqualTo(Date value) {
            addCriterion("LAST_NOTIFY_TIME =", value, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeNotEqualTo(Date value) {
            addCriterion("LAST_NOTIFY_TIME <>", value, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeGreaterThan(Date value) {
            addCriterion("LAST_NOTIFY_TIME >", value, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_NOTIFY_TIME >=", value, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeLessThan(Date value) {
            addCriterion("LAST_NOTIFY_TIME <", value, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_NOTIFY_TIME <=", value, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeIn(List<Date> values) {
            addCriterion("LAST_NOTIFY_TIME in", values, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeNotIn(List<Date> values) {
            addCriterion("LAST_NOTIFY_TIME not in", values, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_NOTIFY_TIME between", value1, value2, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andLastNotifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_NOTIFY_TIME not between", value1, value2, "lastNotifyTime");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Status value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Status value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Status value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Status value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Status value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Status value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(Status value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(Status value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Status> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Status> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Status value1, Status value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Status value1, Status value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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

        public Criteria andYeepayOrderIdLikeInsensitive(String value) {
            addCriterion("upper(YEEPAY_ORDER_ID) like", value.toUpperCase(), "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andBatchNoLikeInsensitive(String value) {
            addCriterion("upper(BATCH_NO) like", value.toUpperCase(), "batchNo");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(DESCRIPTION) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(REMARK) like", value.toUpperCase(), "remark");
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