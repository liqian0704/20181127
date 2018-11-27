<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退款查询</title>
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
            var bdate='<%=request.getParameter("refundAppStart")%>';
            if(bdate!=null && bdate!='null' && bdate!=''){
                document.getElementById("timeStart").value=bdate;
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
                document.getElementById("timeStart").value=dateStr;
            }
            var edate='<%=request.getParameter("refundAppEnd")%>';
            if(edate!=null && edate!='null' && edate!=''){
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
            var startDate='<%=request.getParameter("refundSucstart")%>';
            if(startDate!=null && startDate!='null' && startDate!=''){
                document.getElementById("startDate").value=startDate;
            }else{
                document.getElementById("startDate").value="";
            }
            var endDate='<%=request.getParameter("refundSucEnd")%>';
            if(endDate!=null && endDate!='null' && endDate!=''){
                document.getElementById("endDate").value=endDate;
            }else{
                document.getElementById("endDate").value="";
            }
            var paystart='<%=request.getParameter("payStart")%>';
            if(paystart!=null && paystart!='null' && paystart!=''){
                document.getElementById("payStart").value=paystart;
            }else{
                document.getElementById("payStart").value="";
            }
            var payend='<%=request.getParameter("payEnd")%>';
            if(payend!=null && payend!='null' && payend!=''){
                document.getElementById("payEnd").value=payend;
            }else{
                document.getElementById("payEnd").value="";
            }
        }
    </script>
    <script>
        $().ready(function() {
            loadSelectNameMessage("PayType","payTypeInfo");
        })
        function loadSelectNameMessage(type,id){
            var selectList =$("#"+id+"");
            $.ajax({
                url: "${pageContext.request.contextPath}/dictionary/getDictionariesByType",
                data: {"type": type},
                dataType: "json",
                success: function (json) {
                    if (json.length == 0) {
                        selectList.empty();
                        selectList.append('<option value="">待配置</option>');
                    } else {
                        var selc = '';
                        var opt = '';
                        $.each(json, function (index, entry) {
                            opt = '<option value="' + entry['name'] + '">' + entry['name'] + '</option> ';
                            selc =selc + opt;
                        });
                    }
                    $("#"+id+"").append(selc);
                }

            });
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="refundQuery" id="form" method="get">
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
                                <label class="text_tit">退款请求号：</label>
                                <input type="text" class="input_text" id="refundNo"  name="refundNo" value="${param.refundNo}"/>
                            </p>
                        </li>

                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">原商户订单号：</label>
                                <input type="text" class="input_text" id="origCustomerOrderNo" name="origCustomerOrderNo" value="${param.origCustomerOrderNo}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">原交易流水号：</label>
                                <input type="text" class="input_text" id="origOrderNo"  name="origOrderNo" value="${param.origOrderNo}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">支付时间：</label>
                                <%--<input class="input_text" id="payStart" type="text" name="payStart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="payStart" class="input_text" id="payStart" req="true" readonly="readonly"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <%--<input class="input_text" id="payEnd" type="text" name="payEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="payEnd" class="input_text" id="payEnd" req="true" readonly="readonly"/>
                            </p>
                        </li>
                    </ul>
                    <br/>

                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">退款请求时间：</label>
                                <input type="text" name="refundAppStart" class="input_text" id="timeStart" req="true" readonly="readonly"/>
                                <%--<input class="input_text" id="refundAppStart" type="text" name="refundAppStart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <input type="text" name="refundAppEnd" class="input_text" id="timeEnd" req="true" readonly="readonly"/>
                                <%--<input class="input_text" id="refundAppEnd" type="text" name="refundAppEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">退款成功时间：</label>
                                <input type="text" name="refundSucstart" class="input_text" id="startDate" req="true" readonly="readonly"/>
                                <%--<input class="input_text" id="refundSucstart" type="text" name="refundSucstart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <%--<input class="input_text" id="refundSucEnd" type="text" name="refundSucEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="refundSucEnd" class="input_text" id="endDate" req="true" readonly="readonly"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">退款状态：</label>
                                <dic:select type="RefundStatus" code="${param.refundStatus}" id="refundStatus" name="refundStatus" clazz="select"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">支付方式：</label>
                                <dic:select type="PaySource" code="${param.payTypeInfo}" id="payTypeInfo" name="payTypeInfo" clazz="select"/>
                            </p>
                        </li>
                    </ul>

                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"  class="btn_cancel fw" id="resetid" value="重置" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">
            <q:table queryService="ymforderQueryService" queryKey="refundQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${row}" />
                <q:column title="商户编号" value="${customernumber}" />
                <q:column title="商户名称" value="${customername}" />
                <q:column title="退款请求号" value="${refundorderid}" />
                <q:column title="原商户订单号" value="${customerorderid}" />
                <q:column title="原业务订单号" value="${yeepayorderid}" />
                <q:column title="退款请求金额" value="${refundamount}" />
                <q:column title="已退金额" value="${refundamount}" />
                <q:column title="原支付金额" value="${trxamount}" />
                <q:column title="退款请求时间">
                    <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </q:column>
                <q:column title="退款成功时间">
                    <fmt:formatDate value="${refundtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </q:column>
                <q:column title="退款状态">
                    <dict:write type="RefundStatus" code="${refundstatus}"/>
                </q:column>
                <q:column title="支付方式">
                    <dict:write type="PaySource" code="${paysource}"/>
                </q:column>
                <q:column title="退款备注" value="${cause}"/>
            </q:table>
        </div>
    </div>
</div>
</body>
</html>
