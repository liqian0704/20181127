<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>数据字典修改</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script>
       function modify(){
           var options = {
               type: "post",
               url: "${pageContext.request.contextPath}/dictionary/modify",
               dataType: "text",
               success: function (text) {
                   var resp = JSON.parse(text);
                   if(resp.status=='ok'){
                       alert("操作成功!");
                       window.location="${pageContext.request.contextPath}/dictionary/query";
                   }else if(resp.status=='error'){
                       alert(resp.msg);
                   }else{
                       alert("操作失败!");
                   }
               }
           };
           var form = $("#form");
           form.ajaxSubmit(options);
       }
        function back() {
            window.history.back();
        }
    </script>
</head>
<body>
        <form id="form">
            <input type="hidden" name="id" id="id" value="${dict.id}"/>
            <div class="information">
                <div class="search_tit">
                    <h2 class="fw">数据字典修改</h2>
                </div>
                <div class="clearer"></div>
                <div class="input_cont" >
                    <ul >
                        <li>
                            <p>
                                <label class="text_tit">字典类型：</label>
                                <input type="text" class="input_text" id="type"  name="type" value="${dict.type}"/>
                            </p>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <p>
                                <label class="text_tit">字典编码：</label>
                                <input type="text" class="input_text" id="code"  name="code" value="${dict.code}"/>
                            </p>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <p>
                                <label class="text_tit">字典数值：</label>
                                <input type="text" class="input_text" id="value"  name="value" value="${dict.value}"/>
                            </p>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <p>
                                <label class="text_tit">字典名称：</label>
                                <input type="text" class="input_text" id="name"  name="name" value="${dict.name}"/>
                            </p>
                        </li>
                    </ul>
                    <ul >
                        <li>
                            <p>
                                <label class="text_tit">状态：</label>
                                <input type="radio" name="status" value="ACTIVE" <c:if test="${dict.status=='ACTIVE'}">checked="checked"</c:if>/>&nbsp;&nbsp;开通
                                <input type="radio" name="status" value="INACTIVE" <c:if test="${dict.status=='INACTIVE'}">checked="checked"</c:if>/>&nbsp;&nbsp;关闭
                            </p>
                        </li>
                    </ul>
                    <ul >
                        <li>
                            <p>
                                <label class="text_tit">是否能删除：</label>
                                <input type="radio" name="canDelete" value="1" <c:if test="${dict.canDelete}">checked="checked"</c:if>/>&nbsp;&nbsp;能
                                <input type="radio" name="canDelete" value="0" <c:if test="${!dict.canDelete}">checked="checked"</c:if>/>&nbsp;&nbsp;不能
                            </p>
                        </li>
                    </ul>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" id="queryid" onclick="modify();" value="保存" />
                        <input type="button"  class="btn_sure fw" onclick="back();" value="返回" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
</body>
</html>
