<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/select2/select2.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/static/select2/select2.min.js"></script>
    <title>批量生成二维码</title>
    <script type="application/javascript">
        window.onload=changeSel;
           function  getSales() {
                $.ajax({
                    url: "getSalesInfo",
                    type: 'post',
                    dataType: "json",
                    success: function (msg) {
                        if (null == msg || msg == "false" || msg.status == "false") {
                            alert("查询异常,请重试");
                            return;
                        } else {
                            $('#sales').empty();
                            var selc = '';
                            var opt = '';
                            $.each(msg.result, function (key, value) {
                                opt = '<option value="' +value.XINGMING + ',' + value.YOUXIANGQIANZHUI + ','+value.GONGHAO  + '">' + value.XINGMING + ',' + value.YOUXIANGQIANZHUI + ','+value.GONGHAO + '</option> ';
                                selc = selc + opt
                            })

                            $('#sales').append("<option value='' ></option>" + selc);
                            $('#sales').select2();

                        }
                    }
                })
           }

        function toQrcodeDetail(id){
            window.location="../qrCode/toDetail?id="+id;
        }
        function createQrcode(customerNumber){
            window.location="../qrCode/createQrcode?customerNumber="+customerNumber;
        }
        function toQrcodeModify(id){
            window.location="../qrCode/modifyQrcode?id="+id;
        }
        function checkParam() {
            $('#createid').disabled=true;
            var  reg =/^[1-9]+[0-9]*]*$/;
            var qrCount = $('#qrCount').val().trim();
            if(!reg.test(qrCount)){
                alert("亲,生成数量!!!数量!!!填正整数会不会!");
                $('#createid').disabled=false;
                return;
            } else if(qrCount>50){
                alert("一次最多生成50个,不要太贪心啊亲!");
                $('#createid').disabled=false;
                return;
            }else if(qrCount.length == 0||qrCount<1){
                alert("一个也不生成,你瞎点个球!");
                $('#createid').disabled=false;
                return;
            }
            var qrOwner = $("#qrOwner").val();
            if(""==qrOwner||null==qrOwner){
                alert("生成二维码对象你得选一个吧!");
                return;
            }
            if(qrOwner =='SALES'){
                if($('#sales').val()==''||$('#sales').val()==null){
                    alert("选销售,不选销售姓名,是不是傻!!");
                    return;
                }
              var invoice =  $('#qrInvoice').val();
                if('' ==invoice || null ==invoice){
                    alert("请填写单据号!");
                    return;
                }
            }
            var owner = $("#owner").attr('name');
            if(owner == 'customerNumber'){
                var qrBusinessType = $("#qrBusinessType").val();
                if(""==qrBusinessType||null==qrBusinessType){
                    alert("二维码版本都不选啊!亲!!");
                    return;
                }
                var customerNumber = $("#owner").val();
                if ( null != customerNumber && '' !=customerNumber) {
                    $.ajax({
                        url: "getCustomerInfo",
                        type: 'post',
                        data: {"customerNumber": customerNumber},
                        dataType:"json",
                        success: function (msg) {
                            if(null==msg){
                                alert("商户编号查找无记录，请重新输入");
                                $('#createid').disabled=false;
                                return;
                            }else {
                                $('#createform').submit();
                                $('#createid').disabled=false;
                            }
                        }
                    });
                }else{
                    alert('请填写商户编号啊亲,选了商户还不填,是不是傻!');
                    $('#createid').disabled=false;
                }
            }else{
                $('#createform').submit();
                $('#createid').disabled=false;
            }
        }
        function changeSel() {

             $('#customerLi').hide();
             $('#businessTypeLi').hide();

             var sel =  $("#qrOwner").val();
             if(sel=='SALES'){
               $("#selName").text('销售:');
               $("#sales").show();
                 getSales();
               $("#owner").empty().hide();
            }else if(sel=='AGENT'){
               $("#selName").text('代理商编号:');
                 $("#sales").empty().hide();
                 $("#owner").show();
                 $("#owner").attr("name","agentNumber");
             }else if(sel=='CUSTOMER'){
                 $("#selName").text('商户编号:');
                 $("#sales").empty().hide();
                 $("#owner").show();
                 $("#owner").attr("name","customerNumber");
                     $("input[name='customerNumber']").focusout(function () {
                         var customerNumber = $("#owner").val();
                         if(null!=customerNumber && ''!=customerNumber){
                             checkCustomer(customerNumber);
                         }
                     })
             }
        }
        function checkCustomer (customerNumber){
            $.ajax({
                url: "getCustomerInfo",
                type: 'post',
                data: {"customerNumber": customerNumber},
                dataType:"json",
                success: function (msg) {
                    if(null==msg){
                        alert("商户编号查找无记录，请重新输入");
                        return;
                    }else if(null == msg.customer){
                        alert(msg.msg);
                        return;
                    }else {
                        $('#customerName').val(msg.customer.customerName)
                        $('#customerLi').insertAfter('#businessLi');
                        $('#businessTypeLi').insertAfter('#qrInvoiceLi');
                        $('#customerLi').show();
                        $('#businessTypeLi').show();
                        var selc = '';
                        var opt = '';
                        $.each(msg.businessMap,function (key,value) {
                            opt = '<option value="' +key + '">' +value + '</option> ';
                            selc = selc+opt
                        })
                        $('#qrBusinessType').empty();
                        $('#qrBusinessType').append("<option value='' >请选择</option>"+selc);
                    }
                }
            });
        }

      function toCreateQrCode() {
          $.ajax({
              url: "${pageContext.request.contextPath}/qrCode/cancelQrcode",
              type: 'post',
              data: {"qrCodeId": qrCodeId},
              dataType:"text",
              success: function (text) {
                  if (text == "success") {
                      alert('核销成功');
                      window.location="${pageContext.request.contextPath}/qrCode/query";
                  }else if(text=="NOTEXISTQRCODE") {
                      alert("二维码不存在!");
                  }else{
                      alert("操作失败!");
                  }
              }
          });
      }


    </script>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="create" id="createform" method="post">
            <div class="search">
                <div class="search_tit">
                    <h2 class="fw fleft f14">批量生成二维码</h2>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">生成二维码对象：</label>
                                <select id="qrOwner" name="qrOwner"  onchange="changeSel()" class="input_text">
                                    <option value="">请选择</option>
                                    <option value="SALES">销售</option>
                                    <option value="AGENT">代理商</option>
                                    <option value="CUSTOMER">商户</option>
                                </select>
                            </p>
                        </li>
                        <li id="businessLi">
                            <p>
                                <label class="text_tit" id="selName">二维码业务方：</label>
                                <input type="text" class="input_text" id="owner" name="" value="" />
                                <select id="sales" name="sales" hidden="hidden" class="input_text">
                                </select>
                                <%--<input type="text" class="input_text" id="agentNumber" name="agentNumber" value=""/>--%>
                                <%--<input type="text" class="input_text" id="customerNumber" name="customerNumber" value=""/>--%>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <ul class="fix">
                        <li>
                            <p>
                                <label class="text_tit">生成数量：</label>
                                <input type="number" min="1" max="50" class="input_text" id="qrCount" name="qrCount" value="${param.qrCount}"/>
                            </p>
                        </li>
                        <li id="qrInvoiceLi">
                            <p>
                                <label class="text_tit">单据号：</label>
                                <input type="" class="input_text" id="qrInvoice" name="qrInvoice" value="${param.qrInvoice}"/>
                            </p>
                        </li>
                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <div class="btn">
                        <input type="button"  class="btn_sure fw" id="createid" onclick="checkParam()" value="生成二维码" />
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <li id="customerLi" hidden="hidden">
            <p>
                <label class="text_tit" id="selName">商户名称：</label>
                <input disabled="disabled" class="input_text" id="customerName" name="" value=""/>
                <%--<input type="text" class="input_text" id="agentNumber" name="agentNumber" value=""/>--%>
                <%--<input type="text" class="input_text" id="customerNumber" name="customerNumber" value=""/>--%>
            </p>
        </li>
        <li id="businessTypeLi" hidden="hidden">
            <p>
                <label class="text_tit">二维码版本：</label>
                <select id="qrBusinessType" name="businessType"  onchange="" class="input_text">
                </select>
            </p>
        </li>
    </div>
</div>
</div>
</body>
</html>
