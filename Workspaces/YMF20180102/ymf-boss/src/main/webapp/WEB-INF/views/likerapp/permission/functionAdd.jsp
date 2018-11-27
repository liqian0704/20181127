<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<%@ page import="com.yeepay.g3.facade.laike.enumtype.FuncLevelEnum" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>来客新增功能</title>

    <style>
        .zTreeDemoBackground{
            border: 1px solid black;
            width: 250px;
            position: absolute;
            right: 100px;
            top: 150px;
        }

        .text{
            text-align: left;
        }

        .input_cont{
            border-bottom: 0;
        }

    </style>

</head>
<body>
<form id="form">
    <div class="information">
        <div class="search_tit">
            <h2 class="fw">来客新增功能</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul >
                <li>
                    <p>
                        <label class="text_tit">功能名称：</label>
                        <input type="text" class="input_text" id="funName"  name="funName" value="${param.funName}"/>
                    </p>
                </li>
            </ul>
            <ul >
                <li>
                    <p>
                        <label class="text_tit">功能编码：</label>
                        <input type="text" class="input_text" id="funCode"  name="funCode" value="${param.funCode}"/>
                    </p>
                </li>
            </ul>
                <p class="check-parent">
                    <label class="text_tit">功能级别：</label>
                    <c:forEach var="enumv" items="<%=FuncLevelEnum.values() %>">
                        <input type="checkbox"  name="funLevel" value="${enumv}" <c:if test="${funLevel == enumv }">checked</c:if>/>&nbsp;&nbsp;${enumv.bossDisplayName }
                    </c:forEach>
                </p>
            </li>

            <div class="btn">
                <input type="button"  class="btn_sure fw" id="queryid" onclick="addFunc();" value="新增" />
                <input type="button" class="btn_sure fw" onclick="back();" value="返回"/>
            </div>
        </div>
        <div class="zTreeDemoBackground"><p class="text">授权角色：</p>
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree/jquery.ztree.all.js"></script>
<script>

    function addFunc() {
        if (is_checked()) {
            var form = $("#form");
            treeNode();
            var data = form.serialize() + "&array=" +  array;
            var options = {
                type: "post",
                url: "${pageContext.request.contextPath}/user/permission/function/add",
                data: data,
                dateType: "json",
                success: function (resp) {
                    if (resp.status == "ok") {
                        alert("操作成功!");
                        window.location = "${pageContext.request.contextPath}/user/permission/function/query";
                    } else {
                        alert(resp.msg);
                    }
                },
                error : function (e) {
                    alert(e.responseText);
                }
            };
            $.ajax(options);
        } else {
            alert("功能级别不能为空");
        }
    };

    var setting = {
        callback: {
            onCheck: zTreeOnCheck
        },
        view: {
            showIcon: false
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId"
            },
            key : {
                name : "roleName"
            }
        }
    };

    function setCheck() {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
        type = { "Y" : "s", "N" : "s" };
        zTree.setting.check.chkboxType = type;
    }

    var zNodes = ${roleList};

    var array = new Array();

    function zTreeOnCheck(event, treeId, treeNode) {
    };

    function treeNode() {
        nodes = $.fn.zTree.getZTreeObj("tree").getCheckedNodes();
        for (var i=0, l=nodes.length; i<l; i++) {
            array[i] = nodes[i].id;
        }
    };
    
    function is_checked() {
        var funcLevel = document.getElementsByName("funLevel");
        for(var j=0; j < funcLevel.length; j++){
            if(funcLevel[j].checked){
                return true;
            }
        }
        return false;
    }
    
    function monitor() {
        $('.check-parent input').click(function(){
            var index = $('.check-parent input').index(this);
            var checkBox = $('.check-parent input');
            if(checkBox[index].checked == true){
                for(var i=0; i<index; i++){
                    checkBox[i].checked = true;
                }
            }else{
                for(var i=index; i<checkBox.length; i++){
                    checkBox[i].checked = false;
                }
            }
        });
    }



    $(document).ready(function(){
        var ztree = $.fn.zTree.init($("#tree"), setting, zNodes);
        setCheck();
        ztree.expandAll(true);
        monitor();
    });



</script>
</body>
</html>
