<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/select2/select2.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/static/select2/select2.min.js"></script>
    <title>二维码网点关联</title>
    <script type="application/javascript">
        $(function () {
            //初始化网点信息
            var shopMap = '${shopMap}' ;
            if (shopMap != '') {
                var shopJson = eval('(' + shopMap + ')');
                // 网点
                var shopSelc = '';
                var shopOpt = '';
                $.each(shopJson, function (key, value) {
                    shopOpt = '<option value="' + key + '">' + '[' + key + ']' + value + '</option> ';
                    shopSelc = shopSelc + shopOpt
                })
                $('#shopNumber').empty();
                $('#shopNumber').append("<option value='' >请选择</option>" + shopSelc);
            } else {
                alert("该商户下没有网点，请先到【网点管理】添加网点信息！");
                return ;
            }

            var shopNumber = '${qrCode.shopNumber}';
            $('#shopNumber').val(shopNumber);

            //初始化二维码版本
            var businessType = '${businessType}' ;
            var businessTypeJson = eval('(' + businessType + ')');
            // 二维码版本
            var selc = '';
            var opt = '';
            $.each(businessTypeJson, function (key, value) {
                opt = '<option value="' + key + '">' + value + '</option> ';
                selc = selc + opt
            })
            $('#businessType').empty();
            $('#businessType').append("<option value='' >请选择</option>" + selc);

            var busiType = '${qrCode.businessType}';
            $('#businessType').val(busiType);

        });


        function checkParam(){
            var id = $("#id").val();
            var customerNumber = $("#customerNumber").val();
            if (id == "" || customerNumber == ""){
                alert("参数错误");
                return false;
            }
            var shopNumber = $("#shopNumber").val();
            var businessType = $("#businessType").val();
            if (shopNumber == "") {
                alert("请选择网点");
                return false;
            }
            if (businessType == "") {
                alert("请选择二维码版本");
                return false;
            }

        }

    </script>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="updateQrCodeShop" id="updateQrCodeShop" method="post">
            <input class="input_text" type="hidden" name="id" value="${qrCode.id}"/>
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">网点关联</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">二维码ID：</label>
                                <input disabled="disabled" class="input_text" id="" name="qrId" value="${qrCode.qrId}"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">商户编号：</label>
                                <input readonly class="input_text" id="customerNumber" name="customerNumber"
                                       value="${customer.customerNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit" id="cust">商户名称：</label>
                                <input disabled="disabled" class="input_text" id="customerName" name=""
                                       value="${customer.customerName}"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">网点编号：</label>
                                <select id="shopNumber" name="shopNumber" class="input_text">
                                </select>
                            </p>
                        </li>
                        <li id="businessTypeLi">
                            <p>
                                <label class="text_tit">二维码版本：</label>
                                <select id="businessType" name="businessType"  onchange="" class="input_text">
                                </select>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="createid" onclick="return checkParam()" value="确定" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>
