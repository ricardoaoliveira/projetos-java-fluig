function beforeDocumentViewer(){ 

	//Informações do documento
	var doc = getValue("WKDocument");
	var docExpires = doc.getExpires();
	var docExpirationDate = doc.getExpirationDate();

	var calDocExpirationDate = java.util.Calendar.getInstance();
	calDocExpirationDate.setTime(docExpirationDate);
	calDocExpirationDate.set(java.util.Calendar.MINUTE, 0);
	calDocExpirationDate.set(java.util.Calendar.SECOND, 0);
	calDocExpirationDate.set(java.util.Calendar.MILLISECOND, 0);
	calDocExpirationDate.set(java.util.Calendar.HOUR_OF_DAY, 0);
	
	var calNow = java.util.Calendar.getInstance();
	calNow.set(java.util.Calendar.MINUTE, 0);
	calNow.set(java.util.Calendar.SECOND, 0);
	calNow.set(java.util.Calendar.MILLISECOND, 0);
	calNow.set(java.util.Calendar.HOUR_OF_DAY, 0);
	
	if ( docExpires == true && calDocExpirationDate.before(calNow) || calDocExpirationDate.getTime().equals(calNow.getTime()) ) {
		log.warn("The document " + doc.getDocumentId() + " it cannot be viewed by user " + getValue("WKUser") + " because the due date is equal to the current date");
		throw ("Esse documento venceu, não pode ser visualizado.")
	} else {
		log.info("This documment is allowed to visualization.")
	}
	
} 
