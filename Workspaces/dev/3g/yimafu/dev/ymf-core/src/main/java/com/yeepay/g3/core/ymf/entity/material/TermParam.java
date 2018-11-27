package com.yeepay.g3.core.ymf.entity.material;

import com.yeepay.g3.facade.ymf.enumtype.term.StockStatus;
import com.yeepay.g3.facade.ymf.enumtype.term.TermStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TermParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TermParam() {
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

        public Criteria andInTimeIsNull() {
            addCriterion("IN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInTimeIsNotNull() {
            addCriterion("IN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInTimeEqualTo(Date value) {
            addCriterion("IN_TIME =", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotEqualTo(Date value) {
            addCriterion("IN_TIME <>", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThan(Date value) {
            addCriterion("IN_TIME >", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("IN_TIME >=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThan(Date value) {
            addCriterion("IN_TIME <", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThanOrEqualTo(Date value) {
            addCriterion("IN_TIME <=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeIn(List<Date> values) {
            addCriterion("IN_TIME in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotIn(List<Date> values) {
            addCriterion("IN_TIME not in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeBetween(Date value1, Date value2) {
            addCriterion("IN_TIME between", value1, value2, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotBetween(Date value1, Date value2) {
            addCriterion("IN_TIME not between", value1, value2, "inTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeIsNull() {
            addCriterion("OUT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOutTimeIsNotNull() {
            addCriterion("OUT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOutTimeEqualTo(Date value) {
            addCriterion("OUT_TIME =", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotEqualTo(Date value) {
            addCriterion("OUT_TIME <>", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeGreaterThan(Date value) {
            addCriterion("OUT_TIME >", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("OUT_TIME >=", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeLessThan(Date value) {
            addCriterion("OUT_TIME <", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeLessThanOrEqualTo(Date value) {
            addCriterion("OUT_TIME <=", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeIn(List<Date> values) {
            addCriterion("OUT_TIME in", values, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotIn(List<Date> values) {
            addCriterion("OUT_TIME not in", values, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeBetween(Date value1, Date value2) {
            addCriterion("OUT_TIME between", value1, value2, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotBetween(Date value1, Date value2) {
            addCriterion("OUT_TIME not between", value1, value2, "outTime");
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

        public Criteria andTermTypeIsNull() {
            addCriterion("TERM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTermTypeIsNotNull() {
            addCriterion("TERM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTermTypeEqualTo(String value) {
            addCriterion("TERM_TYPE =", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotEqualTo(String value) {
            addCriterion("TERM_TYPE <>", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeGreaterThan(String value) {
            addCriterion("TERM_TYPE >", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_TYPE >=", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeLessThan(String value) {
            addCriterion("TERM_TYPE <", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeLessThanOrEqualTo(String value) {
            addCriterion("TERM_TYPE <=", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeLike(String value) {
            addCriterion("TERM_TYPE like", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotLike(String value) {
            addCriterion("TERM_TYPE not like", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeIn(List<String> values) {
            addCriterion("TERM_TYPE in", values, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotIn(List<String> values) {
            addCriterion("TERM_TYPE not in", values, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeBetween(String value1, String value2) {
            addCriterion("TERM_TYPE between", value1, value2, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotBetween(String value1, String value2) {
            addCriterion("TERM_TYPE not between", value1, value2, "termType");
            return (Criteria) this;
        }

        public Criteria andManufactIsNull() {
            addCriterion("MANUFACT is null");
            return (Criteria) this;
        }

        public Criteria andManufactIsNotNull() {
            addCriterion("MANUFACT is not null");
            return (Criteria) this;
        }

        public Criteria andManufactEqualTo(String value) {
            addCriterion("MANUFACT =", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactNotEqualTo(String value) {
            addCriterion("MANUFACT <>", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactGreaterThan(String value) {
            addCriterion("MANUFACT >", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactGreaterThanOrEqualTo(String value) {
            addCriterion("MANUFACT >=", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactLessThan(String value) {
            addCriterion("MANUFACT <", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactLessThanOrEqualTo(String value) {
            addCriterion("MANUFACT <=", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactLike(String value) {
            addCriterion("MANUFACT like", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactNotLike(String value) {
            addCriterion("MANUFACT not like", value, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactIn(List<String> values) {
            addCriterion("MANUFACT in", values, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactNotIn(List<String> values) {
            addCriterion("MANUFACT not in", values, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactBetween(String value1, String value2) {
            addCriterion("MANUFACT between", value1, value2, "manufact");
            return (Criteria) this;
        }

        public Criteria andManufactNotBetween(String value1, String value2) {
            addCriterion("MANUFACT not between", value1, value2, "manufact");
            return (Criteria) this;
        }

        public Criteria andTermStatusIsNull() {
            addCriterion("TERM_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTermStatusIsNotNull() {
            addCriterion("TERM_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTermStatusEqualTo(TermStatus value) {
            addCriterion("TERM_STATUS =", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusNotEqualTo(TermStatus value) {
            addCriterion("TERM_STATUS <>", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusGreaterThan(TermStatus value) {
            addCriterion("TERM_STATUS >", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusGreaterThanOrEqualTo(TermStatus value) {
            addCriterion("TERM_STATUS >=", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusLessThan(TermStatus value) {
            addCriterion("TERM_STATUS <", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusLessThanOrEqualTo(TermStatus value) {
            addCriterion("TERM_STATUS <=", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusLike(TermStatus value) {
            addCriterion("TERM_STATUS like", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusNotLike(TermStatus value) {
            addCriterion("TERM_STATUS not like", value, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusIn(List<TermStatus> values) {
            addCriterion("TERM_STATUS in", values, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusNotIn(List<TermStatus> values) {
            addCriterion("TERM_STATUS not in", values, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusBetween(TermStatus value1, TermStatus value2) {
            addCriterion("TERM_STATUS between", value1, value2, "termStatus");
            return (Criteria) this;
        }

        public Criteria andTermStatusNotBetween(TermStatus value1, TermStatus value2) {
            addCriterion("TERM_STATUS not between", value1, value2, "termStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusIsNull() {
            addCriterion("STOCK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStockStatusIsNotNull() {
            addCriterion("STOCK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStockStatusEqualTo(StockStatus value) {
            addCriterion("STOCK_STATUS =", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotEqualTo(StockStatus value) {
            addCriterion("STOCK_STATUS <>", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusGreaterThan(StockStatus value) {
            addCriterion("STOCK_STATUS >", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusGreaterThanOrEqualTo(StockStatus value) {
            addCriterion("STOCK_STATUS >=", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLessThan(StockStatus value) {
            addCriterion("STOCK_STATUS <", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLessThanOrEqualTo(StockStatus value) {
            addCriterion("STOCK_STATUS <=", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLike(StockStatus value) {
            addCriterion("STOCK_STATUS like", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotLike(StockStatus value) {
            addCriterion("STOCK_STATUS not like", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusIn(List<StockStatus> values) {
            addCriterion("STOCK_STATUS in", values, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotIn(List<StockStatus> values) {
            addCriterion("STOCK_STATUS not in", values, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusBetween(StockStatus value1, StockStatus value2) {
            addCriterion("STOCK_STATUS between", value1, value2, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotBetween(StockStatus value1, StockStatus value2) {
            addCriterion("STOCK_STATUS not between", value1, value2, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andSnSerialLikeInsensitive(String value) {
            addCriterion("upper(SN_SERIAL) like", value.toUpperCase(), "snSerial");
            return (Criteria) this;
        }

        public Criteria andTermTypeLikeInsensitive(String value) {
            addCriterion("upper(TERM_TYPE) like", value.toUpperCase(), "termType");
            return (Criteria) this;
        }

        public Criteria andManufactLikeInsensitive(String value) {
            addCriterion("upper(MANUFACT) like", value.toUpperCase(), "manufact");
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