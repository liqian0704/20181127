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


/**
 *
 * {
 *  formId :
 *  buttonId :
 *  queryId :
 *  downloadType :
 *  url :
 * }
 * @param option 配置参数
 */
function func_download(option) {
    var formId = option.formId || "form";
    var buttonId = option.buttonId || "download";
    var downloadType = option.downloadType || "excel";
    var url = option.url || "down";
    var form = document.getElementById(formId);
    var btn = document.getElementById(buttonId);
    btn.setAttribute("disabled", "true");
    var node_downloadFlag = self_func_create_input("hidden", "download", "true");
    var node_downloadType = self_func_create_input("hidden", "downloadType", downloadType);
    var node_queryId = self_func_create_input("hidden", "queryId", option.queryId);
    form.appendChild(node_downloadFlag);
    form.appendChild(node_downloadType);
    form.appendChild(node_queryId);
    form.setAttribute("action", url);
    form.submit();
    form.setAttribute("action", "");
    btn.removeAttribute("disabled");
    form.removeChild(node_downloadFlag);
    form.removeChild(node_downloadType);
    form.removeChild(node_queryId);
}

/**
 *
 * @param type input type
 * @param name input name
 * @param value input value
 * @returns {Element}
 */
function self_func_create_input(type, name, value) {
    var node = document.createElement('input');
    node.setAttribute('type', type);
    node.setAttribute('name', name);
    node.setAttribute('value', value);
    return node;
}

/**
 *
 * @param id queryId
 * @param merType 成员类型
 * @param complex 是否复杂查询
 * @returns {{url: string, dataType: string, delay: number, data: data, processResults: processResults, cache: boolean}}
 */
function searchConfig(id,complex) {
    var q_url;
    if (complex) {
        q_url = out_ctx + "/search/complex/" + id;
    } else {
        q_url = out_ctx + "/search/" + id;
    }
    return {
        url: q_url,
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                q: params.term, // search term
                page: params.page
            };
        },
        processResults: function (data, params) {
            params.page = params.page || 1;
            var array;
            array = data.items;
            return {
                results: array,
                pagination: {
                    more: (params.page * 10) < data.totalCount
                }
            };
        },
        cache: true
    }
}

/**
 * 下拉选项显示格式
 * @param data
 * @returns {*}
 */
function searchCustomerResult(data) {
    if (data.loading) return "请输入查询条件";
    var markup = "" + data.id;
    markup += " | " + data.name;
    return markup;
}

/**
 * 选中后显示格式
 * @param data
 * @returns {*}
 */
function searchCustomerSelection(data) {
    $("#customerNumber").val(data.id);
    return data.id;
}
