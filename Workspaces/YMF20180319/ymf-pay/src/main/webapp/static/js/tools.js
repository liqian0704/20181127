/**
 * Created by chuaijing.
 */
//插件加载引导类
(function(window, undefined) {
    var Tools = (function() {
        var Tools = function() {
            return new Tools.fn.init();
        };
        Tools.fn = Tools.prototype = {
            constructor : Tools,
            //初始化
            init : function() {}
        };
        //Tools.fn.init.prototype = Tools.fn;
        // move from jQuery
        Tools.extend = Tools.fn.extend = function(){
            var o,s,t,n,e,l,i=arguments[0]||{},r=1,a=arguments.length,u=!1;
            for("boolean"==typeof i&&(u=i,i=arguments[1]||{},r=2),"object"==typeof i||Tools.isFunction(i)||(i={}),a===r&&(i=this,--r);a>r;r++)if(null!=(o=arguments[r]))for(s in o)t=i[s],n=o[s],i!==n&&(u&&n&&(Tools.isPlainObject(n)||(e=Tools.isArray(n)))?(e?(e=!1,l=t&&Tools.isArray(t)?t:[]):l=t&&Tools.isPlainObject(t)?t:{},i[s]=Tools.extend(u,l,n)):void 0!==n&&(i[s]=n));
            return i;
        };
        return Tools;
    })();
    window.Tools = Tools;
})(window);

//popup 插件
Tools.extend({
    /*询问框*/
    confirmbox:function(msg,btn1,btn2,yes,no){
        layer.open({
            style:'width:100%;',
            content: msg,
            btn: [btn1, btn2],
            yes: function(index){
                (yes && "function"==typeof yes)&&(yes());
                layer.close(index);
            },
            no: function(index){
                (no && "function"==typeof no)&&(no());
                layer.close(index);
            }
        });
    },
    /*询问框有标题*/
    confirm:function(title,msg,yes,no){
        var layers = layer.open({
            style:'width:100%;',
            title:[
                title,
                'text-align:center;' //标题样式
            ],
            content: msg,
            btn: ['确认', '取消'],
            yes: function(index){
                (yes && "function"==typeof yes)&&(yes());
                layer.close(index);
            },
            no: function(index){
                (no && "function"==typeof no)&&(no());
                layer.close(index);
            }
        });
    },
    /*信息框*/
    open:function(msg,callBack){
        layer.open({
            style:'text-align:center; background:#000; color:#fff; opacity:0.8;',
            time: 2,
            content: msg,
            end:callBack
        });
    },
    /*页面层*/
    message:function(msg,options,callBack){
        if(options && "function"==typeof options){
            callBack=options;
            options={};
        }
        layer.open(Tools.extend({
            content: msg,
            style: 'width:100%;',
            end:callBack
        },options));
    },
    /*提示框*/
    alert:function(msg,callback){
        layer.open({
            style:'text-align:center;',
            shadeClose: false,
            content: msg,
            btn: ['确定'],
            yes: callback
        });
    },
    /*加载层*/
    load:function(callback){
        layer.open({
            type: 2,
            shade:true, 
            shadeClose: false,
            end: callback
        });
    },
    //判断输入框是否为空，为空时弹出提示框
    checknull:function(){   
        var vs = document.getElementsByTagName('input');
        var check = true;
        for( var i= 0; i<vs.length; i++){
                var v=vs[i].value;
                v=v.replace(/(^\s*)|(\s*$)/g,"");///去除空格
                if (v.length==0){
                  check = false;
                } 
        }
        return check;
    },
    //判断字符字符串的长度
    checkTextLength:function(obj, length) {
        if(obj.value.length > length){
            obj.value = obj.value.substr(0, length);
        }
    },
    //银行卡号验证及格式化
    formatBankNo:function(BankNo){
        if (BankNo.value == "") return;
        var account = new String (BankNo.value);
        account = account.substring(0,23); /*帐号的总数, 包括空格在内 */
        if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null){
            /* 对照格式 */
            if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" +
                ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null){
                var accountNumeric = accountChar = "", i;
                for (i=0;i<account.length;i++){
                    accountChar = account.substr (i,1);
                    if (!isNaN (accountChar) && (accountChar != " ")) accountNumeric = accountNumeric + accountChar;
                }
                account = "";
                for (i=0; i<accountNumeric.length; i++){   /* 可将以下空格改为-*/
                    if (i == 4) account = account + " "; /* 帐号第四位数后加空格 */
                    if (i == 8) account = account + " "; /* 帐号第八位数后加空格 */
                    if (i == 12) {account = account + " ";};/* 帐号第十二位后数后加空格 */
                    if (i == 16) {account = account + " ";};/* 帐号第十六位后数后加空格 */
                    account = account + accountNumeric.substr (i,1);
                    //点亮下一步按钮
                    if($('.btn-default')){
                        if (i > 10){
                            $('.btn-default').addClass('btn-active');
                            $('#hidBtnState').val(true);
                        }else{
                            $('.btn-default').removeClass('btn-active');
                            $('#hidBtnState').val(false);
                        }
                    }
                }
            }
        }
        else
        {
            account = " " + account.substring (1,5) + " " + account.substring (6,10) + " " + account.substring (14,18) + "-" + account.substring(18,25);
        }
        if (account != BankNo.value) BankNo.value = account;
    },
    //判断访问终端
    browser:{
        versions:function(){
            var u = navigator.userAgent, app = navigator.appVersion;
            return {
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                qq: u.match(/\sQQ/i) == " qq" //是否QQ
            };
        }(),
        language:(navigator.browserLanguage || navigator.language).toLowerCase()
    }
});
jQuery.extend(jQuery.validator.messages, {
    required: "必选字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
    minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
    rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
    range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: jQuery.validator.format("请输入一个最大为{0} 的值"),
    min: jQuery.validator.format("请输入一个最小为{0} 的值")
});
$.validator.setDefaults({
    onfocusout:false, //是否在获取焦点时验证 默认:true
    onkeyup:false,    //是否在敲击键盘时验证 默认:true
    onclick:false,    //是否在鼠标点击时验证(一般验证checkbox,radiobox)
    focusInvalid: false, //提交表单后,未通过验证的表单会获得焦点
    focusCleanup: true,  //当未通过验证的元素获得焦点时移除错误提示
    debug: true,//进行调试模式（表单不提交）
    submitHandler: function(form) {
        //alert("submitted!");
        //$(form).submit();
    },
    errorPlacement: function(error, element) {
        var timer =null;
        var errorText = error[0].innerText;
        clearTimeout(timer);
        timer = setTimeout(function(){
            Tools.alert(errorText);
        },350);
    },
    errorElement: "span",
    showErrors:function(errorMap,errorList) {
        if(errorList.length>1){
            errorList.length=1;
        }
        this.defaultShowErrors();
    }
});
jQuery.validator.addMethod("stringCheck", function(value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "只能包括中文字、英文字母、数字和下划线");
jQuery.validator.addMethod("isValiDate", function(value, element) {
    return this.optional(element) || /^\d{4}/.test(value);
}, "请输入正确的有效期");
jQuery.validator.addMethod("isCVV", function(value, element) {
    return this.optional(element) || /^\d{3}/.test(value);
}, "请输入正确的CVV");
jQuery.validator.addMethod("isPhone", function(value, element) {
    return this.optional(element) || /^(1[0-9]{10})/.test(value);
}, "请输入正确的手机号");
jQuery.validator.addMethod("isCode", function(value, element) {
    return this.optional(element) || /^\d{3}|^\d{6}/.test(value);
}, "请输入3位或6位数字验证码");
jQuery.validator.addMethod("isIndentNo", function(value, element) {
    return this.optional(element) || isIndentNo(value);
}, "请输入正确的身份证号");
function isIndentNo(num) {
    var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
    var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for(var i=0;i<intStrLen;i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6,14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for(i=0;i<17;i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else{        //length is 15
                 //check date
        var date6 = idNumber.substring(6,12);
        if (isDate6(date6) == false) {

            return false;
        }
    }
    return true;

}
function isDate6(sDate) {
    if(!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}
/**
 * 判断是否为“YYYYMMDD”式的时期
 *
 */
function isDate8(sDate) {
    if(!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}

//卡bin验证方法
function verifyCardNew(){
    var cardElementText = $("#cardNo");
    var customerNo = $("#customerNo").text().trim();
    var cardNo = $.trim(cardElementText.val());
    cardNo = cardNo.replace(/[^\d]/g,'');
    //(1)没有文本值，需要输入
    if(cardNo.length == 0){
        Tools.alert("请输入银行卡号");
        return;
    }
    var timeOut = 150;
    var load =null;

    var requestParm = "creditNo=" + cardNo +"&customerNo=" + customerNo;
    //获取银行必填项
    $.ajax({
        url : "/app-merchant-proxy/eposMobile!verifyCard.action",
        type: "POST",
        data: requestParm,
        dataType: "json",
        contentType: "application/x-www-form-urlencoded;charset=gbk", 
        beforeSend: function(){
            load = Tools.load();
        },
        success: function(resp){
            setTimeout(function(){
                layer.closeAll(load);
                if(resp.status=='success'){
                    parseToPayPage(resp,cardNo);
                }else{
                    Tools.alert(resp.msg);
                }
            },timeOut);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            setTimeout(function(){
                layer.closeAll(load);
                Tools.alert("系统异常或不支持的卡，请尝试换卡重试.");
            } , timeOut);
        }
    });
}
//传递参数病页面跳转支付页面
function parseToPayPage(resp,cardNo){
    var orignUrlParam = location.search.substr(1);
    var payHref = 'jsp/epos/eposMobile/pay.html'+'?'+ orignUrlParam;
                   
    window.location.href = payHref ;
}

//解析请求参数至页面
function parsePageByAjax(resp){ 
    if(resp.customerName!=undefined && resp.customerName.length>0){
        $("#customerName").text(resp.customerName);
    }
    if(resp.customerNo!=undefined &&resp.customerNo.length>0){
        $("#customerNo").text(resp.customerNo);
    }
    if(resp.payAmt!=undefined &&resp.payAmt.length>0){
        $("#payAmt").text(resp.payAmt);
    }
    if(resp.product!=undefined &&resp.product.length>0){
        $("#product").text(decodeURIComponent(resp.product));
    }
    if(resp.requestId!=undefined &&resp.requestId.length>0){
        $("#requestId").text(resp.requestId);
    }
    if(resp.bankId!=undefined &&resp.bankId.length>0){
        $("#bankId").text(resp.bankId);
    }
    if(resp.l!=undefined &&resp.l.length>0){
        $("#l").text(resp.l);
    }
    if(resp.callbackUrl!=undefined &&resp.callbackUrl.length>0){
        $("#callBackUrl").text(decodeURIComponent(resp.callbackUrl));
    }
    if(resp.creditNo!=undefined &&resp.creditNo.length>0){
        $(".cardNo").text(resp.creditNo);
    }
    if(resp.frpIdString!=undefined &&resp.frpIdString.length>0){
        $("#frpId").text(resp.frpIdString);
    }
    if(resp.cardType!=undefined &&resp.cardType.length>0){
        $("#cardType").text(resp.cardType);
        if(resp.cardType.indexOf("CREDIT")==0){
            $("#cardtypename").text("信用卡");
        }else{
            $("#cardtypename").text("借记卡");
        }
    }
    if(resp.needParam!=undefined &&resp.needParam.length>0){
        $("#needParam").text(resp.needParam);
    }
    

    
}

function parseUrlParam(){
    var q = location.search.substr(1);   
    var qs = q.split("&");
    var link ; 
    if (qs) {   
            for (var i=0;i<qs.length;i++) {
                var paraName = qs[i].substring(0,qs[i].indexOf("="));
                var paraValue = qs[i].substring(qs[i].indexOf("=")+1);
                if(paraName.indexOf("l")==0){
                    $("#l").text(paraValue);
                    link = $("#l").text();
                }
        }
    }
    var load;
    var requestParm = "l=" + link;

    $.ajax({
        async : false,
        url : "/app-merchant-proxy/eposMobileParam!parsePage.action",
        type: "POST",
        data: requestParm,
        dataType: "json",
        contentType: "application/x-www-form-urlencoded;charset=gbk", 
        beforeSend: function(){
             load = Tools.load();
        },
        success: function(resp){
            layer.closeAll(load);
            if(resp.status=='success'){
                parsePageByAjax(resp);
            }else{
                Tools.alert(resp.msg);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
             layer.closeAll(load);
            setTimeout(function(){
                layer.closeAll(load);
                Tools.alert("系统异常，请稍后重试.");
            } , timeOut);
        }
    });

}



