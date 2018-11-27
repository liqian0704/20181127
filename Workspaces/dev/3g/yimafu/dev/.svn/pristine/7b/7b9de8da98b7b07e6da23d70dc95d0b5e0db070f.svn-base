package com.yeepay.g3.core.ymf.entity.material;

import com.yeepay.g3.facade.ymf.enumtype.common.CommonStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TermRelationParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TermRelationParam() {
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

        public Criteria andShopIdIsNull() {
            addCriterion("SHOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("SHOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("SHOP_ID =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("SHOP_ID <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("SHOP_ID >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("SHOP_ID <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("SHOP_ID in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("SHOP_ID not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andSnSerialIsNull() {
            addCriterion("SN_SERIAL is null");
            return (Criteria) this;
        }

        public Criteria andSnSerialIsNotNull() {
            addCriterion("SN_SERIAL is not null");
            return (Criteria) this;
        }

        public Criteria andSnSerialEqualTo(String value) {
            addCriterion("SN_SERIAL =", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialNotEqualTo(String value) {
            addCriterion("SN_SERIAL <>", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialGreaterThan(String value) {
            addCriterion("SN_SERIAL >", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialGreaterThanOrEqualTo(String value) {
            addCriterion("SN_SERIAL >=", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialLessThan(String value) {
            addCriterion("SN_SERIAL <", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialLessThanOrEqualTo(String value) {
            addCriterion("SN_SERIAL <=", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialLike(String value) {
            addCriterion("SN_SERIAL like", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialNotLike(String value) {
            addCriterion("SN_SERIAL not like", value, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialIn(List<String> values) {
            addCriterion("SN_SERIAL in", values, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialNotIn(List<String> values) {
            addCriterion("SN_SERIAL not in", values, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialBetween(String value1, String value2) {
            addCriterion("SN_SERIAL between", value1, value2, "snSerial");
            return (Criteria) this;
        }

        public Criteria andSnSerialNotBetween(String value1, String value2) {
            addCriterion("SN_SERIAL not between", value1, value2, "snSerial");
            return (Criteria) this;
        }

        public Criteria andTermIdIsNull() {
            addCriterion("TERM_ID is null");
            return (Criteria) this;
        }

        public Criteria andTermIdIsNotNull() {
            addCriterion("TERM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTermIdEqualTo(Long value) {
            addCriterion("TERM_ID =", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotEqualTo(Long value) {
            addCriterion("TERM_ID <>", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdGreaterThan(Long value) {
            addCriterion("TERM_ID >", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TERM_ID >=", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdLessThan(Long value) {
            addCriterion("TERM_ID <", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdLessThanOrEqualTo(Long value) {
            addCriterion("TERM_ID <=", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdIn(List<Long> values) {
            addCriterion("TERM_ID in", values, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotIn(List<Long> values) {
            addCriterion("TERM_ID not in", values, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdBetween(Long value1, Long value2) {
            addCriterion("TERM_ID between", value1, value2, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotBetween(Long value1, Long value2) {
            addCriterion("TERM_ID not between", value1, value2, "termId");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNull() {
            addCriterion("BIND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNotNull() {
            addCriterion("BIND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBindTimeEqualTo(Date value) {
            addCriterion("BIND_TIME =", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotEqualTo(Date value) {
            addCriterion("BIND_TIME <>", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThan(Date value) {
            addCriterion("BIND_TIME >", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BIND_TIME >=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThan(Date value) {
            addCriterion("BIND_TIME <", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThanOrEqualTo(Date value) {
            addCriterion("BIND_TIME <=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeIn(List<Date> values) {
            addCriterion("BIND_TIME in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotIn(List<Date> values) {
            addCriterion("BIND_TIME not in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeBetween(Date value1, Date value2) {
            addCriterion("BIND_TIME between", value1, value2, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotBetween(Date value1, Date value2) {
            addCriterion("BIND_TIME not between", value1, value2, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindOperatorIsNull() {
            addCriterion("BIND_OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andBindOperatorIsNotNull() {
            addCriterion("BIND_OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andBindOperatorEqualTo(String value) {
            addCriterion("BIND_OPERATOR =", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorNotEqualTo(String value) {
            addCriterion("BIND_OPERATOR <>", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorGreaterThan(String value) {
            addCriterion("BIND_OPERATOR >", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("BIND_OPERATOR >=", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorLessThan(String value) {
            addCriterion("BIND_OPERATOR <", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorLessThanOrEqualTo(String value) {
            addCriterion("BIND_OPERATOR <=", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorLike(String value) {
            addCriterion("BIND_OPERATOR like", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorNotLike(String value) {
            addCriterion("BIND_OPERATOR not like", value, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorIn(List<String> values) {
            addCriterion("BIND_OPERATOR in", values, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorNotIn(List<String> values) {
            addCriterion("BIND_OPERATOR not in", values, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorBetween(String value1, String value2) {
            addCriterion("BIND_OPERATOR between", value1, value2, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andBindOperatorNotBetween(String value1, String value2) {
            addCriterion("BIND_OPERATOR not between", value1, value2, "bindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeIsNull() {
            addCriterion("UNBIND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeIsNotNull() {
            addCriterion("UNBIND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeEqualTo(Date value) {
            addCriterion("UNBIND_TIME =", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeNotEqualTo(Date value) {
            addCriterion("UNBIND_TIME <>", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeGreaterThan(Date value) {
            addCriterion("UNBIND_TIME >", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UNBIND_TIME >=", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeLessThan(Date value) {
            addCriterion("UNBIND_TIME <", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeLessThanOrEqualTo(Date value) {
            addCriterion("UNBIND_TIME <=", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeIn(List<Date> values) {
            addCriterion("UNBIND_TIME in", values, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeNotIn(List<Date> values) {
            addCriterion("UNBIND_TIME not in", values, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeBetween(Date value1, Date value2) {
            addCriterion("UNBIND_TIME between", value1, value2, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeNotBetween(Date value1, Date value2) {
            addCriterion("UNBIND_TIME not between", value1, value2, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorIsNull() {
            addCriterion("UNBIND_OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorIsNotNull() {
            addCriterion("UNBIND_OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorEqualTo(String value) {
            addCriterion("UNBIND_OPERATOR =", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorNotEqualTo(String value) {
            addCriterion("UNBIND_OPERATOR <>", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorGreaterThan(String value) {
            addCriterion("UNBIND_OPERATOR >", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("UNBIND_OPERATOR >=", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorLessThan(String value) {
            addCriterion("UNBIND_OPERATOR <", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorLessThanOrEqualTo(String value) {
            addCriterion("UNBIND_OPERATOR <=", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorLike(String value) {
            addCriterion("UNBIND_OPERATOR like", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorNotLike(String value) {
            addCriterion("UNBIND_OPERATOR not like", value, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorIn(List<String> values) {
            addCriterion("UNBIND_OPERATOR in", values, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorNotIn(List<String> values) {
            addCriterion("UNBIND_OPERATOR not in", values, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorBetween(String value1, String value2) {
            addCriterion("UNBIND_OPERATOR between", value1, value2, "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorNotBetween(String value1, String value2) {
            addCriterion("UNBIND_OPERATOR not between", value1, value2, "unbindOperator");
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

        public Criteria andStatusEqualTo(CommonStatus value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(CommonStatus value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(CommonStatus value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(CommonStatus value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(CommonStatus value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(CommonStatus value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(CommonStatus value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(CommonStatus value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<CommonStatus> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<CommonStatus> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(CommonStatus value1, CommonStatus value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(CommonStatus value1, CommonStatus value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOpCodeIsNull() {
            addCriterion("OP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOpCodeIsNotNull() {
            addCriterion("OP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOpCodeEqualTo(String value) {
            addCriterion("OP_CODE =", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotEqualTo(String value) {
            addCriterion("OP_CODE <>", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeGreaterThan(String value) {
            addCriterion("OP_CODE >", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeGreaterThanOrEqualTo(String value) {
            addCriterion("OP_CODE >=", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLessThan(String value) {
            addCriterion("OP_CODE <", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLessThanOrEqualTo(String value) {
            addCriterion("OP_CODE <=", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLike(String value) {
            addCriterion("OP_CODE like", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotLike(String value) {
            addCriterion("OP_CODE not like", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeIn(List<String> values) {
            addCriterion("OP_CODE in", values, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotIn(List<String> values) {
            addCriterion("OP_CODE not in", values, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeBetween(String value1, String value2) {
            addCriterion("OP_CODE between", value1, value2, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotBetween(String value1, String value2) {
            addCriterion("OP_CODE not between", value1, value2, "opCode");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_NUMBER) like", value.toUpperCase(), "customerNumber");
            return (Criteria) this;
        }

        public Criteria andSnSerialLikeInsensitive(String value) {
            addCriterion("upper(SN_SERIAL) like", value.toUpperCase(), "snSerial");
            return (Criteria) this;
        }

        public Criteria andBindOperatorLikeInsensitive(String value) {
            addCriterion("upper(BIND_OPERATOR) like", value.toUpperCase(), "bindOperator");
            return (Criteria) this;
        }

        public Criteria andUnbindOperatorLikeInsensitive(String value) {
            addCriterion("upper(UNBIND_OPERATOR) like", value.toUpperCase(), "unbindOperator");
            return (Criteria) this;
        }

        public Criteria andOpCodeLikeInsensitive(String value) {
            addCriterion("upper(OP_CODE) like", value.toUpperCase(), "opCode");
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