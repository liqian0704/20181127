/**
 * Created by xiaobin.liu on 17/4/19.
 */
/**
 * 金额输入判断
 * @param id  对应文本域的id
 */
function onAmtInput(id) {
//    Tools.alert(value);
    var transAmt = $("#" + id);
    var value = transAmt.val();
    if(value.length > 9) {
        value = value.slice(0,value.length - 1);
        transAmt.val(value);
        return;
    }
    if(/^\d+(\.\d{1,2})?$/g.test(value)){
        var amt = Number(value) * Number(100);
        if(amt > Number(99999999)){
            value = value.slice(0,value.length - 1);
            transAmt.val(value);
        }
    } else {
        if(value.indexOf(".") >= 0 ) {
            var arr = value.split(".");
            if (arr[1].length > 2) {
                value = value.slice(0,value.length - 1);
                transAmt.val(value);
                return;
            }
        }
    }
}