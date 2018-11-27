<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>业务方查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script>
        function addBusiness(){
            window.location="${pageContext.request.contextPath}/business/toAdd";
        }
        function toBusinessModify(id){
            window.location="${pageContext.request.contextPath}/business/toModify?id="+id;
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="query" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">业务方查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">业务方名称：</label>
                                <input type="text" class="input_text" id="bizName"  name="bizName" value="${param.bizName}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">业务方标识：</label>
                                <input type="text" class="input_text" id="bizCode" name="bizCode" value="${param.bizCode}"/>
                            </p>
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
                    <br/>

                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">状态：</label>
                                <select id="status" name="status" value="${param.status}" class="input_text">
                                    <option value="">请选择</option>
                                    <option value="ACTIVE">开通</option>
                                    <option value="INACTIVE">关闭</option>
                                </select>
                            </p>
                        </li>

                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"  class="btn_cancel fw" id="resetid" value="重置" />
                        <input type="button" onclick="addBusiness()"  class="btn_sure fw" value="新增" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">

            <q:table queryService="ymfQueryService" queryKey="businessQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="业务方名称" value="${bizname}" />
                <q:column title="业务方标识" value="${bizcode}" />
                <q:column title="创建时间" value="${createtime}" />
                <q:column title="修改时间" value="${updatetime}" />
                <q:column title="商户个数" value="${customercount}" />
                <q:column title="状态" value="${status}" />
                <q:column title="操作" escapeHtml="false">
                    <a href="javascript:toBusinessModify('${id}')">修改</a>
                </q:column>
            </q:table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/main/footer.jsp"%>
</body>
</html>
