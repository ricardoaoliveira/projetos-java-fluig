<head>
<!-- 	<link rel="stylesheet" type="text/css" href="/portal/resources/style-guide/css/fluig-style-guide.min.css"> -->
<!-- 	<script src="/portal/resources/js/jquery/jquery.js"></script> -->
<!-- 	<script src="/portal/resources/js/jquery/jquery-ui.min.js"></script> -->
<!-- 	<script src="/portal/resources/style-guide/js/fluig-style-guide.min.js"></script> -->
	<script src="/portal/resources/style-guide/js/fluig-style-guide-chart.min.js"></script>
</head>
<body>
	<div class="fluig-style-guide">
		<h3>
			<strong>
					Projetado vs Realizado:	
			</strong>
		</h3>
        <div id="MY_SELECTOR"></div>
    </div>
</body>
<script type="text/javascript">

var data = {
    labels: ["Janeiro", "Fevereiro", "Março"],
    datasets: [
        {
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [65, 59, 80]
        },
        {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [28, 48, 40]
        }
    ]
};

var chart = FLUIGC.chart('#MY_SELECTOR', {
    id: 'set_an_id_for_my_chart',
    width: '120',
    height: '50',
});

var options = new Object();

var barChart = chart.bar(data, options);

</script>