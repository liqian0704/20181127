<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>APP错误信息查询</title>
    <link rel="stylesheet" href="${likerPath}/static/css/jquery-ui-timepicker-addon.css"/>
    <link rel="stylesheet" href="${likerPath}/static/css/layout.css"/>
    <script src="${likerPath}/static/js/layout.js"></script>
    <script src="${likerPath}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${likerPath}/static/js/jquery-ui/js/jquery-ui-1.8.20.custom.min.js"></script>
    <script type="text/javascript" src="${likerPath}/static/js/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="${likerPath}/static/js/common.js"></script>
    <script>
        jQuery(function () {
            // 时间设置
            jQuery('#startCreateDate').datetimepicker({
                timeFormat: "HH:mm:ss",
                dateFormat: "yy-mm-dd"
            });
            jQuery('#endCreateDate').datetimepicker({
                timeFormat: "HH:mm:ss",
                dateFormat: "yy-mm-dd"
            });

        });
    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">

            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">APP错误信息查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul id="list" class="fix">
                        <li>
                            <label class="text_tit">会员号：</label>
                            <input type="text" class="input_text" name="memberNo" id="memberNo"
                                   value="${memberNo}"/>
                        </li>
                        <li>
                            <label class="text_tit">手机平台：</label>
                            <div class="select_border">
                                <div class="container">
                                    <select class="select" name="platform" id="platform">
                                        <option value="${platform}" selected="selected">请选择</option>
                                        <option value="ANDROID">ANDROID</option>
                                        <option value="IOS">IOS</option>
                                    </select>
                                </div>
                            </div>
                        </li>

                        <li>
                            <label class="text_tit">日志级别：</label>
                            <div class="select_border">
                                <div class="container">
                                    <select class="select" name="logLevel" id="logLevel">
                                        <option value="${logLevel}" selected="selected">请选择</option>
                                        <option value="INFO">INFO</option>
                                        <option value="WARN">WARN</option>
                                        <option value="ERROR">ERROR</option>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <label class="text_tit">手机厂商：</label>
                            <input type="text" class="input_text" name="phoneManufacture" id="phoneManufacture"
                                   value="${phoneManufacture}"/>
                        </li>
                        <li>
                            <label class="text_tit">sdk版本：</label>
                            <input type="text" class="input_text" name="sdk" id="sdk"
                                   value="${sdk}"/>
                        </li>
                        <li>
                            <label class="text_tit">当前页面：</label>
                            <input type="text" class="input_text" name="currentActivity" id="currentActivity"
                                   value="${currentActivity}"/>
                        </li>
                        <li>
                            <label class="text_tit">创建时间：</label>
                            <input type="text" class="input_text" name="startCreateDate" id="startCreateDate" value="${startCreateDate}" style="width: 145px;" />
                        </li>
                        <li>
                            <label class="text_tit">至：</label>
                            <input type="text" class="input_text" name="endCreateDate" id="endCreateDate" value="${endCreateDate}" style="width: 145px;"/>
                        </li>
                    </ul>
                    <div class="btn">
                        <input type="submit"  class="btn_sure fw" id="queryid" value="查询" />
                        <input type="reset"  class="btn_cancel fw" id="resetid" value="重置" />
                    </div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfQueryService" queryKey="appErrorMsgQuery" class="list" formId="form">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${_rowstatus.globalIndex}" width="50px" escapeHtml="false" />
                    <q:column title="会员号" value="${memberno}" width="110px" escapeHtml="false" />
                    <q:column title="手机平台" value="${platform}" width="100px" escapeHtml="false" />
                    <q:column title="手机厂商" value="${phonemanufacture}" width="50px" escapeHtml="false" />
                    <q:column title="手机型号" value="${phonemodel}" width="50px" escapeHtml="false" />
                    <q:column title="请求接口" value="${reqinterface}" width="100px" escapeHtml="false" />
                    <q:column title="日志级别" value="${loglevel}" width="50px" escapeHtml="false" />
                    <q:column title="日志内容" value="${logcontent}" width="300px" escapeHtml="false" />
                    <q:column title="手机系统版本" value="${sdk}" width="50px" escapeHtml="false" />
                    <q:column title="当前页面" value="${currentactivity}" width="100px" escapeHtml="false" />
                    <q:column title="手机标识" value="${imei}" width="100px" escapeHtml="false" />
                    <q:column title="创建时间">
                        <fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                </q:table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
