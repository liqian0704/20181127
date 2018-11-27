<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>一级商户管理</title>
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
		.PopupBoxRefund {
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
		.input_cont .groupUl li{
			height: 20px;
		    line-height: 20px;
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		}
		#groupUl li:hover{
			cursor:pointer;
		}
		.groupIcon{
		    position: absolute;
		    top: 4px;
		    left: 300px;
		}
    </style>
</head>
<body>
<div class='pop displayN' id='modal'>
<div class='mask'></div>
<div id="bindCustomerDiv"  class='PopupBoxRefund' >
	<form id="bindCustomerForm" method="post">
		<div id="inputContent" class="input_cont border_n">
			<ul>
				<li id="providerCodeInput"><label class="text_tit">一级商户名称：</label>
					<!-- select name="group_id" class="select">
                        	<option value="">请选择</option>
                        	<c:forEach items="${groupList}" var="group">  
		                  	<option value="${group.id}">${group.groupName}</option>
		                  	</c:forEach>
                     </select -->
                      <input type="text" style=' display: block;    width: 220px;   height: 30px;   padding: 3px;  box-sizing: border-box;' id='groupSearch' name="groupSearch"/>
				</li>
				<li  id='groupUl' class='displayN' style='padding:0;margin-top:-5px;'><label class="text_tit">&nbsp;</label>
                      <ul style='float:left;border: 1px solid #b3b3b3; width: 220px;' class='groupUl'>
   							<c:forEach items="${groupList}" var="group">  
		                  	<li data-value="${group.id}">${group.groupName}</li>
		                  	</c:forEach>
                      </ul>
				</li>
				<li  style='position: relative;'><label class="text_tit">二级商户名称：</label>
					<input type="text" id="customerSearch" placeholder="商户名称查询搜索" style='display: block;width: 220px;height: 30px;padding: 3px; box-sizing: border-box;'/><!-- 搜索框 -->
					<i class='iconfont groupIcon'>&#xe610;</i>
				</li>
				<li  style='margin-top: -10px;'><label class="text_tit">&nbsp;&nbsp;</label>
					<select name="customerIds"  id="customerIds"  multiple="multiple" style='width: 220px;height: 200px;'>
                     </select>
                     <span style='padding-left: 100px;'>可通过拖动鼠标或按住CTRL/COMMADN键进行多选</span>
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
                    <h2 class="fw fleft f14">一级商户管理</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">一级商户名称：</label>
                                <select name="groupId" class="select">
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
                                <label class="text_tit">创建时间：</label>
                               <input type="text" name="from" class="input_text" id="from" readonly="true" value="${startDate}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
							    <input type="text" name="to" class="input_text" id="to" readonly="true" value="${endDate}"/>
                            </p>
                        </li>
                        
                         <li>
                            <p>
                                <label class="text_tit">关联状态：</label>
                                <select name="related" class="select">
                                	<option value="">全部</option>
                                	<option value="1">已关联</option>
                                	<option value="2">未关联</option>
                                </select>
                            </p>
                        </li>
                    </ul>
                    
					<br>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"   class="btn_cancel fw" id="resetid" value="重置" />
                        <input type="button" class="btn_sure fw" value="新增关联" id="bindCustomers"/>
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">

             <q:table queryService="ymfQuery2Service" queryKey="ymfGroupQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="二级商户编号" value="${customer_number}" />
                <q:column title="二级商户名称" value="${customer_name}" />
                <q:column title="一级商户名称" value="${group_name}" />
                <q:column title="操作" escapeHtml="false">
                	<c:choose>  
                		<c:when test="${id!=null}"><a href="javascript:;" onclick="unBind(${group_id},${id})" >核销</a></c:when>
                		<c:otherwise><a href="javascript:;" onclick="bindCustomer('${group_name}')" >新增关联</a></c:otherwise>
                	</c:choose>
                </q:column>
            </q:table>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/main/footer.jsp"%>
<script language="javascript">
function bindCustomer(group_name){
    $('#modal').removeClass('displayN');
    if(typeof(group_name)!="undefined"&&group_name!=null){
    	$("#groupSearch").val(group_name);
    }
}

$(function(){
	DatePickerExt.date("from",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
    DatePickerExt.date("to",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
    
	$("#bindCustomers").click(function(){
		bindCustomer("");
	});
	
	$('#closeModal').click(function(){
		$('#modal').addClass('displayN');
	});
	$('#groupSearch').focus(function(){
		 $('#groupUl').removeClass('displayN');
	});
	 
	$('#groupUl li').live('click',function(){
		$('#groupSearch').val($(this).html());	
		$('#groupUl').addClass('displayN');
	});
	$("#customerSearch").keyup(function(){
		findCustomer($("#customerSearch").val());
	});
	findCustomer("");
	
	$("#submitModal").click(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/group/bindCustomers",
			data:$("#bindCustomerForm").serialize(),
			//dataType:"json",
			type:"POST",
			success:function(data,status){
				alert(data);
				$('#modal').addClass('displayN');
				location.reload();
			}
		});
	});
})

function findCustomer(customerName){
	$.ajax({
		url:"${pageContext.request.contextPath}/group/customers",
		data:{
			customerName:$("#customerSearch").val()
        },
		dataType:"json",
		type:"POST",
		success:function(data,status){
			$("#customerIds").empty();
			$.each(data,function(index,obj){
				$("#customerIds").append("<option value='"+obj.id+"'>"+obj.customerName+"</option>");
			})
		}
	});
}

function unBind(groupId,customerId){
	if(confirm("确认核销?")){
		$.ajax({
			url:"${pageContext.request.contextPath}/group/unBind",
			data:{
				groupId:groupId,
				customerId:customerId
	        },
			//dataType:"json",
			type:"POST",
			success:function(data,status){
				if(data=='success'){
					alert("商户核销成功.");
					location.reload();
				}else{
					alert("核销失败，请稍后再试");
				}
			}
		});
	}
}

</script>
</body>
</html>
