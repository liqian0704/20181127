<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>二维码信息变更</title>
    <script language="JavaScript">
        function getCustomerInfo() {
            var customerNumber = $('#customerNumber').val();
            if(null==customerNumber||''==customerNumber){
                alert("请填写商户编号!");
                return;
            }
            $.ajax({
                url: "getCustomerInfo",
                type: 'post',
                data: {"customerNumber": customerNumber},
                dataType:"json",
                success: function (msg) {
                    if(null==msg){
                       alert("商户信息未注册");
                    }else{
                        $('#customerName').text(msg.customerName);
                        if('INDUSTRY' == msg.appType){
                            $('#appType').text('行业版');
                        }else if('NORMAL' == msg.appType){
                            $('#appType').text('标准版');
                        }
                    }
                }
            });
        }
    </script>
</head>
<body>
    <div class="search_tit">
          <h2 class="fw">二维码信息变更</h2>
     </div>
     <div class="clearer"></div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <form action="updateQrCode" id="form" method="post">
            <input type="hidden" name="qrcodeId" value="${qrCode.id}"/>
            <table border="1" style="width:98%">
                <tr>
                    <td colspan="4">
                        <label class="text_tit" style="font-size: 16px;">基本信息</label>
                    </td>
                </tr>
                <tr>
                    <td width="14%">
                        <label class="text_tit" >商户编号</label>
                    </td>
                    <td width="24%"> <label class="text_tit">
                        <c:choose>
                            <c:when test="${null == customer}">
                                 <input type="text" maxlength="120" value="${customer.customerNumber}" class="input_text" id="customerNumber"  name="customerNumber"/>
                                <input type="button"  class="" onclick="getCustomerInfo()" value="同步商户信息" />
                            </c:when>
                             <c:otherwise>
                                 <input type="hidden" name="customerNumber" value="${customer.customerNumber}"/>
                                ${customer.customerNumber}
                             </c:otherwise>
                        </c:choose>
                    </label>
                    </td>
                    <td width="24%">
                        <label class="text_tit" >商户名称</label>
                    </td>
                    <td width="44%"><label class="text_tit" id="customerName">
                        ${customer.customerName}
                        </label>
                        </td>
                </tr>
                <tr>
                    <td width="14%">
                        <label class="text_tit" >应用类型</label>
                    </td>
                    <td width="14%"><label class="text_tit" id="appType">
                        <c:choose>
                            <c:when test="${'NORMAL' == customer.appType}">
                                标准版
                            </c:when>
                            <c:when test="${'INDUSTRY' == customer.appType}">
                                行业版
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </label>
                    </td>
                    <td width="24%">
                        <label  class="text_tit" >二维码ID</label>
                    </td>
                    <td width="44%">
                        <label class="text_tit" style="width: 35%;">${qrCode.qrId}</label>
                    </td>
                </tr>
                <tr>
                    <td width="14%">
                        <label class="text_tit" >网点名称</label>
                    </td>
                    <td width="14%"><label class="text_tit">
                        <input type="text" maxlength="120" class="input_text" value="${qrCode.shopname}" id="shopname" name="shopname"/>
                        </label>
                    </td>
                    <td width="14%">
                        <label class="text_tit" >二维码版本 </label>
                    </td>
                    <td width="14%"><label class="text_tit">
                        <select id="qrBusinessType" name="businessType"  onchange="" class="input_text">
                             <c:forEach var="busMap" items="${businessType}">
                                 <span>test ${busMap.key} </span>
                                 <option value="${busMap.key}" <c:if test="${busMap.key==qrCode.businessType}">selected="selected"</c:if> >${busMap.value}</option>
                             </c:forEach>
                        </select>
                        </label>
                    </td>
                </tr>
            </table>
            <div class="btn">
                <input type="submit"  class="btn_sure fw" id="queryid" value="修改" />
            </div>
        </form>

    </div>

</body>
</html>
