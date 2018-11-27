<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>保险代理人交易统计</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/download.js"></script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="report" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">代理人交易统计</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                	  <ul  class="fix">
                        <li>
                            <p>
                                <label class="text_tit">二维码ID</label>
                                <input type="text" name="qrId" class="input_text" id="qrId"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">代理人姓名</label>
							    <input type="text" name="userName" class="input_text" id="userName"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">代理人手机号</label>
							    <input type="text" name="mobile" class="input_text" id="mobile"/>
                            </p>
                        </li>
                    </ul>
                    
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">一级商户名称：</label>
                                <select name="groupId" class="select" id="groupId">
                                	<option value="">全部</option>
                                	<c:forEach items="${groupList}" var="group">  
		                        	<option value="${group.id}">${group.groupName}</option>
		                        	</c:forEach>
                                </select>
                            </p>
                        </li>
                         <li>
                            <p>
                                <label class="text_tit">二级商户编号：</label>
                                <input type="text" class="input_text" id="customerNumber" name="customerNumber"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">二级商户名称：</label>
                                <input type="text" class="input_text" id="customerName" name="customerName" />
                            </p>
                        </li>
                       </ul>
                     	
                     	<ul>
                        <li>
                            <p>
                                <label class="text_tit">开始时间：</label>
                               <input type="text" name="from" class="input_text" id="from" readonly="true" value="${startDate}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">结束时间：</label>
							    <input type="text" name="to" class="input_text" id="to" readonly="true" value="${endDate}"/>
                            </p>
                        </li>
                        </ul>
					<br>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"   class="btn_cancel fw" id="resetid" value="重置" />
                        <input type="button"   class="btn_cancel fw" id="downloadid" value="下载excel" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">

             <q:table queryService="ymfQuery2Service" queryKey="ymfSalesReportQuery" class="list" formId="form">
             	<q:summary queryService="ymfQuery2Service" queryKey="ymfSalesReportSummaryQuery">
                        总笔数：${sum_cnt}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总金额：${sum_amt}元
                 </q:summary>
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="一级商户名称" value="${group_name}" />
                <q:column title="二级商户编号" value="${customer_number}" />
                <q:column title="二级商户名称" value="${customer_name}" />
                <q:column title="代理人姓名" value="${user_name}" />
                <q:column title="二维码ID" value="${qr_id}" />
                <q:column title="代理人手机号" value="${mobile}" />
                <q:column title="卡号" value="${bank_no}" />
                <q:column title="银行" value="${bank_name}" />
                <q:column title="笔数" value="${cnt}" />
                <q:column title="金额/元" value="${amt}" />
            </q:table>
            
            
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/main/footer.jsp"%>
<script language="javascript">

$(function(){
	DatePickerExt.date("from",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
    DatePickerExt.date("to",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
    $("#downloadid").click(function(){
    	download();
    });
})

function download() {
	var url = "${pageContext.request.contextPath}/sales/download?qrId="+$("#qrId").val()+"&userName="+$("#userName").val()+"&mobile="+$("#mobile").val()+"&groupId="+$("#groupId").val()+"&customerNumber="+$("#customerNumber").val()+"&customerName="+$("#customerName").val()+"&from="+$("#from").val()+"&to="+$("#to").val();
	$("#ifile")[0].src = url;
}

</script>
<iframe id="ifile" style="display: none"></iframe>
</body>
</html>
