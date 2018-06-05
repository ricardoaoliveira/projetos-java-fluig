var updateDiaSemana = function() {
	var dtReuniao = $("#dt_reuniao input").val()
	console.log(dtReuniao);
	
	if (dtReuniao != null) {
		var dtReuniaoValues = dtReuniao.split('/');	
		
		console.log(dtReuniaoValues);
		
		var dia = parseInt(dtReuniaoValues[0]);
		var mes = parseInt(dtReuniaoValues[1]);
		var ano = parseInt(dtReuniaoValues[2]);
		
		var dayName = new Array ("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
		var now = new Date();
		now.setYear(ano);
		now.setDate(dia);
		now.setMonth(mes - 1);
		
		console.log( "now: " + now );
		
		var strDiaSemana = dayName[now.getDay()];
		
		console.log( strDiaSemana );
		
		$("#nm_dia_semana").val(strDiaSemana);
		
		$("#ds_descriptor").val(strDiaSemana);
	}
};

$(function() {
	
	$('#tbreservas').show();
	
	var dtReuniaoCalendar = FLUIGC.calendar('#dt_reuniao', {
		
	}).setDate($('#dt_reuniao :input').attr('value') != null ? $("#dt_reuniao :input").attr('value') : new Date());
		
	updateDiaSemana();
	
	$( "#dt_reuniao :input" ).blur(function() {
	  updateDiaSemana();
	});
	
	$( "#dt_reuniao :input" ).change(function() {
	  updateDiaSemana();
	});
});