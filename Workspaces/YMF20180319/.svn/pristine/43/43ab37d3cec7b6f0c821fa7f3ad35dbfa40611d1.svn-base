<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>网点查询-批量新增网点</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout.css?v=3"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datatable/jquery.dataTables.min.js"></script>
    <script>
        /**
         * 下载模板文件
         */
        function downloadTemplate() {
            window.location="${pageContext.request.contextPath}/shop/downloadTemplete?name=shop_batch_add_template.xlsx";
        }

        /**
         * 批量新增
         */
        function batchAddShop() {
            if (!$("#opCode").val()) {
                alert("请填写OP单号!");
                return false;
            }
            if (!$("#batchAddFile").val()) {
                alert("请上传文件!");
                return false;
            }
            var option = {
                type: "POST",
                url: "${pageContext.request.contextPath}/shop/batchAdd",
                dataType: "json",
                beforeSend : function () {
                    showme();
                },
                success: function (resp) {
                    hiddenme();
                    if(resp.status=='ok'){
                        if (resp.flag) {
                            var linkDom = $("#link");
                            linkDom.empty();
                            linkDom.append('<a href="javascript:downloadBatchShopResponse()">下载失败结果</a>');
                            var content =
                                "<table id='content' class='display compact' cellspacing='0' width='100%' >";
                            for (var index in resp.content) {
                                var array = resp.content[index];
                                if (0 == index) {
                                    content += "<thead><tr>";
                                    for (var j in array) {
                                        content += "<th>" + j + "</th>";
                                    }
                                    content += "</tr></thead>";
                                }
                                content += "<tr>";
                                for (var j in array) {
                                    var obj = array[j];
                                    content += "<td>" + obj + "</td>";
                                }
                                content += "</tr>";
                            }
                            content +=  "</table>";
                            $("#out").html(content);
                            $('#content').DataTable();
                        } else {
                            alert("全部保存成功!");
                            window.location = "${pageContext.request.contextPath}/shop/query";
                        }
                    }else if(resp.status=='error'){
                        alert(resp.msg);
                    }else{
                        alert("操作失败!");
                    }
                }
            };
            $("#form").ajaxSubmit(option);
        }

        /**
         * 下载失败记录
         */
        function downloadBatchShopResponse() {
            window.location="${pageContext.request.contextPath}/shop/downloadBatchShopResponse";
        }
    </script>
</head>
<body>
<form id="form" enctype="multipart/form-data">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">批量新增网点</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul>
                <li>
                    <p>
                        <label class="text_tit">OP单号：</label>
                        <input type="text" title="OP单号" id="opCode" class="input_text" name="opCode" value="${param.opCode}" required/>
                    </p>
                    <p>
                        <label class="text_tit">上传文件：</label>
                        <input type="file" title="上传文件" id="batchAddFile" class="input_text" name="batchAddFile" required />
                        &nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="downloadTemplate()"  class="btn_sure fw" value="下载模板" />
                    </p>
                </li>
            </ul>
            <div class="btn">
                <input type="button" id="bind" onclick="batchAddShop()"  class="btn_sure fw" value="确 定" />
                <input type="button" onclick="window_back()"  class="btn_sure fw" value="返 回" />
            </div>
            <div class="clearer"></div>
        </div>
    </div>
</form>
<br/>
<div id="link">

</div>
<br/>
<p id="out"></p>

<!--模态窗口-->
<div class="vnone" id="maskid">
    <iframe width="99%" height="100%" class="mask" ></iframe>
    <div class="vnone" id="boxid">
        <div id="poptop">
            <div class="text_div">
                <img src="${pageContext.request.contextPath}/static/images/loading/loading_5.gif">
            </div>
        </div>
    </div>
</div>
</body>
</html>
