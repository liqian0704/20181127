<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增网点</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/customer/customer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/checkUtils.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/select2/select2.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/select2/i18n/zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
    <script>
        var out_ctx = "${pageContext.request.contextPath}";
        <%--var ctx = "${pageContext.request.contextPath}/${bossPrefix}";--%>
    </script>
    <script>
        $(function () {
            var provinces = '${provinces}' ;
            if (provinces != '') {
                var provincesJson = eval('(' + provinces + ')');
                // 网点
                var provinceSelc = '';
                var provinceOpt = '';
                $.each(provincesJson, function (key, value) {
                    provinceOpt = '<option value="' + key + '">' + value + '</option> ';
                    provinceSelc = provinceSelc + provinceOpt
                })
                $('#province').empty();
                $('#province').append("<option value='' >请选择</option>" + provinceSelc);
            }

            //商户编号
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
        });

        //根据省查询所有下级
        function queryArea(code) {
            $.ajax({
                url: "${pageContext.request.contextPath}/area/areaInfo",
                data: "areaCode="+code ,
                type: 'post',
                dataType: "json",
                success: function (msg) {
                    if (null == msg || msg == "false" || msg.status == "false") {
                        alert("查询异常,请重试");
                        return;
                    } else {
                        if (msg.status=='ok' && msg.content != null) {
                            $('#city').empty();
                            var selc = '';
                            var opt = '';
                            //var areaJson = eval(msg.content);
                            $.each(msg.content, function (key, value) {
                                opt = '<option value="' + key + '">' + value + '</option> ';
                                selc = selc + opt
                            })

                            $('#city').append("<option value='' >请选择</option>" + selc);
                            var cityValue = '${param.city}' ;
                            $('#city').val(cityValue);
                        }
                    }
                }
            })
        }

        /**
         * 新增网点
         * @returns {boolean}
         */
        function addShop(){
            var customerNumber = $("#customerNumber").val();
            var customerName = $("#customerName").val();
            var shopName = $("#shopName").val();
            var province = $("#province").val();
            var city = $("#city").val();
            var address = $("#address").val();
            var linkMan = $("#linkMan").val();
            var linkPhone = $("#linkPhone").val();
            var qrNo = $("#qrNo").val();

            var provinceName = $("#province").find("option:selected").text();
            var cityName = $("#city").find("option:selected").text();

            if (provinceName == "") {
                alert("未获取省份");
                return false;
            }
            if (cityName == "") {
                alert("未获取城市");
                return false;
            }
            $("#provinceName").val(provinceName);
            $("#cityName").val(cityName);

            if (customerNumber == "") {
                alert("商户编号必填");
                return false;
            }
            if (customerName == "") {
                alert("商户编号必填");
                return false;
            }
            if (shopName == "") {
                alert("网点名称必填");
                return false;
            }
            if (province == "") {
                alert("请选择省份");
                return false;
            }
            if (city == "") {
                alert("请选择城市");
                return false;
            }
            if (linkMan == "") {
                alert("请填写联系人姓名");
                return false;
            }
            if (linkPhone == "") {
                alert("请填写联系人电话");
                return false;
            }
            if (!checkMobile(linkPhone)) {
                alert("请输入正确的手机号码!");
                return false;
            }
            if (qrNo > 100) {
                alert("二维码最多只能1次创建100个");
                return false;
            }

            var options = {
                type: "post",
                url: "${pageContext.request.contextPath}/shop/addShop",
                dataType: "text",
                success: function (data) {
                    var resp = JSON.parse(data);
                    if(resp.status == "ok"){
                        alert("操作成功!");
                        window.location="${pageContext.request.contextPath}/shop/query";
                    }else if(resp.status == "error"){
                        alert(resp.msg);
                    }else {
                        alert("操作失败!");
                    }
                }
            }
            var form = $("#form");
            form.ajaxSubmit(options);
        }

        /**
         * 联查商户名称
         * @param customerNumber
         */
        function queryCustomerName(customerNumber) {
            $("#customerName").attr("value","");
            $.ajax({
                url: "${pageContext.request.contextPath}/shop/queryCustomerName",
                data: {"customerNumber": customerNumber},
                dataType: "json",
                success: function (json) {
                    if(json.status == "ok"){
                        $("#customerName").attr("value",json.content);
                    }else{
                        alert(json.msg);
                    }
                }

            });
        }
    </script>
</head>
<body>
    <form id="form" >
        <input type="hidden" name="provinceName" id="provinceName">
        <input type="hidden" name="cityName" id="cityName">
        <div class="information">
            <div class="search_tit">
                <h2 class="fw">新增网点</h2>
            </div>
            <div class="clearer"></div>
            <div class="input_cont">
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">商户编号：</label>
                            <select class="select search_query" style="width: 240px" queryId="customerNumberSearch"
                                    name="customerNumber" id="customerNumber" onchange="queryCustomerName(value)" >
                                <%--<option value="${param.customerName}" selected="selected">${param.customerName}</option>--%>
                            </select>
                            <label class="text_tit">商户名称：</label>
                            <input type="text" class="input_text" id="customerName"  name="customerName" value="${param.customerName}" readonly/>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">网点名称：</label>
                            <input type="text" style="width:300px"  class="input_text" id="shopName"  name="shopName" value="${param.shopName}"/>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">所属省份：</label>
                            <select id="province" name="province"  class="input_text" onchange="queryArea(value)">
                            </select>

                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">所属城市：</label>
                            <select id="city" name="city"  class="input_text">
                            </select>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">详细地址：</label>
                            <textarea type="textfield" cols="90" rows="1" class="textfield"  id="address"
                                      name="address"></textarea>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">联系人姓名：</label>
                            <input type="text" class="input_text" id="linkMan"  name="linkMan" value="${param.linkMan}"/>
                            <label class="text_tit">联系人电话：</label>
                            <input type="text" class="input_text" id="linkPhone"  name="linkPhone" value="${param.linkPhone}"/>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">二维码数量：</label>
                            <input type="number" class="input_text" id="qrNo"  name="qrNo" value=""
                                   onkeyup="this.value=this.value.replace(/\D/g,'')"
                                   onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                        </p>
                    </li>
                </ul>
                </div>
                <div class="btn">
                    <input type="button"  class="btn_sure fw" id="queryid"  onclick="addShop();" value="添加" />
                    <input type="reset"  class="btn_sure fw" id="resetid" value="清空" />
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
