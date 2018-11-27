<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>绑定终端</title>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>--%>
    <script>
    var out_ctx = "${pageContext.request.contextPath}";
<%--var ctx = "${pageContext.request.contextPath}/${bossPrefix}";--%>
    </script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/select2/select2.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/i18n/zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
    <script>
    $(function () {
            $("#customerNumber").change(function () {
                var customerNumber = $("#customerNumber").val();
                if (null != customerNumber && '' != customerNumber) {
                    checkCustomer(customerNumber);
                }
            });

            $(".search_query").each(function (i,obj) {
                $(obj).select2({
                    ajax: searchConfig($(obj).attr("queryId")),
                    minimumInputLength: 1,
                    placeholder: "请输入查询条件",
                    allowClear: true,
                    language: "zh-CN",
                    escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
                    templateResult: searchCustomerResult,
                    templateSelection: searchCustomerSelection
                });
            });
//            $(".select_normal").each(function (i,obj) {
//                $(obj).select2({
//                    placeholder: "请输入选择条件",
//                    allowClear: true,
//                    language: "zh-CN"
//                });
//            });
        })



        /**
         * 校验商户
         * @param customerNumber
         */
        function checkCustomer(customerNumber) {
            $.ajax({
                url: "${pageContext.request.contextPath}/newQrCode/getCustomerInfo",
                type: 'post',
                data: {"customerNumber": customerNumber},
                dataType: "json",
                success: function (msg) {
                    if (null == msg) {
                        alert("商户编号查找无记录，请重新输入");
                        return;
                    } else if (null == msg.customer) {
                        alert(msg.msg);
                        return;
                    } else {
                        $('#customerName').val(msg.customer.customerName);

                        // 网点
                        var shopSelc = '';
                        var shopOpt = '';
                        $.each(msg.shopMap, function (key, value) {
                            shopOpt = '<option value="' + key + '">' + '' + key + ' | ' + value + '</option> ';
                            shopSelc = shopSelc + shopOpt
                        })
                        $('#shopNumber').empty();
                        $('#shopNumber').append("<option value='' >请选择</option>" + shopSelc);
                    }
                }
            });
        }


        function bind() {
            var options = {
                type: "post",
                url: "${pageContext.request.contextPath}/material/bindTerm",
                dataType: "json",
                success: function (resp) {
                    if (resp.status == "ok") {
                        alert("操作成功!");
                        window.location = "${pageContext.request.contextPath}/material/query";
                    } else if (resp.status == "error") {
                        alert(resp.msg);

                    } else {
                        alert("操作失败!");
                    }
                }
            };
            var form = $("#form");
            form.ajaxSubmit(options);
        }
    </script>
</head>
<body>
<form id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">绑定终端</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul>
                <li>
                    <p>
                        <label class="text_tit">SN号：</label>
                        <input type="text" title="SN号" class="input_text" name="snSerial" value="${snSerial}" readonly/>
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">商户编号：</label>
                        <%--<input type="text" title="商户编号" class="input_text" name="customerNumber" id="customerNumber" />--%>
                        <select class="select search_query" size="240px" style="width: 240px" queryId="customerNumberSearch"
                                name="customerNumber" id="customerNumber">
                            <%--<option value="${param.customerName}" selected="selected">${param.customerName}</option>--%>
                        </select>
                    </p>
                </li>
                <li>
                    <p>
                        <label class="text_tit">商户名称：</label>
                        <input type="text" disabled title="商户名称" class="input_text" name="customerName" id="customerName" />
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">网点编号：</label>
                        <select id="shopNumber" name="shopNumber" class="input_text">
                        </select>
                    </p>
                </li>
            </ul>
            <div class="btn">
                <input type="button" class="btn_sure fw" onclick="bind();" value="确定"/>
                <input type="reset" class="btn_sure fw" onclick="window_back();" value="返回"/>
            </div>
            <div class="clearer"></div>
        </div>
    </div>
</form>
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
