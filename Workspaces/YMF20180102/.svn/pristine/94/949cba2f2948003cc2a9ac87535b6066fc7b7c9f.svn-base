<%@ taglib prefix="dict" uri="/dicutils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>网点管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout.css"/>
    <script src="${pageContext.request.contextPath}/static/js/layout.js"></script>


    <link rel="stylesheet" type="text/css" href="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/css/jquery-common-dist.css">
    <!-- 1.9.x 以上版本的兼容组件 -->
    <!--时间格式-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery.min.js"></script>
    <!--时间插件-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui.min.js"></script>
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.8.3/extend/date/jquery-ui-timepicker-addon.min.js"></script>
    <!--中英文-->
    <script type="text/javascript" src="https://resource.yeepay.com/yeepay-resources/common/component/jquery1.4/lib/jquery-ui-i18n.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/download.js"></script>
    <%--<script language="javascript" type="text/javascript" defer="defer" src="${pageContext.request.contextPath}/static/js/wdatePicker/WdatePicker.js"></script>--%>
    <script>
        $(function () {

            var provinces = '${provinces}' ;
            if (provinces != '') {
                var provincesJson = eval('(' + provinces + ')');
                // 网点
                var provinceSelc = '';
                var provinceOpt = '';
                $.each(provincesJson, function (key, value) {
                    provinceOpt = '<option value="' + key + '">' + value + '</option> ';
                    provinceSelc = provinceSelc + provinceOpt
                })
                $('#province').empty();
                $('#province').append("<option value='' >请选择</option>" + provinceSelc);
            }

            var province = '${param.province}';
            if (province != '') {
                queryArea(province);
            }


        });

        //根据省查询所有下级
        function queryArea(code) {
            $.ajax({
                url: "${pageContext.request.contextPath}/area/areaInfo",
                data: "areaCode="+code ,
                type: 'post',
                dataType: "json",
                success: function (msg) {
                    if (null == msg || msg == "false" || msg.status == "false") {
                        alert("查询异常,请重试");
                        return;
                    } else {
                        if (msg.status=='ok' && msg.content != null) {
                            $('#city').empty();
                            var selc = '';
                            var opt = '';
                            //var areaJson = eval(msg.content);
                            $.each(msg.content, function (key, value) {
                                opt = '<option value="' + key + '">' + value + '</option> ';
                                selc = selc + opt
                            })

                            $('#city').append("<option value='' >请选择</option>" + selc);
                            var cityValue = '${param.city}' ;
                            $('#city').val(cityValue);
                        }
                    }
                }
            })
        }

        //查询校验
        function verification() {
            $("#form").attr("action","query");
            $("#form").submit();
            $("#form").attr("action","");
            return true;
        }

        //新增网点
        function toShopAdd(){
            window.location="${pageContext.request.contextPath}/shop/toAdd";
        }

        //网点详情
        function shopDetail(id){
            window.location="${pageContext.request.contextPath}/shop/toShopDetail?id=" + id;
        }

        //修改网点
        function modifyShop(id){
            window.location="${pageContext.request.contextPath}/shop/toModifyShop?id=" + id;
        }

        //excel批量新增
        function toBatchAdd() {
            window.location = "${pageContext.request.contextPath}/shop/toBatchAdd";
        }

        /**
         * 下载模板文件
         */
        function downloadTemplate() {
            window.location = "${pageContext.request.contextPath}/shop/downloadTemplete?name=shop_batch_add_template.xlsx";
        }

        //核销网点
        function destroyShop(id,custName,shopName) {
            if(confirm('你正在删除商户 ' + custName + ' 的网点' + shopName + '，是否确定核销？')){
                $.ajax({
                    url: "${pageContext.request.contextPath}/shop/destroyShop",
                    type: 'POST',
                    data: {"id": id},
                    dataType: "json",
                    success: function (json) {
                        if(json.status == "ok"){
                            alert("核销成功,请刷新.");
                        }else{
                            alert(json.msg);
                        }
                    }
                });
            }
        }

    </script>
</head>
<body>

<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">网点查询</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">商户编号：</label>
                                <input type="text" class="input_text" id="customerNumber"  name="customerNumber" value="${param.customerNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">商户名称：</label>
                                <input type="text" class="input_text" id="customerName" name="customerName" value="${param.customerName}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点编号：</label>
                                <input type="text" class="input_text" id="shopNumber"  name="shopNumber" value="${param.shopNumber}"/>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">网点名称：</label>
                                <input type="text" class="input_text" id="shopName" name="shopName" value="${param.shopName}"/>
                            </p>
                        </li>
                    </ul>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">网点状态：</label>
                                <select id="status" name="status" value="${param.status}" class="input_text">
                                    <option value="">请选择</option>
                                    <option value="ACTIVE">开通</option>
                                    <option value="INACTIVE">关闭</option>
                                    <option value="DELETED">核销</option>
                                </select>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">所属省份：</label>
                                <select class="input_text" id="province" name="province" value="${param.province}"
                                onchange="queryArea(value)">
                                    <option value="">请选择</option>
                                </select>
                            </p>
                        </li>
                        <li>
                            <p>
                                <label class="text_tit">所属城市：</label>
                                <select class="input_text" id="city" name="city" value="${param.city}">
                                    <option value="">请选择</option>
                                </select>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" onclick="verification()" id="queryid" value="查询" />
                        <input class="btn_sure fw" type="reset" value="重置" />
                        <input type="button"  class="btn_sure fw" id="toShopAdd1" onclick="toShopAdd()" value="新增网点" />
                        <input type="button"  class="btn_sure fw" id="toBatchAdd1" onclick="toBatchAdd()" value="EXCEL批量新增" />
                        <input type="button"  class="btn_sure fw" id="downloadTemplate1" onclick="downloadTemplate()" value="Excel模板下载" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <div style="margin: 0px auto;">
            <div>
                <q:table queryService="ymfDownloadQueryService" queryKey="queryShopInfo" class="list" formId="form"
                         doSum="false">
                    <q:nodata>无数据</q:nodata>
                    <q:column title="序号" value="${mid}" />
                    <q:column title="商户编号" value="${customer_number}" />
                    <q:column title="商户名称" value="${customer_name}" />
                    <q:column title="网点编号" value="${shop_number}" />
                    <q:column title="网点名称" value="${shop_name}" />
                    <q:column title="所属省份" value="${province_name}" />
                    <q:column title="所属城市" value="${city_name}"></q:column>
                    <q:column title="详细地址" value="${address}"></q:column>
                    <q:column title="联系人" value="${link_man}"></q:column>
                    <q:column title="电话号码" value="${link_phone}"></q:column>
                    <q:column title="状态" >
                        <e:write type="com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus"
                                 name="${status}"></e:write>
                    </q:column>
                    <q:column title="创建时间">
                        <fmt:formatDate value="${create_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </q:column>
                    <q:column title="操作" escapeHtml="false"  width = "130px">
                        <a href="javascript:shopDetail('${id}')">详情</a>
                        <a href="javascript:modifyShop('${id}')">修改</a>
                        <c:if test="${status !='DELETED' }">
                            <a href="javascript:destroyShop('${id}','${customer_name}','${shop_name}')">核销</a>
                        </c:if>
                    </q:column>
                </q:table>
            </div>

        </div>
    </div>
</div>
</body>
</html>
