<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>日志查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">日志查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">商户编号：</label>
                                <input type="text" class="input_text" id="customerNumber"  name="customerNumber" value="${param.customerNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">实体名称：</label>
                                <select class="select" name="entityName">
                                    <option value="">全部</option>
                                    <c:forEach items="${entry}" var="e">
                                        <option value="${e.value}">${e.key}</option>
                                    </c:forEach>
                                </select>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">操作类型：</label>
                                <dic:select id="optType" name="optType" clazz="select" type="OperateType" code="${param.optType}" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">操作人：</label>
                                <input type="text" class="input_text" id="optName" name="optName" value="${param.optName}" />
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">操作时间：</label>
                                <input type="text" name="startDate" class="input_text" id="startDate" readonly="true"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <input type="text" name="endDate" class="input_text" id="endDate" readonly="true"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">业务类型：</label>
                                <input type="text" name="bizType" class="input_text" id="bizType" value="${param.bizType}"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <div class="btn">
                        <input type="submit" class="btn_sure fw" value="查询" />
                        <input class="btn_sure fw" type="reset" value="重置" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">
            <q:table queryService="ymfQueryService" queryKey="logQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="商户编号" value="${customernumber}" />
                <q:column title="操作时间">
                    <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </q:column>
                <q:column title="操作类型">
                    <e:write type="optType" name="${opttype}" />
                </q:column>
                <q:column title="操作人" value="${optname}" />
                <q:column title="操作实体" value="${entityname}" />
                <q:column title="执行时间" value="${proceedtime}" />
                <q:column title="详情" value="${description}" />
                <q:column title="业务类型" value="${biztype}" />
            </q:table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/main/footer.jsp"%>
</body>
</html>
