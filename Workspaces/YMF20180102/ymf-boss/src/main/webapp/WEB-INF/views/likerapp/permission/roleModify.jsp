<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/main/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>来客角色修改</title>

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
            <h2 class="fw">来客角色修改</h2>
        </div>
        <div class="clearer"></div>
        <div class="input_cont">
            <ul>
                <li>
                    <p>
                        <label class="text_tit">角色名称：</label>
                        <input type="text" class="input_text" name="roleName" id="roleName"
                               value="${role.roleName}"/>
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">角色编码：</label>
                        ${role.roleCode}
                    </p>
                </li>
            </ul>
            <ul>
                <li>
                    <p>
                        <label class="text_tit">描述：</label>
                        <textarea type="text" id="description" name="description" rows=3
                                  cols="80">${role.description}</textarea>
                    </p>
                </li>
            </ul>
            <input type="hidden" name="id" value="${role.id}"/>
            <div class="btn">
                <input type="button" class="btn_sure fw" id="queryid" onclick="modify();" value="修改"/>
                <input type="button" class="btn_sure fw" onclick="back();" value="返回"/>
            </div>
            <div class="clearer"></div>
        </div>
        <div class="zTreeDemoBackground"><p class="text">角色上级：</p>
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree/jquery.ztree.all.js"></script>
<script>
    function modify() {

        var form = $("#form");
        var data = form.serialize() + "&parentId=" +  checkedId;
        var options = {
            type: "post",
            url: "${pageContext.request.contextPath}/user/permission/role/modify",
            data: data,
            dateType: "json",
            success: function (resp) {
                if (resp.status == "ok") {
                    alert("操作成功!");
                    window.location = "${pageContext.request.contextPath}/user/permission/role/query";
                } else {
                    alert(resp.msg);
                }
            },
            error : function (e) {
                alert(e.responseText);
            }
        };
        $.ajax(options);
    }

    function back() {
        window.history.back();
    }

    var setting = {
        callback: {
            onCheck: zTreeOnCheck
        },
        view: {
            showIcon: false
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
        },
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        }
    };

    var zNodes = ${roleList};

    var checkedId = '${role.parentId}';

    function zTreeOnCheck(event, treeId, treeNode) {
        if (treeNode.checked){
            checkedId = treeNode.id;
        }else {
            checkedId = '${role.parentId}';
        }
    };

    $(document).ready(function(){
        var ztree = $.fn.zTree.init($("#tree"), setting, zNodes);
        var node = ztree.getNodeByParam("id", '${role.id}', null);
        ztree.setChkDisabled(node, true);
        ztree.expandAll(true);
    });
</script>
</body>
</html>
