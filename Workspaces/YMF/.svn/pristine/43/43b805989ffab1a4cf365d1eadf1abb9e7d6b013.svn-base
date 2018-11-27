<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<%@ page import="com.yeepay.g3.facade.laike.enumtype.AppRoleEnum" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>推客汇入网审核</title>
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
        function modify(memberno, id, isModify){
            window.location="${pageContext.request.contextPath}/appAudit/toModify?memberNo="+memberno + "&id=" + id + "&isModify=" + isModify;
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">

            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">推客汇入网审核</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul id="list" class="fix">
                        <li>
                            <p>
                                <label class="text_tit">审核状态：</label>
                                <select class="select" name="status" id="status">
                                    <option value="${status}" selected="selected">请选择</option>
                                    <option value="OCR_AUDIT">未审核</option>
                                    <option value="SUCCESS">已审核</option>
                                </select>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">创建时间：</label>
                                <%--<input type="text" class="input_text Wdate" name="startDate" id="startDate" value="${startDate}" style="width: 145px;" />--%>
                                <input type="text" name="startDate" class="input_text" id="startDate" req="true" style="width: 145px;"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <input type="text" name="endDate" class="input_text" id="endDate" req="true" style="width: 145px;"/>
                                <%--<input type="text" class="input_text Wdate" name="endDate" id="endDate" value="${endDate}" style="width: 145px;" />--%>
                            </p>
                        </li>
                        <li>
                            <label class="text_tit">发起人：</label>
                            <input type="text" class="input_text" name="merchantname" id="merchantname"
                                   value="${merchantname}"/>
                        </li>
                        <li>
                            <label class="text_tit">商户编号：</label>
                            <input type="text" class="input_text" name="merchantNo" id="merchantNo"
                                   value="${merchantNo}"/>
                        </li>
                    </ul>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"   class="btn_sure fw" id="resetid" value="重置" />
                    </div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfQueryService" queryKey="allianceAuditQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${_rowstatus.globalIndex}" width = "50px"/>
                    <q:column title="入网单号" value="${id}" width = "200px"/>
                    <q:column title="商编" value="${merchantno}" width = "200px"/>
                    <q:column title="入网单名称" escapeHtml="false" width = "250px">
                        <a href="javascript:modify('${memberno}', '${id}' , 'false')">推客汇入网_<fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd"/>_${merchantname}</a>
                    </q:column>
                    <q:column title="发起人" value="${merchantname}" width = "200px"/>
                    <q:column title="到达时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="审核状态" escapeHtml="false"  width = "80px">
                        <c:if test="${lolstatus =='SUCCESS' or lolstatus =='RETURN'}">
                            已审核
                        </c:if>
                        <c:if test="${lolstatus =='OCR_AUDIT'}">
                            未审核
                        </c:if>
                    </q:column>
                    <q:column title="操作" escapeHtml="false"  width = "80px">
                        <c:if test="${lolstatus =='SUCCESS' or lolstatus =='RETURN'}">
                            <a href="javascript:modify('${memberno}', '${id}' , 'false')">详情</a>
                        </c:if>
                        <c:if test="${lolstatus =='OCR_AUDIT'}">
                            <a href="javascript:modify('${memberno}', '${id}', 'true')">办理</a>
                        </c:if>
                    </q:column>
                </q:table>
            </div>
        </div>
    </div>
</div>
</body>
</html>