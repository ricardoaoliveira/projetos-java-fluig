$(document).ready(function() {
	
});

var updateDescriptorOnChange = function(value) {
	var str = "";
	
	str += $("#titulo").val();
	
	$("#ds_descriptor").val(str);
};