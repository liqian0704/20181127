package com.yeepay.g3.core.ymf.entity.profit;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfitSummationParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProfitSummationParam() {
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

        public Criteria andTotalCountIsNull() {
            addCriterion("TOTAL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("TOTAL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(Long value) {
            addCriterion("TOTAL_COUNT =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(Long value) {
            addCriterion("TOTAL_COUNT <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(Long value) {
            addCriterion("TOTAL_COUNT >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COUNT >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(Long value) {
            addCriterion("TOTAL_COUNT <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COUNT <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<Long> values) {
            addCriterion("TOTAL_COUNT in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<Long> values) {
            addCriterion("TOTAL_COUNT not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COUNT between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COUNT not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountIsNull() {
            addCriterion("TOTAL_DAY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountIsNotNull() {
            addCriterion("TOTAL_DAY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountEqualTo(Long value) {
            addCriterion("TOTAL_DAY_COUNT =", value, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountNotEqualTo(Long value) {
            addCriterion("TOTAL_DAY_COUNT <>", value, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountGreaterThan(Long value) {
            addCriterion("TOTAL_DAY_COUNT >", value, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_DAY_COUNT >=", value, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountLessThan(Long value) {
            addCriterion("TOTAL_DAY_COUNT <", value, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_DAY_COUNT <=", value, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountIn(List<Long> values) {
            addCriterion("TOTAL_DAY_COUNT in", values, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountNotIn(List<Long> values) {
            addCriterion("TOTAL_DAY_COUNT not in", values, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountBetween(Long value1, Long value2) {
            addCriterion("TOTAL_DAY_COUNT between", value1, value2, "totalDayCount");
            return (Criteria) this;
        }

        public Criteria andTotalDayCountNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_DAY_COUNT not between", value1, value2, "totalDayCount");
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

        public Criteria andTotalTrxAmtIsNull() {
            addCriterion("TOTAL_TRX_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtIsNotNull() {
            addCriterion("TOTAL_TRX_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtEqualTo(BigDecimal value) {
            addCriterion("TOTAL_TRX_AMT =", value, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_TRX_AMT <>", value, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_TRX_AMT >", value, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_TRX_AMT >=", value, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtLessThan(BigDecimal value) {
            addCriterion("TOTAL_TRX_AMT <", value, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_TRX_AMT <=", value, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtIn(List<BigDecimal> values) {
            addCriterion("TOTAL_TRX_AMT in", values, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_TRX_AMT not in", values, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_TRX_AMT between", value1, value2, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andTotalTrxAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_TRX_AMT not between", value1, value2, "totalTrxAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtIsNull() {
            addCriterion("MITANG_TOTAL_TRXAMT is null");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtIsNotNull() {
            addCriterion("MITANG_TOTAL_TRXAMT is not null");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_TRXAMT =", value, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtNotEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_TRXAMT <>", value, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtGreaterThan(BigDecimal value) {
            addCriterion("MITANG_TOTAL_TRXAMT >", value, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_TRXAMT >=", value, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtLessThan(BigDecimal value) {
            addCriterion("MITANG_TOTAL_TRXAMT <", value, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_TRXAMT <=", value, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtIn(List<BigDecimal> values) {
            addCriterion("MITANG_TOTAL_TRXAMT in", values, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtNotIn(List<BigDecimal> values) {
            addCriterion("MITANG_TOTAL_TRXAMT not in", values, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_TOTAL_TRXAMT between", value1, value2, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalTrxamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_TOTAL_TRXAMT not between", value1, value2, "mitangTotalTrxamt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtIsNull() {
            addCriterion("MITANG_TOTAL_PROFIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtIsNotNull() {
            addCriterion("MITANG_TOTAL_PROFIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT =", value, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtNotEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT <>", value, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtGreaterThan(BigDecimal value) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT >", value, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT >=", value, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtLessThan(BigDecimal value) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT <", value, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT <=", value, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtIn(List<BigDecimal> values) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT in", values, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtNotIn(List<BigDecimal> values) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT not in", values, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT between", value1, value2, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andMitangTotalProfitAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MITANG_TOTAL_PROFIT_AMT not between", value1, value2, "mitangTotalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtIsNull() {
            addCriterion("TOTAL_PROFIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtIsNotNull() {
            addCriterion("TOTAL_PROFIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtEqualTo(BigDecimal value) {
            addCriterion("TOTAL_PROFIT_AMT =", value, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_PROFIT_AMT <>", value, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_PROFIT_AMT >", value, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_PROFIT_AMT >=", value, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtLessThan(BigDecimal value) {
            addCriterion("TOTAL_PROFIT_AMT <", value, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_PROFIT_AMT <=", value, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtIn(List<BigDecimal> values) {
            addCriterion("TOTAL_PROFIT_AMT in", values, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_PROFIT_AMT not in", values, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_PROFIT_AMT between", value1, value2, "totalProfitAmt");
            return (Criteria) this;
        }

        public Criteria andTotalProfitAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_PROFIT_AMT not between", value1, value2, "totalProfitAmt");
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

        public Criteria andMonthLikeInsensitive(String value) {
            addCriterion("upper(MONTH) like", value.toUpperCase(), "month");
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