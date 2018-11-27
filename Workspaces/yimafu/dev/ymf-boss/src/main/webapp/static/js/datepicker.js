// $(function () {
// 	DatePickerExt.between("createStartTime", "createEndTime", {
// 		maxDate: 0,
// 		showButtonPanel: true
// 	});
// });
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