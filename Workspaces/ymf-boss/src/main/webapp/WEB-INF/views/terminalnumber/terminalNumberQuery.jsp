<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银联终端号管理</title>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>

    <script type="text/javascript">
        function verification(){
            $("#form").attr("action","query");
            $("#form").submit();
            $("#form").attr("action","");
        }

        function addterminalnumber(){
            window.location="${pageContext.request.contextPath}/terminal/toAdd";
        }
        function updateTerminalNO(id){
            if(confirm("确定需要更改银联终端号的状态?")){
                $.ajax({
                    url: "${pageContext.request.contextPath}/terminal/modify",
                    data: "id="+id,
                    dataType: "json",
                    success: function (resp) {
                        if (resp.status=='ok') {
                            alert("操作成功");
                            window.location="${pageContext.request.contextPath}/terminal/query";
                        } else if(resp.status=='error'){
                            alert(resp.msg);
                        }else{
                            alert("操作失败!");
                        }

                    }

                });
            }
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">

            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">银联终端号管理</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <br/>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">商户号：</label>
                                <input type="text" class="input_text" id="customerNumber"  name="customerNumber" value="${param.customerNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">银联终端号：</label>
                                <input type="text" class="input_text" id="terminalNumber" name="terminalNumber" value="${param.terminalNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">终端状态：</label>
                                <select class="input_text" id="status" name="status" value="${param.status}">
                                    <option value="">请选择</option>
                                    <option value="ACTIVE">可用</option>
                                    <option value="INACTIVE">禁用</option>
                                </select>
                            </p>
                        </li>
                    </ul>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" onclick="verification()" id="queryid" value="查询" />
                        <input type="button" onclick="addterminalnumber()"  class="btn_sure fw" value="新增" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfQueryService" queryKey="terminalNumberQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${rowid}" />
                    <q:column title="商户号" value="${customernumber}" />
                    <q:column title="银联终端号" value="${terno}" />
                    <q:column title="终端状态"  width = "100px" escapeHtml="false">
                        <c:if test="${status=='ACTIVE'}">
                            <a href="javascript:updateTerminalNO('${id}')">可用</a>
                        </c:if>
                        <c:if test="${status=='INACTIVE'}">
                            <a href="javascript:updateTerminalNO('${id}')">禁用</a>
                        </c:if>
                    </q:column>
                    <q:column title="创建时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="修改时间">
                        <fmt:formatDate value="${updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                </q:table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
