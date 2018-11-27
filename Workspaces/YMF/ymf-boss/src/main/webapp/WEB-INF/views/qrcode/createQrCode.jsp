<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/select2/select2.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/i18n/zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js">--%>
    <script>
        var out_ctx = "${pageContext.request.contextPath}";
        <%--var ctx = "${pageContext.request.contextPath}/${bossPrefix}";--%>
    </script>
    <title>批量生成二维码</title>
    <script type="application/javascript">
        $(function() {
            $("#business").hide();
            //商户编号
            loadCustomerNumber();
            $("#customerNumber").change(function () {
                var customerNumber = $("#customerNumber").val();
                if (null != customerNumber && '' != customerNumber) {
                    checkCustomer(customerNumber);
                }
            })
        });

        function loadCustomerNumber() {
//            $("#customerNumber").select2("val", "");
            //商户编号
            $("#customerNumber").select2({
                ajax: searchConfig($("#customerNumber").attr("queryId")),
                minimumInputLength: 1,
                placeholder: "请输入查询条件",
                allowClear: true,
                language: "zh-CN",
                escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
                templateResult: searchCustomerResult,
                templateSelection: searchCustomerSelection
            });
        }

        window.onload=changeSel;

        function getSales() {
            $.ajax({
                url: "getSalesInfo",
                type: 'post',
                dataType: "json",
                success: function (msg) {
//                    var msg = JSON.parse(data);
                    if (null == msg || msg == "false" || msg.status == "false") {
                        alert("查询异常,请重试");
                        return;
                    } else {
                        $('#sales').empty();
                        var selc = '';
                        var opt = '';
                        $.each(msg.result, function (key, value) {
                            opt = '<option value="' + value.XINGMING + ',' + value.YOUXIANGQIANZHUI + ',' + value.GONGHAO + '">' + value.XINGMING + ',' + value.YOUXIANGQIANZHUI + ',' + value.GONGHAO + '</option> ';
                            selc = selc + opt
                        });

                        $('#sales').append("<option value='' ></option>" + selc);
//                        $('#sales').select2();

                    }
                }
            })
        }

        function toQrcodeDetail(id){
            window.location="../qrCode/toDetail?id="+id;
        }
        function createQrcode(customerNumber){
            window.location="../qrCode/createQrcode?customerNumber="+customerNumber;
        }
        function toQrcodeModify(id){
            window.location="../qrCode/modifyQrcode?id="+id;
        }
        function checkParam() {
            var  reg =/^[1-9]+[0-9]*]*$/;
            var qrCount = $('#qrCount').val().trim();
            if(!reg.test(qrCount)){
                alert("亲,生成数量!!!数量!!!填正整数会不会!");
                return false;
            } else if(qrCount>50){
                alert("一次最多生成50个,不要太贪心啊亲!");
                return false;
            }else if(qrCount.length == 0||qrCount<1){
                alert("一个也不生成,你瞎点个球!");
                return false;
            }
            var qrOwner = $("#qrOwner").val();
            if(""==qrOwner||null==qrOwner){
                alert("生成二维码对象你得选一个吧!");
                return false;
            }
            if(qrOwner =='SALES'){
                if ($('#sales').val() == '' || $('#sales').val() == null) {
                    alert("选销售,不选销售姓名,是不是傻!!");
                    return false;
                }
                var invoice = $('#qrInvoice').val();
                if ('' == invoice || null == invoice) {
                    alert("请填写单据号!");
                    return false;
                }
                $('#createform').submit();
            } else if(qrOwner =='CUSTOMER') {
                var qrBusinessType = $("#qrBusinessType").val();
                if(""==qrBusinessType||null==qrBusinessType){
                    alert("二维码版本都不选啊!亲!!");
                    return false;
                }
                var shopNumber = $("#shopNumber").val();
                if(""==shopNumber || null==shopNumber){
                    alert("请选择网点。");
                    return false;
                }
                var customerNumber = $("#customerNumber").val();
                if ( null != customerNumber && '' !=customerNumber) {
                    $('#createform').submit();
                    return true;
                } else {
                    alert('请填写商户编号啊亲,选了商户还不填,是不是傻!');
                    return false;
                }
            } else {
                $('#createform').submit();
            }
        }

        /**
         * 切换二维码对象
         */
        function changeSel() {

            $('#customerLi').hide();
            $('#businessTypeLi').hide();
            $('#shopInfo').hide();

            var sel = $("#qrOwner").val();
            if (sel == 'SALES') {
                $("#selName").text('销售:');
                $("#sales").show();
                $("#business").hide();
                $("#owner").empty().hide();
                getSales();
            } else if (sel == 'AGENT') {
                $("#selName").text('代理商编号:');
                $("#sales").empty().hide();
                $("#business").hide();
                $("#owner").show();
                $("#owner").attr("name", "agentNumber");
            } else if (sel == 'CUSTOMER') {
                $("#businessLi").hide();
                $('#sales').empty();
                $("#sales").hide();
                $("#owner").empty().hide();
//                loadCustomerNumber();
                $("#business").show();

//                $("#owner").attr("name", "customerNumber");
            }
        }

        /**
         * 校验商户
         * @param customerNumber
         */
        function checkCustomer(customerNumber) {
            $.ajax({
                url: "getCustomerInfo",
                type: 'post',
                data: {"customerNumber": customerNumber},
                dataType: "json",
                success: function (msg) {
                    if (null == msg) {
                        $('#qrBusinessType').val("");
                        $('#shopNumber').val("");
                        $('#customerName').val("");

                        $('#customerLi').hide();
                        $('#businessTypeLi').hide();
                        $('#shopInfo').hide();
                        alert("商户编号查找无记录，请重新输入");
                        return;
                    } else if (null == msg.customer) {
                        $('#qrBusinessType').val("");
                        $('#shopNumber').val("");
                        $('#customerName').val("");

                        $('#customerLi').hide();
                        $('#businessTypeLi').hide();
                        $('#shopInfo').hide();
                        alert(msg.msg);
                        return;
                    } else {
                        $('#customerName').val(msg.customer.customerName)
//                        $('#customerLi').insertAfter('#businessLi');
//                        $('#businessTypeLi').insertAfter('#qrInvoiceLi');
                        $('#customerLi').show();
                        $('#businessTypeLi').show();
                        $("#shopInfo").show();
                        // 二维码版本
                        var selc = '';
                        var opt = '';
                        $.each(msg.businessMap, function (key, value) {
                            opt = '<option value="' + key + '">' + value + '</option> ';
                            selc = selc + opt
                        })
                        $('#qrBusinessType').empty();
                        $('#qrBusinessType').append("<option value='' >请选择</option>" + selc);

                        // 网点
                        var shopSelc = '';
                        var shopOpt = '';
                        $.each(msg.shopMap, function (key, value) {
                            shopOpt = '<option value="' + key + '">' + '[' + key + ']' + value + '</option> ';
                            shopSelc = shopSelc + shopOpt
                        })
                        $('#shopNumber').empty();
                        $('#shopNumber').append("<option value='' >请选择</option>" + shopSelc);
                    }
                }
            });
        }

        function toCreateQrCode() {
            $.ajax({
                url: "${pageContext.request.contextPath}/qrCode/cancelQrcode",
                type: 'post',
                data: {"qrCodeId": qrCodeId},
                dataType: "text",
                success: function (text) {
                    if (text == "success") {
                        alert('核销成功');
                        window.location = "${pageContext.request.contextPath}/qrCode/query";
                    } else if (text == "NOTEXISTQRCODE") {
                        alert("二维码不存在!");
                    } else {
                        alert("操作失败!");
                    }
                }
            });
        }


    </script>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="create" id="createform" method="post">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">批量生成二维码</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">生成二维码对象：</label>
                                <select id="qrOwner" name="qrOwner"  onchange="changeSel()" class="input_text">
                                    <option value="">请选择</option>
                                    <option value="SALES">销售</option>
                                    <option value="AGENT">代理商</option>
                                    <option value="CUSTOMER">网点</option>
                                </select>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li id="businessLi">
                            <p>
                                <label class="text_tit" id="selName">二维码业务方：</label>
                                <input type="text" class="input_text" id="owner" name="" value="" />
                                <select id="sales" name="sales" hidden="hidden" class="input_text">
                                </select>
                                <%--<input type="text" class="input_text" id="agentNumber" name="agentNumber" value=""/>--%>
                                <%--<input type="text" class="input_text" id="customerNumber" name="customerNumber" value=""/>--%>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li id="business">
                            <p>
                                <label class="text_tit" id="customerLabel">商户编号：</label>
                                <select class="select search_query" queryId="customerNumberSearch"
                                        name="customerNumber" id="customerNumber" >
                                    <%--<option value="${param.customerName}" selected="selected">${param.customerName}</option>--%>
                                </select>
                            </p>
                        </li>
                        <li id="customerLi" hidden="hidden">
                            <p>
                                <label class="text_tit" id="selName">商户名称：</label>
                                <input disabled="disabled" class="input_text" id="customerName" name="" value=""/>
                                <%--<input type="text" class="input_text" id="agentNumber" name="agentNumber" value=""/>--%>
                                <%--<input type="text" class="input_text" id="customerNumber" name="customerNumber" value=""/>--%>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix" hidden="hidden" id="shopInfo">
                        <li>
                            <p>
                                <label class="text_tit">网点编号：</label>
                                <select id="shopNumber" name="shopNumber" class="input_text">
                                </select>
                            </p>
                        </li>
                        <li id="businessTypeLi" hidden="hidden">
                            <p>
                                <label class="text_tit">二维码版本：</label>
                                <select id="qrBusinessType" name="businessType"  onchange="" class="input_text">
                                </select>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">生成数量：</label>
                                <input type="number" min="1" max="50" class="input_text" id="qrCount" name="qrCount" value="${param.qrCount}"/>
                            </p>
                        </li>
                        <li id="qrInvoiceLi">
                            <p>
                                <label class="text_tit">单据号：</label>
                                <input type="" class="input_text" id="qrInvoice" name="qrInvoice" value="${param.qrInvoice}"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" id="createid" onclick="return checkParam()" value="生成二维码" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
<script type="text/javascript">
    // jquery 1.9以后的兼容
    (function(jQuery){

        if(jQuery.browser) return;

        jQuery.browser = {};
        jQuery.browser.mozilla = false;
        jQuery.browser.webkit = false;
        jQuery.browser.opera = false;
        jQuery.browser.msie = false;

        var nAgt = navigator.userAgent;
        jQuery.browser.name = navigator.appName;
        jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion);
        jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);
        var nameOffset,verOffset,ix;

// In Opera, the true version is after "Opera" or after "Version"
        if ((verOffset=nAgt.indexOf("Opera"))!=-1) {
            jQuery.browser.opera = true;
            jQuery.browser.name = "Opera";
            jQuery.browser.fullVersion = nAgt.substring(verOffset+6);
            if ((verOffset=nAgt.indexOf("Version"))!=-1)
                jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
        }
// In MSIE, the true version is after "MSIE" in userAgent
        else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {
            jQuery.browser.msie = true;
            jQuery.browser.name = "Microsoft Internet Explorer";
            jQuery.browser.fullVersion = nAgt.substring(verOffset+5);
        }
// In Chrome, the true version is after "Chrome"
        else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {
            jQuery.browser.webkit = true;
            jQuery.browser.name = "Chrome";
            jQuery.browser.fullVersion = nAgt.substring(verOffset+7);
        }
// In Safari, the true version is after "Safari" or after "Version"
        else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {
            jQuery.browser.webkit = true;
            jQuery.browser.name = "Safari";
            jQuery.browser.fullVersion = nAgt.substring(verOffset+7);
            if ((verOffset=nAgt.indexOf("Version"))!=-1)
                jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
        }
// In Firefox, the true version is after "Firefox"
        else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {
            jQuery.browser.mozilla = true;
            jQuery.browser.name = "Firefox";
            jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
        }
// In most other browsers, "name/version" is at the end of userAgent
        else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) <
            (verOffset=nAgt.lastIndexOf('/')) )
        {
            jQuery.browser.name = nAgt.substring(nameOffset,verOffset);
            jQuery.browser.fullVersion = nAgt.substring(verOffset+1);
            if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) {
                jQuery.browser.name = navigator.appName;
            }
        }
// trim the fullVersion string at semicolon/space if present
        if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1)
            jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);
        if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1)
            jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);

        jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10);
        if (isNaN(jQuery.browser.majorVersion)) {
            jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion);
            jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);
        }
        jQuery.browser.version = jQuery.browser.majorVersion;
    })(jQuery);
</script>
</body>
</html>
