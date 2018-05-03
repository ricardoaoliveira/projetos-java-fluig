var OpenReservaSalaReuniao = SuperWidget.extend({
   
    init: function() {
    	this.initView(this.instanceId);
    },
    
    initView: function(vInstanceId) {
    	var data = {
    		content: [{
    			titulo: "Reserva da Sala de Reunião",
    			codigo: "Reserva_Sala_Reuniao_Docs",
    			imageUrl: "/Open_Reserva_Sala_Reuniao/resources/images/Agenda.png",
    			descricao: "Clique aqui para visualizar"
    		}]    			
    	};
    	
    	if (data.content.length > 0) {
    		var template = $("#template_" + vInstanceId).html();

    		for (var i=0; i<data.content.length; i++) {
    			
    		}
    		
    		try {
    			var html = Mustache.render(template, data);
        		
        		$("#container_" + vInstanceId).append(html);
    		} catch(e) {
    			console.log(e);
    		}
    	}
    },
  
});

