<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>新增消息</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form id="addBusinessForm" method="post">
            <div class="information">
                <div class="search_tit">
                    <h1 class="fw fleft f14" style="padding-left:30px">新增业务方配置</h1>
                </div>
                <div class="clearer"></div>
                <div class="input_cont">
                    <ul>
                        <li style="float: none"><label class="text_tit">业务方名称：</label>
                            <input type="text" id="businessName" name="businessName" class="input_text"/>
                        </li>
                        <li style="float: none"><label class="text_tit">业务方编码：</label>
                            <input type="text" id="businessCode" name="businessCode" class="input_text"/>
                        </li>
                        <li style="float: none"><label class="text_tit">认证类型：</label>
                            <input name="authType" type="checkbox" id="authType" value="FastRealIdName">&nbsp;身份证两项&nbsp;
                            <input name="authType" type="checkbox" id="authType" value="FastRealNameAuth">&nbsp;银行卡三项&nbsp;
                            <input name="authType" type="checkbox" id="authType" value="FastRealNameVerify">&nbsp;银行卡四项&nbsp;
                        </li>
                        <li style="float: none"><label class="text_tit">银行卡类型：</label>
                            <input name="cardType" type="radio" id="cardType" value="ALL">&nbsp;通用&nbsp;
                            <input name="cardType" type="radio" id="cardType" value="DEBIT">&nbsp;仅借记卡&nbsp;
                            <input name="cardType" type="radio" id="cardType" value="CREDIT">&nbsp;仅贷记卡&nbsp;
                        </li>
                    </ul>
                </div>
                <div class="clearer"></div>
                <div class="btn">
                    <input type="button" id="btn_save" class="btn_sure fw" value="提交" />
                    <input type="button" class="btn_cancel fw" value="取消" onclick="history.go(-1);" />
                </div>
            </div>
        </form>
        <div class="clearer"></div>
    </div>
</div>
<script type="text/javascript">
    var path = GV.ctxPath + "/business";
    $(document).ready(function() {
        $("#btn_save").click(function() {
            var businessCode = $.trim($('#businessCode').val());
            var businessName = $.trim($('#businessName').val());
            var cardType = $('#cardType:checked').val();
            var authTypeArray = new Array();
            var authTypeChecks = document.getElementsByName("authType");
            for (var i = 0; i < authTypeChecks.length; i++) {
                if (authTypeChecks[i].checked) {
                    authTypeArray[i] = authTypeChecks[i].value;
                }
            }
            var authTypeStr = authTypeArray.join(',');
            if(businessCode == '') {
                alert('业务方编码不能为空');
                return;
            }
            if(businessCode.length > 32) {
                alert('业务方编码超长（32个字符）');
                return;
            }
            if(businessName == '') {
                alert('业务方名称不能为空');
                return;
            }
            if(businessName.length > 32) {
                alert('业务方名称超长（32个字符）');
                return;
            }
            if(authTypeStr == ''){
                alert('请选择认证类型');
                return;
            }
            if(typeof(cardType) == "undefined") {
                alert('请选择银行卡类型');
                return;
            }
            var submitData = {
                'businessCode' : businessCode,
                'businessName' : businessName,
                'cardType' : cardType,
                'authTypeStr' : authTypeStr
            };
            $.ajax({
                type : 'POST',
                url : path + '/save',
                dataType : 'text',
                data : submitData,
                success : function(result) {
                    if (result == 'SUCCESS') {
                        window.location.href = path + '/query';
                    } else {
                        alert(result);
                        return;
                    }
                },
                error : function() {
                    alert('保存失败');
                    return;
                }
            });
        });
    });
</script>
</body>
</html>