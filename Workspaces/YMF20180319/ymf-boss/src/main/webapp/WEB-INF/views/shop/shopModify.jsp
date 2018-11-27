<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改网点</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/customer/customer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/checkUtils.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>--%>
    <script>
        $(function () {
            $("#status").val("${shop.status}") ;

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

            var province = '${shop.province}';
            if (province != '') {
                $('#province').val(province);
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
                            var cityValue = '${shop.city}' ;
                            $('#city').val(cityValue);
                        }
                    }
                }
            })
        }

        /**
         * 保存网点
         * @returns {boolean}
         */
        function modifyShop(){
            var customerNumber = $("#customerNumber").val();
            var customerName = $("#customerName").val();
            var shopName = $("#shopName").val();
            var province = $("#province").val();
            var city = $("#city").val();
            var address = $("#address").val();
            var linkMan = $("#linkMan").val();
            var linkPhone = $("#linkPhone").val();

            var provinceName = $("#province").find("option:selected").text();
            var cityName = $("#city").find("option:selected").text();

            if (provinceName == "") {
                alert("未获取省份");
                return false;
            }
            if (cityName == "") {
                alert("未获取城市");
                return false;
            }
            $("#provinceName").val(provinceName);
            $("#cityName").val(cityName);

            if (customerNumber == "") {
                alert("商户编号必填");
                return false;
            }
            if (customerName == "") {
                alert("商户编号必填");
                return false;
            }
            if (shopName == "") {
                alert("网点名称必填");
                return false;
            }
            if (province == "") {
                alert("请选择省份");
                return false;
            }
            if (city == "") {
                alert("请选择城市");
                return false;
            }
            if (linkMan == "") {
                alert("请填写联系人姓名");
                return false;
            }
            if (linkPhone == "") {
                alert("请填写联系人电话");
                return false;
            }
            if (!checkMobile(linkPhone)) {
                alert("请输入正确的手机号码!");
                return false;
            }

            var options = {
                type: "post",
                url: "${pageContext.request.contextPath}/shop/modifyShop",
                dataType: "text",
                success: function (data) {
                    var resp = JSON.parse(data);
                    if(resp.status == "ok"){
                        alert("操作成功!");
                        window.location="${pageContext.request.contextPath}/shop/query";
                    }else if(resp.status == "error"){
                        alert(resp.msg);
                    }else {
                        alert("操作失败!");
                    }
                }
            }
            var form = $("#form");
            form.ajaxSubmit(options);
        }

        /**
         * 联查商户名称
         * @param customerNumber
         */
        function queryCustomerName(customerNumber) {
            $("#customerName").attr("value","");
            $.ajax({
                url: "${pageContext.request.contextPath}/shop/queryCustomerName",
                data: {"customerNumber": customerNumber},
                dataType: "json",
                success: function (json) {
                    if(json.status == "ok"){
                        $("#customerName").attr("value",json.content);
                    }else{
                        alert(json.msg);
                    }
                }

            });
        }


    </script>
</head>
<body>
    <form id="form" >
        <input type="hidden" name="id" value="${shop.id}">
        <input type="hidden" name="provinceName" id="provinceName">
        <input type="hidden" name="cityName" id="cityName">
        <div class="information">
            <div class="search_tit">
                <h2 class="fw">修改网点</h2>
            </div>
            <div class="clearer"></div>
            <div class="input_cont">
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">商户编号：</label>
                            <input type="text" readonly class="input_text" id="customerNumber"  name="customerNumber"
                                   value="${shop.customerNumber}" onchange="queryCustomerName(this.value);"/>
                            <label class="text_tit">商户名称：</label>
                            <input type="text" class="input_text" id="customerName"  name="customerName"
                                   value="${customer.customerName}" readonly/>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">网点名称：</label>
                            <input type="text" style="width:300px" class="input_text" id="shopName"  name="shopName" value="${shop.shopName}"/>

                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">所属省份：</label>
                            <select id="province" name="province"  class="input_text" onchange="queryArea(value)">
                            </select>
                            <label class="text_tit">状态：</label>
                            <select type="text" class="input_text" id="status"  name="status"/>
                            <option value="ACTIVE">开通</option>
                            <option value="INACTIVE">关闭</option>
                            </select>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">所属城市：</label>
                            <select id="city" name="city"  class="input_text">
                            </select>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">详细地址：</label>
                            <textarea type="textfield" cols="90" rows="1" class="textfield"  id="address"
                                      name="address">${shop.address}</textarea>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">联系人姓名：</label>
                            <input type="text" class="input_text" id="linkMan"  name="linkMan" value="${shop.linkMan}"/>
                            <label class="text_tit">联系人电话：</label>
                            <input type="text" class="input_text" id="linkPhone"  name="linkPhone" value="${shop.linkPhone}"/>
                        </p>
                    </li>
                </ul>
                </div>
                <div class="btn">
                    <input type="button"  class="btn_sure fw" id="queryid"  onclick="modifyShop();" value="保存" />
                    <input type="reset"  class="btn_sure fw" id="resetid" value="清空" />
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </form>
</body>
</html>
