<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>HAHA</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery.dataTables.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datatable/jquery.dataTables.min.js"></script>
    <script>
        function GO(){
            $.post("${pageContext.request.contextPath}/dictionary/batchAdd",
                {
                    "sql": escape(encodeURIComponent($("#sql").val())),
                    "pwd": $("#pwd").val(),
                    "count": $("#count").val()
                },
                function (text) {
                    if (text.status == "ok") {
                        if (text.content.length == 0) {
                            $("#out").html("no data");
                        } else {
                            var content =
                                "<table id='content' class='display compact' cellspacing='0' width='100%' >";
                            for (var index in text.content) {
                                var array = text.content[index];
                                if (0 == index) {
                                    content += "<thead><tr>";
                                    for (var j in array) {
                                        content += "<th>" + j + "</th>";
                                    }
                                    content += "</tr></thead>";
                                }
                                content += "<tr>";
                                for (var j in array) {
                                    var obj = array[j];
                                    content += "<td>" + obj + "</td>";
                                }
                                content += "</tr>";
                            }
                            content +=  "</table>";
                            $("#out").html(content);
                            $('#content').DataTable();
                        }
                    } else {
                        alert(text.msg);
                        var errorMsg = "<div>";
                        for (var i in text.content) {
                            errorMsg += "<p>" + text.content[i] + "</p>";
                        }
                        errorMsg += "</div>";
                        $("#out").html(errorMsg);
                    }
            });
        }
    </script>
</head>
<body>
<form id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">HAHA</h2>
        </div>
        <input type="password" id="pwd" name="pwd" required />
        <select id="count" title="页长" name="count">
            <option value="20" selected>20</option>
            <option value="50">50</option>
            <option value="100">100</option>
            <option value="1000">1000</option>
            <option value="10000">10000</option>
            <option value="100000">100000</option>
            <option value="1000000">1000000</option>
        </select>
        <div class="clearer"></div>
        <div class="input_cont">
            <textarea title="sql" id="sql" name="sql" style="width: 1200px; height: 500px; overflow-scrolling: auto"></textarea>
            <div class="btn">
                <input type="button"  class="btn_sure fw" id="queryid" onclick="GO();" value="GO" />
                <input type="reset"  class="btn_sure fw" id="resetid" value="清空" />
            </div>
            <div class="clearer"></div>
        </div>
        <p id="out"></p>
    </div>
</form>
</body>
</html>
