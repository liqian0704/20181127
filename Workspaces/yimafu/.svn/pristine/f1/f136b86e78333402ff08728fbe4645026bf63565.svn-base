<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<jsp:useBean id="customer" scope="request" type="com.yeepay.g3.core.ymf.entity.customer.Customer"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商户详情</title>
    <script>
    </script>
</head>
<body>
    <div class="search_tit">
          <h2 class="fw">商户信息详情</h2>
     </div>
     <div class="clearer"></div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <table border="1" style="width:98%">
            <tr>
                <td colspan="4">
                    <label class="text_tit" style="font-size: 16px;">基本信息</label>

                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >商户编号</label>
                </td>
                <td width="24%">
                   <label class="text_tit"> ${customer.customerNumber}</label>
                </td>
                <td width="24%">
                    <label class="text_tit" >商户名称</label>
                </td>
                <td width="24%">
                <label class="text_tit"> ${customer.customerName}</label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >所属行业</label>
                </td>
                <td width="24%">
                    <label class="text_tit">
                        <dic:write type="IndustryType" code="${customer.industryType}"/>
                    </label>
                </td>
                <td width="24%">
                    <label class="text_tit" >应用类型</label>
                </td>
                <td width="24%">
                    <label class="text_tit"> <dic:write type="AppType" code="${customer.appType}" /></label>

                </td>
                <%--<td width="24%">--%>
                    <%--<label class="text_tit" >商户等级</label>--%>
                <%--</td>--%>
                <%--<td width="24%">--%>
                <%--${customer.customerLevel.displayName}--%>
                   <%--<label class="text_tit"> <e:write type="customerLevel" name="${customer.customerLevel}" /></label>--%>
                    <%--<e:write type="com.yeepay.g3.facade.ymf.enumtype.CustomerLevel" name="${customer.customerLevel}" />--%>
                    <%--${e:displayOfEnum({'customerLevel', customer.customerLevel})}--%>
                    <%--${e:displayOfEnum({'com.yeepay.g3.facade.ymf.enumtype.CustomerLevel', customer.customerLevel})}--%>

                <%--</td>--%>
            </tr>
            <%--<tr>--%>
                <%--<td width="24%">--%>
                    <%--<label class="text_tit" >商户类型</label>--%>
                <%--</td>--%>
                <%--<td width="24%">--%>
                    <%--<label class="text_tit">--%>
                        <%--<dic:write type="CustomerType" code="${customer.customerType}"/>--%>
                <%--</label> </td>--%>
                <%--<td width="24%">--%>
                    <%--<label class="text_tit" >应用类型</label>--%>
                <%--</td>--%>
                <%--<td width="24%">--%>
                 <%--<label class="text_tit"> <dic:write type="AppType" code="${customer.appType}" /></label>--%>

                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td width="24%">
                    <label class="text_tit" >业务方</label>
                </td>
                <td width="24%">
                   <label class="text_tit"> ${business.bizName}</label>
                </td>
                <td width="24%"></td>
                <td width="24%"></td>
            </tr>
        </table>
    </div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <table border="1" style="width:98%">
            <tr>
                <td colspan="4">
                    <label class="text_tit" style="font-size: 16px;">支付配置</label>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="text_tit">支付方式</label>
                </td>
                <td colspan="3">
                    <label class="text_tit"> ${customer.payTypeInfo}</label>
                </td>

            </tr>
        </table>
    </div>
    <%--<div style="margin-left: 10px;margin-top: 20px;">--%>
        <%--<table border="1" style="width:98%">--%>
            <%--<tr>--%>
                <%--<td colspan="7">--%>
                    <%--<label class="text_tit" style="font-size: 16px;">风控配置</label>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="14%">--%>
                    <%--<label class="text_tit">限额类型</label>--%>
                <%--</td>--%>
                <%--<td width="14%">--%>

                    <%--<label class="text_tit">银行卡类型</label>--%>
                <%--</td>--%>
                <%--<td width="14%">--%>
                    <%--<label class="text_tit">单笔限额（元）</label>--%>
                <%--</td>--%>
                <%--<td width="14%">--%>
                    <%--<label class="text_tit">单日限额（元）</label>--%>
                <%--</td>--%>
                <%--<td width="14%">--%>
                    <%--<label class="text_tit">单月限额（元）</label>--%>
                <%--</td>--%>
                <%--<td width="14%">--%>
                    <%--<label class="text_tit">日累计次数（次）</label>--%>
                <%--</td>--%>
                <%--<td width="14%">--%>
                    <%--<label class="text_tit">月累计次数（次）</label>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>
</body>
</html>
