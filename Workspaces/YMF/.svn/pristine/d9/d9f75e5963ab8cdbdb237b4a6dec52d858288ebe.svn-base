<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <title>公众号文件上传</title>
    <script>
    function upload() {
        var fileUpload = $("#fileUpload").val();
        if(fileUpload == null || fileUpload=="") {
            alert("请选择文件") ;
            return ;
        }
        var options = {
            type: "post",
            url: "${pageContext.request.contextPath}/mpfile/uploadFile",
            dataType: "text",
            success: function (data) {
                var start = data.indexOf(">");
                if(start != -1) {
                    var end = data.indexOf("<", start + 1);
                    if(end != -1) {
                        data = data.substring(start + 1, end);
                    }
                }
                var resp=JSON.parse(data);
                if(resp.status == "ok"){
                    alert("操作成功!");
                }else if(resp.status == "error"){
                    alert(resp.msg);
                }else {
                    alert("操作失败!");
                }
            }
        }
        var form = $("#form1");
        form.ajaxSubmit(options);
    }
    </script>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form id="form1" action="${pageContext.request.contextPath}/mpfile/uploadFile" method="post" enctype="multipart/form-data">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">公众号文件上传</h2>
                </div>
                <div class="clearer"></div>
                <div class="input_cont">
                    <ul>
                        <li>
                            <p>
                                <label class="text_tit" id="selName">选择文件：</label>
                                <input type="file" name="fileUpload" id="fileUpload"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" id="uploadBtn" onclick="upload()" value="开始上传" />
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
