<div id="GoogleOrgChats_${instanceId}" class="super-widget wcm-widget-class"
	data-params="GoogleOrgChats.instance()">

	<div>
		<h4>Organograma</h4>
	</div>

	<div id="chart_div_org_chats_${instanceId}"></div>

</div>

<script type="text/javascript">
	google.charts.load('current', {packages:["orgchart"]});
    google.charts.setOnLoadCallback(drawChart);

	function buildF(nome, cargo) {
		if (nome == null || nome.length == 0) {
			nome = "&nbsp;";
		}
		if (cargo == null || cargo.length == 0) {
			cargo = "&nbsp;";
		}
		var divNome = '<div class="nome">' + nome + '</div>';
		var divCargo = '<div class="cargo">' + cargo + '</div>';
		var divResult = divNome + divCargo + "&nbsp;";
		return divResult;
	}

	function buildRow(raiz, codigo, nome, cargo) {
		var rowResult = [
			{v: codigo, f:buildF(nome, cargo)}, raiz, cargo,
		];
		return rowResult;
	}

	function drawChart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Name');
		data.addColumn('string', 'Manager');
		data.addColumn('string', 'ToolTip');
	
		// For each orgchart box, provide the name, manager, and tooltip to show.
		data.addRows([
	
			buildRow('', 'Recepcao', '', 'Recepção'),
		
			buildRow('', 'CEO', 'Valmir', 'CEO'),
		
			buildRow('', 'RH', '', 'RH'),
			buildRow('', 'TI', '', 'TI'),
		
			buildRow('CEO', 'Adm_Financeiro', 'Gustavo', 'Adm / Financeiro'),
				buildRow('Adm_Financeiro', 'Financeiro', '', 'Financeiro'),
				buildRow('Adm_Financeiro', 'Controller', '', 'Controller'),
		
			buildRow('CEO', 'Operacao', 'Evaldo', 'Operação'),
				buildRow('Operacao', 'BackOffice', '', 'BackOffice'),
				buildRow('Operacao', 'Supervisão_Escobs', '', 'Supervisão Escobs'),
				buildRow('Operacao', 'Juridico', '', 'Jurídico'),
		]);

		// Create the chart.
		var chart = new google.visualization.OrgChart(document.getElementById('chart_div_org_chats_${instanceId}'));
		// Draw the chart, setting the allowHtml option to true for the tooltips.
		chart.draw(data, {allowHtml:true});
	}
</script>