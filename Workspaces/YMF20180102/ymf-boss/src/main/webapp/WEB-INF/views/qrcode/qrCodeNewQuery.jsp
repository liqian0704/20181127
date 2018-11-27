<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="enum" uri="/enumutils" %>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>二维码管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datetime.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/download.js"></script>
    <script>
        $(function () {
            var today = todayStr(new Date());
            var begin = getWannaDate1(new Date(), -30);

            var startDate = $("#startDate").val();
            if (startDate == ''){
                $("#startDate").val(begin);
            } else {
                $("#startDate").val(startDate);
            }

            var endDate = $("#endDate").val();
            if (endDate == ''){
                $("#endDate").val(today);
            } else {
                $("#endDate").val(endDate);
            }

        });
        function toQrcodeDetail(id){
            window.location="../qrCode/toDetail?id="+id;
        }
        function createQrcode(customerNumber){
            window.location="../qrCode/createQrcode?customerNumber="+customerNumber;
        }
        function shopManage(id){
            window.location="../newQrCode/toModify?id="+id;
        }
        function toCreateQrCode() {
            window.location="../newQrCode/toCreate";
        }
        /** 网点关联 **/
        function toModifyShop(id) {
            window.location="../newQrCode/toModifyShop?id="+id;
        }
        function downLoadImage(qrId){
            window.location="${pageContext.request.contextPath}/newQrCode/download?qrId="+qrId;
        }

        /**获取当天日期:2017-05-27 */
        function todayStr(date) {
            var dateStr = date.getFullYear() + "-";
            if ((date.getMonth() + 1 ) < 10) {
                dateStr += "0";
            }
            dateStr += (date.getMonth() + 1) + "-";
            if (date.getDate() < 10) {
                dateStr += "0";
            }
            dateStr += date.getDate();
            return dateStr;
        };

        /**
         * 获取当前时间curDate dayBetween天后／前的日期
         * @param curDate
         * @param dayBetween
         * @returns
         */
        function getWannaDate1(curDate,dayBetween){
            var wannaDate = new Date(curDate.getTime() + 1000*3600*24*dayBetween);
            return wannaDate.format("yyyy-MM-dd");
        }

        /**
         * excel下载
         */
        function downloadResult(){
            func_download({
                formId : "form",
                buttonId : "downid",
                queryId : "batchCreateQrcodeQuery",
                downloadType : "excel",
                url : "down"
            });
        }

        /**
         * excel下载
         */
        function downloadPic(){
            func_download({
                formId : "form",
                buttonId : "downPicId",
                queryId : "batchCreateQrcodeQuery",
                downloadType : "other",
                url : "downloadZip"
            });
        }

        //校验参数
        function checkSubmit() {
            var startDate = $("#startDate").val();
            var endDate = ("#endDate").val();
            if(startDate != "" && endDate != "") {
                if (startDate > endDate){
                    alert("创建时间结束时间不能小于开始时间!");
                    return false;
                }
            }
        }

        function cancelQrcode(qrCodeId){
            if(confirm("确认要注销二维码吗？")){
                $.ajax({
                            url: "../qrCode/cancelQrcode",
                            type: 'post',
                            data: {"qrCodeId": qrCodeId},
                            dataType:"text",
                            success: function (text) {
                                if (text == "success") {
                                    alert('核销成功');
                                    window.location="${pageContext.request.contextPath}/newQrCode/toQuery";
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
        <form action="toQuery" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">二维码管理</h2>
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
                                <label class="text_tit">采购订单：</label>
                                <input type="text" class="input_text" id="requestId"  name="requestId" value="${param.requestId}"/>
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

                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">商户名称：</label>
                                <input type="text" class="input_text" id="customerName"  name="customerName" value="${param.customerName}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点编号：</label>
                                <input type="text" class="input_text" id="shopNumber"  name="shopNumber" value="${param.shopNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点名称：</label>
                                <input type="text" class="input_text" id="shopName"  name="shopName" value="${param.shopName}"/>
                            </p>
                        </li>

                    </ul>

                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">应用类型：</label>
                                <%--<dic:select type="AppType" code="${param.appType}" id="appType" name="appType" clazz="select" />--%>
                                <select id="appType" name="appType" value="${param.appType}" class="select">
                                    <option value="">请选择</option>
                                    <option value="NORMAL">标准版</option>
                                    <option value="INDUSTRY">行业版</option>
                                    <option value="NORMAL,INDUSTRY">通用版</option>
                                </select>
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
                        <li>
                            <p>
                                <label class="text_tit">二维码类型：</label>
                                <dic:select type="BusinessType" code="${param.businessType}" id="businessType" name="businessType" clazz="select" />
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" onclick="return checkSubmit();" />
                        <input type="reset"   class="btn_cancel fw" id="resetid" value="清空" />
                        <input type="button" class="btn_sure fw" value="生成二维码" onclick="toCreateQrCode()" />
                        <input type="button"  class="btn_sure fw" id="downid" onclick="downloadResult()" value="导出链接" />
                        <input type="button"  class="btn_sure fw" id="downPicId" onclick="downloadPic()" value="导出图片" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div class="search">

            <q:table queryService="ymfDownloadQueryService" queryKey="batchCreateQrcodeQuery" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="序号" value="${rowid}" />
                <q:column title="二维码ID" value="${qrid}" />
                <q:column title="二维码类型"  >
                    <c:if test="${businesstype == null}">未设置</c:if>
                    <enum:write type="com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType" name="${businesstype}"/>
                </q:column>
                <q:column title="商户编号" value="${customernumber}" />
                <q:column title="商户名称" value="${customername}" />
                <q:column title="网点编号" value="${shop_number}" />
                <q:column title="网点名称" value="${shop_name}" />
                <q:column title="采购订单" value="${requestid}" />
                <q:column title="销售姓名" value="${salesname}" />
                <q:column title="应用类型" >
                    ${apptype}
                </q:column>
                <q:column title="生成时间" value="${createtime}" />
                <q:column title="修改时间" value="${updatetime}" />
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
                        <c:if test="${not empty customernumber}">
                            <a href="javascript:shopManage('${id}')">网点管理</a>
                            <a href="javascript:toModifyShop('${id}')">网点关联</a>
                            <%--<c:if test="${empty shop_number}">--%>

                            <%--</c:if>--%>
                        </c:if>
                        <a href="javascript:downLoadImage('${qrid}')">下载</a>
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