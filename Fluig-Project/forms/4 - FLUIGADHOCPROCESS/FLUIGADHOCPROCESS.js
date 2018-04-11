$(function() {

	var string = $("#request").attr('value') + " - " + $("#requestDescription").attr('value');
	$("#requestText").attr('value', string);

	$(document).ready(function(){
		$("#formAdHoc").show();
	});
});