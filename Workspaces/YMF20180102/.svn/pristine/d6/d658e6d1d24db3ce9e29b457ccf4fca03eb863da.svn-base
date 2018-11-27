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
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.8.3/extend/date/jquery-ui-timepicker-addon.min.js"></script>
    <!--中英文-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui-i18n.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/download.js"></script>
    <%--<script language="javascript" type="text/javascript" defer="defer" src="${pageContext.request.contextPath}/static/js/wdatePicker/WdatePicker.js"></script>--%>
    <script>
        //关闭自动时间设置
        var query_autoFillDate = false;
        //在页面加载时赋值
        $(function(){
            //注意：：日期组件的坑，name属性里带有“start”“from”字样默认日期是当前日期的前7天。。

            DatePickerExt.date("create_time1",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
            DatePickerExt.date("create_time2",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
            var today = todayStr();

            var bdate='<%=request.getParameter("create_time1")%>';;
            if(bdate!=null && bdate!='null'){
                document.getElementById("create_time1").value=bdate;
            } else {
                document.getElementById("create_time1").value=today;
            }
            var edate='<%=request.getParameter("create_time2")%>';
            if(edate!=null && edate!='null'){
                document.getElementById("create_time2").value=edate;
            }else{
                document.getElementById("create_time2").value=today;
            }
            var $paytime1 = $("#payTime1").datetimepicker({
                dateFormat: 'yy-mm-dd',
                showSecond: true,
                timeFormat: 'hh:mm:ss',
                stepHour: 1,
                stepMinute: 1,
                stepSecond: 1
            });
            var $paytime2 = $("#payTime2").datetimepicker({
                dateFormat: 'yy-mm-dd',
                showSecond: true,
                timeFormat: 'hh:mm:ss',
                stepHour: 1,
                stepMinute: 1,
                stepSecond: 1,
                hour: 23,
                minute: 59,
                second: 59
            });

            var payTime1 = '${param.payTime1}';
//            document.getElementById("payTime1").value = payTime1;
//            $paytime1.datetimepicker('setDate', (payTime1));
            $paytime1.Value = payTime1;

            var payTime2 = '${param.payTime2}';
//            document.getElementById("payTime2").value = payTime2;
//            $paytime2.datetimepicker('setDate', payTime2);
            $paytime2.Value = payTime2;

            loadBusinessCode();
        });


        /**获取当天日期:2017-05-27 */
        function todayStr() {
            var date = new Date();
            var dateStr = date.getFullYear() + "-";
            if ((date.getMonth() + 1 ) < 10) {
                dateStr += "0";
            }
            dateStr += (date.getMonth() + 1) + "-";
            if (date.getDate() < 10) {
                dateStr += "0";
            }
            dateStr += date.getDate();
            return dateStr;
        }

        /** 加载业务方*/
        function loadBusinessCode(){
            var bizCodeList = $("#business_id");
            var lastSel = '${param.business_id}';
            $.ajax({
                url: "${pageContext.request.contextPath}/business/loadBusinessCode",
                dataType: "json",
                success: function (json) {
                    if (json.length == 0) {
                        bizCodeList.empty();
                        bizCodeList.append('<option value="">无业务方，待配置</option>');
                    } else {
                        var selc = '';
                        var opt = '';
                        $.each(json, function (index, entry) {
                            opt = '<option value="' + entry['id'] + '">' + entry['bizName'] + '</option> ';
                            selc =selc + opt;
                        });
                    }
                    bizCodeList.append(selc);
                    bizCodeList.val(lastSel);
                }

            });
        }

        //查询订单详细
        function orderDetail(id){
            window.location="${pageContext.request.contextPath}/order/detail?id="+id;
        }

        //订单创建日期检查
        function  timecheck(){
            var timeStart=$("#create_time1").val();
            var timeEnd=$("#timeEnd").val();
            if(timeEnd!=""){
                if(timeStart==""){
                    alert("创建开始时间为空,请填写!");
                    document.getElementById("create_time1").focus();
                }
            }

            if(timeStart!="" && timeEnd==""){
                alert("创建结束时间为空,请填写!");
                document.getElementById("create_time2").focus();
            }
            if(timeStart!="" && timeEnd!=""){
                if (timeStart > timeEnd){
                    alert("请求结束时间不能小于开始时间!");
                    document.getElementById("create_time2").value="";
                    document.getElementById("create_time2").focus();
                }
            }
        }
        //支付时间检查
        function checkpay(){
            var payBegin=$("#payTime1").val();
            var payEnd=$("#payTime2").val();
            if(payEnd!=""){
                if(payBegin==""){
                    alert("支付开始时间为空,请填写!");
                    document.getElementById("payTime1").focus();
                }
            }

            if(payBegin!="" && payEnd==""){
                alert("支付结束时间为空,请填写!");
                document.getElementById("payTime2").focus();
            }
            if(payBegin!="" && payEnd!=""){
                if (payBegin > payEnd){
                    alert("请求结束时间不能小于开始时间!");
                    document.getElementById("payTime2").value="";
                    document.getElementById("payTime2").focus();
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
        //查询表单提交校验
        function checkSubmit(){
            var customerNumber=$("#customerNumber").val().trim();
            var customerName=$("#customerName").val().trim();
            var customerOrder=$("#customerOrder").val().trim();
            var businessOrder=$("#businessOrder").val().trim();
            var timeStart=$("#create_time1").val();
            var timeEnd=$("#create_time2").val();
            var payBegin=$("#payTime1").val();
            var payEnd=$("#payTime2").val();
            var payStatus=$("#payStatus").val();
            var payTypeInfo=$("#payTypeInfo").val();
            //var bankCardName=$("#bankCardName").val();
            var bankCardType=$("#bankCardType").val();
            var bankCardNo=$("#bankCardNo").val().trim();
//            var mobilePhone=$("#mobilePhone").val();
//            var refundStatus=$("#refundStatus").val();
            var balanceyType=$("#balanceyType").val().trim();
//            var tradeRemark=$("#tradeRemark").val().trim();
            var batchCusOrderNo=$("#batchCusOrderNo").val();
            var business_id=$("#business_id").val();
            var scan_code=$("#scan_code").val();
            var shop_name=$("#shop_name").val();
            var shopNumber=$("#shopNumber").val();
            var shopName=$("#shopName").val();
            if(customerOrder!="" || businessOrder!=""){
                return true;
            }
            if(batchCusOrderNo!=""){
                return true;
            }

            if((timeStart == "" || timeEnd == "") && ( payBegin == "" || payEnd == ""
                    )&& (customerNumber=="" || customerName =="" ||payStatus=="" || payTypeInfo==""
                 || bankCardType=="" || bankCardNo=="" ||balanceyType=="" || business_id == ""
                || scan_code == "" || shop_name == "" || shopNumber == "" || shopName == "") ){
                alert("请选择查询项加时间组合!");
                return false;
            }
            if(timeStart != "" && timeEnd != ""){
                if (timeStart > timeEnd){
                    alert("请求结束时间不能小于开始时间!");
                    return false;
                }

                var start = strToDate(timeStart);
                var end = strToDate(timeEnd);

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
                    if(customerNumber=="" && customerName=="" && payStatus=="" && payTypeInfo==""
                        && bankCardType=="" && bankCardNo=="" && business_id == ""
                            && balanceyType=="" && scan_code == "" && shop_name== "" && shopNumber == "" && shopName == ""){
                        alert("请选择查询项!");
                        return false;
                    }
                }

            }

            if(payBegin != "" && payEnd != ""){
                if (payBegin > payEnd){
                    alert("请求结束时间不能小于开始时间!");
                    return false;
                }

                var pstart = strToDate(payBegin);
                var pend = strToDate(payEnd);

                if(pstart>pend){
                    alert('开始时间不能大于结束时间');
                    return false;
                }
                var p = (pend.getTime()-pstart.getTime())/(31*24 * 60 * 60 * 1000);
                if(p>1){
                    alert("不能查询超过31天的订单！");
                    return false;
                }
                if(payBegin != payEnd){
                    if(customerNumber=="" && customerName=="" && payStatus=="" && payTypeInfo==""
                        && bankCardType=="" && bankCardNo=="" && balanceyType=="" && business_id == ""
                    && scan_code == "" && shop_name == "" && shopNumber == "" && shopName == ""){
                        alert("请选择查询项!");
                        return false;
                    }
                }

            }
            return true;
        }

        //日期串转换为date
        function strToDate(dateStr) {
            if (dateStr == null || dateStr == '') {
                return null;
            }
            var psy = dateStr.substring(0,4);
            var psm = dateStr.substring(5,7);
            var psd = dateStr.substring(8,10);
            return new Date(psy,psm,psd);
        }

        function downloadResult(){
            if(checkSubmit()){
                func_download({
                    formId : "form",
                    buttonId : "downid",
                    queryId : "orderQuery",
                    downloadType : "excel",
                    url : "down"
                });
            }
        }
        function downLoadTxtResult (){
            if(checkSubmit()){
                func_download({
                    formId : "form",
                    buttonId : "downtxtid",
                    queryId : "orderQuery",
                    downloadType : "txt",
                    url : "down"
                });
            }
        }
       function queryNotifyRecord(){
           window.location.href="${pageContext.request.contextPath}/notifyRecord/query"
       }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
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
                                <input type="text" name="create_time1" class="input_text" id="create_time1" req="true"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <%--<input class="input_text" id="timeEnd" type="text" name="timeEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="create_time2" class="input_text" id="create_time2" req="true" onchange="timecheck();"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">支付时间：</label>
                                <%--<input class="input_text" id="payBegin" type="text" name="payBegin" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="payTime1" class="input_text" id="payTime1" value="${param.payTime1}" req="false"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <%--<input class="input_text" id="payEnd" type="text" name="payEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="payTime2" class="input_text" id="payTime2" req="false" value="${param.payTime2}" onchange="checkpay();"/>
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
                                <label class="text_tit">业务方：</label>
                                <select class="input_text" id="business_id" name="business_id" value="${param.business_id}">
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
                                <label class="text_tit">结算类型：</label>
                                <select id="balanceyType" name="balanceyType" value="${param.balanceyType}" class="input_text">
                                    <option value="">请选择</option>
                                    <option value="T1">T1</option>
                                    <option value="S0">S0</option>
                                </select>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">二维码ID：</label>
                                <input type="text" class="input_text" id="scan_code"  name="scan_code" value="${param.scan_code}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">标识网点名称：</label>
                                <input type="text" class="input_text" id="shop_name"  name="shop_name" value="${param.shop_name}"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">订单金额：</label>
                                <input type="number" class="input_text" id="trx_amt_begin"  name="trx_amt_begin" value="${param.trx_amt_begin}"/>
                                -
                            </p>
                        </li>
                        <li>
                            <p>
                                <input type="number" class="input_text" id="trx_amt_end"  name="trx_amt_end" value="${param.trx_amt_end}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点编号：</label>
                                <input type="text" class="input_text" id="shopNumber"  name="shopNumber" value="${param.shopNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点名称：</label>
                                <input type="text" class="input_text" id="shopName" name="shopName" value="${param.shopName}"/>
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
                        <input type="button"  class="btn_sure fw" id="notifyRecord" onclick="queryNotifyRecord()" value="异常通知查询" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfDownloadQueryService" queryKey="orderQuery" class="list" formId="form" doSum="false">
                    <q:summary queryService="ymfDownloadQueryService" queryKey="orderQueryCount">
                        总笔数：${sum_externalid}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总金额：${sum_amount}
                    </q:summary>
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${mid}" />
                    <q:column title="商户编号" value="${customernumber}" />
                    <q:column title="商户名称" value="${customername}" />
                    <q:column title="网点编号" value="${shop_number}" />
                    <q:column title="网点名称" value="${shop_name}" />
                    <q:column title="商户订单号" value="${cusorderid}" />
                    <q:column title="业务订单号" value="${externalid}" />
                    <q:column title="收银台单号/易宝单号" value="${yeepay_order_id}" />
                    <q:column title="标识网点名称" value="${shopname}"></q:column>
                    <q:column title="二维码ID" value="${scan_code}"></q:column>
                    <q:column title="交易系统" value="${trade_system}"></q:column>
                    <q:column title="结算类型" value="${balance_type}"></q:column>
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
                    <%--<q:column title="支付凭证码" value="${payconfirm}"/>--%>
                    <q:column title="支付方式">
                        <dict:write type="PaySource" code="${paymethod}"/>
                    </q:column>
                    <q:column title="银行卡类型">
                        <dict:write type="BankCardType" code="${cardtype}"/>
                    </q:column>
                    <q:column title="入账状态" value="${settle_status}" />
                    <q:column title="入账时间" value="${settle_time}" pattern="yyyy-MM-dd HH:mm:ss" />
                    <q:column title="操作" escapeHtml="false"  width = "130px">
                        <a href="javascript:orderDetail('${id}')">详情</a>
                        <a href="javascript:orderConfirm('${id}')">补单</a>
                        <c:if test="${(bustype=='ORDER_PAY' || bustype=='REQUIRE_PAY') && paystatus=='SUCCESS'}">
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
