<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增商户</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/customer/customer.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>--%>
    <script>
        $(function() {

            loadBusinessCode();
            loadDefaultGratuity();
//            loadSelectMessage("CustomerType","customerType");
            loadCheckBoxMessage("CollectType","collecttypeinfocheckbox","collectTypeInfo");
            loadCheckBoxMessage("PayType","paytypeinfocheckbox1","payTypeInfo");
            $("#periodul").hide();
            $(".appType").change(function() {
                var appType = $('#appType input[name="appType"]:checked ').val();
                if(appType=='INDUSTRY'){
                    $("#periodul").show();
                }else{
                    $("#periodul").hide();
                }
            });
        })

        function loadBusinessCode(){
            var bizCodeList = $("#businessId");
            $.ajax({
                url: "${pageContext.request.contextPath}/business/loadBusinessCode",
                type:"POST",
                dataType: "json",
                success: function (json) {
                    if (json.length == 0) {
                        bizCodeList.empty();
                        bizCodeList.append('<option value="">无业务方，待配置</option>');
                    } else {
                        var selc = '';
                        var opt = '';
                        $.each(json, function (index, entry) {
                            if(entry['bizCode']=='YMF'){
                                opt = '<option value="' + entry['id'] + '" selected>' + entry['bizName'] + '</option> ';
                            }else {
                                opt = '<option value="' + entry['id'] + '">' + entry['bizName'] + '</option> ';
                            }
                            selc =selc + opt;
                        });
                    }
                    $("#businessId").append(selc);
                }

            });
        }
        function loadSelectMessage(type,id){
            var selectList =$("#"+id+"");
            $.ajax({
                url: "${pageContext.request.contextPath}/dictionary/getDictionariesByType",
                data: {"type": type},
                dataType: "json",
                success: function (json) {
                    if (json.length == 0) {
                        selectList.empty();
                        selectList.append('<option value="">待配置</option>');
                    } else {
                        var selc = '';
                        var opt = '';
                        $.each(json, function (index, entry) {
                            opt = '<option value="' + entry['code'] + '">' + entry['name'] + '</option> ';
                            selc =selc + opt;
                        });
                    }
                    $("#"+id+"").append(selc);
                }

            });
        }
        function loadCheckBoxMessage(type,id,name){
            var checkboxList =$("#"+id+"");
            $.ajax({
                url: "${pageContext.request.contextPath}/dictionary/getDictionariesByType",
                type:"POST",
                data: {"type": type},
                dataType: "json",
                success: function (json) {
                    if (json.length == 0) {
                        checkboxList.empty();
                        checkboxList.append('查无此类型');
                    } else {
                       var str='';
                        var opt='';
                        $.each(json, function (index, entry) {
                            opt = '<input type="checkbox"  name="'+name+'" value="'+entry['code']+'"/>&nbsp;&nbsp;'+entry['name']+'&nbsp;&nbsp;';
                            if(index>0&&index%7==0){
                                str =str + opt+'</br>';
                            }else{
                                str =str + opt;
                            }

                        });
                    }
                    $("#"+id+"").append(str);
                }
            });
        }

       function addCustomer(){
           var fileUpload = $("#fileUpload").val();
           var options="";
//         此处验证打赏金额
            if($('#manualInput1').attr('checked')){
                var temp = "";
                $.each($('.selfInput'),function (index,entry) {
                    temp =temp+","+$(entry).val();
                })
                temp = temp.substr(1);
                $('#gratuityTmp').val(temp);
            }else if(!$('#gratuity').attr('checked')){
                $('#gratuityTmp').val("-1");
            }

           if(fileUpload==null||fileUpload=="") {
               options = {
               type: "post",
               url: "${pageContext.request.contextPath}/customer/add",
                   dataType: "text",
               success: function (data) {
                   var resp=JSON.parse(data);
                   if(resp.status == "ok"){
                       alert("操作成功!");
                       window.location="${pageContext.request.contextPath}/customer/query";
                   }else if(resp.status == "error"){
                       alert(resp.msg);
                   }else {
                       alert("操作失败!");
                   }
                   }
               }
           }else{
               options = {
                   type: "post",
                   url: "${pageContext.request.contextPath}/customer/addCustomerLogo",
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
                           window.location="${pageContext.request.contextPath}/customer/query";
                       }else if(resp.status == "error"){
                           alert(resp.msg);
                       }else {
                           alert("操作失败!");
                       }
                   }
               }
           }
           var form = $("#form");
           form.ajaxSubmit(options);
       }
       function get2GCustomerName(customerNumber) {
           $.ajax({
               url: "${pageContext.request.contextPath}/customer/get2GCustomer",
               data: {"customerNumber": customerNumber},
               dataType: "json",
               success: function (json) {
                   if(json==null||json.length==0){
                       alert("商编错误,二代不存在!");
                   }else{
                       $("#customerName").attr("value",json.customerName);
                   }
               }

           });
       }
//      加载默认打赏模板
        function loadDefaultGratuity() {

            $.ajax({
                url: "${pageContext.request.contextPath}/dictionary/getDictionariesByType",
                data: {"type": 'DefaultGratuity'},
                dataType: "json",
                success: function (json) {
                    if (json.length == 0) {
                        $('#manualUl').hide();
                    } else {
                        if($('#gratuity').attr('checked')){
                            $('#manualUl').show();
                        }else{
                            $('#manualUl').hide();
                        }
                        var selc = '';
                        var opt = '';
                        $.each(json, function (index, entry) {
                            $('#manualInput').attr('checked',true)
                            var values = entry['value'].split(',');
                            $.each(values,function (n,value) {
                                opt = '<input type="text" disabled="true" value="'+value+'"/>';
                                selc = selc+opt;
                            })
                        });
                        $("#manualInput").after("&nbsp;&nbsp;&nbsp;默&nbsp;&nbsp;&nbsp;认&nbsp;"+selc+'<br>');
                    }

                }

            });
        }
        var reg = /^[0-9]+.?[0-9]*$/;
        function checkInput() {
            if($('#manualInput1').attr("checked")){
                if(!reg.test($(this).val())){
                    alert("金额不知道填写数字吗?是不是傻!");
                }
            }
        }
        $("document").ready(function(){

            $('#checkAll').click(function () {
                 if($(this).attr("checked")){
                     $("[name='appType']").attr("checked",'true');
                 }
             }
            )

            $('#gratuity').change(function () {

               if($('#gratuity').attr('checked')){
                   $('#manualUl').show();
               }else{
                   $('#manualUl').hide();
               }
            })
        })

    </script>
</head>
<body>
    <form id="form" enctype="multipart/form-data">
        <div class="information">
            <div class="search_tit">
                <h2 class="fw">新增商户</h2>
            </div>
            <div class="clearer"></div>
            <div class="input_cont">
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">商户编号：</label>
                            <input type="text" class="input_text" id="customerNumber"  name="customerNumber" value="${param.customerNumber}" onchange="get2GCustomerName(this.value);"/>
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">商户名称：</label>
                            <input type="text" class="input_text" id="customerName"  name="customerName" value="${param.customerName}" readonly/>
                        </p>
                    </li>
                </ul>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<p>--%>
                            <%--<label class="text_tit">商户类型：</label>--%>
                            <%--<select class="select" id="customerType"  name="customerType" value="${param.customerType}">--%>
                                <%--<option value="">请选择</option>--%>
                            <%--</select>--%>
                        <%--</p>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">所属行业：</label>
                            <dic:select type="IndustryType" code="${param.industryType}" id="industryType" name="industryType" clazz="select" />

                        </p>
                    </li>
                </ul>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<p>--%>
                            <%--<label class="text_tit">商户等级：</label>--%>
                            <%--<select id="customerLevel" name="customerLevel" class="select">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<option value="A">甲级</option>--%>
                                <%--<option value="B">乙级</option>--%>
                                <%--<option value="C">丙级</option>--%>
                            <%--</select>--%>
                        <%--</p>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">业务方：</label>
                            <select class="select" id="businessId" name="businessId">
                                <option value="">请选择业务方</option>
                            </select>
                        </p>
                    </li>
                </ul>
                <ul id="appType">
                    <li>
                        <p>
                            <label class="text_tit">应用版本：</label>
                            <span id="appTypeAdio">
                                <input type="checkbox" name="appType" value="NORMAL" class="appType" checked/>&nbsp;&nbsp;&nbsp;标准版
                                <input type="checkbox" name="appType" value="INDUSTRY" class="appType"/>&nbsp;&nbsp;&nbsp;行业版
                                <input type="checkbox"  id ="checkAll" />&nbsp;&nbsp;&nbsp;全选
                            </span>
                        </p>
                    </li>
                    <li>
                        <p>
                            <label class="text_tit">营销版本：</label>
                            <span id="popularizeAdio">
                                <input type="checkbox" value="GRATUITY" id ="gratuity" />&nbsp;&nbsp;&nbsp;打赏版
                            </span>
                        </p>
                    </li>
                </ul>
                <ul id="manualUl">
                    <li>
                            <label class="text_tit">打赏金额：</label>
                            <span id="manualSpan">
                                 <input type="radio" name="gratuityRadio" id="manualInput" class="appType" />
                                 <input type="radio" name="gratuityRadio" id="manualInput1" class="appType" />&nbsp;&nbsp;&nbsp;自定义
                                 <input type="hidden" name="gratuityTmp" id="gratuityTmp" />
                                 <input type="text"  onchange="checkInput();" class="selfInput"/><input type="text"  onchange="checkInput();" class="selfInput"/><input type="text" onchange="checkInput();" class="selfInput"/><input type="text" onchange="checkInput();" class="selfInput"/>
                            </span>
                        </span>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">收款方式：</label>
                            <span id="collecttypeinfocheckbox">
                            </span>
                        </p>
                    </li>

                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">支付方式：</label>
                            <span  id="paytypeinfocheckbox1">
                            </span>
                            <span id="paytypeinfocheckbox2">
                            </span>
                        </p>
                    </li>

                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">产品权限：</label>
                            <input type="radio" name="status" value="ACTIVE" checked="checked"/>&nbsp;&nbsp;开通
                            <input type="radio" name="status" value="INACTIVE"/>&nbsp;&nbsp;关闭
                        </p>
                    </li>
                </ul>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">结算类型：</label>
                            <input type="radio" name="balanceProduct" value="T1" checked="checked"/>&nbsp;&nbsp;T1结算
                            <input type="radio" name="balanceProduct" value="S0"/>&nbsp;&nbsp;秒到
                        </p>
                    </li>
                </ul>
                <div id="periodul">
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">订单有效期：</label>
                            <input type="text" name="validityPeriod" id="validityPeriod"/>&nbsp;&nbsp;&nbsp;天
                        </p>
                    </li>
                </ul>
                    </div>
                <ul>
                    <li>
                        <p>
                            <label class="text_tit">商户LOGO：</label>
                            <input type="file" name="fileUpload" id="fileUpload" />
                        </p>
                    </li>
                </ul>
                <div class="btn">
                    <input type="button"  class="btn_sure fw" id="queryid"  onclick="addCustomer();" value="添加" />
                    <input type="reset"  class="btn_sure fw" id="resetid" value="清空" />
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </form>
</body>
</html>
