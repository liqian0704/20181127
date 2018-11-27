<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增微信公众号关系</title>
    <%@ include file="/WEB-INF/main/metas.jsp"%>
    <script>
        function add() {
            $("#form").ajaxSubmit({
                type: "post",
                url: "${pageContext.request.contextPath}/wechatRel/add",
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
<form id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">新增商户与微信公众号关系</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul>
                <li>
                    <p>
                        <label class="text_tit">商户编号：</label>
                        <input type="text" style="width: 300px" class="input_text" id="customerNumber"  name="customerNumber" value="${customerNumber}"/>
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">AppId：</label>
                        <input type="text" style="width: 300px" class="input_text" id="appId"  name="appId" value="" />
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">App密钥：</label>
                        <input type="text" style="width: 300px" class="input_text" id="appSecret"  name="appSecret" value="" />
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">状态：</label>
                        <dic:select type="Status" name="status" code="ACTIVE" id="status" clazz="input_text" />
                    </p>
                </li>
            </ul>

        </div>

        <div class="btn">
            <input type="button"  class="btn_sure fw" id="queryid"  onclick="add();" value="添加" />
            <input type="reset"  class="btn_sure fw" id="resetid" value="清空" />
        </div>
        <div class="clearer"></div>
        </div>
    </div>
</form>
</body>
</html>
