<div class="wcm-widget-class super-widget fluig-style-guide" data-params="Editable.instance()" id="editable_${instanceId}">
	<script type="text/template" class="editable-template">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">${i18n.getTranslation('sample.editable.title')}</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-2 control-label fs-txt-left" for="editable-developer-${instanceId?c}">${i18n.getTranslation('sample.editable.developer')}</label>
				<div class="col-sm-10">
					<div id="editable-developer-${instanceId?c}">{{developer}}</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label fs-txt-left" for="editable-technology-${instanceId?c}">${i18n.getTranslation('sample.editable.technology')}</label>
				<div class="col-sm-10">
					<div id="editable-technology-${instanceId?c}">{{technologyDefault}}</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label fs-txt-left" for="editable-description-${instanceId?c}">${i18n.getTranslation('sample.editable.description')}</label>
				<div class="col-sm-10">
					<div id="editable-description-${instanceId?c}">{{description}}</div>
				</div>
			</div>
		</div>
	</div>
	</script>
</div>