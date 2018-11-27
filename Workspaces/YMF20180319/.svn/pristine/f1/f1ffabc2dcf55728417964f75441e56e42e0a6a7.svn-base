<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>来客</title>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0" />
    <style type="text/css">
        html,body,div,a,img,p{margin:0; padding:0;}
        img{border:0;}
        html{width:100%; height:100%;}
        body{width:100%; max-height:100%; position:relative; background-color:#ffffff;}
        .body_bg{background:url(${pageContext.request.contextPath }/static/images/content-bg.png) no-repeat #ffffff; background-size:100% 100%;}
        .logo{width:23%; margin:86px auto 20px; position:relative; height:auto;margin-top: 0px;padding-top: 86px;}
        .logo p{max-width:249px; position:relative; margin:0 auto; height:auto;}
        .left_right{width:40%; margin:10px auto 20px; position:relative; height:auto;}
        .left_right p{max-width:436px; position:relative; margin:0 auto; height:auto;}
        .btns{padding:50px 5% 0; width:90%; height:auto; position:relative;}
        a{display:block; max-width:570px; height:auto; position:relative; margin:0 auto; margin-bottom:20px; text-align:center;}
        .tip{position:absolute; left:0; top:0; width:100%; height:100%; z-index:999999; background-image:url(${pageContext.request.contextPath}/static/images/open_browser_tips.png); background-repeat:no-repeat; background-position:top right; background-color:#000;    background-size:280px auto; opacity:0.66; display:none;}
    </style>
    <meta name="apple-mobile-web-app-title" content=""> <!--添加到主屏后的标题-->
    <meta http-equiv="Cache-Control" content="no-cache"> <!--清除浏览器缓存-->
    <meta name="format-detection" content="telephone=no"> <!--禁止数字识自动别为电话号码-->
    <meta name="apple-mobile-web-app-capable" content="yes"> <!-- 是否启用 WebApp 全屏模式 -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"> <!-- 设置状态栏的背景 -->
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,minimal-ui">


</head>
<body class="body_bg">
<div id="tip" class="tip"></div>
<div class="logo">
    <p><img src="../static/images/liker_logo.png" width="100%"></p>
</div>
<div class="left_right">
    <!--<p><img src="../static/images/left_right.png" width="100%"></p>-->
</div>
<div class="btns">
    <a  id="int-ios" href="javascript:void(0);"><img src="${pageContext.request.contextPath}/static/images/ios_down_btns.png" width="100%"></a>
    <p id="iosTips" style="color: gray" align="center" width="100%">*IOS用户需要系统信任来客。请打开设置-通用-描述文件-YEEPAY CO.LTD-信任</p>
    <a id="int-and" href="javascript:void(0);z"><img src="${pageContext.request.contextPath}/static/images/andr_down_btns.png" width="100%"></a>
</div>
<span>&nbsp;</span>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/><span>&nbsp;</span>
<span>&nbsp;</span>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/><span>&nbsp;</span>

</body>
<script type="text/javascript">
    function param(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");var r = document.location.search.substr(1).match(reg);if (r != null) return unescape(r[2]); return null;
    }
    function isWeixin(){
        var userAgentString = window.navigator ? window.navigator.userAgent : "";
        var weixinreg = /MicroMessenger/i;
        var androidreg = /android/i;
        if (!weixinreg.test(userAgentString) ) {
            return true;
        }
        var iosreg = /(iphone)|(ipod)/i;
        if (iosreg.test(userAgentString) || androidreg.test(userAgentString)) {
        }
        return false;
    };

    var ioshref = "${iosUrl}";
    var andhref =  "${androidUrl}";

    if(!isWeixin()){
        var tip = document.getElementById("tip");
        tip.style.display="block";
        var ios = document.getElementById("int-ios");
        var android = document.getElementById("int-and");
        tip.addEventListener('touchstart' , function(){
            this.style.display = "none";
        });
        ios.addEventListener('click' , function(){
            tip.style.display = "block";
        });
        android.addEventListener('click' , function(){
            tip.style.display = "block";
        });

    }else{
        var ios = document.getElementById("int-ios");
        var android = document.getElementById("int-and");
        if(param("auto")==1){
            var u = navigator.userAgent, app = navigator.appVersion;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
            if(isiOS){
                window.location = ioshref;
            }else if(isAndroid){
                window.location = andhref;
            }else{
                ios.setAttribute("href",ioshref);
                android.setAttribute("href",andhref);
            }
        }else{
            ios.setAttribute("href",ioshref);
            android.setAttribute("href",andhref);
        }
    }
</script>
</html>
