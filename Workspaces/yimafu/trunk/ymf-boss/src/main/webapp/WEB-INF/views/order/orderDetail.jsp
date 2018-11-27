<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<%@ taglib prefix="e" uri="/enumutils" %>
<%@ taglib prefix="dict" uri="/dicutils" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单详情</title>
    <script>
    </script>
</head>
<body>
    <div class="search_tit">
          <h2 class="fw">订单详情</h2>
     </div>
     <div class="clearer"></div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <table border="1" style="width:98%">
            <tr>
                <td colspan="4">
                    <label class="text_tit" style="font-size: 16px;">订单详情:</label>

                </td>
            </tr>
            <tr>
                <th width="24%">
                    <label class="text" style="font-size: 16px;">商户编号</label>
                </th>

                <td width="24%">
                    ${order.customerNumber}
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;" >商户名称</label>
                </td>
                <td width="24%">
                 ${customer.customerName}
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;" >商户订单号</label>
                </td>
                <td width="24%">
                     ${order.customerOrderId}
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;" >业务订单号</label>
                </td>
                <td width="24%">
                     ${order.externalId}
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;" >订单金额</label>
                </td>
                <td width="24%">
                     ${payment.trxAmt}
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">手续费</label>
                </td>
                <td width="24%">
                    ${payment.fee}
                </td>
            </tr>
            <tr>
                <th width="24%">
                    <label class="text" style="font-size: 16px;" >请求时间</label>
                </th>
                <td width="24%">
                        <fmt:formatDate value="${payment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;"  >支付时间</label>
                </td>
                <td width="24%">
                         <fmt:formatDate value="${payment.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">支付状态</label>
                </td>
                <td width="24%">
                        <dict:write type="PayStatus" code="${payment.payStatus}"/>
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">支付方式</label>
                </td>
                <td width="24%">
                         <dict:write type="PaySource" code="${payment.paySource}"/>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">银行卡名称</label>
                </td>
                <td width="24%">
                    <label class="text"> ${payment.bankName} </label>
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">银行卡类型</label>
                </td>
                <td width="24%">
                    <e:write type="com.yeepay.g3.facade.ymf.enumtype.common.CardType" name="${payment.cardType}"/>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">银行卡号</label>
                </td>
                <td width="24%">
                    <label class="text"> ${payment.cardNo} </label>
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">手机号</label>
                </td>
                <td width="24%">
                    <label class="text"> -- </label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">返回码</label>
                </td>
                <td width="24%">
                    <label class="text"> -- </label>
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">返回信息</label>
                </td>
                <td width="24%">
                    <label class="text"> -- </label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">商品标识</label>
                </td>
                <td width="24%">
                    <label class="text"> -- </label>
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">支付凭证码</label>
                </td>
                <td width="24%">
                    <label class="text"> ${order.payConfirm} </label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">支付版本</label>
                </td>
                <td width="24%">
                    <label class="text">  </label>
                    <e:write type="com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType" name="${order.businessType}"/>
                </td>
            </tr>

        </table>
    </div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <table border="1" style="width:98%">
            <tr>
                <td colspan="4">
                    <label class="text_tit" style="font-size: 16px;">退款详情</label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">退款状态</label>
                </td>
                <td width="24%">
                    <c:if test="${refund=='true'}">
                        已退款
                    </c:if>
                    <c:if test="${refund=='false'}">
                        未退款
                    </c:if>
                </td>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">可退金额</label>
                </td>
                <td width="24%">
                    ${main}
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text" style="font-size: 16px;">已退金额</label>
                </td>
                <td width="24%">
                    <label class="text">
                        <c:if test="${refund=='true'}">
                        ${payment.refundAmt}
                        </c:if>
                        <c:if test="${refund=='false'}">
                            --
                        </c:if> </label>
                </td>

            </tr>
        </table>
    </div>
</body>
</html>
