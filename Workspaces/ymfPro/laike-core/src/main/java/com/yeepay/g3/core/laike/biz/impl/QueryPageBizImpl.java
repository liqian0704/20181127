package com.yeepay.g3.core.laike.biz.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.common.Amount;
import com.yeepay.g3.common.laike.utils.Constants;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.QueryPageBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.utils.SecurityUtil;
import com.yeepay.g3.facade.alliance.enums.member.MerType;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.dto.alliance.AllianceRequest;
import com.yeepay.g3.facade.laike.dto.alliance.ShareStatisticsResponse;
import com.yeepay.g3.facade.laike.dto.alliance.StatisticsResponse;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerRequest;
import com.yeepay.g3.facade.laike.enumtype.*;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.query.QueryResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 分页查询
 * Author: jiawen.huang
 * Date: 2017/6/24
 * Time: 14:21
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class QueryPageBizImpl extends AbstractBiz implements QueryPageBiz {

	@Override
	public QueryListResponse queryMsg(QueryPushMsgRequest request) {
		Map<String, Object> param = Maps.newHashMap();
		checkParam(request);
		if (null != request.getType()) {
			param.put("type", request.getType().getValue());
		}
		return doQuery(request, param, "QUERY_PUSH_MSG_LIST");
	}

	@Override
	public QueryListResponse queryOrder(QueryOrderRequest request) {
		Map<String, Object> param = Maps.newHashMap();
		checkParam(request);
        AccountOpenEntity entity = accountOpenService.findByMemberNo(request.getMemberNo());
        QueryListResponse response = new QueryListResponse();
        if (null != entity && StringUtils.isNotEmpty(entity.getMerchantNo())) {
            param.put("merchantNo", entity.getMerchantNo());
            if (null != request.getOrderStatus()) {
                param.put("orderStatus", request.getOrderStatus().name());
            }
            if (null != request.getOrderType()) {
                param.put("orderType", request.getOrderType().name());
            }
            if (null != request.getPaySource()) {
                param.put("paySource", request.getPaySource().name());
            }
            if (StringUtils.isNotEmpty(request.getExternalId())) {
                param.put("externalId", request.getExternalId());
            }
            if (StringUtils.isNotEmpty(request.getShopNumber())) {
                param.put("shopNumber", request.getShopNumber());
            }
            if (StringUtils.isNotEmpty(request.getBalanceType())) {
                param.put("balanceType", request.getBalanceType());
            }
            if (StringUtils.isNotEmpty(request.getYeepayOrderId())) {
                param.put("yeepayOrderId", request.getYeepayOrderId());
            }
            response = doQuery(request, param, "QUERY_ORDER_LIST");
            if (response.getCount() > 0) {
                for (Map<String, Object> map : response.getList()) {
                    if (!"SUCCESS".equals(map.get("settlestatus"))) {
                        map.put("settleamount", null);
                        map.put("fee", null);
                        map.put("settletime", null);
                    }
                }
            }
        }
        return response;
    }

	@Override
	public QueryListResponse querySettle(QueryOrderRequest request) {
		Map<String, Object> param = Maps.newHashMap();
		request.setPageSize(Constants.QUERY_PAGE_DEFAULT_SIZE);
		checkParam(request);
		UserEntity userEntity = userService.findByMemberNo(request.getMemberNo());
		if (null == userEntity || StringUtils.isEmpty(userEntity.getMerchantNo())) {
			return new QueryListResponse();
		} else {
			param.put("merchantNo", userEntity.getMerchantNo());
			return doQuery(request, param, "QUERY_SETTLE_LIST");
		}
	}

	@Override
	public QueryListResponse querySettleAll(QueryOrderRequest request) {
		QueryListResponse response = new QueryListResponse();
		UserEntity userEntity = userService.findByMemberNo(request.getMemberNo());
		Map<String, Object> param = Maps.newHashMap();
		checkParam(request);
		if (null == userEntity || StringUtils.isEmpty(userEntity.getMerchantNo())) {
			return new QueryListResponse();
		} else {
			param.put("merchantNo", userEntity.getMerchantNo());
			QueryListResponse sumList = doQuery(request, param, "QUERY_ALL_SETTLE_SUM");
			if (sumList.getList().size() > 0) {
				BigDecimal t1Sum = null == sumList.getList().get(0) ? BigDecimal.ZERO :
						null == sumList.getList().get(0).get("1") ? BigDecimal.ZERO : (BigDecimal) sumList.getList().get(0).get("1");
				if (sumList.getCount() > 1) {
					BigDecimal s0Sum = null == sumList.getList().get(1) ? BigDecimal.ZERO :
							null == sumList.getList().get(1).get("1") ? BigDecimal.ZERO : (BigDecimal) sumList.getList().get(1).get("1");
					response.setSum(s0Sum.add(t1Sum));
				} else {
					response.setSum(t1Sum);
				}
			}
			QueryListResponse detailList = doQuery(request, param, "QUERY_ALL_SETTLE");
			response.setList(detailList.getList());
			response.setCount(detailList.getCount());
			return response;
		}
	}

	@Override
	public QueryListResponse queryYmfCustomer(RegisterMerRequest registerMerRequest) {
		Map<String, Object> param = Maps.newHashMap();
		Long bizNo = (Long) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_BIZ_NO);
		param.put("merchantNo", registerMerRequest.getMerchantNo());
		param.put("bizNo", bizNo);
		return doQuery(new QueryBaseRequest(), param, "QUERY_YMF_CUSTOMER");
	}

	@Override
	public QueryTodayResponse queryToday(QueryOrderRequest request) {
		try {
			UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
			AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
			Date date = new Date();
			Map<String, Object> param = Maps.newHashMap();
			param.put("merchantNo", accountOpenEntity.getMerchantNo());
			param.put("startDate", DateUtils.getDayStart(date));
			param.put("endDate", DateUtils.getDayEnd(date));
			if (StringUtils.isNotEmpty(accountOpenEntity.getMerchantNo())) {
				Map<String, Object> result = doQuery(new QueryBaseRequest(), param, "QUERY_ORDER_TODAY").getList().get(0);
				QueryTodayResponse response = new QueryTodayResponse();
                if ((CompanyTypeEnum.MICRO.equals(accountOpenEntity.getCompanyType()) && OpenStatusEnum.PAY_SUCCESS.equals(accountOpenEntity.getOpenStatus()))
                        || OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus())) {
                    Map<String, String> resultMap = accountManageService.getBalance(accountOpenEntity.getMerchantNo());
                    response.setAccountBalance(resultMap.get("availableBalance"));
                }
                response.setTotalCount((Integer) result.get("totalCount"));
				response.setTotalAmount(new Amount((BigDecimal) result.get("totalAmount")));
				response.setCurrentTime(date);
				return response;
			}
		} catch (LaikeSysException e) {
			//该接口不抛业务异常
		}
		return new QueryTodayResponse();
	}

	@Override
	public StatisticsResponse queryStatisticsAll(BaseRequest request) {
		StatisticsResponse response = new StatisticsResponse();
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		if (StringUtils.isBlank(userEntity.getMerchantNo())) {
			return response;
		}
		Map<String, Object> param = Maps.newHashMap();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date startDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = calendar.getTime();
		param.put("merchantNo", userEntity.getMerchantNo());
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		QueryListResponse memberResult = doQuery(new QueryBaseRequest(), param, "ALLIANCE_STATISTICS_COUNT");
		QueryListResponse monthResult = doQuery(new QueryBaseRequest(), param, "ALLIANCE_STATISTICS_MONTH");
		AccountOpenEntity accountOpenEntity = accountOpenService.findByMerchantNo(userEntity.getAgentNo());
		if (null == accountOpenEntity) {
			QueryListResponse lordResult = doQuery(new QueryBaseRequest(), param, "ALLIANCE_LORD");
			if (lordResult.getCount() > 0) {
				response.setLordName((String) lordResult.getList().get(0).get("lord"));
			}
		} else {
			UserEntity agent = userService.findByMemberNo(accountOpenEntity.getMemberNo());
			response.setLordTel(agent.getPhoneNo());
			response.setLordName(accountOpenEntity.getMerchantName());
		}
		if (memberResult.getCount() > 0) {
			response.setLolChildMerchant((Integer) memberResult.getList().get(0).get("count"));
			response.setChildMerchant((Integer) memberResult.getList().get(1).get("count"));
			response.setLolMerchant((Integer) memberResult.getList().get(2).get("count"));
		}
		if (monthResult.getCount() > 0) {
			for (Map<String, Object> map : monthResult.getList()) {
				if (map.get("type").equals(MerType.CHILD.name())) {
					response.setChildMonth((BigDecimal) map.get("amt"));
				} else {
					response.setLolMonth((BigDecimal) map.get("amt"));
				}
			}
		}
		return response;
	}

	@Override
	public QueryListResponse queryLolTradeDetail(AllianceRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
		BigDecimal sum = new BigDecimal(0);
		Map<String, Object> param = Maps.newHashMap();
		checkParam(request);
		param.put("merchantNo", accountOpenEntity.getMerchantNo());
		param.put("type", request.getAccountType().equals(AccountType.LK) ?
				MerType.CHILD : MerType.ALLY);
		QueryListResponse response = doQuery(request, param, "ALLIANCE_TRADE");
		if (response.getCount() > 0) {
			for (Map<String, Object> map : response.getList()) {
				if (null != map.get("phone")) {
					map.put("phone", SecurityUtil.decryptL2Info((String) map.get("phone")));
				}
				if (null != map.get("sum")) {
					sum = sum.add((BigDecimal) map.get("sum"));
				}
			}
		}
        response.setSum(sum);
        return response;
	}

	@Override
	public QueryListResponse queryAllianceDetail(AllianceRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
		Map<String, Object> param = Maps.newHashMap();
        //checkParam(request);
        param.put("appSource", request.getAccountType().equals(AccountType.LK) ?
				AppSourceEnum.LIKER.getValue() : AppSourceEnum.ALLIANCE.getValue());
		param.put("agentNo", accountOpenEntity.getMerchantNo());
		param.put("inviteCode", userEntity.getAllianceInviteCode());
		if (BoolEnum.TRUE.equals(request.getOpenStatus())) {
			param.put("statusOpen", "u.MERCHANT_NO <> ''");
		} else if (BoolEnum.FALSE.equals(request.getOpenStatus())) {
			param.put("statusOpen", "(u.MERCHANT_NO IS NULL OR u.MERCHANT_NO = '')");
		}
		QueryListResponse response = doQuery(request, param, "ALLIANCE_MEMBER_DETAIL");
		if (response.getCount() > 0) {
			for (Map<String, Object> map : response.getList()) {
				if (!map.get("name").equals("未实名认证")) {
					if (null != map.get("name")) {
						map.put("name", SecurityUtil.decryptL2Info((String) map.get("name")));
					}
				}
				map.put("phone", SecurityUtil.decryptL2Info((String) map.get("phone")));
			}
		}
		return response;
	}

	@Override
	public QueryListResponse queryAllianceMerchantCount(QueryBaseRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
        Integer sum = new Integer(0);
        Map<String, Object> param = Maps.newHashMap();
        //checkParam(request);
        param.put("merchantNo", accountOpenEntity.getMerchantNo());
		QueryListResponse response = doQuery(request, param, "ALLIANCE_MERCHANT_SUM");
		if (response.getCount() > 0) {
			for (Map<String, Object> map : response.getList()) {
				map.put("phone", SecurityUtil.decryptL2Info((String) map.get("phone")));
                if (null != map.get("childnum")) {
                    sum += (Integer) map.get("childnum");
                }
            }
		}
        response.setSum(new BigDecimal(sum));
        return response;
	}

	@Override
	public ShareStatisticsResponse queryAllianceShareAll(QueryBaseRequest request) {
		ShareStatisticsResponse response = new ShareStatisticsResponse();
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
		Map<String, Object> param = Maps.newHashMap();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date shareDateStart = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date shareDateEnd = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date preShareDateStart = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date preShareDateEnd = calendar.getTime();
		param.put("merchantNo", accountOpenEntity.getMerchantNo());
		param.put("shareDateStart", shareDateStart);
		param.put("shareDateEnd", shareDateEnd);
		param.put("preShareDateStart", preShareDateStart);
		param.put("preShareDateEnd", preShareDateEnd);
		QueryListResponse result = doQuery(request, param, "ALLIANCE_SHARE_SUM");
        if (result.getCount() > 0) {
            for (Map<String, Object> map : result.getList()) {
                if (map.get("type").equals("EXPAND")) {
                    response.setExpandAward((BigDecimal) map.get("share"));
                } else if (map.get("type").equals("SPREAD")) {
                    response.setSpreadAward((BigDecimal) map.get("share"));
                } else if (map.get("type").equals("ENCOURAGE")) {
                    response.setEncourageAward((BigDecimal) map.get("share"));
                } else if (map.get("type").equals("TEACHER")) {
                    response.setTeacherAward((BigDecimal) map.get("share"));
                } else if (map.get("type").equals("FRIEND")) {
                    response.setFriendAward((BigDecimal) map.get("share"));
                } else if (map.get("type").equals("NOW")) {
                    response.setCurrentMonth((BigDecimal) map.get("share"));
                } else if (map.get("type").equals("PRE")) {
                    response.setPreMonth((BigDecimal) map.get("share"));
                } else if (map.get("type").equals("ALL")) {
                    response.setTotalShare((BigDecimal) map.get("share"));
                }
            }
        }
        return response;
    }

	@Override
	public QueryListResponse queryAllianceShareDetail(QueryBaseRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
		Map<String, Object> param = Maps.newHashMap();
        checkParam(request);
        param.put("merchantNo", accountOpenEntity.getMerchantNo());
		return doQuery(request, param, "ALLIANCE_SHARE_DETAIL");
	}

    @Override
    public QueryListResponse queryShopName(QueryBaseRequest request) {
        Map<String, Object> param = Maps.newHashMap();
        checkParam(request);
        QueryListResponse result = doQuery(request, param, "QUERY_SHOP_LIST");
        long count = result.getCount();
        if (result.getCount() > 0) {
            for (int i = 0; i < result.getList().size(); i++) {
                if (result.getList().get(i).containsValue("默认网点")) {
                    result.getList().remove(i);
                    count--;
                }
            }
        }
        result.setCount(count);
        return result;
    }

	private void checkParam(QueryBaseRequest request) {
		request.setPageSize(request.getPageSize() > Constants.PAGE_MAX_SIZE ?
				Constants.PAGE_MAX_SIZE : request.getPageSize() <= Constants.MATH_ZERO ?
				Constants.PAGE_DEFAULT_SIZE : request.getPageSize());
		request.setPageIndex(request.getPageIndex() > Constants.PAGE_MAX_INDEX ?
				Constants.PAGE_MAX_INDEX : request.getPageIndex() <= Constants.MATH_ZERO ?
				Constants.PAGE_DEFAULT_INDEX : request.getPageIndex());
		try {
			request.setStartDate(null == request.getStartDate() ?
					DateUtils.getReqDate(new Date()) :
					DateUtils.parseDate(request.getStartDate(), DateUtils.DATE_FORMAT_DATEONLY).
							before(DateUtils.addDay(new Date(), -90)) ?
							DateUtils.getReqDate(DateUtils.addDay(new Date(), -90)) : request.getStartDate());
		} catch (ParseException e) {
			throw new LaikeSysException(ErrorCode.PARAM_EXCEPTION, e);
		}
		request.setEndDate(null == request.getEndDate() ?
				DateUtils.getReqDate(new Date()) : request.getEndDate());
	}

	private QueryListResponse doQuery(QueryBaseRequest request, Map<String, Object> param, String sqlKey) {
		if (StringUtils.isNotEmpty(request.getMemberNo())) {
			param.put("memberNo", request.getMemberNo());
		}
		if (StringUtils.isNotEmpty(request.getPhoneNo())) {
			param.put("phoneNo", request.getStartDate());
		}
		if (StringUtils.isNotEmpty(request.getStartDate())) {
			param.put("startDate", request.getStartDate());
		}
		if (StringUtils.isNotEmpty(request.getEndDate())) {
			param.put("endDate", request.getEndDate());
		}
		QueryResult queryResult = likerQueryService.query((request.getPageIndex() - 1) * request.getPageSize() + 1, request.getPageSize(),
				sqlKey, param, null, false, false);
		QueryListResponse response = new QueryListResponse();
		response.setCount(queryResult.getTotalCount());
		response.setList((List<Map<String, Object>>) queryResult.getData());
		return response;
	}
}
