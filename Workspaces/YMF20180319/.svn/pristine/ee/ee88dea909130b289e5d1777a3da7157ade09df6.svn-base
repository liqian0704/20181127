<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>易码付注册申请</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="format-detection" content="telephone=no">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layer.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.10.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ymfpublic.js"></script> 
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/validateRules.js"></script>

</head>
<body>
     <div class="registertitle">易码付注册申请</div>
        <form class="editform" id='form'  method="post">
                <div class="form-group">
                    <label class="" for="">公司名称：</label>
                    <div class="form-inlineblock">
                        <select name="groupId" id="groupId" data-rule="isNotNull" class="form-control-select">
                        	<option value="">请选择</option>
                        	<c:forEach items="${groupList}" var="group">  
                        	<option value="${group.id}">${group.groupName}</option>
                        	</c:forEach>
                        </select>
                        <p></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="" for="">分支机构名称：</label>
                    <div class="form-inlineblock">
                        <select name="customerId" id="customerId" data-rule="isNotNull" class="form-control-select">
                        	<option value="">请选择</option>
                        </select>
                         <p></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="" for="">姓名：</label>
                    <div class="form-inlineblock">
                        <input type="text" class="form-control" name="userName" id="userName" value="" placeholder=""  data-rule="name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="" for="">工号：</label>
                    <div class="form-inlineblock">
                        <input type="text" class="form-control" name="userNo" id="userNo" value="" placeholder=""/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="" for="">手机号：</label>
                    <div class="form-inlineblock">
                        <input type="text" class="form-control" name="mobile" id="mobile" value="" placeholder="" data-rule="tel"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="" for="">微信号：</label>
                    <div class="form-inlineblock">
                        <input type="text" class="form-control" name="wxName" id="wxName" value="" placeholder="" />
                    </div>
               </div>
                <div class="form-group">
                    <label class="" for="">邮箱：</label>
                    <div class="form-inlineblock">
                        <input type="text" class="form-control" name="email" id="email" value="" placeholder=""/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="" for="">银行卡号：</label>
                    <div class="form-inlineblock">
                        <input type="text" class="form-control" name="bankNo" id="bankNo" value="" placeholder=""  data-rule="isNotNull"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="" for="">银行名称：</label>
                    <div class="form-inlineblock">
                        <input type="text" class="form-control" name="bankName" id="bankName" value="" placeholder=""  data-rule="isNotNull"/>
                    </div>
                </div>
                 <div class="form-submit">
                      <input type="button" class="conquer" id="conquer" value="确认"/>
                 </div>
                 <span style="color:red" id="errorMsg"></span>
                 
        </form>
  <script>
  $(document).ready(function(){
	  
	  $("#groupId").change(function(){
		  if(this.value!=null&&this.value!=""){
			  $.ajax({
					url:"${pageContext.request.contextPath}/sales/customers/"+this.value,
					dataType:"json",
					success:function(data,status){
						$("#customerId").html("<option value=\"\">请选择</option>");
						$.each(data,function(index,obj){
							$("#customerId").append("<option value='"+obj.id+"'>"+obj.customerName+"</option>");
							//console.log(obj.id+"\t"+obj.customerName);
						})
					}
				});
		  }
	  });
	 $("#conquer").click(function(){
	              var validResult=validate('form', 'submit');
	              var selectGroup=false;
	              var selectCustomer=false;
	              if($('#groupId').val()==''){
	                  $('#groupId').parent().parent().addClass('has-error');
	                  $('#groupId').parent().find('p').html('请选择公司');
	              }else{
	                  $('#groupId').parent().parent().removeClass('has-error');
	                  $('#groupId').parent().find('p').html('');
	                  selectGroup=true;
	              }
	              
	              if($('#customerId').val()==''){
	                  $('#customerId').parent().parent().addClass('has-error');
	                  $('#customerId').parent().find('p').html('请选择分支机构');
	              }else{
	                  $('#customerId').parent().parent().removeClass('has-error');
	                  $('#customerId').parent().find('p').html('');
	                  selectCustomer=true;
	              }
	              if (!(validResult&&selectGroup&&selectCustomer)) {
	                event.preventDefault();
	                return;
	          	  }
	              
	              $.ajax({
	  				url:"${pageContext.request.contextPath}/sales/save",
	  				data:$('#form').serialize(),
	  				dataType:"json",
	  				type:"POST",
	  				beforeSend:function(){
	  					$("#conquer").val("提交中...");
	  				},
	  				success:function(data,status){
	  					if(data.result=="success"){
	  						location.href="${pageContext.request.contextPath}/sales/qrcode/"+data.message;
	  					}else{
	  						$("#conquer").val("确认");
	  						$("#errorMsg").html(data.message);
	  					}
	  				}
	  		});
        }
	  );
  });
  </script>
</body>
</html>