<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银联终端号新增</title>
</head>
<body>
<form id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">银联终端号新增</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul >
                <li>
                    <p>
                        <label class="text_tit">商户号：</label>
                        <input type="text" class="input_text" id="customerNumber"  name="customerNumber" value="${param.customerNumber}" />
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">银联终端号：</label>
                        <input type="text" class="input_text" id="terminalNumber"  name="terminalNumber" value="${param.terminalNumber}" onchange="checkTerminalNumber()"/>
                    </p>
                </li>
            </ul>
            <div class="btn">
                <input type="button"  class="btn_sure fw" id="queryid" onclick="add();" value="新增" />
                <input type="reset"  class="btn_sure fw" id="resetid" value="清空" />
                <input type="button"  class="btn_sure fw" onclick="back();" value="返回" />
            </div>
            <div id="message"></div>
            <div class="clearer"></div>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<script>

    function add(){

        var customerNumber=$("#customerNumber").val();
        var terminalNumber=$("#terminalNumber").val();

        if(terminalNumber=="" || customerNumber==""){
            alert("信息不完整!");
            return false;
        }

        var options = {
            type: "post",
            url: "${pageContext.request.contextPath}/terminal/add",
            dataType: "json",
            success: function (resp) {
                if(resp.status == "ok"){
                    alert("操作成功!");
                    if(confirm("继续添加银联终端号?")){
                        window.location="${pageContext.request.contextPath}/terminal/toAdd";
                    }else{
                        window.location="${pageContext.request.contextPath}/terminal/query";
                    }
                }else {
                    alert(resp.msg);
                }
            }

        };
        var form = $("#form");
        form.ajaxSubmit(options);
    }
    function back() {
        window.location="${pageContext.request.contextPath}/terminal/query";
    }

    function checkTerminalNumber(){
        var terminalNumber=$("#terminalNumber").val();
        var reg=/\d+(\.\d+)*/;
        if(terminalNumber!=""){
            if(!reg.test(terminalNumber)){
                alert("银联终端号格式不正确");
                $("#terminalNumber").val("");
            }
        }
    }
</script>
</body>
</html>
