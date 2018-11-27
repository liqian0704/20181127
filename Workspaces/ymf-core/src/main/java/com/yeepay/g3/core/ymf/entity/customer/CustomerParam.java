package com.yeepay.g3.core.ymf.entity.customer;

import com.yeepay.g3.facade.ymf.enumtype.CustomerFlag;
import com.yeepay.g3.facade.ymf.enumtype.CustomerLevel;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerParam() {
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

        public Criteria andOptimisitcIsNull() {
            addCriterion("OPTIMISITC is null");
            return (Criteria) this;
        }

        public Criteria andOptimisitcIsNotNull() {
            addCriterion("OPTIMISITC is not null");
            return (Criteria) this;
        }

        public Criteria andOptimisitcEqualTo(Long value) {
            addCriterion("OPTIMISITC =", value, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcNotEqualTo(Long value) {
            addCriterion("OPTIMISITC <>", value, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcGreaterThan(Long value) {
            addCriterion("OPTIMISITC >", value, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcGreaterThanOrEqualTo(Long value) {
            addCriterion("OPTIMISITC >=", value, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcLessThan(Long value) {
            addCriterion("OPTIMISITC <", value, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcLessThanOrEqualTo(Long value) {
            addCriterion("OPTIMISITC <=", value, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcIn(List<Long> values) {
            addCriterion("OPTIMISITC in", values, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcNotIn(List<Long> values) {
            addCriterion("OPTIMISITC not in", values, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcBetween(Long value1, Long value2) {
            addCriterion("OPTIMISITC between", value1, value2, "optimisitc");
            return (Criteria) this;
        }

        public Criteria andOptimisitcNotBetween(Long value1, Long value2) {
            addCriterion("OPTIMISITC not between", value1, value2, "optimisitc");
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

        public Criteria andCustomerNameIsNull() {
            addCriterion("CUSTOMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("CUSTOMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("CUSTOMER_NAME =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("CUSTOMER_NAME >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("CUSTOMER_NAME <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("CUSTOMER_NAME like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("CUSTOMER_NAME not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("CUSTOMER_NAME in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_NAME not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIsNull() {
            addCriterion("CUSTOMER_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIsNotNull() {
            addCriterion("CUSTOMER_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelEqualTo(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL =", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotEqualTo(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL <>", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelGreaterThan(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL >", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelGreaterThanOrEqualTo(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL >=", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelLessThan(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL <", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelLessThanOrEqualTo(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL <=", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelLike(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL like", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotLike(CustomerLevel value) {
            addCriterion("CUSTOMER_LEVEL not like", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIn(List<CustomerLevel> values) {
            addCriterion("CUSTOMER_LEVEL in", values, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotIn(List<CustomerLevel> values) {
            addCriterion("CUSTOMER_LEVEL not in", values, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelBetween(CustomerLevel value1, CustomerLevel value2) {
            addCriterion("CUSTOMER_LEVEL between", value1, value2, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotBetween(CustomerLevel value1, CustomerLevel value2) {
            addCriterion("CUSTOMER_LEVEL not between", value1, value2, "customerLevel");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeIsNull() {
            addCriterion("INDUSTRY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeIsNotNull() {
            addCriterion("INDUSTRY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeEqualTo(String value) {
            addCriterion("INDUSTRY_TYPE =", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeNotEqualTo(String value) {
            addCriterion("INDUSTRY_TYPE <>", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeGreaterThan(String value) {
            addCriterion("INDUSTRY_TYPE >", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INDUSTRY_TYPE >=", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeLessThan(String value) {
            addCriterion("INDUSTRY_TYPE <", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeLessThanOrEqualTo(String value) {
            addCriterion("INDUSTRY_TYPE <=", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeLike(String value) {
            addCriterion("INDUSTRY_TYPE like", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeNotLike(String value) {
            addCriterion("INDUSTRY_TYPE not like", value, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeIn(List<String> values) {
            addCriterion("INDUSTRY_TYPE in", values, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeNotIn(List<String> values) {
            addCriterion("INDUSTRY_TYPE not in", values, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeBetween(String value1, String value2) {
            addCriterion("INDUSTRY_TYPE between", value1, value2, "industryType");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeNotBetween(String value1, String value2) {
            addCriterion("INDUSTRY_TYPE not between", value1, value2, "industryType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIsNull() {
            addCriterion("CUSTOMER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIsNotNull() {
            addCriterion("CUSTOMER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE =", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE <>", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThan(String value) {
            addCriterion("CUSTOMER_TYPE >", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE >=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThan(String value) {
            addCriterion("CUSTOMER_TYPE <", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE <=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLike(String value) {
            addCriterion("CUSTOMER_TYPE like", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotLike(String value) {
            addCriterion("CUSTOMER_TYPE not like", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIn(List<String> values) {
            addCriterion("CUSTOMER_TYPE in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotIn(List<String> values) {
            addCriterion("CUSTOMER_TYPE not in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeBetween(String value1, String value2) {
            addCriterion("CUSTOMER_TYPE between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_TYPE not between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoIsNull() {
            addCriterion("PAY_TYPE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoIsNotNull() {
            addCriterion("PAY_TYPE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoEqualTo(String value) {
            addCriterion("PAY_TYPE_INFO =", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoNotEqualTo(String value) {
            addCriterion("PAY_TYPE_INFO <>", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoGreaterThan(String value) {
            addCriterion("PAY_TYPE_INFO >", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE_INFO >=", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoLessThan(String value) {
            addCriterion("PAY_TYPE_INFO <", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoLessThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE_INFO <=", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoLike(String value) {
            addCriterion("PAY_TYPE_INFO like", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoNotLike(String value) {
            addCriterion("PAY_TYPE_INFO not like", value, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoIn(List<String> values) {
            addCriterion("PAY_TYPE_INFO in", values, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoNotIn(List<String> values) {
            addCriterion("PAY_TYPE_INFO not in", values, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoBetween(String value1, String value2) {
            addCriterion("PAY_TYPE_INFO between", value1, value2, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoNotBetween(String value1, String value2) {
            addCriterion("PAY_TYPE_INFO not between", value1, value2, "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("BUSINESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("BUSINESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(Long value) {
            addCriterion("BUSINESS_ID =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(Long value) {
            addCriterion("BUSINESS_ID <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(Long value) {
            addCriterion("BUSINESS_ID >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BUSINESS_ID >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(Long value) {
            addCriterion("BUSINESS_ID <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(Long value) {
            addCriterion("BUSINESS_ID <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<Long> values) {
            addCriterion("BUSINESS_ID in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<Long> values) {
            addCriterion("BUSINESS_ID not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(Long value1, Long value2) {
            addCriterion("BUSINESS_ID between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(Long value1, Long value2) {
            addCriterion("BUSINESS_ID not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNull() {
            addCriterion("APP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNotNull() {
            addCriterion("APP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAppTypeEqualTo(String value) {
            addCriterion("APP_TYPE =", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotEqualTo(String value) {
            addCriterion("APP_TYPE <>", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThan(String value) {
            addCriterion("APP_TYPE >", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThanOrEqualTo(String value) {
            addCriterion("APP_TYPE >=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThan(String value) {
            addCriterion("APP_TYPE <", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThanOrEqualTo(String value) {
            addCriterion("APP_TYPE <=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLike(String value) {
            addCriterion("APP_TYPE like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotLike(String value) {
            addCriterion("APP_TYPE not like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeIn(List<String> values) {
            addCriterion("APP_TYPE in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotIn(List<String> values) {
            addCriterion("APP_TYPE not in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeBetween(String value1, String value2) {
            addCriterion("APP_TYPE between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotBetween(String value1, String value2) {
            addCriterion("APP_TYPE not between", value1, value2, "appType");
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

        public Criteria andCustomerLogoIsNull() {
            addCriterion("CUSTOMER_LOGO is null");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoIsNotNull() {
            addCriterion("CUSTOMER_LOGO is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoEqualTo(String value) {
            addCriterion("CUSTOMER_LOGO =", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoNotEqualTo(String value) {
            addCriterion("CUSTOMER_LOGO <>", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoGreaterThan(String value) {
            addCriterion("CUSTOMER_LOGO >", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_LOGO >=", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoLessThan(String value) {
            addCriterion("CUSTOMER_LOGO <", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_LOGO <=", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoLike(String value) {
            addCriterion("CUSTOMER_LOGO like", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoNotLike(String value) {
            addCriterion("CUSTOMER_LOGO not like", value, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoIn(List<String> values) {
            addCriterion("CUSTOMER_LOGO in", values, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoNotIn(List<String> values) {
            addCriterion("CUSTOMER_LOGO not in", values, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoBetween(String value1, String value2) {
            addCriterion("CUSTOMER_LOGO between", value1, value2, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_LOGO not between", value1, value2, "customerLogo");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodIsNull() {
            addCriterion("VALIDITY_PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodIsNotNull() {
            addCriterion("VALIDITY_PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodEqualTo(Integer value) {
            addCriterion("VALIDITY_PERIOD =", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodNotEqualTo(Integer value) {
            addCriterion("VALIDITY_PERIOD <>", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodGreaterThan(Integer value) {
            addCriterion("VALIDITY_PERIOD >", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("VALIDITY_PERIOD >=", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodLessThan(Integer value) {
            addCriterion("VALIDITY_PERIOD <", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("VALIDITY_PERIOD <=", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodIn(List<Integer> values) {
            addCriterion("VALIDITY_PERIOD in", values, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodNotIn(List<Integer> values) {
            addCriterion("VALIDITY_PERIOD not in", values, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodBetween(Integer value1, Integer value2) {
            addCriterion("VALIDITY_PERIOD between", value1, value2, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("VALIDITY_PERIOD not between", value1, value2, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andDocumentNoIsNull() {
            addCriterion("DOCUMENT_NO is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNoIsNotNull() {
            addCriterion("DOCUMENT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNoEqualTo(String value) {
            addCriterion("DOCUMENT_NO =", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotEqualTo(String value) {
            addCriterion("DOCUMENT_NO <>", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoGreaterThan(String value) {
            addCriterion("DOCUMENT_NO >", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoGreaterThanOrEqualTo(String value) {
            addCriterion("DOCUMENT_NO >=", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoLessThan(String value) {
            addCriterion("DOCUMENT_NO <", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoLessThanOrEqualTo(String value) {
            addCriterion("DOCUMENT_NO <=", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoLike(String value) {
            addCriterion("DOCUMENT_NO like", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotLike(String value) {
            addCriterion("DOCUMENT_NO not like", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoIn(List<String> values) {
            addCriterion("DOCUMENT_NO in", values, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotIn(List<String> values) {
            addCriterion("DOCUMENT_NO not in", values, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoBetween(String value1, String value2) {
            addCriterion("DOCUMENT_NO between", value1, value2, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotBetween(String value1, String value2) {
            addCriterion("DOCUMENT_NO not between", value1, value2, "documentNo");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagIsNull() {
            addCriterion("CUSTOMER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagIsNotNull() {
            addCriterion("CUSTOMER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagEqualTo(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG =", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagNotEqualTo(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG <>", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagGreaterThan(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG >", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagGreaterThanOrEqualTo(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG >=", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagLessThan(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG <", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagLessThanOrEqualTo(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG <=", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagLike(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG like", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagNotLike(CustomerFlag value) {
            addCriterion("CUSTOMER_FLAG not like", value, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagIn(List<CustomerFlag> values) {
            addCriterion("CUSTOMER_FLAG in", values, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagNotIn(List<CustomerFlag> values) {
            addCriterion("CUSTOMER_FLAG not in", values, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagBetween(CustomerFlag value1, CustomerFlag value2) {
            addCriterion("CUSTOMER_FLAG between", value1, value2, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andCustomerFlagNotBetween(CustomerFlag value1, CustomerFlag value2) {
            addCriterion("CUSTOMER_FLAG not between", value1, value2, "customerFlag");
            return (Criteria) this;
        }

        public Criteria andTradeSystemIsNull() {
            addCriterion("TRADE_SYSTEM is null");
            return (Criteria) this;
        }

        public Criteria andTradeSystemIsNotNull() {
            addCriterion("TRADE_SYSTEM is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSystemEqualTo(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM =", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemNotEqualTo(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM <>", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemGreaterThan(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM >", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemGreaterThanOrEqualTo(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM >=", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemLessThan(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM <", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemLessThanOrEqualTo(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM <=", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemLike(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM like", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemNotLike(TradeSystemEnum value) {
            addCriterion("TRADE_SYSTEM not like", value, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemIn(List<TradeSystemEnum> values) {
            addCriterion("TRADE_SYSTEM in", values, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemNotIn(List<TradeSystemEnum> values) {
            addCriterion("TRADE_SYSTEM not in", values, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemBetween(TradeSystemEnum value1, TradeSystemEnum value2) {
            addCriterion("TRADE_SYSTEM between", value1, value2, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andTradeSystemNotBetween(TradeSystemEnum value1, TradeSystemEnum value2) {
            addCriterion("TRADE_SYSTEM not between", value1, value2, "tradeSystem");
            return (Criteria) this;
        }

        public Criteria andBalanceProductIsNull() {
            addCriterion("BALANCE_PRODUCT is null");
            return (Criteria) this;
        }

        public Criteria andBalanceProductIsNotNull() {
            addCriterion("BALANCE_PRODUCT is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceProductEqualTo(BalanceType value) {
            addCriterion("BALANCE_PRODUCT =", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductNotEqualTo(BalanceType value) {
            addCriterion("BALANCE_PRODUCT <>", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductGreaterThan(BalanceType value) {
            addCriterion("BALANCE_PRODUCT >", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductGreaterThanOrEqualTo(BalanceType value) {
            addCriterion("BALANCE_PRODUCT >=", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductLessThan(BalanceType value) {
            addCriterion("BALANCE_PRODUCT <", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductLessThanOrEqualTo(BalanceType value) {
            addCriterion("BALANCE_PRODUCT <=", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductLike(BalanceType value) {
            addCriterion("BALANCE_PRODUCT like", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductNotLike(BalanceType value) {
            addCriterion("BALANCE_PRODUCT not like", value, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductIn(List<BalanceType> values) {
            addCriterion("BALANCE_PRODUCT in", values, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductNotIn(List<BalanceType> values) {
            addCriterion("BALANCE_PRODUCT not in", values, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductBetween(BalanceType value1, BalanceType value2) {
            addCriterion("BALANCE_PRODUCT between", value1, value2, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andBalanceProductNotBetween(BalanceType value1, BalanceType value2) {
            addCriterion("BALANCE_PRODUCT not between", value1, value2, "balanceProduct");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Long value) {
            addCriterion("GROUP_ID =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Long value) {
            addCriterion("GROUP_ID <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Long value) {
            addCriterion("GROUP_ID >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GROUP_ID >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Long value) {
            addCriterion("GROUP_ID <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("GROUP_ID <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Long> values) {
            addCriterion("GROUP_ID in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Long> values) {
            addCriterion("GROUP_ID not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Long value1, Long value2) {
            addCriterion("GROUP_ID between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("GROUP_ID not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNull() {
            addCriterion("SHORT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNotNull() {
            addCriterion("SHORT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShortNameEqualTo(String value) {
            addCriterion("SHORT_NAME =", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotEqualTo(String value) {
            addCriterion("SHORT_NAME <>", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThan(String value) {
            addCriterion("SHORT_NAME >", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHORT_NAME >=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThan(String value) {
            addCriterion("SHORT_NAME <", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThanOrEqualTo(String value) {
            addCriterion("SHORT_NAME <=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLike(String value) {
            addCriterion("SHORT_NAME like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotLike(String value) {
            addCriterion("SHORT_NAME not like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameIn(List<String> values) {
            addCriterion("SHORT_NAME in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotIn(List<String> values) {
            addCriterion("SHORT_NAME not in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameBetween(String value1, String value2) {
            addCriterion("SHORT_NAME between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotBetween(String value1, String value2) {
            addCriterion("SHORT_NAME not between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_NUMBER) like", value.toUpperCase(), "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_NAME) like", value.toUpperCase(), "customerName");
            return (Criteria) this;
        }

        public Criteria andIndustryTypeLikeInsensitive(String value) {
            addCriterion("upper(INDUSTRY_TYPE) like", value.toUpperCase(), "industryType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_TYPE) like", value.toUpperCase(), "customerType");
            return (Criteria) this;
        }

        public Criteria andPayTypeInfoLikeInsensitive(String value) {
            addCriterion("upper(PAY_TYPE_INFO) like", value.toUpperCase(), "payTypeInfo");
            return (Criteria) this;
        }

        public Criteria andAppTypeLikeInsensitive(String value) {
            addCriterion("upper(APP_TYPE) like", value.toUpperCase(), "appType");
            return (Criteria) this;
        }

        public Criteria andCustomerLogoLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_LOGO) like", value.toUpperCase(), "customerLogo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoLikeInsensitive(String value) {
            addCriterion("upper(DOCUMENT_NO) like", value.toUpperCase(), "documentNo");
            return (Criteria) this;
        }

        public Criteria andShortNameLikeInsensitive(String value) {
            addCriterion("upper(SHORT_NAME) like", value.toUpperCase(), "shortName");
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