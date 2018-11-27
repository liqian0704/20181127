package com.yeepay.g3.core.ymf.entity.common;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HolidayInfoParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HolidayInfoParam() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andLastmodifyTimeIsNull() {
            addCriterion("LASTMODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeIsNotNull() {
            addCriterion("LASTMODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeEqualTo(Date value) {
            addCriterion("LASTMODIFY_TIME =", value, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeNotEqualTo(Date value) {
            addCriterion("LASTMODIFY_TIME <>", value, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeGreaterThan(Date value) {
            addCriterion("LASTMODIFY_TIME >", value, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LASTMODIFY_TIME >=", value, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeLessThan(Date value) {
            addCriterion("LASTMODIFY_TIME <", value, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("LASTMODIFY_TIME <=", value, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeIn(List<Date> values) {
            addCriterion("LASTMODIFY_TIME in", values, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeNotIn(List<Date> values) {
            addCriterion("LASTMODIFY_TIME not in", values, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeBetween(Date value1, Date value2) {
            addCriterion("LASTMODIFY_TIME between", value1, value2, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andLastmodifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("LASTMODIFY_TIME not between", value1, value2, "lastmodifyTime");
            return (Criteria) this;
        }

        public Criteria andHolidayDateIsNull() {
            addCriterion("HOLIDAY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andHolidayDateIsNotNull() {
            addCriterion("HOLIDAY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andHolidayDateEqualTo(Date value) {
            addCriterionForJDBCDate("HOLIDAY_DATE =", value, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("HOLIDAY_DATE <>", value, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateGreaterThan(Date value) {
            addCriterionForJDBCDate("HOLIDAY_DATE >", value, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HOLIDAY_DATE >=", value, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateLessThan(Date value) {
            addCriterionForJDBCDate("HOLIDAY_DATE <", value, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HOLIDAY_DATE <=", value, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateIn(List<Date> values) {
            addCriterionForJDBCDate("HOLIDAY_DATE in", values, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("HOLIDAY_DATE not in", values, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HOLIDAY_DATE between", value1, value2, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andHolidayDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HOLIDAY_DATE not between", value1, value2, "holidayDate");
            return (Criteria) this;
        }

        public Criteria andDealTimesIsNull() {
            addCriterion("DEAL_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andDealTimesIsNotNull() {
            addCriterion("DEAL_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andDealTimesEqualTo(Integer value) {
            addCriterion("DEAL_TIMES =", value, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesNotEqualTo(Integer value) {
            addCriterion("DEAL_TIMES <>", value, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesGreaterThan(Integer value) {
            addCriterion("DEAL_TIMES >", value, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("DEAL_TIMES >=", value, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesLessThan(Integer value) {
            addCriterion("DEAL_TIMES <", value, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesLessThanOrEqualTo(Integer value) {
            addCriterion("DEAL_TIMES <=", value, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesIn(List<Integer> values) {
            addCriterion("DEAL_TIMES in", values, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesNotIn(List<Integer> values) {
            addCriterion("DEAL_TIMES not in", values, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesBetween(Integer value1, Integer value2) {
            addCriterion("DEAL_TIMES between", value1, value2, "dealTimes");
            return (Criteria) this;
        }

        public Criteria andDealTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("DEAL_TIMES not between", value1, value2, "dealTimes");
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