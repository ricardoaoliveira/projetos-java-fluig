var updateDiaSemana = function() {
	var dtEvento = $("#dt_event input").val()
	console.log(dtEvento);
	
	if (dtEvento != null) {
		var dtEventoValues = dtEvento.split('/');	
		
		console.log(dtEventoValues);
		
		var dia = parseInt(dtEventoValues[0]);
		var mes = parseInt(dtEventoValues[1]);
		var ano = parseInt(dtEventoValues[2]);
		
		$("#dt_event_fmt").val(dtEventoValues[2] + "-" + dtEventoValues[1] + "-" + dtEventoValues[0]);
		
		var dayName = new Array ("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
		var now = new Date();
		now.setYear(ano);
		now.setDate(dia);
		now.setMonth(mes - 1);
		
		console.log( "now: " + now );
		
		var strDiaSemana = dayName[now.getDay()];
		
		console.log( strDiaSemana );
		
		$("#nm_dia_semana").val(strDiaSemana);
		
		$("#ds_descriptor").val(dtEvento);
	}
};

$(function() {
	
	$('#tbeventos').show();
	
	var dtReuniaoCalendar = FLUIGC.calendar('#dt_event', {
		
	}).setDate($('#dt_event :input').attr('value') != null ? $("#dt_event :input").attr('value') : new Date());
		
	updateDiaSemana();
	
	$( "#dt_event :input" ).blur(function() {
	  updateDiaSemana();
	});
	
	$( "#dt_event :input" ).change(function() {
	  updateDiaSemana();
	});
});