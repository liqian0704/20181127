<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>批量同步</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
    <script>
        function sync() {
            var options = {
                type: "post",
                url: "${pageContext.request.contextPath}/material/batchSyncTerm",
                dataType: "json",
                success: function (resp) {
                    if (resp.status == "ok") {
                        alert("操作成功!");
                        window.location = "${pageContext.request.contextPath}/material/query";
                    } else if (resp.status == "error") {
                        alert(resp.msg);
                    } else {
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
            <h2 class="fw">批量同步</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul>
                <li>
                    <p>
                        <label class="text_tit">出入库单号：</label>
                        <input type="text" title="出入库单号" class="input_text" name="inAndOutNum"/>
                    </p>
                </li>
            </ul>
            <div class="btn">
                <input type="button" class="btn_sure fw" onclick="sync();" value="同步"/>
                <input type="button" class="btn_sure fw" onclick="window_back();" value="返回"/>
            </div>
            <div class="clearer"></div>
        </div>
    </div>
</form>
</body>
</html>
