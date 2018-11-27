<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>推客汇入网单</title>
    <style>
        .cent{
            text-align: center;
            width: 1000px;
        }
        .cent1{
            text-align: center;
            width: 450px;
            height: 380px;
            display: inline-block;
        }
        .init{
            width: 430px;
            height: 350px;
        }
    </style>
</head>
<body>
<form action="" id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">推客汇入网单</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul >
                <li>
                    <p>
                        <label class="text_tit">商户名称：</label>
                        <input type="text" class="input_text" readonly="true" id="merchantName"  name="merchantName" value="${response.merchantName}"/>
                        <label class="text_tit">身份证号：</label>
                        <input type="text" class="input_text" readonly="true" id="legalIdCard"  name="legalIdCard" value="${response.legalIdCard}"/>
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">需审核原因：</label>
                        <input type="text" class="input_text" readonly="true" id="remark"  name="remark" style="color: red" value="${remark}"/>
                        <label class="text_tit">结算银行卡号：</label>
                        <input type="text" class="input_text" readonly="true" id="settleCardNo"  name="settleCardNo" value="${response.settleCardNo}"/>
                        <label class="text_tit">结算银行：</label>
                        <input type="text" class="input_text" readonly="true" id="settleBankName"  name="settleBankName" value="${response.settleBankName}"/>
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <div class="cent"><div class="cent1"><p>身份证正面</p><img class="init" src="${response.idcardImg1}"/></div><div class="cent1"><p>结算银行卡</p><img class="init" src="${response.bankcardImg}"/></div></div>
                </li>
            </ul>
            <input type="hidden" name="id" value="${id}"/>
            <li name="audit" id="audit">
                <p>
                    <label class="text_tit">审核结果：</label>
                    <select class="select" name="result" id="result">
                        <option value="${result}" selected="selected">请选择</option>
                        <option value="idCardError">身份证有误</option>
                        <option value="bankCardError">银行卡有误</option>
                        <option value="bothError">身份证和银行卡有误</option>
                        <option value="SUCCESS">通过</option>
                    </select>
                </p>
            </li>
            <div class="btn">
                <input type="button" class="btn_sure fw" id="submit_btn" onclick="modify();" value="确定"/>
                <input type="button" class="btn_sure fw" onclick="back();" value="后退"/>
            </div>
            <div class="clearer"></div>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<script>
    $(document).ready(function() {
        if ("${isModify}") {
            if("${isModify}"=="false"){
                $("#audit").attr("hidden",true);
                $("#submit_btn").attr("hidden",true);
            }
        }
    });

    function modify() {

        var result =$("#result").val();
        if(result == null || result=="") {
            alert("请选择审核结果") ;
            return false;
        }

        var options = {
            type: "post",
            url: "${pageContext.request.contextPath}/appAudit/modify",
            dateType: "json",
            success: function (resp) {
                if (resp.status == "ok") {
                    alert("操作成功!");
                    window.location = "${pageContext.request.contextPath}/appAudit/query";
                } else {
                    alert(resp.msg);
                }
            }
        };
        var form = $("#form");
        form.ajaxSubmit(options);
    }

    function back() {
        window.history.back();
    }
</script>
</body>
</html>
