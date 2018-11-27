<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>来客角色查询</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout.css"/>
    <script src="${pageContext.request.contextPath}/static/js/layout.js"></script>


    <link rel="stylesheet" type="text/css"
          href="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/css/jquery-common-dist.css">

    <!-- 1.9.x 以上版本的兼容组件 -->
    <!--时间格式-->
    <script type="text/javascript"
            src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery.min.js"></script>
    <!--时间插件-->
    <script type="text/javascript"
            src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui.min.js"></script>
    <!--中英文-->
    <script type="text/javascript"
            src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui-i18n.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>


</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">

            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">来客角色权限查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul id="list" class="fix">
                        <li>
                            <p>
                                <label class="text_tit">角色编码：</label>
                                <input type="text" class="input_text" name="rolecode" id="rolecode"
                                       value="${role.roleCode}"/>
                            </p>
                        </li>
                    </ul>
                    <input type="hidden" name="id" value="${role.id}"/>
                    <div class="btn">
                        <input type="submit" class="btn_sure fw" id="queryid" value="查询"/>
                    </div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfQueryService" queryKey="appRoleFunQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${_rowstatus.globalIndex}" />
                    <q:column title="功能名称" value="${functionname}"/>
                    <q:column title="功能编码" value="${functioncode}"/>
                    <q:column title="操作员" value="${operator}"/>
                    <q:column title="创建时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="状态" value="${(available eq 1)?'启用':'禁用'}" />
                    <q:column title="级别">
                        <c:out value="${app:convert2FunLevel(funclevel)}"/>
                    </q:column>
                    <q:column title="操作" escapeHtml="false">
                        <c:choose>
                            <c:when test="${available eq 1}">
                                <a href="javascript:toggleRoleFun('${id}')">禁用</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:toggleRoleFun('${id}')">启用</a>
                            </c:otherwise>
                        </c:choose>
                    </q:column>
                </q:table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function toggleRoleFun(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/permission/function/role/toggleFunc",
            type: "post",
            data: "id=" + id,
            success: function (data) {
                if (data.status == "ok") {
                    alert("操作成功!");
                    location.reload();
                } else {
                    alert(data.msg);
                }
            },
            error : function (e) {
                alert(e.responseText);
            }
        });
    }
</script>
</body>
</html>
