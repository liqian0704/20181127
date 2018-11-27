<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户开通</title>
    <style type="text/css">
        tr{
            width: 100%;
            height: 30px;
        }
        table{
            width: 98%;
            height: 100%;
        }
        th{
            text-align: left;
        }
        .th_left{
            width: 30%;
        }
        .th_right{

        }
    </style>
</head>
<form id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">用户开通</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont" style="background:#FFD39B">
            <div class="btn" height="200px">
                <input type="button"  class="btn_sure fw" id="button" onclick="download()" value="下载" />
                <input type="button" class="btn_sure fw" onclick="window_back();" value="返回"/>
            </div>
            <div id="message"></div>
            <div class="clearer"></div>
        </div>
    </div>
    <div style="margin: 0px auto;">
        <div>
        <table border="1px" st id="test">
            <tr>
                <td>序号</td>
                <td>注册手机号</td>
                <td>商户编号</td>
                <td>校验结果</td>
                <td>注册结果</td>
                <td>OP单号</td>
            </tr>
            <c:forEach items="${resultList}" var="list" varStatus="listStatus">
                <tr>
                    <td>${listStatus.index + 1}</td>
                    <c:forEach items="${list}" var="l">
                    <td>${l}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
    </form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
<script>

    function download(){
        window.location.href = "${pageContext.request.contextPath}/user/downFile";
    }

</script>
</body>
</html>
