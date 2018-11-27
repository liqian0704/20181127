<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>终端查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/download.js"></script>
    <script>
        function toBindTerm(snSerial){
            window.location="${pageContext.request.contextPath}/material/toBindTerm?snSerial="+snSerial;
        }
        function toBatchBindTerm(){
            window.location="${pageContext.request.contextPath}/material/toBatchBindTerm";
        }
        function toBatchSyncTerm(){
            window.location="${pageContext.request.contextPath}/material/toBatchSyncTerm";
        }
        /**
         * 解绑
         * @param snSerial 机具编号
         * @param customerNumber 商户编号
         */
        function unbindTerm(snSerial, customerNumber) {
            if (confirm("是否解绑终端?")) {
                $.post("${pageContext.request.contextPath}/material/unbindTerm",
                    {
                        snSerial: snSerial,
                        customerNumber: customerNumber
                    },
                    function (resp) {
                    if(resp.status=='ok'){
                        alert("解绑成功!");
                        window.location="${pageContext.request.contextPath}/material/query";
                    }else if(resp.status=='error'){
                        alert(resp.msg);
                    }else{
                        alert("操作失败!");
                    }
                });
            }
        }

        /**
         * 下载
         */
        function download_term() {
            func_download({
                queryId : "queryTermInfo",
                url : "${pageContext.request.contextPath}/order/down"
            });
        }
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="query" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">终端查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">商户编号：</label>
                                <input type="text" title="商户编号" class="input_text" name="customerNumber" value="${param.customerNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">商户名称：</label>
                                <input type="text" title="商户名称" class="input_text" name="customerName" value="${param.customerName}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点编号：</label>
                                <input type="text" title="网点编号" class="input_text" name="shopNo" value="${param.shopNo}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点名称：</label>
                                <input type="text" title="网点名称" class="input_text" name="shopName" value="${param.shopName}"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">SN号：</label>
                                <input type="text" title="SN号" class="input_text" name="snSerial" value="${param.snSerial}"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">厂商：</label>
                                <dict:select type="TermManufacture" clazz="select" name="manufact" code="${param.manufact}" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">终端类型：</label>
                                <dict:select type="TermType" clazz="select" name="termType" code="${param.termType}" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">终端状态：</label>
                                <dict:select type="TermStatus" clazz="select" name="termStatus" code="${param.termStatus}" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">库存状态：</label>
                                <dict:select type="StockStatus" clazz="select" name="stockStatus" code="${param.stockStatus}" />
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"  class="btn_cancel fw" value="重置" />
                        <input type="button" id="download" onclick="download_term()"  class="btn_sure fw" value="下载" />
                        <input type="button" onclick="toBatchBindTerm()"  class="btn_sure fw" value="批量绑定" />
                        <input type="button" onclick="toBatchSyncTerm()"  class="btn_sure fw" value="同步POSBOSS" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">
            <q:table queryService="ymfDownloadQueryService" queryKey="queryTermInfo" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="SN号" value="${snserial}" />
                <q:column title="终端类型">
                    <dict:write type="TermType" code="${termtype}" />
                </q:column>
                <q:column title="厂商">
                    <dict:write type="TermManufacture" code="${manufact}" />
                </q:column>
                <q:column title="商户编号" value="${customernumber}" />
                <q:column title="商户名称" value="${customername}" />
                <q:column title="网点编号" value="" />
                <q:column title="网点名称" value="" />
                <q:column title="绑定时间">
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${bindtime}" />
                </q:column>
                <q:column title="终端状态">
                    <dict:write type="TermStatus" code="${termstatus}" />
                </q:column>
                <q:column title="库存状态">
                    <dict:write type="StockStatus" code="${stockstatus}" />
                </q:column>
                <q:column title="操作" escapeHtml="false">
                    <c:if test="${'UNBIND' == termstatus}">
                        <a href="javascript:toBindTerm('${snserial}')">绑定</a>
                    </c:if>
                    <c:if test="${'BIND' == termstatus}">
                        <a href="javascript:unbindTerm('${snserial}', '${customernumber}')">解绑</a>
                    </c:if>
                </q:column>
            </q:table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/main/footer.jsp"%>
</body>
</html>
