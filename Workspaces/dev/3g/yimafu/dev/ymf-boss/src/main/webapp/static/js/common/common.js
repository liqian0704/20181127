/**
 * 统一处理ResponseMessage
 * Created by chen.liu on 17/3/13.
 */
function handleResponse(resp) {
    if(resp.status=='ok'){
        alert("操作成功!");
    }else if(resp.status=='error'){
        alert(resp.msg);
    }else{
        alert("操作失败!");
    }
}

/**
 * 返回
 */
function window_back() {
    window.history.go(-1);
}


//弹出窗口
function showme() {
    var divid1 = document.getElementById('maskid');
    var divid2 = document.getElementById('boxid');
    var divid3 = document.getElementById("poptop");
    divid1.className = "mask";
    divid1.style.height = (document.documentElement.clientHeight > document.body.clientHeight?document.documentElement.clientHeight:document.body.clientHeight)+(document.documentElement.scrollTop || document.body.scrollTop)+"px";
    divid2.className = "popbox";
    divid3.style.marginTop = (document.documentElement.clientHeight-divid3.offsetHeight)/2 + (document.documentElement.scrollTop || document.body.scrollTop)+"px";
}

//关闭弹出窗口
function hiddenme() {
    var divid1 = document.getElementById('boxid');
    var divid2 = document.getElementById('maskid');
    divid1.className = "vnone";
    divid2.className = "vnone";
}