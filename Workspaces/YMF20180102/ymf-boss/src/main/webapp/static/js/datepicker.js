// $(function () {
// 	DatePickerExt.between("createStartTime", "createEndTime", {
// 		maxDate: 0,
// 		showButtonPanel: true
// 	});
// });

// 关闭自动设置时间参数  同时，时间中带有begin和start的也不会再设置为前7日的时间
var query_autoFillDate = false;

$(function () {
	DatePickerExt.date("startDate",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
});
$(function () {
	DatePickerExt.date("endDate",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
});
$(function () {
	DatePickerExt.date("timeStart",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
});
$(function () {
	DatePickerExt.date("timeEnd",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
});
$(function () {
	DatePickerExt.date("payStart",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
});
$(function () {
	DatePickerExt.date("payEnd",{maxDate:0, showButtonPanel:true,changeMonth: true, changeYear: true});
});