/**
 * Created by dongxulu on 16/12/27.
 */
// id是支付方式的id,name是支付方式参数名 type是收款方式编码
function jqGridInclude()
{
    var t = "${pageContext.request.contextPath}";
}
function getayTypeByCollection(code,id,name,url){
    var checkboxList =$("#"+id+"");
    $.ajax({
        url: url,
        type:"POST",
        data: {"code": code},
        dataType: "json",
        success: function (result) {
            if (result == null) {
                checkboxList.empty();
                checkboxList.append('查无此类型');
            } else {
                if(code==result.code){
                    var payTypes = result.value.split(",");
                    if(payTypes.length>0){
                        $.each(payTypes,function (index,value) {
                            getDictionariesByCode(value,id,name,url)
                        })
                    }
                }
            }
        }
    });
}
function modifyPayTypeByCollection(code,id,flag,url){
    var checkboxList =$("#"+id+"");
    $.ajax({
        url: url,
        type:"POST",
        data: {"code": code},
        dataType: "json",
        success: function (result) {
            if (result == null) {
                checkboxList.empty();
                checkboxList.append('查无此类型');
            } else {
                if(code==result.code){
                    var payTypes = result.value.split(",");
                    if(payTypes.length>0){
                        $.each(payTypes,function (index,value) {
                            if(flag){
                                $("span[id='"+value+"']").show();
                                $("input[value='"+value+"']").attr("checked",true);
                            }else{
                                $("input[value='"+value+"']").attr("checked",false);
                                $("span[id='"+value+"']").hide();
                            }

                        })
                    }
                }
            }
        }
    });
}

function   getDictionariesByCode(code,id,name,url){
    var checkboxList =$("#"+id+"");
    $.ajax({
        url: url,
        type:"POST",
        data: {"code": code},
        dataType: "json",
        success: function (result) {
            if (result == null) {
                checkboxList.empty();
                checkboxList.append('查无此类型');
            } else {
                var str='';
                var opt='';
                opt = '<input type="checkbox"  name="'+name+'" value="'+result.code+'"/>&nbsp;&nbsp;'+result.name+'&nbsp;&nbsp;';
                str =str + opt;
            }
            $("#"+id+"").append(str);
        }
    });
}