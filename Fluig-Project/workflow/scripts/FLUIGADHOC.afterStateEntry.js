function afterStateEntry(sequenceId){

	var cardData = new java.util.HashMap();

	if (sequenceId == 10) {
        var adHocTasks = new Array();
        var process = getValue("WKNumProces");
        cardData = hAPI.getCardData(process);
        var keys = cardData.keySet().toArray();
        if (getValue("WKNumState") != 8) {
	        for (var key in keys) {
	            var field = keys[key];
	            if (field.indexOf("nomeativ___") > -1) {
	                var name = cardData.get(field);
	                var index = field.replace("nomeativ___", "");
	                var hdnrespativ = cardData.get("hdnrespativ___" + index);
	                var dueDate="";
	                if (cardData.get("dtprazoativ___" + index) != "") {
	                	if (cardData.get("dtprazoativ___" + index) != undefined){
			                var dataStr = cardData.get("dtprazoativ___" + index).split("/");
			                dueDate = dataStr[0] + "/" + dataStr[1] + "/" + dataStr[2];
		                }
	                }
	                var task = { name:name, responsible:hdnrespativ, dueDate:dueDate};
	                adHocTasks.push(task);
	            }
	        }

	        hAPI.createAdHocTasks(process, sequenceId, cardData.get("meeting"), cardData.get("detail"), adHocTasks);
	    }
    }

	if (sequenceId == 8 || sequenceId == 10) {
		var process = getValue("WKNumProces");
		if (cardData.isEmpty()) {
        	cardData = hAPI.getCardData(process);
		}
		if (cardData.get("dueDate") != "") {
        	if (cardData.get("dueDate") != undefined) {
	        	var dataStr = cardData.get("dueDate").split("/");
	        	var data = new Date();
	        	data.setFullYear(dataStr[2]);
	        	data.setMonth(dataStr[1]-1);
	        	data.setDate(dataStr[0]);
	        	hAPI.setDueDate(process, 0, getValue("WKUser"), data, (24*60*60)-1 );
	        }
        }
	}
}