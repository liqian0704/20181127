<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增业务方</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script>
       function addBusiness(){
           var options = {
               type: "post",
               url: "${pageContext.request.contextPath}/business/add",
               dataType: "text",
               success: function (text) {
                   var resp = JSON.parse(text);
                   if(resp.status=="ok"){
                       alert("操作成功!");
                       window.location="${pageContext.request.contextPath}/business/query";
                   }else if(resp.status=="error"){
                       alert(resp.msg);

                   }else{
                       alert("操作失败!");
                   }
               }
           };
           var form = $("#form");
           form.ajaxSubmit(options);
       }
    </script>
</head>
<body>
        <form id="form">
                    <div class="information">
                        <div class="search_tit">
                            <h2 class="fw">新增业务方</h2>
                        </div>
                        <div class="clearer"></div>
                        <div class="input_cont">
                            <ul >
                                <li>
                                    <p>
                                        <label class="text_tit">业务方：</label>
                                        <input type="text" class="input_text" id="bizName"  name="bizName" value="${param.bizName}"/>
                                    </p>
                                </li>
                            </ul>
                            <ul >
                                <li>
                                    <p>
                                        <label class="text_tit">业务方标识：</label>
                                        <input type="text" class="input_text" id="bizCode"  name="bizCode" value="${param.bizCode}"/>
                                        <span style="margin-left: 10px;">注：英文字母或数字</span>
                                    </p>
                                </li>
                            </ul>
                            <ul >
                                <li>
                                    <p>
                                        <label class="text_tit">状态：</label>
                                        <input type="radio" name="status" value="ACTIVE" checked="checked"/>&nbsp;&nbsp;开通
                                        <input type="radio" name="status" value="INACTIVE"/>&nbsp;&nbsp;关闭
                                    </p>
                                </li>
                            </ul>
                            <div class="btn">
                                <input type="button"  class="btn_sure fw" id="queryid" onclick="addBusiness();" value="添加" />
                                <input type="reset"  class="btn_sure fw" id="resetid" value="清空" />
                            </div>
                            <div class="clearer"></div>
                        </div>
                    </div>
        </form>
</body>
</html>
