<div id="MyWidget_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" data-params="MyWidget.instance()">
	<div class="fluig-style-guide">
		<form name="form" role="form">		
			<div class="panel panel-default" id="upload-file">
				<div class="panel-heading">
					<h3 class="panel-title">Upload</h3>
				</div>
				<div class="panel-body">					
					<a class="file-input-wrapper btn btn-default ">
					<span>Buscar Arquivo(s)</span>
					<input 
						id="fileupload" 
						type="file" 
						name="files" 
						data-url="/ecm/upload"
						class="btn btn-primary btn-sm btn-block"
						title="Buscar Arquivo(s)"
						multiple/>
					</a>
				</div>
			</div>		
		</form>
	</div>

</div>