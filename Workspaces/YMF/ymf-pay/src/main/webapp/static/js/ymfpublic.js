/*下拉菜单的调用
downMenu("insert","select","select-ul",matchArr,lable);
"insert",   为代插入的div的ID
"select",   input框的ID
"select-ul",ul的I的
matchArr    下拉选项的数组
'公司名称':   标签名称
调用实例：
 var matchArr=['1易宝支付1','2易宝支付2','3易宝支付3','4易宝支付4','5易宝支付5'];
 downMenu("insert","select","select-ul",matchArr,'公司名称：');
*/

var downMenu=function(param){
  function init(param){
            var html=[];
            html.push('<div class="form-group">'+
                        '<label class="" for="">'+param.label+'</label>'+
                        '<div class="form-inlineblock" >'+
                            '<i class="iconfont select-icon">&#xe600;</i>'+
                           '<input type="text" id="'+param.inputId+'" class="form-control selectTile" data-id="">'+
                            '<ul id="'+param.ulId+'" class="downmenu displayN">');
            for(var i=0;i<param.data.length;i++){
                html.push('<li data-id="'+param.data[i][param.key2name]+'">'+param.data[i][param.key1name]+'</li>')
            }                
            html.push('</ul>'+
                        '<p></p>'+
                        '</div>'+
                      '</div>');
            $('#'+param.insertId).append(html.join(""));
            $('#'+param.inputId).focus(function(){
                $('#'+param.ulId).removeClass('displayN');
            });
            document.getElementById(param.inputId).addEventListener('input',function(e){
                      var value = e.target.value;
                      $('#'+param.ulId).empty();
                      var text=value;
                      var html=[];
                      for(var i=0;i<param.data.length;i++){
                          if(text!=='' && text!==null){
                              if(param.data[i][param.key1name].indexOf(text)!==-1){
                                   html.push('<li data-id="'+param.data[i][param.key2name]+'">'+param.data[i][param.key1name]+'</li>');
                              }
                          }else{
                              html.push('<li data-id="'+param.data[i][param.key2name]+'">'+param.data[i][param.key1name]+'</li>');
                          }
                          
                      }
                      $('#'+param.ulId).append(html.join("")).removeClass('displayN');  
            });
            $('#'+param.insertId).on('click','li',function(){  //下拉菜单Li的点击事件  
                var text=$(this).html();
                $('#'+param.inputId).val(text);
                $('#'+param.inputId).attr('data-id',$(this).attr('data-id'));
                $('#'+param.ulId).addClass('displayN');
            });
            // $(document).bind('click',function(e){
            //     var clickme=$(e.target);
            //     if(!clickme.hasClass("selectTile")){      //在不是列表输入区域单击时（有可能是空白区或者下拉列表中单击）  
            //          $('.downmenu').addClass('displayN');
            //     }else{                                    //先全体隐藏，再显示点中的下拉列表层  
            //          $('.downmenu').addClass('displayN');
            //          clickme.next().removeClass('displayN');
            //     }

            // });
    }
    function validateMenu(param){
          var inputValue=$('#'+param.inputId).val();
          if(inputValue!==''){
              for(var i=0;i<param.data.length;i++){
                if(param.data[i][param.key2name]==$('#select').attr('data-id')){
                  if(param.data[i][param.key1name]!==inputValue){
                      $('#'+param.inputId).parent().parent().addClass('has-error');
                      $('#'+param.inputId).parent().find('p').html('请从下拉列表中选择值'); 
                      return false;                      
                  }else{
                      $('#'+param.inputId).parent().parent().removeClass('has-error');
                      $('#'+param.inputId).parent().find('p').html('');
                      return true;
                  }

                }
              }
          }else{
             $('#'+param.inputId).parent().parent().addClass('has-error');
             $('#'+param.inputId).parent().find('p').html('不能为空');
             return false;    
          }

          
    }
    return {
    init : init,
    validata:validateMenu,
   }
}();
     

// -------------------------------------------------------------------------------------------------------------------
// 跨浏览器的事件、事件对象处理程序
// -------------------------------------------------------------------------------------------------------------------
var EventUtil = {// 
  addHandler : function(element, type, handler) {
    if (element.addEventListener) { // IE9、Firefox,Safari,Chrome和Opera支持DOM2级事件处理程序
      element.addEventListener(type, handler, false);
    } else if (element.attachEvent) {// IE,Opera
      element.attachEvent("on" + type, handler);
    } else {// DOM0级
      element["on" + type] = handler;
    }
  },
  removeHandler : function(element, type, handler) {
    if (element.removeEventListenter) {
      element.removeEventListenter(type, handler, false);
    } else if (element.detachEvent) {
      element.detachEvent("on" + type, handler);
    } else {
      element["on" + type] = null;
    }
  },
  getEvent : function(event) {
    return event ? event : window.event;
  },
  getTarget : function(event) {
    return event.target || event.srcElement;
  },
  preventDefault : function(event) {
    if (event.preventDefault) {
      event.preventDefault();
    } else {
      event.returnValue = false;
    }
  },
  stopPropagation : function(event) {
    if (event.stopPropagation) {
      event.stopPropagation();
    } else {
      event.cancelBubble = true;
    }
  },

};

// -------------------------------------------------------------------------------------------------------------------
// 表单校验
// -------------------------------------------------------------------------------------------------------------------
/*
 * 功能说明 校验表单，校验表单中的输入框,加载页面时调用 HTML代码示例 <input type="text" data-rule="text"/>
 * 示例参数说明 data-rule:可省，为空和省略则表示不校验。
 * 校验规则名,具体规则参见validateRules中的rule,提示信息参见message。
 * 
 * 
 * 函数 validate(formId) 调用示例 validate("formId","submit") 参数说明 formId:表单ID，必填
 * submit:表单提交参数，可省，省略表示表单校验初始化，在提交时使用该参数，表示提交时校验表单,如
 * EventUtil.addHandler(document.getElementById('abc'), "click", function(event) {
 * return validate('formId','submit'); })
 */

function validate(formId, submit) {
  if (!formId) {
    formError('noId');
    return false;
  }
  var _validate = true, _itemValidate = true, _ruleList = [], _ruleItem, _formList = [], _valueList = [], _inputList, _value, _formObj, _btnObj, _readonly = "";

  _formObj = document.getElementById(formId);
  if (_formObj === null) {
    formError('noId');
    return false;
  }
  _inputList = _formObj.getElementsByTagName('input');

  // 过滤掉无需校验的对象
  for (var j = 0; j < _inputList.length; j++) {
    _readonly = _inputList[j].getAttribute('readOnly');
    _ruleItem = _inputList[j].getAttribute('data-rule');
    if ((_readonly === null || _readonly === 'null')
        && _inputList[j].type !== 'hidden'
        && (_ruleItem != '' || _ruleItem != null)) {
      _formList.push(_inputList[j]);
      _ruleList.push(_ruleItem);
    }
  }

  if (_formList !== null && _formList !== "" && _formList !== undefined) {
    // 多参分支处理
    if (arguments.length == 2) {
      return formValidate(_formList, _ruleList)
    } else if (arguments.length == 1) {
      for (var i = 0; i < _formList.length; i++) {
        if (_ruleList[i]) {
          inputHandleBlur(_formList[i], _ruleList[i]);
        }
      }
    }

  }
}

function inputHandleBlur(obj, rule) {
  EventUtil.addHandler(obj, "blur", function(event) {
    event = EventUtil.getEvent(event); // 获取事件对象
    var target = EventUtil.getTarget(event); // 获取事件对象目标
    _value = trim(target.value);
    if (_value.length > 0) {
      _validate = inputHandleBlurCheck(target, rule, _value);
    }
  })
}

function inputHandleBlurCheck(obj, rules, value) {
  var checkResult = inputValidate(rules, value);
  inputInsertError(obj, checkResult);
    $(obj).closest('.fll').removeClass('tishi2');
  if (checkResult.validate) {
    $(obj).closest('.form-group').removeClass('has-error');
  } else {
    $(obj).closest('.form-group').addClass('has-error');
  }
  return checkResult.validate;
}

function inputInsertError(obj, checkResult) {// 插入P
  var pObj = obj.parentNode.getElementsByTagName("p");
  if (pObj.length === 0) { // 不存在P
    var pElement = document.createElement("p");
    pElement.innerHTML = checkResult.message;
    obj.parentNode.appendChild(pElement);
    pObj.innerHTML = checkResult.message;
  } else if (pObj[0].innerHTML.length >= 0) { // 已存在P标签
    pObj[0].innerHTML = checkResult.message;
  }
}

function inputSmsSuccess(obj, checkResult) {// 插入P
  var pObj = obj.parentNode.getElementsByTagName("span");
  if (pObj.length === 0) { // 不存在P
    var pElement = document.createElement("span");
    pElement.innerHTML = checkResult.message;
    obj.parentNode.appendChild(pElement);
    pObj.innerHTML = checkResult.message;
  } else if (pObj[0].innerHTML.length >= 0) { // 已存在P标签
    pObj[0].innerHTML = checkResult.message;
  }
}

function inputValidate(text, value) {
  var textArr = new Array;
  if (text.indexOf('&') > 0) { // 判断校验规则是一个还是多个
    textArr = text.split('&'); // 拆分校验规则
  } else {
    textArr[0] = text;
  }
  return inputValidateArr(textArr, value);
}

function inputValidateArr(arr, value) {
  var textReg, validate = true, msg = '', validateItem = true;
  for (var i = 0; i < arr.length; i++) {
    textReg = new RegExp(validateRules.rule[arr[i]]);
    validateItem = textReg.test(value);
    msg = validateItem ? '' : validateRules.message[arr[i]];
    validate = validate && validateItem;
    if(!validateItem){
      break;
    }
  }
  return {
    validate : validate,
    message : msg
  }
}

function formValidate(obj, rules) {
  var _validate = true, _itemValidate = true, _value;
  for (var i = 0; i < obj.length; i++) {
    _value = obj[i].value;
    if (rules[i]) {
      _itemValidate = inputHandleBlurCheck(obj[i], rules[i], _value);

    }
    _validate = _validate && _itemValidate;
  }
  return _validate;
}

function formError(str) {
  switch (str) {
  case 'noId':
    console.log('Form标签的id不能为空');
    break;

  }
}
// 去左右空格;
function trim(s) {
  if (s == undefined || s == '') {
    return '';
  } else {
    return s.replace(/(^\s*)|(\s*$)/g, "");
  }
}

function ajax(options) {
  /*
   * ajax请求
   */
  var _options = {
    async : true,
    method : "POST",
    url : location.href,
    timeout : 10000,
    contentType : 'application/x-www-form-urlencoded; charset=UTF-8'
  }
  if (Util.type(options) == "object") {
    for ( var key in options) {
      _options[key] = options[key];
    }
  }
  _options.data = Util.type(_options.data) == "object" ? params(_options.data)
      : "";
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4) {
      if(xhr.status==302){
        location.assign(loginUrl);
      }
      else if (xhr.status >= 200 && xhr.status < 300 || xhr.status == 304) {
        typeof _options.success == "function" ? _options.success(
            xhr.responseText, xhr.status, xhr) : false;
      } else {
        typeof _options.error == "function" ? _options.error(
            xhr.status, xhr) : false;
      }
    }

  };
  function params(data) {
    /*
     * 数据键值对转换成字符串
     */
    var arr = [];
    for ( var key in data) {
      // 特殊字符传参产生的问题可以使用encodeURIComponent()进行编码处理
      arr.push(encodeURIComponent(key) + '='
          + encodeURIComponent(data[key]));
    }
    return arr.join('&');
  }

  if (_options.method == "GET") {
    xhr.open(_options.method, _options.url + "?" + _options.data,
        _options.async);
    xhr.setRequestHeader("Content-Type", _options.contentType);
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    if (_options.async) {
      xhr.timeout = _options.timeout;
      xhr.ontimeout = _options.timeoutCallback || null;
    }
    xhr.send();
  } else {
    xhr.open(_options.method, _options.url, _options.async);
    xhr.setRequestHeader("Content-Type", _options.contentType);
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    if (_options.async) {
      xhr.timeout = _options.timeout;
      xhr.ontimeout = _options.timeoutCallback || null;
    }
    xhr.send(_options.data);
  }
  return xhr;
}