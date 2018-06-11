var MyWidget = SuperWidget.extend({
    //variáveis da widget
    variavelNumerica: null,
    variavelCaracter: null,

    //método iniciado quando a widget é carregada
    init: function() {
    	$(function () {
    		$('#fileupload').fileupload({
    		    dataType: 'json',
    		    done: function (e, data) {
    		    	
    		    	var myLoading1 = FLUIGC.loading('#upload-file');
    		    	myLoading1.show();
    		    	
    		        $.each(data.result.files, function (index, file) {
    		            $.ajax({
    		                async : true,
    		                type : "POST",
    		                contentType: "application/json",
    		                url : '/api/public/ecm/document/createDocument',
    		
    		        		data: JSON.stringify({
    		        			"description": file.name,
    		        			"parentId": "2",
    		        			"attachments": [{
    		        				"fileName": file.name
    		        			}],
    		        		}),
    		
    		        		error: function() {
    		        			FLUIGC.toast({
    		        			     title: '',
    		        			     message: "Falha ao enviar",
    		        			     type: 'danger'
    		        			 });
    		        			myLoading1.hide();
    		        		},
    		        		
    		        		success: function(data) {
    		        			FLUIGC.toast({
    		        			     title: '',
    		        			     message: "Documento publicado - " + file.name,
    		        			     type: 'info'
    		        			 });
    		        			myLoading1.hide();
    		        		},
    		        	});
    		        });
    		    }
    		});
    	});
    },
  
    //BIND de eventos
    bindings: {
        local: {
            'execute': ['click_executeAction']
        },
        global: {}
    },
 
    executeAction: function(htmlElement, event) {
    }

});