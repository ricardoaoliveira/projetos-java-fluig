

	<div>
		<h4>Organograma</h4>
	</div>

	<div id="chart_div_org_chats_${instanceId}"></div>
	

	
    <script type="text/javascript">
      google.charts.load('current', {packages:["orgchart"]});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('string', 'Manager');
        data.addColumn('string', 'ToolTip');

        // For each orgchart box, provide the name, manager, and tooltip to show.
        data.addRows([
        
         [{v:'Tamires', f:'Tamires<div style="color:red; font-style:italic">Recepção</div>'},'', 'Recepção'],
		
          [{v:'Valmir', f:'Valmir<div style="color:red; font-style:italic">CEO</div>'},'', 'CEO'],
		  
		  [{v:'Valmir_RH', f:'Valmir<div style="color:red; font-style:italic">RH               </div>'},'', 'RH             '],
		  [{v:'Milton', f:'Milton<div style="color:red; font-style:italic">Desenv. TI / HelpDesk TI</div>'},'', 'Desenvolvimento'],
				
			[{v:'Gustavo', f:'Gustavo<div style="color:red; font-style:italic">Adm / Financeiro</div>'},'Valmir', 'Adm / Financeiro'],
			  [{v:'Rita_Financeiro', f:'Rita<div style="color:red; font-style:italic">Financeiro</div>'},'Gustavo', 'Financeiro'],
			  [{v:'Matheus_Controller', f:'Matheus<div style="color:red; font-style:italic">Controller</div>'},'Gustavo', 'Controller'],
			
			[{v:'Evaldo', f:'Evaldo<div style="color:red; font-style:italic">Operação</div>'},'Valmir', 'Operação'],
			  [{v:'Samuel', f:'Samuel<div style="color:red; font-style:italic">BackOffice</div>'},'Evaldo', 'BackOffice'],
			  [{v:'Albano', f:'Albano<div style="color:red; font-style:italic">Supervisão Escobs</div>'},'Evaldo', 'Supervisão Escobs'],
			  [{v:'Vanessa', f:'Vanessa<div style="color:red; font-style:italic">Jurídico</div>'},'Evaldo', 'Jurídico'],
		  
        ]);

        // Create the chart.
        var chart = new google.visualization.OrgChart(document.getElementById('chart_div_org_chats_${instanceId}'));
        // Draw the chart, setting the allowHtml option to true for the tooltips.
        chart.draw(data, {allowHtml:true});
      }
   </script>
  


