<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单查询</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout.css"/>
    <script src="${pageContext.request.contextPath}/static/js/layout.js"></script>


    <link rel="stylesheet" type="text/css" href="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/css/jquery-common-dist.css">
    <!-- 1.9.x 以上版本的兼容组件 -->
    <!--时间格式-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery.min.js"></script>
    <!--时间插件-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui.min.js"></script>
    <!--中英文-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui-i18n.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script>
        window.onload=function  setValue()//在页面加载时赋值
        {
            var bdate='<%=request.getParameter("timeStart")%>';
            if(bdate!=null && bdate!='null'){
                document.getElementById("timeStart").value=bdate;
            }else{
                date = new Date();
                var dateStr = date.getFullYear() + "-";
                if ((date.getMonth() +1 )< 10) {
                    dateStr += "0";
                }
                dateStr += (date.getMonth() + 1) + "-";
                if (date.getDate() < 10) {
                    dateStr += "0";
                }
                dateStr += date.getDate();
                document.getElementById("timeStart").value=dateStr;
            }
//            var edate=$("#endtime").val();
            var edate='<%=request.getParameter("timeEnd")%>';
            if(edate!=null && edate!='null'){
                document.getElementById("timeEnd").value=edate;
            }else{
                date = new Date();
                var dateStr = date.getFullYear() + "-";
                if ((date.getMonth() +1 ) < 10) {
                    dateStr += "0";
                }
                dateStr += (date.getMonth() + 1) + "-";
                if (date.getDate() < 10) {
                    dateStr += "0";
                }
                dateStr += date.getDate();
                document.getElementById("timeEnd").value=dateStr;
            }
            var paystart='<%=request.getParameter("payStart")%>';
            if(paystart!=null && paystart!='null'){
                document.getElementById("payStart").value=paystart;
            }else{
                document.getElementById("payStart").value="";
            }
            var payend='<%=request.getParameter("payEnd")%>';
            if(payend!=null && payend!='null'){
                document.getElementById("payEnd").value=payend;
            }else{
                document.getElementById("payEnd").value="";
            }


        }
    </script>
    <script>

        <%--$().ready(function() {--%>
        <%--loadSelectNameMessage("PayType","payTypeInfo");--%>
        <%--})--%>
        <%--function loadSelectNameMessage(type,id){--%>
        <%--var selectList =$("#"+id+"");--%>
        <%--$.ajax({--%>
        <%--url: "${pageContext.request.contextPath}/dictionary/getDictionariesByType",--%>
        <%--data: {"type": type},--%>
        <%--dataType: "json",--%>
        <%--success: function (json) {--%>
        <%--if (json.length == 0) {--%>
        <%--selectList.empty();--%>
        <%--selectList.append('<option value="">待配置</option>');--%>
        <%--} else {--%>
        <%--var selc = '';--%>
        <%--var opt = '';--%>
        <%--$.each(json, function (index, entry) {--%>
        <%--opt = '<option value="' + entry['name'] + '">' + entry['name'] + '</option> ';--%>
        <%--selc =selc + opt;--%>
        <%--});--%>
        <%--}--%>
        <%--$("#"+id+"").append(selc);--%>
        <%--}--%>

        <%--});--%>
        <%--}--%>

        function orderDetail(id){
            window.location="${pageContext.request.contextPath}/order/detail?id="+id;
        }
        function  timecheck(){
            var timeStart=$("#timeStart").val();
            var timeEnd=$("#timeEnd").val();
            if(timeEnd!=""){
                if(timeStart==""){
                    alert("创建开始时间为空,请填写!");
                    document.getElementById("timeStart").focus();
                }
            }

            if(timeStart!="" && timeEnd==""){
                alert("创建结束时间为空,请填写!");
                document.getElementById("timeEnd").focus();
            }
            if(timeStart!="" && timeEnd!=""){
                if (timeStart > timeEnd){
                    alert("请求结束时间不能小于开始时间!");
                    document.getElementById("timeEnd").value="";
                    document.getElementById("timeEnd").focus();
                }
            }
        }
        function checkpay(){
            var payStart=$("#payStart").val();
            var payEnd=$("#payEnd").val();
            if(payEnd!=""){
                if(payStart==""){
                    alert("支付开始时间为空,请填写!");
                    document.getElementById("payStart").focus();
                }
            }

            if(payStart!="" && payEnd==""){
                alert("支付结束时间为空,请填写!");
                document.getElementById("payEnd").focus();
            }
            if(payStart!="" && payEnd!=""){
                if (payStart > payEnd){
                    alert("请求结束时间不能小于开始时间!");
                    document.getElementById("payEnd").value="";
                    document.getElementById("payEnd").focus();
                }
            }
        }
        /**订单手工确认 */
        function orderConfirm(id){
            $.ajax({
                url: "${pageContext.request.contextPath}/order/orderConfirm",
                data: "id="+id,
                dataType: "json",
                success: function (json) {
                    if (json.status=='ok') {
                        alert("2121112");
                    } else if(json.status=='error'){
                        alert(json.msg);
                    }else{
                        alert("操作失败!");
                    }

                }

            });
        }
        /**
         * 行业版交易凭证下载
         * @param orderId
         */
        function confirm(orderId){
            window.location="${pageContext.request.contextPath}/download/downloadProof?orderId="+orderId;
        }
        function verification(){
            if(checkSubmit()){
                $("#form").attr("action","query");
                $("#form").submit();
                $("#form").attr("action","");
            }
        }
        function checkSubmit(){
            var customerNumber=$("#customerNumber").val().trim();
            var customerName=$("#customerName").val().trim();
            var customerOrder=$("#customerOrder").val().trim();
            var businessOrder=$("#businessOrder").val().trim();
            var timeStart=$("#timeStart").val();
            var timeEnd=$("#timeEnd").val();
            var payStart=$("#payStart").val();
            var payEnd=$("#payEnd").val();
            var payStatus=$("#payStatus").val();
            var payTypeInfo=$("#payTypeInfo").val();
            var bankCardName=$("#bankCardName").val();
            var bankCardType=$("#bankCardType").val();
            var bankCardNo=$("#bankCardNo").val().trim();
//            var mobilePhone=$("#mobilePhone").val();
//            var refundStatus=$("#refundStatus").val();
            var payConfirm=$("#payConfirm").val().trim();
            var tradeRemark=$("#tradeRemark").val().trim();
            var batchCusOrderNo=$("#batchCusOrderNo").val();
            if(customerOrder!="" || businessOrder!=""){
                return true;
            }
            if(batchCusOrderNo!=""){
                return true;
            }

            if((timeStart == "" || timeEnd == "") && ( payStart == "" || payEnd == ""
                    )&& (customerNumber=="" || customerName =="" ||payStatus=="" || payTypeInfo=="" || bankCardName=="" || bankCardType=="" || bankCardNo=="" ||payConfirm=="" || tradeRemark=="" ) ){
                alert("请选择查询项加时间组合!");
                return false;
            }
            if(timeStart != "" && timeEnd != ""){
                if (timeStart > timeEnd){
                    alert("请求结束时间不能小于开始时间!");
                    return false;
                }
                var sy = timeStart.substring(0,4);
                var sm = timeStart.substring(5,7);
                var sd = timeStart.substring(8,10);

                var ey = timeEnd.substring(0,4);
                var em = timeEnd.substring(5,7);
                var ed = timeEnd.substring(8,10);

                var start = new Date(sy,sm,sd);
                var end = new Date(ey,em,ed);

                if(start>end){
                    alert('开始时间不能大于结束时间');
                    return false;
                }
                var t = (end.getTime()-start.getTime())/(31*24 * 60 * 60 * 1000);
                if(t>1){
                    alert("不能查询超过31天的订单！");
                    return false;
                }
                if(timeStart!=timeEnd){
                    if(customerNumber=="" && customerName=="" && payStatus=="" && payTypeInfo=="" && bankCardName=="" && bankCardType=="" && bankCardNo==""
                            && payConfirm=="" && tradeRemark=="" ){
                        alert("请选择查询项!");
                        return false;
                    }
//                    if(customerNumber!="" || customerName!="" ||payStatus!="" || payTypeInfo!="" || bankCardName!="" || bankCardType!="" || bankCardNo!=""
//                             ||payConfirm!="" || tradeRemark!="" ){
//                        alert("请选择查询项!");
//                        return false;
//                    }
                }

            }
//            else{
//                alert("时间选项不完整!");
//                return false;
//            }

            if(payStart != "" && payEnd != ""){
                if (payStart > payEnd){
                    alert("请求结束时间不能小于开始时间!");
                    return false;
                }
                var psy = payStart.substring(0,4);
                var psm = payStart.substring(5,7);
                var psd = payStart.substring(8,10);

                var pey = payEnd.substring(0,4);
                var pem = payEnd.substring(5,7);
                var ped = payEnd.substring(8,10);

                var pstart = new Date(psy,psm,psd);
                var pend = new Date(pey,pem,ped);

                if(pstart>pend){
                    alert('开始时间不能大于结束时间');
                    return false;
                }
                var p = (pend.getTime()-pstart.getTime())/(31*24 * 60 * 60 * 1000);
                if(p>1){
                    alert("不能查询超过31天的订单！");
                    return false;
                }
                if(payStart != payEnd){
//                    if(customerNumber=="" || customerName=="" ||payStatus=="" || payTypeInfo=="" || bankCardName=="" || bankCardType=="" || bankCardNo==""
//                            ||payConfirm=="" || tradeRemark=="" ){
//                        alert("请选择查询项!");
//                        return false;
//                    }
                    if(customerNumber=="" && customerName=="" && payStatus=="" && payTypeInfo=="" && bankCardName=="" && bankCardType=="" && bankCardNo==""
                            && payConfirm=="" && tradeRemark=="" ){
                        alert("请选择查询项!");
                        return false;
                    }
                }

            }
//            else{
//                alert("时间选项不完整!");
//                return false;
//            }

            return true;
        }
        function downloadResult(){
            if(checkSubmit()){
                var button=$("#downid");
                button.attr("disabled",true);
                var form = $("#form");
                var isdown=$("#isdown");
                var downXml=$("#downXml");
                var downtype=$("#downtype");

                isdown.val("isDown")
                downtype.val("downExcel");
                downXml.val("download");
                form.attr("action","down")
                form.submit();
                isdown.val("");
                downtype.val("");
                downXml.val("");
                $("#form").attr("action","");
                button.attr("disabled",false);
            }
        }
        function downLoadTxtResult (){
            if(checkSubmit()){
                var button=$("#downtxtid");
                button.attr("disabled",true);
                var form = $("#form");
                var isdown=$("#isdown");
                var downtype=$("#downtype");
                var downXml=$("#downXml");
                isdown.val("isDown")
                downtype.val("downTxt");
                downXml.val("download");
                form.attr("action","down")
                form.submit();
                isdown.val("");
                downtype.val("");
                downXml.val("");
                $("#form").attr("action","");
                button.attr("disabled",false);
            }
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <input type="hidden"  id="isdown"  name="isDown" />
            <input type="hidden"  id="downtype"  name="downtype" />
            <input type="hidden"  id="downXml"  name="downXml" />
            <input type="hidden" id="queryKey" name="queryKey" value="orderQuery">

            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">订单查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">商户编号：</label>
                                <input type="text" class="input_text" id="customerNumber"  name="customerNumber" value="${param.customerNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">商户名称：</label>
                                <input type="text" class="input_text" id="customerName" name="customerName" value="${param.customerName}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">商户订单号：</label>
                                <input type="text" class="input_text" id="customerOrder"  name="customerOrder" value="${param.customerOrder}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">业务订单号：</label>
                                <input type="text" class="input_text" id="businessOrder" name="businessOrder" value="${param.businessOrder}"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">创建时间：</label>
                                <%--<input class="input_text" id="timeStart" type="text" name="timeStart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="timeStart" class="input_text" id="timeStart" req="true" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <%--<input class="input_text" id="timeEnd" type="text" name="timeEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="timeEnd" class="input_text" id="timeEnd" req="true"  onchange="timecheck();"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">支付时间：</label>
                                <%--<input class="input_text" id="payStart" type="text" name="payStart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="payStart" class="input_text" id="payStart" req="true" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <%--<input class="input_text" id="payEnd" type="text" name="payEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="payEnd" class="input_text" id="payEnd" req="true"  onchange="checkpay();"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">支付状态：</label>
                                <dic:select type="PayStatus" code="${param.payStatus}" id="payStatus" name="payStatus" clazz="select"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">支付方式：</label>
                                <dic:select type="PaySource" code="${param.payTypeInfo}" id="payTypeInfo" name="payTypeInfo" clazz="select"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">银行名称：</label>
                                <select class="input_text" id="bankCardName" name="bankCardName" value="${param.bankCardName}">
                                    <option value="">请选择</option>
                                </select>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">银行卡类型：</label>
                                <dic:select type="BankCardType" code="${param.bankCardType}" id="bankCardType" name="bankCardType" clazz="select"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">银行卡号：</label>
                                <input type="text" class="input_text" id="bankCardNo"  name="bankCardNo" value="${param.bankCardNo}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">支付凭证码：</label>
                                <input type="text" class="input_text" id="payConfirm"  name="payConfirm" value="${param.payConfirm}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">商品标识：</label>
                                <input type="text" class="input_text" id="tradeRemark"  name="tradeRemark" value="${param.tradeRemark}"/>
                            </p>
                        </li>
                    </ul>
                    <ul style="text-align: left;padding: 15px 8px;background: none repeat scroll 0 0 #fffced;height: 120px;">
                        <li style="height: 120px;width: 860px;">
                            <label class="text_tit">批量商户订单号:</label>
                            <textarea id="batchCusOrderNo" name="batchCusOrderNo" rows=5 cols="80" style="float: left;" placeholder="请输入业务订单号，用回车或英文逗点隔开">${batchCusOrderNo}</textarea>
                            <%--<span style="color:red"> 批量查询条数小于500条</span>--%>
                        </li>

                    </ul>
                    <br/>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" onclick="verification()" id="queryid" value="查询" />
                        <input class="btn_sure fw" type="reset" value="重置" />
                        <input type="button"  class="btn_sure fw" id="downid" onclick="downloadResult()" value="EXCEL下载" />
                        <input type="button"  class="btn_sure fw" id="downtxtid" onclick="downLoadTxtResult()" value="TXT下载" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymforderQueryService" queryKey="orderQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${mid}" />
                    <q:column title="商户编号" value="${customernumber}" />
                    <q:column title="商户名称" value="${customername}" />
                    <q:column title="商户订单号" value="${cusorderid}" />
                    <q:column title="业务订单号" value="${externalid}" />
                    <q:column title="商品标识">
                        --
                    </q:column>
                    <q:column title="订单金额" value="${amount}" />
                    <q:column title="请求时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="支付时间">
                        <fmt:formatDate value="${paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="支付状态">
                        <dict:write type="PayStatus" code="${paystatus}"/>
                    </q:column>
                    <q:column title="支付凭证码" value="${payconfirm}"/>
                    <q:column title="支付方式">
                        <dict:write type="PaySource" code="${paymethod}"/>
                    </q:column>
                    <q:column title="银行卡类型">
                        <dict:write type="BankCardType" code="${cardtype}"/>
                    </q:column>
                    <q:column title="返回码" value="--" />
                    <q:column title="返回信息" value="--" />
                    <q:column title="操作" escapeHtml="false"  width = "130px">
                        <a href="javascript:orderDetail('${id}')">详情</a>
                        <a href="javascript:orderConfirm('${id}')">补单</a>
                        <c:if test="${bustype=='ORDER_PAY' && paystatus=='SUCCESS'}">
                            <a href="javascript:confirm('${id}')">电子凭证</a>
                        </c:if>
                    </q:column>
                </q:table>
            </div>

        </div>
    </div>
</div>
</body>
</html>
