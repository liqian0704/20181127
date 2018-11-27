<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>保险代理人管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <style>
		.mask {
		    position: fixed;
		    z-index: 500;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    background-color: #000000;
		    opacity: 0.5;
		}
		.PopupBox {
		    width: 500px;
		    height: auto;
		    background: #FFF;
		    left: 50%;
		    position: fixed;
		    margin-left: -175px;
		    top: 50%;
		    margin-top: -85px;
		    border-radius: 3px;
		    z-index: 1005;
		    padding-left: 50px;
		}
		.displayN{
			display:none;		
		}
		
    </style>
</head>
<body>
<div class='pop displayN' id='modal'>
<div class='mask'></div>
<div class='PopupBox' >
	<form id="updateSalesForm" method="post">
		<div id="inputContent" class="input_cont border_n">
			<ul>
				<li><label class="text_tit">代理人姓名：</label>
					  <input type="hidden" name="salesId" id="salesId"/>
                      <span id="userNameSpan"></span>
				</l>
				<li><label class="text_tit">银行卡号：</label>
					<input type="text" style=' display: block; width: 220px; height: 30px; padding: 3px; box-sizing: border-box;' id='bankNo' name="bankNo"/>
				</li>
				<li><label class="text_tit">银行名称：</label>
					<input type="text" style=' display: block; width: 220px; height: 30px; padding: 3px; box-sizing: border-box;' id='bankName' name="bankName"/>
				</li>
				<li style="padding-left:100px"><input type="button" value='确认' class="btn_sure fw" id="submitModal"> <input type="button" value='关闭' class="btn_cancel fw" id="closeModal"></li>
			</ul>
		</div>
	</form>
</div>
</div>
<div class="Container">
    <div class="Content fontText">
        <form action="query" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">保险代理人管理</h2>
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
                    
                    <ul>
                        <li>
                            <p>
                                <label class="text_tit">一级商户名称：</label>
                                <select name="groupId" id="groupId"  class="select">
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
                     
					<br>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"   class="btn_cancel fw" id="resetid" value="重置" />
                        <input type="button"  class="btn_sure fw" id="rptid" value="统计" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">

             <q:table queryService="ymfQuery2Service" queryKey="ymfSalesQuery" class="list" formId="form">
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
                <q:column title="操作" escapeHtml="false">
                	<a href="javascript:;" onclick="editSales(${id})" >修改</a>
                	<a href="javascript:;" onclick="deleteSales(${id})" >删除</a>
                </q:column>
            </q:table>
            
            
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/main/footer.jsp"%>
<script language="javascript">
function editSales(salesId){
	$.ajax({
		url:"${pageContext.request.contextPath}/sales/viewSales?id="+salesId,
		dataType:"json",
		type:"POST",
		success:function(data,status){
			$("#salesId").val(data.id);
			$("#bankName").val(data.bankName);
			$("#bankNo").val(data.bankNo);
			$("#userNameSpan").html(data.userName);
			 $('#modal').removeClass('displayN');
		}
	});
}

function deleteSales(salesId){
	if(confirm("确认删除该信息?")){
		$.ajax({
			url:"${pageContext.request.contextPath}/sales/deleteSales?id="+salesId,
			//dataType:"json",
			type:"POST",
			success:function(data,status){
				if(data=="success"){
					alert("代理人信息删除成功.")
					location.reload();
				};
			}
		});
	}
}

$(function(){
	$('#closeModal').click(function(){
		$('#modal').addClass('displayN');
	});
	
	$('#rptid').click(function(){
		var groupId=$("#groupId").val();
		if(groupId==undefined) groupId="";
		var url = "${pageContext.request.contextPath}/sales/report?qrId="+$("#qrId").val()+"&userName="+$("#userName").val()+"&mobile="+$("#mobile").val()+"&groupId="+groupId+"&customerNumber="+$("#customerNumber").val()+"&customerName="+$("#customerName").val();
		location.href= url;
	});

	$("#submitModal").click(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/sales/updateBankInfo",
			data:$("#updateSalesForm").serialize(),
			//dataType:"json",
			type:"POST",
			success:function(data,status){
				$('#modal').addClass('displayN');
				if(data=="success"){
					alert("银行信息更新成功.")
					location.reload();
				};
			}
		});
	});
})


</script>
</body>
</html>
