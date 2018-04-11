$(document).ready(function() {
	
});

var updateDescriptorOnChange = function(value) {
	var str = "";
	
	str += $("#nm").val();
	
	$("#ds_descriptor").val(str);
};