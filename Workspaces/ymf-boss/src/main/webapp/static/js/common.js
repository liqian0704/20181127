var DateUtil = {};
DateUtil.toUnixStamp = function(val) {
	var t_timestamp = Date.parse(val.replace(/-/g, "/")) / 1000;
	return t_timestamp;
};
DateUtil.toDate = function(val, format) {
	var t_date = new Date(parseInt(val) * 1000).Format(format);
	return t_date;
};

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

// 对象定义（构造函数)
// 参数：所有参数须为字符串形式.
// 当参数长度为1时，参数值将是字符串之间连接的分隔符
// 当参数长度大于1时，最后一位将是字符串之间的分隔符,其余的参数将是字符串值
// 当不指定参数时，分隔符默认为空白
// 也可以不指定分隔符，而在ToString中显式指定分隔符
// 如：var str = new StringBuilder(',')； 则在ToString时，将使用,号作为分隔符连接字符串
// var str = new StringBuilder('a','b','c',','); 则在ToString时，将输出 'a,b,c'
function StringBuilder() {
	this._buffers = [];
	this._length = 0;
	this._splitChar = arguments.length > 0 ? arguments[arguments.length - 1]
			: '';

	if (arguments.length > 0) {
		for ( var i = 0, iLen = arguments.length - 1; i < iLen; i++) {
			this.Append(arguments[i]);
		}
	}
}

// 向对象中添加字符串
// 参数：一个字符串值
StringBuilder.prototype.Append = function(str) {
	this._length += str.length;
	this._buffers[this._buffers.length] = str;
};
StringBuilder.prototype.Add = StringBuilder.prototype.append;

// 向对象中添加字符串，并在末尾换行
StringBuilder.prototype.AppendLine = function() {
	if (arguments[0] != undefined && arguments[0] != null) {
		this.Append(arguments[0]);
	} else {
		this.Append("\r\n");
	}
};

// 向对象附加格式化的字符串
// 参数：参数一是预格式化的字符串，如：'{0} {1} {2}'
// 格式参数可以是数组，或对应长度的arguments,
// 参见示例
StringBuilder.prototype.AppendFormat = function() {
	if (arguments.length > 1) {
		var TString = arguments[0];
		if (arguments[1] instanceof Array) {
			for ( var i = 0, iLen = arguments[1].length; i < iLen; i++) {
				var jIndex = i;
				var re = eval("/\\{" + jIndex + "\\}/g;");
				TString = TString.replace(re, arguments[1][i]);
			}
		} else {
			for ( var i = 1, iLen = arguments.length; i < iLen; i++) {
				var jIndex = i - 1;
				var re = eval("/\\{" + jIndex + "\\}/g;");
				TString = TString.replace(re, arguments[i]);
			}
			;
		}
		this.Append(TString);
	} else if (arguments.length == 1) {
		this.Append(arguments[0]);
	}
};

// 字符串长度（相当于ToString()后输出的字符串长度
StringBuilder.prototype.Length = function() {
	if (this._splitChar.length > 0 && (!this.IsEmpty())) {
		return this._length
				+ (this._splitChar.length * (this._buffers.length - 1));
	} else {
		return this._length;
	}
};
// 字符串是否为空
StringBuilder.prototype.IsEmpty = function() {
	return this._buffers.length <= 0;
};
// 清空
StringBuilder.prototype.Clear = function() {
	this._buffers = [];
	this._length = 0;
};
// 输出
// 参数：可以指定一个字符串（或单个字符），作为字符串拼接的分隔符
StringBuilder.prototype.ToString = function() {
	if (arguments.length == 1) {
		return this._buffers.join(arguments[1]);
	} else {
		return this._buffers.join(this._splitChar);
	}
};
