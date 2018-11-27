<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改微信公众号关系</title>
    <%@ include file="/WEB-INF/main/metas.jsp"%>
    <script>
        function modify() {
            $("#form").ajaxSubmit({
                type: "post",
                url: "${pageContext.request.contextPath}/wechatRel/modify",
                dataType: "json",
                success: function (json) {
                    if(json.status == "ok"){
                        alert("操作成功!");
                        window.location="${pageContext.request.contextPath}/wechatRel/query";
                    }else if(json.status == "error"){
                        alert(json.msg);
                    }else {
                        alert("操作失败!");
                    }
                }
            });
        }
    </script>
</head>
<body>
<form id="form" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${wechatRel.id}">
    <input type="hidden" name="customerNumber" value="${wechatRel.customerNumber}">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">修改微信公众号关系</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul>
                <li>
                    <p>
                        <label class="text_tit">商户编号：</label>
                        ${wechatRel.customerNumber}
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit" for="appId">AppId：</label>
                        <input type="text" style="width: 300px" class="input_text" id="appId"  name="appId" value="${wechatRel.appId}" />
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit" for="appSecret">App密钥：</label>
                        <input type="text" style="width: 300px" class="input_text" id="appSecret"  name="appSecret" value="${wechatRel.appSecret}" />

                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit" for="status">状态：</label>
                        <dic:select type="Status" code="${wechatRel.status}" name="status" clazz="input_text" />
                    </p>
                </li>
            </ul>
            <div class="btn">
                <input type="button" onclick="modify();" class="btn_sure fw" value="保存"/>
            </div>
            <div class="clearer"></div>
        </div>
    </div>
</form>
</body>
</html>
