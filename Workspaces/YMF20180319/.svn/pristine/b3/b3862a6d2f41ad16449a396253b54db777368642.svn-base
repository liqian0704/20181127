<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<%@ page import="com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户开通</title>
</head>
<body>
<form action="addUser" id="form"  method="post" enctype="multipart/form-data" >
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">用户开通</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont" style="background:#FFD39B">
            <ul >
                <li>
                    <p>
                        说明：请上传最新版新增用户excel模板
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">OP单号：</label>
                        <input type="text" class="input_text" id="opNo"  name="opNo" value="${param.opNo}"/>
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">企业类型：</label>
                        <select class="select" name="companyType" id="companyType">
                            <option value="">请选择</option>
                            <c:forEach var="enumv" items="<%=CompanyTypeEnum.values() %>" varStatus="status">
                                <option value="<c:out value="${enumv }"/>"
                                        <c:if test="${CompanyTypeEnum==enumv }">selected="selected"</c:if>>
                                    <c:out value="${enumv.displayName }"/>
                                </option>
                            </c:forEach>
                        </select>
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">BOSS用户：</label>
                        <input type="text" class="input_text" id="bossUer"  name="bossUer" value="${param.bossUer}"/>
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">选取文件：</label>
                        <input type="file" name="uploadFile" id="uploadFile"/>
                    </p>
                </li>
            </ul>
            <div class="btn">
                <input type="button"  class="btn_sure fw" id="queryid" onclick="add();" value="提交" />
            </div>
            <div id="message"></div>
            <div class="clearer"></div>
        </div>
    </div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
<script>

    function add(){

        var opNo = $("#opNo").val();
        var uploadFile = $("#uploadFile").val();
        var companyType = $("#companyType").val();
        var bossUer = $("#companyType").val();


        if(opNo=="" || uploadFile=="" || companyType=="" || bossUer==""){
            alert("信息不完整!");
            return false;
        }
        if(opNo.length>50){
            alert("单号不合法");
            return false;
        }

        $("#queryid").attr("disabled",true);
        $("#queryid").css("background", "#CCC");
        $("#queryid").val("正在上传请稍等!");


        $("#form").submit();

    }

    $(document).ready(function() {
        if ("${excelUpdateStatus}") {
            if("${excelUpdateStatus}"=="phoneRepeat"){
                alert("手机号有重复，请检查后重新导入");
            }else if("${excelUpdateStatus}"=="merchantNoSame"){
                alert("商编不一致，请检查后重新导入");
            }else if("${excelUpdateStatus}"=="excelIsEmpty"){
                alert("确少必要信息，请检查后重新导入");
            }else if("${excelUpdateStatus}"=="noIsEmpty"){
                alert("手机号或商编为空，请检查后重新导入");
            }else if("${excelUpdateStatus}"=="bossIsNoExist"){
                alert("该手机号不在excel中，请检查后重新导入");
            }else if("${excelUpdateStatus}"=="listIsOverLimit"){
                alert("超过最大注册数量，请检查后重新导入");
            }else {
                alert("${excelUpdateStatus}");
            }
        }
    });

</script>
</body>
</html>
