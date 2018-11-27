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
