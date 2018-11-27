<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<jsp:useBean id="customer" class="com.yeepay.g3.core.ymf.entity.customer.Customer" scope="request" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout.css"/>
<script src="${pageContext.request.contextPath}/static/js/layout.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商户修改</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/customer/customer.js"></script>
    <script type="text/javascript">
        $("document").ready(function(){
            $("#industryBox").click(function () {
                if( $(this).attr("checked")){
                    $('#periodul').show();
                }else{
                    $('#periodul').hide();
                }
            });

        })
        $(function(){
            var appType = $('#getAppType').val();
            var appTypes = appType.split(',');
//            $.each(appTypes,function (index,value) {
//                alert(value);预留,如果以后扩展,使用此函数进行判断
//            })
            $('#periodul').hide();
            if(appTypes == 'INDUSTRY'){
                $('#periodul').show();
            }
            if(appTypes.length>1){
                $('.appType').attr('checked',true);
                $('#periodul').show();
            }
            var temps = $('#gratuityTmp').val().split(',');
            var elem='';
            var elems='';
            $.each(temps,function (index,value) {
                elem = '<input type="text"  class="selfInput" value="'+value+'"/>';
                elems += elem;
            })
            $('#manualSpan').append(elems);
        });

        //      加载默认打赏模板
        var reg = /^[0-9]+.?[0-9]*$/;
        function modifyCustomer(){
            var tempval = $('#gratuitTmp').val();
            if('undefined'!=tempval){
                var temp = "";
                $.each($('.selfInput'),function (index,entry) {
                    if(!reg.test($(entry).val())){
                        alert("打赏金额不知道填写数字吗?是不是傻!");
                        return;
                    }
                    temp =temp+","+$(entry).val();
                })
                temp = temp.substr(1);
                $('#gratuityTmp').val(temp);
            }
            var fileUpload = $("#fileUpload").val();
            var options="";
            if(fileUpload==null||fileUpload=="") {
             options = {
                type: "post",
                url: "${pageContext.request.contextPath}/customer/modify",
                dataType: "text",
                success: function (text) {
                    var resp = JSON.parse(text);
                    if(resp.status=='ok'){
                        alert("操作成功!");
                        window.location="${pageContext.request.contextPath}/customer/query";
                    }else if(resp.status=='error'){
                        alert(resp.msg);
                    }else{
                        alert("操作失败!");
                    }
                }
            };
            }else{
                options = {
                    type: "post",
                    url: "${pageContext.request.contextPath}/customer/modifyCustomerLogo",
                    dataType: "text",
                    success: function (data) {
                        var start = data.indexOf(">");//某个指定的字符串值在字符串中首次出现的位置
                        if(start != -1) {
                            var end = data.indexOf("<", start + 1);
                            if(end != -1) {
                                data = data.substring(start + 1, end);
                            }
                        }
                        var resp = JSON.parse(data);
                        if(resp.status=='ok'){
                            alert("操作成功!");
                            window.location="${pageContext.request.contextPath}/customer/query";
                        }else if(resp.status=='error'){
                            alert(resp.msg);
                        }else{
                            alert("操作失败!");
                        }
                    }
                };
            }
            var form = $("#form");
            form.ajaxSubmit(options);
        }
        function get2GCustomerName() {
            var customerNumber = $("#customerNumber").val();
            var customerName = $("#customerName").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/customer/get2GCustomer",
                data: {"customerNumber": customerNumber},
                dataType: "json",
                success: function (json) {
                    if(json==null||json.length==0){
                        alert("商编错误,二代不存在!");
                    }else{
                        if(customerName!=json.customerName) {
                            $("#customerName").attr("value", json.customerName);
                            document.getElementById("newCustomerName").innerHTML = json.customerName;
                        }else{
                            alert("商户名称未改变!");
                        }
                    }
                }

            });
        }
      function addTemp(){
          if($('.selfInput').length>3){
            alert('小伙,不要太贪心,4个赠送金额够用了!');
            return;
         }
          $('#manualSpan').append('<input type="text"  class="selfInput" />');
      }

    </script>
</head>
<body>
        <form id="form" enctype="multipart/form-data">
            <input type="hidden" name="id" id="id" value="${customer.id}"/>
            <input type="hidden" name="customerNumber" id="customerNumber" value="${customer.customerNumber}">
            <input type="hidden" name="customerName" id="customerName" value="${customer.customerName}">
            <input type="hidden" id="customerType" name="customerType" class="input_text" value="${customer.customerType}" />
                    <div class="information">
                        <div class="search_tit">
                            <h2 class="fw">商户修改</h2>
                        </div>
                        <div class="clearer"></div>
                        <div class="input_cont" >
                            <ul>
                                <li>
                                    <p>
                                        <label class="text_tit">商户编号：</label>
                                        ${customer.customerNumber}
                                    </p>
                                </li>
                            </ul>
                            <ul >
                                <li>
                                    <p>
                                        <label class="text_tit">商户名称：</label>
                                        <span id="newCustomerName" name="newCustomerName">${customer.customerName}</span>
                                        <input type="button"  class="btn_sure fw" style="margin-left: 20px;" id="modifyCustomerName" value="更新" onclick="get2GCustomerName();"/>
                                    </p>
                                </li>
                            </ul>
                            <ul >
                                <li>
                                    <p>
                                        <label class="text_tit">商户简称：</label>
                                        <input type="text" class="input_text" id="shortName"  name="shortName" value="${customer.shortName}"/>
                                    </p>
                                </li>
                            </ul>
                            <%--<ul>--%>
                                <%--<li>--%>
                                    <%--<p>--%>
                                        <%--<label class="text_tit">商户类型：</label>--%>
                                        <%--<dic:write type="CustomerType" code="${customer.customerType}"/>--%>
                                    <%--</p>--%>
                                <%--</li>--%>
                            <%--</ul>--%>
                            <ul >
                                <li>
                                    <p>
                                        <label class="text_tit">所属行业：</label>
                                        <dic:select type="IndustryType" code="${customer.industryType}" id="industryType" name="industryType" clazz="select" />
                                        <%--<checkbox:write type="IndustryType" code="${customer.industryType}" id="industryType" name="industryType" clazz="" />--%>
                                    </p>
                                </li>
                            </ul>
                            <%--<ul>--%>
                                <%--<li>--%>
                                    <%--<p>--%>
                                        <%--<label class="text_tit">商户等级：</label>--%>
                                        <%--<select class="select" id="customerLevel" name="customerLevel" >--%>
                                            <%--<option value="">请选择</option>--%>
                                            <%--<option value="A" <c:if test="${customer.customerLevel=='A'}">selected="selected"</c:if>>甲级</option>--%>
                                            <%--<option value="B" <c:if test="${customer.customerLevel=='B'}">selected="selected"</c:if>>乙级</option>--%>
                                            <%--<option value="C" <c:if test="${customer.customerLevel=='C'}">selected="selected"</c:if>>丙级</option>--%>
                                        <%--</select>--%>

                                    <%--</p>--%>
                                <%--</li>--%>
                            <%--</ul>--%>
                            <ul>
                                <li>
                                    <p>
                                        <label class="text_tit">业务方：</label>
                                        <select id="businessId" name="businessId" class="select">
                                            <option value="">请选择</option>
                                            <c:forEach var="h" items="${businessList}">
                                                <option value="${h.id}" <c:if test="${customer.businessId.equals(h.id)}">selected="selected"</c:if> >${h.bizName}</option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                </li>
                            </ul>
                            <ul class="fix" id="appType">
                                <li>
                                    <p>
                                        <label class="text_tit">应用类型：</label>
                                        <input type="checkbox" class="appType" name="appType" value="NORMAL" <c:if test="${customer.appType.equals('NORMAL')}">checked</c:if> />&nbsp;&nbsp;标准版
                                        <input type="checkbox" class="appType" name="appType" value="INDUSTRY" id="industryBox" <c:if test="${customer.appType.equals('INDUSTRY')}">checked</c:if> />&nbsp;&nbsp;行业版
                                        <input type="hidden" id="getAppType" value="${customer.appType}" />
                                    </p>
                                </li>
                                <c:if test="${gratuity != null}">
                                <li>
                                    <p>
                                        <label class="text_tit">营销版本：</label>打赏版
                                    </p>
                                </li>

                            </ul>
                            <ul id="manualUl">
                                <li>
                                    <label class="text_tit">打赏金额：</label>
                                    <span id="manualSpan">
                                    <input type="button" onclick="addTemp();" value="添加打赏"/>
                                    <input type="hidden" name="gratuityTmp" id="gratuityTmp" value="${gratuity.gratuityTemplate}" />
                                    </span>
                                </li>
                            </ul>
                            </c:if>
                            <ul class="fix">
                                <li>
                                    <p>
                                        <label class="text_tit">收款方式：</label>
                                        <c:forEach var="h" items="${collectTypeList}">
                                            <input type="checkbox"  name="collectTypeInfo" value="${h.code}" <c:if test="${customer.collectTypeInfo.contains(h.code)}">checked</c:if>/>&nbsp;&nbsp;${h.name}
                                        </c:forEach>
                                        <%--<checkbox:write type="PayType" code="${customer.industryType}" id="industryType" name="industryType" clazz="select" />--%>
                                    </p>
                                </li>

                            </ul>
                            <ul >
                                <li >
                                    <p>
                                        <label class="text_tit">支付方式：</label>
                                        <span >
                                        <c:forEach var="h" items="${payTypeList}" varStatus="stats">
                                            <c:if test="${stats.index>0 && stats.index%7==0}">
                                            <span id="${h.code}">
                                                    <input type="checkbox"  name="payTypeInfo" value="${h.code}"
                                                           <c:if test="${customer.payTypeInfo.contains(h.code)}">checked</c:if>
                                                    />&nbsp;&nbsp;${h.name}
                                            </span></br>
                                            </c:if>
                                            <c:if test="${stats.index==0 || stats.index%7!=0}">
                                            <span id="${h.code}">
                                                    <input type="checkbox"  name="payTypeInfo" value="${h.code}"
                                                           <c:if test="${customer.payTypeInfo.contains(h.code)}">checked</c:if>
                                                    />&nbsp;&nbsp;${h.name}
                                            </span>
                                            </c:if>
                                        </c:forEach>
                                        </span>
                                    </p>
                                </li>

                            </ul>
                            <ul>
                                <li>
                                    <p>
                                        <label class="text_tit">产品权限：</label>
                                        <input type="radio" name="status"  value="ACTIVE" <c:if test="${customer.status=='ACTIVE'}">checked</c:if> />&nbsp;&nbsp;开通&nbsp;&nbsp;
                                        <input type="radio" name="status"  value="INACTIVE" <c:if test="${customer.status=='INACTIVE'}">checked</c:if>/>&nbsp;&nbsp;关闭&nbsp;&nbsp;
                                    </p>
                                </li>
                            </ul>
                            <ul>
                                <li>
                                    <p>
                                        <label class="text_tit">结算类型：</label>
                                        <input type="radio" name="balanceProduct"  value="T1" <c:if test="${customer.balanceProduct=='T1'}">checked</c:if> />&nbsp;&nbsp;T1结算&nbsp;&nbsp;
                                        <input type="radio" name="balanceProduct"  value="S0" <c:if test="${customer.balanceProduct=='S0'}">checked</c:if>/>&nbsp;&nbsp;秒到&nbsp;&nbsp;
                                    </p>
                                </li>
                            </ul>
                            <div id="periodul">
                            <ul>
                                <li>
                                    <p>
                                        <label class="text_tit">订单有效期：</label>
                                        <input type="text" name="validityPeriod" id="validityPeriod" value="${customer.validityPeriod}"/>&nbsp;&nbsp;&nbsp;天
                                    </p>
                                </li>
                            </ul>
                            </div>
                            <ul>
                                <li>
                                    <p>
                                        <label class="text_tit">商户LOGO：</label>
                                        <c:if test="${customer.customerLogo!=null&&customer.customerLogo!=''}">
                                            <img src="${customer.customerLogo}">
                                        </c:if>
                                        <input type="file" id="fileUpload" name="fileUpload">
                                    </p>
                                </li>
                            </ul>
                            <div class="btn">
                                <input type="button" onclick="modifyCustomer();" class="btn_sure fw" id="queryid" value="保存" />
                            </div>
                            <div class="clearer"></div>
                        </div>
                    </div>
        </form>
</body>
</html>
