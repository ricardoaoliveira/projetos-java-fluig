var HelloWorld = SuperWidget.extend({
    message: null,

    init: function () {

    },

    bindings: {
        local: {
            'show-message': ['click_showMessage']
        }
    },
    
    userInfo: function(){
		$.ajax({
			url: '/restOauth/rest/conn/userInfo',
			type: 'GET',
			contentType: 'application/json',
		}).done(function(data,textStatus,jqXHR) {
			console.log(data.content);
		}).fail(function(jqXHR,textStatus,errorThrown) {
			FLUIGC.toast({
				message: "ERRO: " + errorThrown,
				type: 'danger'
			});
			console.log("ERRO: " + errorThrown);
		}).always(function() {
		});
    },
    
    consulta: function(){
   		
    		$.ajax({
    			url: '/restOauth/rest/conn/search',
    			type: 'GET',
    			contentType: 'application/json',
    		}).done(function(data,textStatus,jqXHR) {
    			console.log(data.content);
    			
    		}).fail(function(jqXHR,textStatus,errorThrown) {
    			console.log('ERRO: ' + errorThrown);
    			FLUIGC.toast({
    				title: 'ERRO: ',
    				message: errorThrown,
    				type: 'danger'
    			});
    		}).always(function() {
    			
    		});
    		
    },

    showMessage: function () {
        $div = $('#helloMessage_' + this.instanceId);
        $message = $('<div>').addClass('message').append(this.message);
        $div.append($message);
        this.consulta();
        this.userInfo();
    }
});