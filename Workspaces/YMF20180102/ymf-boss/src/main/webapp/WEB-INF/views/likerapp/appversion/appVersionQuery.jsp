<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<%@ page import="com.yeepay.g3.facade.laike.enumtype.AppRoleEnum" %>
<%@ page import="com.yeepay.g3.facade.laike.enumtype.VersionPlatform" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>客户端版本查询</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout.css"/>
    <script src="${pageContext.request.contextPath}/static/js/layout.js"></script>


    <link rel="stylesheet" type="text/css" href="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/css/jquery-common-dist.css">
    <!-- 1.9.x 以上版本的兼容组件 -->
    <!--时间格式-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery.min.js"></script>
    <!--时间插件-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui.min.js"></script>
    <!--中英文-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui-i18n.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>

    <script type="text/javascript">
        function verification(){
            $("#form").attr("action","query");
            $("#form").submit();
            $("#form").attr("action","");
        }
        function updateApp(id){
            window.location="${pageContext.request.contextPath}/app/toModify?id="+id;
        }
        function addAppVersion(){
            window.location="${pageContext.request.contextPath}/app/toAdd";
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">

            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">客户端版本查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul id="list" class="fix">
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
                        <li>
                            <label class="text_tit">版本：</label>
                            <input type="text" class="input_text" name="versioncode" id="versioncode"
                                   value="${versioncode}"/>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">创建时间：</label>
                                <input type="text" name="startDate" class="input_text" id="startDate"
                                       readonly="true" req="true" value="${param.startDate}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <input type="text" name="endDate" class="input_text" id="endDate"
                                       readonly="true" req="true" value="${param.endDate}"/>
                            </p>
                        </li>
                    </ul>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" onclick="verification()" id="queryid" value="查询" />
                        <input type="reset"   class="btn_sure fw" id="resetid" value="重置" />
                        <input type="button"  class="btn_sure fw" onclick="addAppVersion()" value="新增" />
                    </div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfQueryService" queryKey="appVersionQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${_rowstatus.globalIndex}" />
                    <q:column title="版本编号" value="${id}" />
                    <q:column title="类型">
                        <e:write type="com.yeepay.g3.facade.laike.enumtype.AppRoleEnum" name="${roletype}"/>
                    </q:column>
                    <q:column title="平台">
                        <e:write type="com.yeepay.g3.facade.laike.enumtype.VersionPlatform" name="${platform}"/>
                    </q:column>
                    <q:column title="版本" value="${versioncode}" width = "100px"/>
                    <q:column title="强制更新" >
                        <dict:write type="AppVersionUpdate" code="${forceupdate}"/>
                    </q:column>
                    <q:column title="创建时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="修改时间">
                        <fmt:formatDate value="${updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="文件路径" value="${fileurl}" />
                    <q:column title="操作人" value="${operator}" />
                    <q:column title="描述" value="${description}" />
                    <q:column title="操作" escapeHtml="false"  width = "80px">
                        <a href="javascript:updateApp('${id}')">修改</a>
                    </q:column>
                </q:table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
