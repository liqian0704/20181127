<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>数据字典查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>

    <script>
        function addDict(){
            window.location="${pageContext.request.contextPath}/dictionary/toAdd";
        }
        function batchAdd(){
            window.location="${pageContext.request.contextPath}/dictionary/toBatchAdd";
        }
        function toModify(id){
            window.location="${pageContext.request.contextPath}/dictionary/toModify?id="+id;
        }
        function remove(id){
            if (confirm("确定要删除字典数据么?")) {
                $.post("${pageContext.request.contextPath}/dictionary/remove",
                        {"id": id},
                        function (resp) {
                            if(resp.status=='ok'){
                                alert("刷新成功!");
                            }else if(resp.status=='error'){
                                alert(resp.msg);
                            }else{
                                alert("操作失败!");
                            }
                        });
            };

        }
        function refresh() {
            $.post("${pageContext.request.contextPath}/dictionary/refresh", function (resp) {
                if(resp.status=='ok'){
                    alert("刷新成功!");
                }else if(resp.status=='error'){
                    alert(resp.msg);
                }else{
                    alert("操作失败!");
                }
            });
        }
        function appQuery(){
            window.location="${pageContext.request.contextPath}/app/query";
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="query" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">数据字典查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">字典类型：</label>
                                <dic:typeSelect id="type" name="type" clazz="select" type="${param.type}" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">字典编码：</label>
                                <input type="text" class="input_text" id="code" name="code" value="${param.code}"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">字典名称：</label>
                                <input type="text" class="input_text" id="name" name="name" value="${param.name}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">字典状态：</label>
                                <select id="statis" name="status" class="select">
                                    <option value="">全部</option>
                                    <option value="ACTIVE" <c:if test="${param.status == 'ACTIVE'}">selected</c:if>>可用</option>
                                    <option value="INACTIVE" <c:if test="${param.status == 'INACTIVE'}">selected</c:if>>不可用</option>
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
                        <input type="button" onclick="addDict()"  class="btn_sure fw" value="新增" />
                        <%--<input type="button" onclick="batchAdd()"  class="btn_sure fw" value="批量新增" />--%>
                        <input type="button" onclick="refresh()"  class="btn_sure fw" value="刷新缓存" />
                        <input type="button" onclick="appQuery()"  class="btn_sure fw" value="APP版本" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">
            <q:table queryService="ymfQueryService" queryKey="dictQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="字典类型" value="${type}" />
                <q:column title="字典编码" value="${code}" />
                <q:column title="字典数值" value="${value}" />
                <q:column title="字典名称" value="${name}" />
                <q:column title="字典状态" >
                    <e:write type="status" name="${status}" />
                </q:column>
                <q:column title="创建时间" value="${createtime}" />
                <q:column title="修改时间" value="${updatetime}" />
                <q:column title="操作" escapeHtml="false">
                    <a href="javascript:toModify('${id}')">修改</a>
                    <c:if test="${candelete == 1}">
                        <a href="javascript:remove('${id}')">删除</a>
                    </c:if>
                </q:column>
            </q:table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/main/footer.jsp"%>
</body>
</html>
