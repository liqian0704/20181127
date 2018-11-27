package com.yeepay.g3.core.ymf.entity.profit;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfitParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProfitParam() {
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

        public Criteria andUniqueIdIsNull() {
            addCriterion("UNIQUE_ID is null");
            return (Criteria) this;
        }

        public Criteria andUniqueIdIsNotNull() {
            addCriterion("UNIQUE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUniqueIdEqualTo(String value) {
            addCriterion("UNIQUE_ID =", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotEqualTo(String value) {
            addCriterion("UNIQUE_ID <>", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdGreaterThan(String value) {
            addCriterion("UNIQUE_ID >", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdGreaterThanOrEqualTo(String value) {
            addCriterion("UNIQUE_ID >=", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdLessThan(String value) {
            addCriterion("UNIQUE_ID <", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdLessThanOrEqualTo(String value) {
            addCriterion("UNIQUE_ID <=", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdLike(String value) {
            addCriterion("UNIQUE_ID like", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotLike(String value) {
            addCriterion("UNIQUE_ID not like", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdIn(List<String> values) {
            addCriterion("UNIQUE_ID in", values, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotIn(List<String> values) {
            addCriterion("UNIQUE_ID not in", values, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdBetween(String value1, String value2) {
            addCriterion("UNIQUE_ID between", value1, value2, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotBetween(String value1, String value2) {
            addCriterion("UNIQUE_ID not between", value1, value2, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNull() {
            addCriterion("DAY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNotNull() {
            addCriterion("DAY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDayCountEqualTo(Long value) {
            addCriterion("DAY_COUNT =", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotEqualTo(Long value) {
            addCriterion("DAY_COUNT <>", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThan(Long value) {
            addCriterion("DAY_COUNT >", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThanOrEqualTo(Long value) {
            addCriterion("DAY_COUNT >=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThan(Long value) {
            addCriterion("DAY_COUNT <", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThanOrEqualTo(Long value) {
            addCriterion("DAY_COUNT <=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountIn(List<Long> values) {
            addCriterion("DAY_COUNT in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotIn(List<Long> values) {
            addCriterion("DAY_COUNT not in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountBetween(Long value1, Long value2) {
            addCriterion("DAY_COUNT between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotBetween(Long value1, Long value2) {
            addCriterion("DAY_COUNT not between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("MONTH is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(String value) {
            addCriterion("MONTH =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(String value) {
            addCriterion("MONTH <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(String value) {
            addCriterion("MONTH >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(String value) {
            addCriterion("MONTH >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(String value) {
            addCriterion("MONTH <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(String value) {
            addCriterion("MONTH <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLike(String value) {
            addCriterion("MONTH like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotLike(String value) {
            addCriterion("MONTH not like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<String> values) {
            addCriterion("MONTH in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<String> values) {
            addCriterion("MONTH not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(String value1, String value2) {
            addCriterion("MONTH between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(String value1, String value2) {
            addCriterion("MONTH not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andSaleIsNull() {
            addCriterion("SALE is null");
            return (Criteria) this;
        }

        public Criteria andSaleIsNotNull() {
            addCriterion("SALE is not null");
            return (Criteria) this;
        }

        public Criteria andSaleEqualTo(String value) {
            addCriterion("SALE =", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleNotEqualTo(String value) {
            addCriterion("SALE <>", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleGreaterThan(String value) {
            addCriterion("SALE >", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleGreaterThanOrEqualTo(String value) {
            addCriterion("SALE >=", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleLessThan(String value) {
            addCriterion("SALE <", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleLessThanOrEqualTo(String value) {
            addCriterion("SALE <=", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleLike(String value) {
            addCriterion("SALE like", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleNotLike(String value) {
            addCriterion("SALE not like", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleIn(List<String> values) {
            addCriterion("SALE in", values, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleNotIn(List<String> values) {
            addCriterion("SALE not in", values, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleBetween(String value1, String value2) {
            addCriterion("SALE between", value1, value2, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleNotBetween(String value1, String value2) {
            addCriterion("SALE not between", value1, value2, "sale");
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

        public Criteria andAgentNumberIsNull() {
            addCriterion("AGENT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andAgentNumberIsNotNull() {
            addCriterion("AGENT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNumberEqualTo(String value) {
            addCriterion("AGENT_NUMBER =", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberNotEqualTo(String value) {
            addCriterion("AGENT_NUMBER <>", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberGreaterThan(String value) {
            addCriterion("AGENT_NUMBER >", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberGreaterThanOrEqualTo(String value) {
            addCriterion("AGENT_NUMBER >=", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberLessThan(String value) {
            addCriterion("AGENT_NUMBER <", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberLessThanOrEqualTo(String value) {
            addCriterion("AGENT_NUMBER <=", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberLike(String value) {
            addCriterion("AGENT_NUMBER like", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberNotLike(String value) {
            addCriterion("AGENT_NUMBER not like", value, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberIn(List<String> values) {
            addCriterion("AGENT_NUMBER in", values, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberNotIn(List<String> values) {
            addCriterion("AGENT_NUMBER not in", values, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberBetween(String value1, String value2) {
            addCriterion("AGENT_NUMBER between", value1, value2, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberNotBetween(String value1, String value2) {
            addCriterion("AGENT_NUMBER not between", value1, value2, "agentNumber");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountIsNull() {
            addCriterion("TOTAL_TRX_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountIsNotNull() {
            addCriterion("TOTAL_TRX_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountEqualTo(Long value) {
            addCriterion("TOTAL_TRX_COUNT =", value, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountNotEqualTo(Long value) {
            addCriterion("TOTAL_TRX_COUNT <>", value, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountGreaterThan(Long value) {
            addCriterion("TOTAL_TRX_COUNT >", value, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_TRX_COUNT >=", value, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountLessThan(Long value) {
            addCriterion("TOTAL_TRX_COUNT <", value, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_TRX_COUNT <=", value, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountIn(List<Long> values) {
            addCriterion("TOTAL_TRX_COUNT in", values, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountNotIn(List<Long> values) {
            addCriterion("TOTAL_TRX_COUNT not in", values, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountBetween(Long value1, Long value2) {
            addCriterion("TOTAL_TRX_COUNT between", value1, value2, "totalTrxCount");
            return (Criteria) this;
        }

        public Criteria andTotalTrxCountNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_TRX_COUNT not between", value1, value2, "totalTrxCount");
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

        public Criteria andProfitAmtIsNull() {
            addCriterion("PROFIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andProfitAmtIsNotNull() {
            addCriterion("PROFIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andProfitAmtEqualTo(BigDecimal value) {
            addCriterion("PROFIT_AMT =", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtNotEqualTo(BigDecimal value) {
            addCriterion("PROFIT_AMT <>", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtGreaterThan(BigDecimal value) {
            addCriterion("PROFIT_AMT >", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PROFIT_AMT >=", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtLessThan(BigDecimal value) {
            addCriterion("PROFIT_AMT <", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PROFIT_AMT <=", value, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtIn(List<BigDecimal> values) {
            addCriterion("PROFIT_AMT in", values, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtNotIn(List<BigDecimal> values) {
            addCriterion("PROFIT_AMT not in", values, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROFIT_AMT between", value1, value2, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andProfitAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROFIT_AMT not between", value1, value2, "profitAmt");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeIsNull() {
            addCriterion("SALES_PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeIsNotNull() {
            addCriterion("SALES_PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeEqualTo(String value) {
            addCriterion("SALES_PRODUCT_CODE =", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeNotEqualTo(String value) {
            addCriterion("SALES_PRODUCT_CODE <>", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeGreaterThan(String value) {
            addCriterion("SALES_PRODUCT_CODE >", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SALES_PRODUCT_CODE >=", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeLessThan(String value) {
            addCriterion("SALES_PRODUCT_CODE <", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeLessThanOrEqualTo(String value) {
            addCriterion("SALES_PRODUCT_CODE <=", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeLike(String value) {
            addCriterion("SALES_PRODUCT_CODE like", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeNotLike(String value) {
            addCriterion("SALES_PRODUCT_CODE not like", value, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeIn(List<String> values) {
            addCriterion("SALES_PRODUCT_CODE in", values, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeNotIn(List<String> values) {
            addCriterion("SALES_PRODUCT_CODE not in", values, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeBetween(String value1, String value2) {
            addCriterion("SALES_PRODUCT_CODE between", value1, value2, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeNotBetween(String value1, String value2) {
            addCriterion("SALES_PRODUCT_CODE not between", value1, value2, "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNull() {
            addCriterion("PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("PRODUCT_CODE =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("PRODUCT_CODE <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("PRODUCT_CODE >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("PRODUCT_CODE <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("PRODUCT_CODE like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("PRODUCT_CODE not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("PRODUCT_CODE in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("PRODUCT_CODE not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeIsNull() {
            addCriterion("PROFIT_PRODUCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeIsNotNull() {
            addCriterion("PROFIT_PRODUCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeEqualTo(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE =", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeNotEqualTo(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE <>", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeGreaterThan(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE >", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeGreaterThanOrEqualTo(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE >=", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeLessThan(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE <", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeLessThanOrEqualTo(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE <=", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeLike(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE like", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeNotLike(ProfitProductTypeEnum value) {
            addCriterion("PROFIT_PRODUCT_TYPE not like", value, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeIn(List<ProfitProductTypeEnum> values) {
            addCriterion("PROFIT_PRODUCT_TYPE in", values, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeNotIn(List<ProfitProductTypeEnum> values) {
            addCriterion("PROFIT_PRODUCT_TYPE not in", values, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeBetween(ProfitProductTypeEnum value1, ProfitProductTypeEnum value2) {
            addCriterion("PROFIT_PRODUCT_TYPE between", value1, value2, "profitProductType");
            return (Criteria) this;
        }

        public Criteria andProfitProductTypeNotBetween(ProfitProductTypeEnum value1, ProfitProductTypeEnum value2) {
            addCriterion("PROFIT_PRODUCT_TYPE not between", value1, value2, "profitProductType");
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

        public Criteria andCustomerTypeEqualTo(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE =", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotEqualTo(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE <>", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThan(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE >", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThanOrEqualTo(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE >=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThan(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE <", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThanOrEqualTo(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE <=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLike(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE like", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotLike(CustomerTypeEnum value) {
            addCriterion("CUSTOMER_TYPE not like", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIn(List<CustomerTypeEnum> values) {
            addCriterion("CUSTOMER_TYPE in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotIn(List<CustomerTypeEnum> values) {
            addCriterion("CUSTOMER_TYPE not in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeBetween(CustomerTypeEnum value1, CustomerTypeEnum value2) {
            addCriterion("CUSTOMER_TYPE between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotBetween(CustomerTypeEnum value1, CustomerTypeEnum value2) {
            addCriterion("CUSTOMER_TYPE not between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtIsNull() {
            addCriterion("MITANG_TRXAMT is null");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtIsNotNull() {
            addCriterion("MITANG_TRXAMT is not null");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtEqualTo(BigDecimal value) {
            addCriterion("MITANG_TRXAMT =", value, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtNotEqualTo(BigDecimal value) {
            addCriterion("MITANG_TRXAMT <>", value, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtGreaterThan(BigDecimal value) {
            addCriterion("MITANG_TRXAMT >", value, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_TRXAMT >=", value, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtLessThan(BigDecimal value) {
            addCriterion("MITANG_TRXAMT <", value, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_TRXAMT <=", value, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtIn(List<BigDecimal> values) {
            addCriterion("MITANG_TRXAMT in", values, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtNotIn(List<BigDecimal> values) {
            addCriterion("MITANG_TRXAMT not in", values, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_TRXAMT between", value1, value2, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTrxamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_TRXAMT not between", value1, value2, "mitangTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtIsNull() {
            addCriterion("MITANG_PROFIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtIsNotNull() {
            addCriterion("MITANG_PROFIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtEqualTo(BigDecimal value) {
            addCriterion("MITANG_PROFIT_AMT =", value, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtNotEqualTo(BigDecimal value) {
            addCriterion("MITANG_PROFIT_AMT <>", value, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtGreaterThan(BigDecimal value) {
            addCriterion("MITANG_PROFIT_AMT >", value, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_PROFIT_AMT >=", value, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtLessThan(BigDecimal value) {
            addCriterion("MITANG_PROFIT_AMT <", value, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_PROFIT_AMT <=", value, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtIn(List<BigDecimal> values) {
            addCriterion("MITANG_PROFIT_AMT in", values, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtNotIn(List<BigDecimal> values) {
            addCriterion("MITANG_PROFIT_AMT not in", values, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_PROFIT_AMT between", value1, value2, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangProfitAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_PROFIT_AMT not between", value1, value2, "mitangProfitAmt");
            return (Criteria) this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("PERCENT is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("PERCENT is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(BigDecimal value) {
            addCriterion("PERCENT =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(BigDecimal value) {
            addCriterion("PERCENT <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(BigDecimal value) {
            addCriterion("PERCENT >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PERCENT >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(BigDecimal value) {
            addCriterion("PERCENT <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PERCENT <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<BigDecimal> values) {
            addCriterion("PERCENT in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<BigDecimal> values) {
            addCriterion("PERCENT not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PERCENT between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PERCENT not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusIsNull() {
            addCriterion("CALCULATE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusIsNotNull() {
            addCriterion("CALCULATE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusEqualTo(Status value) {
            addCriterion("CALCULATE_STATUS =", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusNotEqualTo(Status value) {
            addCriterion("CALCULATE_STATUS <>", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusGreaterThan(Status value) {
            addCriterion("CALCULATE_STATUS >", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusGreaterThanOrEqualTo(Status value) {
            addCriterion("CALCULATE_STATUS >=", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusLessThan(Status value) {
            addCriterion("CALCULATE_STATUS <", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusLessThanOrEqualTo(Status value) {
            addCriterion("CALCULATE_STATUS <=", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusLike(Status value) {
            addCriterion("CALCULATE_STATUS like", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusNotLike(Status value) {
            addCriterion("CALCULATE_STATUS not like", value, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusIn(List<Status> values) {
            addCriterion("CALCULATE_STATUS in", values, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusNotIn(List<Status> values) {
            addCriterion("CALCULATE_STATUS not in", values, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusBetween(Status value1, Status value2) {
            addCriterion("CALCULATE_STATUS between", value1, value2, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andCalculateStatusNotBetween(Status value1, Status value2) {
            addCriterion("CALCULATE_STATUS not between", value1, value2, "calculateStatus");
            return (Criteria) this;
        }

        public Criteria andSummationIdIsNull() {
            addCriterion("SUMMATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSummationIdIsNotNull() {
            addCriterion("SUMMATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSummationIdEqualTo(Long value) {
            addCriterion("SUMMATION_ID =", value, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdNotEqualTo(Long value) {
            addCriterion("SUMMATION_ID <>", value, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdGreaterThan(Long value) {
            addCriterion("SUMMATION_ID >", value, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SUMMATION_ID >=", value, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdLessThan(Long value) {
            addCriterion("SUMMATION_ID <", value, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdLessThanOrEqualTo(Long value) {
            addCriterion("SUMMATION_ID <=", value, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdIn(List<Long> values) {
            addCriterion("SUMMATION_ID in", values, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdNotIn(List<Long> values) {
            addCriterion("SUMMATION_ID not in", values, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdBetween(Long value1, Long value2) {
            addCriterion("SUMMATION_ID between", value1, value2, "summationId");
            return (Criteria) this;
        }

        public Criteria andSummationIdNotBetween(Long value1, Long value2) {
            addCriterion("SUMMATION_ID not between", value1, value2, "summationId");
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

        public Criteria andUniqueIdLikeInsensitive(String value) {
            addCriterion("upper(UNIQUE_ID) like", value.toUpperCase(), "uniqueId");
            return (Criteria) this;
        }

        public Criteria andMonthLikeInsensitive(String value) {
            addCriterion("upper(MONTH) like", value.toUpperCase(), "month");
            return (Criteria) this;
        }

        public Criteria andSaleLikeInsensitive(String value) {
            addCriterion("upper(SALE) like", value.toUpperCase(), "sale");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLikeInsensitive(String value) {
            addCriterion("upper(CUSTOMER_NUMBER) like", value.toUpperCase(), "customerNumber");
            return (Criteria) this;
        }

        public Criteria andAgentNumberLikeInsensitive(String value) {
            addCriterion("upper(AGENT_NUMBER) like", value.toUpperCase(), "agentNumber");
            return (Criteria) this;
        }

        public Criteria andSalesProductCodeLikeInsensitive(String value) {
            addCriterion("upper(SALES_PRODUCT_CODE) like", value.toUpperCase(), "salesProductCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLikeInsensitive(String value) {
            addCriterion("upper(PRODUCT_CODE) like", value.toUpperCase(), "productCode");
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