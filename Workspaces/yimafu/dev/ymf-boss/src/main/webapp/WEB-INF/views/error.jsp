<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>异常信息</title>
</head>

<body>
<div class="container">
    <h2 class="demoHeaders">Exception / Error</h2>
    <br>
    <div class="ui-widget">
        <div class="ui-state-error ui-corner-all" style="padding: 5px;">
            <p>
                <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                <strong>Exception:</strong>
               ${msg}
            </p>
        </div>
    </div>

    <%--<div style="display: none;">--%>
        <%--<s:property value="exceptionStack" />--%>
    <%--</div>--%>
</div>

</body>
</html>