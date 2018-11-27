<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>二维码查询(本功能已经弃用)</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script>
        function toQrcodeDetail(id){
            window.location="${pageContext.request.contextPath}/qrCode/toDetail?id="+id;
        }
        function createQrcode(customerNumber){
            window.location="${pageContext.request.contextPath}/qrCode/createQrcode?customerNumber="+customerNumber;
        }
        function toQrcodeModify(id){
            window.location="${pageContext.request.contextPath}/qrCode/modifyQrcode?id="+id;
        }
        function toCreateQrCode() {
            window.location="../shopQrCode/toCreate";
        }
        function cancelQrcode(qrCodeId){
            if(confirm("确认要注销二维码吗？")){
                $.ajax({
                            url: "${pageContext.request.contextPath}/qrCode/cancelQrcode",
                            type: 'post',
                            data: {"qrCodeId": qrCodeId},
                            dataType:"text",
                            success: function (text) {
                                if (text == "success") {
                                    alert('核销成功');
                                    window.location="${pageContext.request.contextPath}/qrCode/query";
                                }else if(text=="NOTEXISTQRCODE") {
                                    alert("二维码不存在!");
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
        <form action="query" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">二维码查询(本功能已经弃用)</h2>
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
                                <label class="text_tit">生成时间：</label>
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
                                <label class="text_tit">应用类型：</label>
                                <dic:select type="AppType" code="${param.appType}" id="appType" name="appType" clazz="select" />
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">状态：</label>
                                <select id="status" name="status" value="${param.status}" class="input_text">
                                    <option value="">请选择</option>
                                    <option value="NOTEFFECT">未生成</option>
                                    <option value="ACTIVE">已生效</option>
                                    <option value="INACTIVE">已失效</option>
                                </select>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">二维码标识：</label>
                                <input type="text" class="input_text" id="qrId" name="qrId" value="${param.qrId}"/>
                            </p>
                        </li>

                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询(弃用)" />
                        <input type="reset"   class="btn_cancel fw" id="resetid" value="清空" />
                        <input type="button" class="btn_sure fw" value="生成二维码" onclick="toCreateQrCode()" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">

            <q:table queryService="ymfQueryService" queryKey="qrcodeQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="商户编号" value="${customernumber}" />
                <q:column title="商户名称" value="${customername}" />
                <q:column title="应用类型" value="${apptype}" />
                <q:column title="生成时间" value="${createtime}" />
                <q:column title="修改时间" value="${updatetime}" />
                <q:column title="二维码ID" value="${qrid}" />
                <q:column title="状态">
                    <c:if test="${status=='NOTEFFECT'}">未生成</c:if>
                    <c:if test="${status=='ACTIVE'}">已生效</c:if>
                    <c:if test="${status=='INACTIVE'}">已失效</c:if>
                </q:column>
                <q:column title="操作" escapeHtml="false">
                    <c:if test="${status=='NOTEFFECT'}">
                         <a href="javascript:createQrcode('${customernumber}')">生成</a>
                    </c:if>
                    <c:if test="${status=='ACTIVE'}">
                        <a href="javascript:toQrcodeDetail('${id}')">详情</a>
                        <a href="javascript:cancelQrcode('${id}')">核销</a>
                    </c:if>
                    <c:if test="${status=='INACTIVE'}">
                        <a href="javascript:toQrcodeModify('${id}')">生成</a>
                     </c:if>
                </q:column>
            </q:table>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/main/footer.jsp"%>
</body>
</html>
