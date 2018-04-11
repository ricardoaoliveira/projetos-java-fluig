function displayFields(form,customHTML){ 
	if (isViewMode(form,customHTML)) {
		customHTML.append("<script>");
		customHTML.append("hideButtonAddCor();");
		customHTML.append("showButtonAddCor();");
		customHTML.append("</script>");
	} 
}

function isInsertMode(form,customHTML) {
	return form.getFormMode() == "ADD";
}

function isEditMode(form,customHTML) {
	return form.getFormMode() == "MOD";
}

function isViewMode(form,customHTML) {
	return form.getFormMode() == "VIEW";
}