package com.yeepay.g3.core.ymf.entity.order;

import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.SettleStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentParam {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaymentParam() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
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

        public Criteria andExpireTimeIsNull() {
            addCriterion("EXPIRE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("EXPIRE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("EXPIRE_TIME =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("EXPIRE_TIME <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("EXPIRE_TIME >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EXPIRE_TIME >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("EXPIRE_TIME <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("EXPIRE_TIME <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("EXPIRE_TIME in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("EXPIRE_TIME not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("EXPIRE_TIME between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("EXPIRE_TIME not between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andPayUrlIsNull() {
            addCriterion("PAY_URL is null");
            return (Criteria) this;
        }

        public Criteria andPayUrlIsNotNull() {
            addCriterion("PAY_URL is not null");
            return (Criteria) this;
        }

        public Criteria andPayUrlEqualTo(String value) {
            addCriterion("PAY_URL =", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotEqualTo(String value) {
            addCriterion("PAY_URL <>", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlGreaterThan(String value) {
            addCriterion("PAY_URL >", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_URL >=", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLessThan(String value) {
            addCriterion("PAY_URL <", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLessThanOrEqualTo(String value) {
            addCriterion("PAY_URL <=", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLike(String value) {
            addCriterion("PAY_URL like", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotLike(String value) {
            addCriterion("PAY_URL not like", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlIn(List<String> values) {
            addCriterion("PAY_URL in", values, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotIn(List<String> values) {
            addCriterion("PAY_URL not in", values, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlBetween(String value1, String value2) {
            addCriterion("PAY_URL between", value1, value2, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotBetween(String value1, String value2) {
            addCriterion("PAY_URL not between", value1, value2, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeIsNull() {
            addCriterion("PAY_REQUEST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeIsNotNull() {
            addCriterion("PAY_REQUEST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeEqualTo(Date value) {
            addCriterion("PAY_REQUEST_TIME =", value, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeNotEqualTo(Date value) {
            addCriterion("PAY_REQUEST_TIME <>", value, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeGreaterThan(Date value) {
            addCriterion("PAY_REQUEST_TIME >", value, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PAY_REQUEST_TIME >=", value, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeLessThan(Date value) {
            addCriterion("PAY_REQUEST_TIME <", value, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("PAY_REQUEST_TIME <=", value, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeIn(List<Date> values) {
            addCriterion("PAY_REQUEST_TIME in", values, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeNotIn(List<Date> values) {
            addCriterion("PAY_REQUEST_TIME not in", values, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeBetween(Date value1, Date value2) {
            addCriterion("PAY_REQUEST_TIME between", value1, value2, "payRequestTime");
            return (Criteria) this;
        }

        public Criteria andPayRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("PAY_REQUEST_TIME not between", value1, value2, "payRequestTime");
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

        public Criteria andChannelPayTimeIsNull() {
            addCriterion("CHANNEL_PAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeIsNotNull() {
            addCriterion("CHANNEL_PAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeEqualTo(Date value) {
            addCriterion("CHANNEL_PAY_TIME =", value, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeNotEqualTo(Date value) {
            addCriterion("CHANNEL_PAY_TIME <>", value, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeGreaterThan(Date value) {
            addCriterion("CHANNEL_PAY_TIME >", value, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHANNEL_PAY_TIME >=", value, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeLessThan(Date value) {
            addCriterion("CHANNEL_PAY_TIME <", value, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHANNEL_PAY_TIME <=", value, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeIn(List<Date> values) {
            addCriterion("CHANNEL_PAY_TIME in", values, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeNotIn(List<Date> values) {
            addCriterion("CHANNEL_PAY_TIME not in", values, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeBetween(Date value1, Date value2) {
            addCriterion("CHANNEL_PAY_TIME between", value1, value2, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andChannelPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHANNEL_PAY_TIME not between", value1, value2, "channelPayTime");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("PAY_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("PAY_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(PaymentStatus value) {
            addCriterion("PAY_STATUS =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(PaymentStatus value) {
            addCriterion("PAY_STATUS <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(PaymentStatus value) {
            addCriterion("PAY_STATUS >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(PaymentStatus value) {
            addCriterion("PAY_STATUS >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(PaymentStatus value) {
            addCriterion("PAY_STATUS <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(PaymentStatus value) {
            addCriterion("PAY_STATUS <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLike(PaymentStatus value) {
            addCriterion("PAY_STATUS like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotLike(PaymentStatus value) {
            addCriterion("PAY_STATUS not like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<PaymentStatus> values) {
            addCriterion("PAY_STATUS in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<PaymentStatus> values) {
            addCriterion("PAY_STATUS not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(PaymentStatus value1, PaymentStatus value2) {
            addCriterion("PAY_STATUS between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(PaymentStatus value1, PaymentStatus value2) {
            addCriterion("PAY_STATUS not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNull() {
            addCriterion("SETTLE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("SETTLE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(SettleStatus value) {
            addCriterion("SETTLE_STATUS =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(SettleStatus value) {
            addCriterion("SETTLE_STATUS <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(SettleStatus value) {
            addCriterion("SETTLE_STATUS >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(SettleStatus value) {
            addCriterion("SETTLE_STATUS >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(SettleStatus value) {
            addCriterion("SETTLE_STATUS <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(SettleStatus value) {
            addCriterion("SETTLE_STATUS <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLike(SettleStatus value) {
            addCriterion("SETTLE_STATUS like", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotLike(SettleStatus value) {
            addCriterion("SETTLE_STATUS not like", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<SettleStatus> values) {
            addCriterion("SETTLE_STATUS in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<SettleStatus> values) {
            addCriterion("SETTLE_STATUS not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(SettleStatus value1, SettleStatus value2) {
            addCriterion("SETTLE_STATUS between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(SettleStatus value1, SettleStatus value2) {
            addCriterion("SETTLE_STATUS not between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleTimeIsNull() {
            addCriterion("SETTLE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSettleTimeIsNotNull() {
            addCriterion("SETTLE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSettleTimeEqualTo(Date value) {
            addCriterion("SETTLE_TIME =", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeNotEqualTo(Date value) {
            addCriterion("SETTLE_TIME <>", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeGreaterThan(Date value) {
            addCriterion("SETTLE_TIME >", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SETTLE_TIME >=", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeLessThan(Date value) {
            addCriterion("SETTLE_TIME <", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeLessThanOrEqualTo(Date value) {
            addCriterion("SETTLE_TIME <=", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeIn(List<Date> values) {
            addCriterion("SETTLE_TIME in", values, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeNotIn(List<Date> values) {
            addCriterion("SETTLE_TIME not in", values, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeBetween(Date value1, Date value2) {
            addCriterion("SETTLE_TIME between", value1, value2, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeNotBetween(Date value1, Date value2) {
            addCriterion("SETTLE_TIME not between", value1, value2, "settleTime");
            return (Criteria) this;
        }

        public Criteria andBackUrlIsNull() {
            addCriterion("BACK_URL is null");
            return (Criteria) this;
        }

        public Criteria andBackUrlIsNotNull() {
            addCriterion("BACK_URL is not null");
            return (Criteria) this;
        }

        public Criteria andBackUrlEqualTo(String value) {
            addCriterion("BACK_URL =", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlNotEqualTo(String value) {
            addCriterion("BACK_URL <>", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlGreaterThan(String value) {
            addCriterion("BACK_URL >", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_URL >=", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlLessThan(String value) {
            addCriterion("BACK_URL <", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlLessThanOrEqualTo(String value) {
            addCriterion("BACK_URL <=", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlLike(String value) {
            addCriterion("BACK_URL like", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlNotLike(String value) {
            addCriterion("BACK_URL not like", value, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlIn(List<String> values) {
            addCriterion("BACK_URL in", values, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlNotIn(List<String> values) {
            addCriterion("BACK_URL not in", values, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlBetween(String value1, String value2) {
            addCriterion("BACK_URL between", value1, value2, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlNotBetween(String value1, String value2) {
            addCriterion("BACK_URL not between", value1, value2, "backUrl");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdIsNull() {
            addCriterion("BANK_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdIsNotNull() {
            addCriterion("BANK_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdEqualTo(String value) {
            addCriterion("BANK_ORDER_ID =", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdNotEqualTo(String value) {
            addCriterion("BANK_ORDER_ID <>", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdGreaterThan(String value) {
            addCriterion("BANK_ORDER_ID >", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ORDER_ID >=", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdLessThan(String value) {
            addCriterion("BANK_ORDER_ID <", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdLessThanOrEqualTo(String value) {
            addCriterion("BANK_ORDER_ID <=", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdLike(String value) {
            addCriterion("BANK_ORDER_ID like", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdNotLike(String value) {
            addCriterion("BANK_ORDER_ID not like", value, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdIn(List<String> values) {
            addCriterion("BANK_ORDER_ID in", values, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdNotIn(List<String> values) {
            addCriterion("BANK_ORDER_ID not in", values, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdBetween(String value1, String value2) {
            addCriterion("BANK_ORDER_ID between", value1, value2, "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdNotBetween(String value1, String value2) {
            addCriterion("BANK_ORDER_ID not between", value1, value2, "bankOrderId");
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

        public Criteria andTrxTypeIsNull() {
            addCriterion("TRX_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTrxTypeIsNotNull() {
            addCriterion("TRX_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTrxTypeEqualTo(TrxType value) {
            addCriterion("TRX_TYPE =", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeNotEqualTo(TrxType value) {
            addCriterion("TRX_TYPE <>", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeGreaterThan(TrxType value) {
            addCriterion("TRX_TYPE >", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeGreaterThanOrEqualTo(TrxType value) {
            addCriterion("TRX_TYPE >=", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeLessThan(TrxType value) {
            addCriterion("TRX_TYPE <", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeLessThanOrEqualTo(TrxType value) {
            addCriterion("TRX_TYPE <=", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeLike(TrxType value) {
            addCriterion("TRX_TYPE like", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeNotLike(TrxType value) {
            addCriterion("TRX_TYPE not like", value, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeIn(List<TrxType> values) {
            addCriterion("TRX_TYPE in", values, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeNotIn(List<TrxType> values) {
            addCriterion("TRX_TYPE not in", values, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeBetween(TrxType value1, TrxType value2) {
            addCriterion("TRX_TYPE between", value1, value2, "trxType");
            return (Criteria) this;
        }

        public Criteria andTrxTypeNotBetween(TrxType value1, TrxType value2) {
            addCriterion("TRX_TYPE not between", value1, value2, "trxType");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("OPEN_ID is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("OPEN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("OPEN_ID =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("OPEN_ID <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("OPEN_ID >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_ID >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("OPEN_ID <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("OPEN_ID <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("OPEN_ID like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("OPEN_ID not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("OPEN_ID in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("OPEN_ID not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("OPEN_ID between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("OPEN_ID not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIsNull() {
            addCriterion("BANK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeIsNotNull() {
            addCriterion("BANK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeEqualTo(String value) {
            addCriterion("BANK_TYPE =", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotEqualTo(String value) {
            addCriterion("BANK_TYPE <>", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThan(String value) {
            addCriterion("BANK_TYPE >", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_TYPE >=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThan(String value) {
            addCriterion("BANK_TYPE <", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThanOrEqualTo(String value) {
            addCriterion("BANK_TYPE <=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLike(String value) {
            addCriterion("BANK_TYPE like", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotLike(String value) {
            addCriterion("BANK_TYPE not like", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeIn(List<String> values) {
            addCriterion("BANK_TYPE in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotIn(List<String> values) {
            addCriterion("BANK_TYPE not in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeBetween(String value1, String value2) {
            addCriterion("BANK_TYPE between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotBetween(String value1, String value2) {
            addCriterion("BANK_TYPE not between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("CARD_NO not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNull() {
            addCriterion("CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(CardType value) {
            addCriterion("CARD_TYPE =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(CardType value) {
            addCriterion("CARD_TYPE <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(CardType value) {
            addCriterion("CARD_TYPE >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(CardType value) {
            addCriterion("CARD_TYPE >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(CardType value) {
            addCriterion("CARD_TYPE <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(CardType value) {
            addCriterion("CARD_TYPE <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLike(CardType value) {
            addCriterion("CARD_TYPE like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotLike(CardType value) {
            addCriterion("CARD_TYPE not like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<CardType> values) {
            addCriterion("CARD_TYPE in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<CardType> values) {
            addCriterion("CARD_TYPE not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(CardType value1, CardType value2) {
            addCriterion("CARD_TYPE between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(CardType value1, CardType value2) {
            addCriterion("CARD_TYPE not between", value1, value2, "cardType");
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

        public Criteria andPayUrlLikeInsensitive(String value) {
            addCriterion("upper(PAY_URL) like", value.toUpperCase(), "payUrl");
            return (Criteria) this;
        }

        public Criteria andBackUrlLikeInsensitive(String value) {
            addCriterion("upper(BACK_URL) like", value.toUpperCase(), "backUrl");
            return (Criteria) this;
        }

        public Criteria andBankOrderIdLikeInsensitive(String value) {
            addCriterion("upper(BANK_ORDER_ID) like", value.toUpperCase(), "bankOrderId");
            return (Criteria) this;
        }

        public Criteria andYeepayOrderIdLikeInsensitive(String value) {
            addCriterion("upper(YEEPAY_ORDER_ID) like", value.toUpperCase(), "yeepayOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLikeInsensitive(String value) {
            addCriterion("upper(OUT_ORDER_ID) like", value.toUpperCase(), "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLikeInsensitive(String value) {
            addCriterion("upper(OPEN_ID) like", value.toUpperCase(), "openId");
            return (Criteria) this;
        }

        public Criteria andBankTypeLikeInsensitive(String value) {
            addCriterion("upper(BANK_TYPE) like", value.toUpperCase(), "bankType");
            return (Criteria) this;
        }

        public Criteria andBankNameLikeInsensitive(String value) {
            addCriterion("upper(BANK_NAME) like", value.toUpperCase(), "bankName");
            return (Criteria) this;
        }

        public Criteria andCardNoLikeInsensitive(String value) {
            addCriterion("upper(CARD_NO) like", value.toUpperCase(), "cardNo");
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