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
                    <h2 class="fw fleft f14">来客角色查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul id="list" class="fix">
                        <li>
                            <p>
                                <label class="text_tit">角色名称：</label>
                                <input type="text" class="input_text" name="rolename" id="rolename"
                                       value="${rolename}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">角色编码：</label>
                                <input type="text" class="input_text" name="rolecode" id="rolecode"
                                       value="${rolecode}"/>
                            </p>
                        </li>
                    </ul>
                    <div class="btn">
                        <input type="submit" class="btn_sure fw" id="queryid" value="查询"/>
                        <input type="reset" class="btn_sure fw" id="resetid" value="重置"/>
                        <input type="button" class="btn_sure fw" onclick="addRole()" value="新增"/>
                    </div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfQueryService" queryKey="appRoleQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${_rowstatus.globalIndex}" />
                    <q:column title="角色名称" value="${rolename}"/>
                    <q:column title="角色编码" value="${rolecode}"/>
                    <q:column title="操作人" value="${operator}"/>
                    <q:column title="描述" value="${description}"/>
                    <q:column title="创建时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="功能" escapeHtml="false">
                        <a href="javascript:queryFunc('${id}', '${rolecode}')">查看</a>
                    </q:column>
                    <q:column title="操作" escapeHtml="false">
                        <a href="javascript:updateRole('${id}')">修改</a>
                    </q:column>
                </q:table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function queryFunc(id, rolecode) {
        window.location = "${pageContext.request.contextPath}/user/permission/function/role/query?id=" + id + "&rolecode=" + rolecode;
    }

    function updateRole(id) {
        if (id == 'ARO00000000000001'){
            alert("超级用户不支持修改");
        } else {
            window.location = "${pageContext.request.contextPath}/user/permission/role/toModify?id=" + id;
        }
    }

    function addRole() {
        window.location = "${pageContext.request.contextPath}/user/permission/role/toAdd";
    }
</script>
</body>
</html>
