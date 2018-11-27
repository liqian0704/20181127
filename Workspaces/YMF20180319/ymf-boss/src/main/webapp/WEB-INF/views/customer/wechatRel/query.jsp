<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<%@ taglib prefix="decrypt" uri="/decrypt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>微信公众号关系查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script>
        function toAdd(){
            window.location="${pageContext.request.contextPath}/wechatRel/toAdd";
        }
        function toModify(id){
            window.location="${pageContext.request.contextPath}/wechatRel/toModify?id="+id;
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="query" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">商户查询</h2>
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
                                <label class="text_tit">商户名称：</label>
                                <input type="text" class="input_text" id="customerName" name="customerName" value="${param.customerName}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">创建时间：</label>
                                    <input type="text" name="startDate" req="true" class="input_text" id="startDate"
                                           readonly="true" value="${param.startDate}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <input type="text" name="endDate" class="input_text" req="true" id="endDate"
                                       readonly="true" value="${param.endDate}"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">AppId：</label>
                                <input type="text" class="input_text" id="appid"  name="appid" value="${param.appid}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">状态：</label>
                                <dic:select type="Status" code="${param.status}" name="status" clazz="input_text" id="status" />
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"  class="btn_cancel fw" id="resetid" value="重置" />
                        <input type="button" onclick="toAdd()"  class="btn_sure fw" value="新增" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">

            <q:table queryService="ymfQueryService" queryKey="wechatRelQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="商户编号" value="${customernumber}" />
                <q:column title="商户名称" value="${customername}" />
                <q:column title="APP_ID" value="${appid}" />
                <q:column title="APP密钥">
                    <decrypt:secret data="${appsecret}" />
                </q:column>
                <q:column title="创建时间">
                    <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </q:column>
                <q:column title="修改时间">
                    <fmt:formatDate value="${updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </q:column>
                <q:column title="状态">
                    <dic:write type="Status" code="${status}" />
                </q:column>
                <q:column title="操作" escapeHtml="false">
                    <a href="javascript:toModify('${id}')">修改</a>
                </q:column>
            </q:table>
        </div>
    </div>
</div>
</body>

</html>

