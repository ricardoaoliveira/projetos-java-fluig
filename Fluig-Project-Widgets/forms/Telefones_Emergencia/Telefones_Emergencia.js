$(document).ready(function() {
	$('.class-phone').phoneBrazil();
});

var updateDescriptorOnChange = function(value) {
	var str = "";
	
	str += $("#nm").val();
	str += " - ";
	str += $("#fone").val();
	
	$("#ds_descriptor").val(str);
};