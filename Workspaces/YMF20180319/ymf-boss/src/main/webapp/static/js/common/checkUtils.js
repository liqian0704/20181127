//通用的校验相关js

/** 手机号码格式校验 */
function checkMobile(str) {
    var re = /^1\d{10}$/
    if (re.test(str)) {
        return true;
    } else {
        return false;
    }
}


/** 邮箱账号校验 */
function checkEmail(str){
    var
        re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
    if(re.test(str)){
        return true;
    }else{
        return false;
    }
}