<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>通知异常记录查询</title>
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
        function doTry(id){
            if(confirm("确定补单吗?")){
                recordConfirm(id);
            }
        }
        function recordConfirm(id){
            $.ajax({
                url: "${pageContext.request.contextPath}/notifyRecord/doTry",
                data: "id="+id,
                dataType: "text",
                success: function (result) {
                    if (result=='SUCCESS') {
                        alert("补单成功");
                    }else{
                        alert("补单失败!");
                    }
                }
            });
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">通知异常记录查询</h2>
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
                                <label class="text_tit">商户订单号：</label>
                                <input type="text" class="input_text" id="customerOrder"  name="customerOrder" value="${param.customerOrder}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">业务订单号：</label>
                                <input type="text" class="input_text" id="businessOrder" name="businessOrder" value="${param.businessOrder}"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">创建时间：</label>
                                <%--<input class="input_text" id="timeStart" type="text" name="timeStart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="timeStart" class="input_text" id="timeStart" req="true" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">至&nbsp;&nbsp;</label>
                                <%--<input class="input_text" id="timeEnd" type="text" name="timeEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>--%>
                                <input type="text" name="timeEnd" class="input_text" id="timeEnd" req="true"  onchange="timecheck();"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">通知状态：</label>
                                <dic:select type="NotifyStatus" code="${param.notifyStatus}" id="notifyStatus" name="notifyStatus" clazz="select"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw"  id="queryid" value="查询" />
                        <input class="btn_sure fw" type="reset" value="重置" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfQueryService" queryKey="notifyRecordQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${rowid}" />
                    <q:column title="商户编号" value="${customernumber}" />
                    <q:column title="商户订单号" value="${cusorderid}" />
                    <q:column title="业务订单号" value="${externalid}" />
                    <q:column title="通知次数" value="${notifytimes}" />
                    <q:column title="通知信息" value="${remark}" />
                    <q:column title="创建时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="最后通知时间">
                        <fmt:formatDate value="${lastnotifytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="通知状态">
                        <dict:write type="NotifyStatus" code="${status}"/>
                    </q:column>
                    <q:column title="操作" escapeHtml="false"  width = "130px">
                        <c:if test="${status !='SUCCESS'}">
                        <a href="javascript:doTry('${id}')">补单</a>
                        </c:if>
                    </q:column>
                </q:table>
            </div>

        </div>
    </div>
</div>
</body>
</html>
