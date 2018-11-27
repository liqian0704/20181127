<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>客户端版本信息修改</title>

</head>
<body>
<form id="form" enctype="multipart/form-data">
    <input type="hidden" name="id" id="id" value="${dict.id}"/>
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">客户端版本信息修改</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul >
                <li>
                    <p>
                        <label class="text_tit">角色类型：</label>
                        <dict:write type="AppVersionRole" code="${app.roleType}"/>
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">平台：</label>
                        <dict:write type="AppVersionPlat" code="${app.platform}"/>
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">版本号：</label>
                        ${app.versionCode}
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">强制更新：</label>
                        <input type="radio" name="forceUpdate" value="1"
                               <c:if test="${app.forceUpdate=='true'}">checked="checked"</c:if>/>&nbsp;&nbsp;是
                        <input type="radio" name="forceUpdate" value="0"
                               <c:if test="${app.forceUpdate=='false'}">checked="checked"</c:if>/>&nbsp;&nbsp;否
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">文件路径：</label>
                        ${app.fileUrl}
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">上传文件：</label>
                        <input type="file" name="uploadFile" id="uploadFile"/>
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">描述：</label>
                        <textarea type="text" id="description" name="description" rows=3
                                  cols="80">${app.description}</textarea>
                    </p>
                </li>
            </ul>
            <input type="hidden" name="appid" value="${appid}"/>
            <div class="btn">
                <input type="button" class="btn_sure fw" id="queryid" onclick="modify();" value="保存"/>
                <input type="button" class="btn_sure fw" onclick="back();" value="返回"/>
            </div>
            <div class="clearer"></div>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<script>

    var isCommited = false;
    function checkPost(){
        if(!isCommited){
            isCommited = true;
            return true;
        }else{
            alert("正在上传请稍等!");
            return false;
        }
    }

    function modify() {

        var fileUpload =$("#uploadFile").val();
        if(fileUpload == null || fileUpload=="") {
            alert("请选择文件") ;
            return false;
        }

        $("#queryid").val("正在上传请稍等!");
        $("#queryid").attr("disabled",true);
        $("#queryid").css("background", "#CCC");

        checkPost();

        var options = {
            type: "post",
            url: "${pageContext.request.contextPath}/app/modify",
            dateType: "json",
            success: function (resp) {
                if (resp.status == "ok") {
                    alert("操作成功!");
                    window.location = "${pageContext.request.contextPath}/app/query";
                } else {
                    alert(resp.msg);
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
</body>
</html>
