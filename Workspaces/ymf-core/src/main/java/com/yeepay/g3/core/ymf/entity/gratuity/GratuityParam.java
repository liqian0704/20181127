package com.yeepay.g3.core.ymf.entity.gratuity;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import java.util.ArrayList;
import java.util.List;

public class GratuityParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GratuityParam() {
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

        public Criteria andGratuityTemplateIsNull() {
            addCriterion("GRATUITY_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateIsNotNull() {
            addCriterion("GRATUITY_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateEqualTo(String value) {
            addCriterion("GRATUITY_TEMPLATE =", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateNotEqualTo(String value) {
            addCriterion("GRATUITY_TEMPLATE <>", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateGreaterThan(String value) {
            addCriterion("GRATUITY_TEMPLATE >", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("GRATUITY_TEMPLATE >=", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateLessThan(String value) {
            addCriterion("GRATUITY_TEMPLATE <", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateLessThanOrEqualTo(String value) {
            addCriterion("GRATUITY_TEMPLATE <=", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateLike(String value) {
            addCriterion("GRATUITY_TEMPLATE like", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateNotLike(String value) {
            addCriterion("GRATUITY_TEMPLATE not like", value, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateIn(List<String> values) {
            addCriterion("GRATUITY_TEMPLATE in", values, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateNotIn(List<String> values) {
            addCriterion("GRATUITY_TEMPLATE not in", values, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateBetween(String value1, String value2) {
            addCriterion("GRATUITY_TEMPLATE between", value1, value2, "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateNotBetween(String value1, String value2) {
            addCriterion("GRATUITY_TEMPLATE not between", value1, value2, "gratuityTemplate");
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

        public Criteria andGratuityRemarkIsNull() {
            addCriterion("GRATUITY_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkIsNotNull() {
            addCriterion("GRATUITY_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkEqualTo(String value) {
            addCriterion("GRATUITY_REMARK =", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkNotEqualTo(String value) {
            addCriterion("GRATUITY_REMARK <>", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkGreaterThan(String value) {
            addCriterion("GRATUITY_REMARK >", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("GRATUITY_REMARK >=", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkLessThan(String value) {
            addCriterion("GRATUITY_REMARK <", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkLessThanOrEqualTo(String value) {
            addCriterion("GRATUITY_REMARK <=", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkLike(String value) {
            addCriterion("GRATUITY_REMARK like", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkNotLike(String value) {
            addCriterion("GRATUITY_REMARK not like", value, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkIn(List<String> values) {
            addCriterion("GRATUITY_REMARK in", values, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkNotIn(List<String> values) {
            addCriterion("GRATUITY_REMARK not in", values, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkBetween(String value1, String value2) {
            addCriterion("GRATUITY_REMARK between", value1, value2, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkNotBetween(String value1, String value2) {
            addCriterion("GRATUITY_REMARK not between", value1, value2, "gratuityRemark");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_NUMBER) like", value.toUpperCase(), "customerNumber");
            return (Criteria) this;
        }

        public Criteria andGratuityTemplateLikeInsensitive(String value) {
            addCriterion("upper(GRATUITY_TEMPLATE) like", value.toUpperCase(), "gratuityTemplate");
            return (Criteria) this;
        }

        public Criteria andGratuityRemarkLikeInsensitive(String value) {
            addCriterion("upper(GRATUITY_REMARK) like", value.toUpperCase(), "gratuityRemark");
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