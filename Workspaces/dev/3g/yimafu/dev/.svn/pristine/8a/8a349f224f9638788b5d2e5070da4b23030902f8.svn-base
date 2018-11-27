<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<%@ page import="com.yeepay.g3.facade.laike.enumtype.AppRoleEnum" %>
<%@ page import="com.yeepay.g3.facade.laike.enumtype.VersionPlatform" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增APP版本</title>
</head>
<body>
<form id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">新增APP版本</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul >
                <li>
                    <p>
                        <label class="text_tit">角色类型：</label>
                        <%--<dic:select type="AppVersionRole" code="${param.roleType}" id="roleType" name="roleType" clazz="select" />--%>
                        <select class="select" name="roleType" id="roleType">
                            <option value="">请选择</option>
                            <c:forEach var="enumv" items="<%=AppRoleEnum.values() %>" varStatus="status">
                                <option value="<c:out value="${enumv }"/>"
                                        <c:if test="${roleType==enumv }">selected="selected"</c:if>>
                                    <c:out value="${enumv.displayName }"/>
                                </option>
                            </c:forEach>
                        </select>
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">平台：</label>
                        <%--<dic:select type="AppVersionPlat" code="${param.platform}" id="platform" name="platform" clazz="select" />--%>
                        <select class="select" name="platform" id="platform">
                            <option value="">请选择</option>
                            <c:forEach var="enumv" items="<%=VersionPlatform.values() %>" varStatus="status">
                                <option value="<c:out value="${enumv }"/>"
                                        <c:if test="${PlatformEnum==enumv }">selected="selected"</c:if>>
                                    <c:out value="${enumv.displayName }"/>
                                </option>
                            </c:forEach>
                        </select>
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">版本号：</label>
                        <input type="text" class="input_text" id="versionCode"  name="versionCode" value="${param.versionCode}" onchange="checkVersionCode()"/>
                    </p>
                </li>
            </ul>
            <%--<ul >--%>
            <%--<li>--%>
            <%--<p>--%>
            <%--<label class="text_tit">强制更新：</label>--%>
            <%--<input type="radio" name="forceUpdate" value="1" />&nbsp;&nbsp;是--%>
            <%--<input type="radio" name="forceUpdate" value="0" checked="checked"/>&nbsp;&nbsp;否--%>
            <%--</p>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">描述：</label>
                        <textarea type="text" id="description" name="description" rows=3
                                  cols="80">${param.description}</textarea>
                    </p>
                </li>
            </ul>
            <div class="btn">
                <input type="button"  class="btn_sure fw" id="queryid" onclick="add();" value="添加" />
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

        var roleType=$("#roleType").val();
        var platform=$("#platform").val();
        var versionCode=$("#versionCode").val();
        var description = $("#description").val();

        if(roleType=="" || platform=="" ||versionCode=="" || description == ""){
            alert("信息不完整!");
            return false;
        }
        if(description.length>80){
            alert("描述信息过长!");
            return false;
        }

        var options = {
            type: "post",
            url: "${pageContext.request.contextPath}/app/add",
            dataType: "json",
            success: function (resp) {
                if(resp.status == "ok"){
                    alert("操作成功!");
                    window.location="${pageContext.request.contextPath}/app/query";
                }else {
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

    function checkVersionCode(){
        var versionCode=$("#versionCode").val();
        var reg=/\d+(\.\d+)*/;
        if(versionCode!=""){
            if(!reg.test(versionCode)){
                alert("版本格式不正确");
                $("#versionCode").val("");
            }
        }
    }
</script>
</body>
</html>
