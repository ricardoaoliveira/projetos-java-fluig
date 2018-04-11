<#attempt>
<#assign parameters = '{"numberOfRecords": "${numberOfRecords!5}", "widgetVersion" : "${applicationVersion}"}'?html>

<script src="/webdesk/vcXMLRPC.js" type="text/javascript"></script>

<div id="widgetConvenios_${instanceId}" class="wcm-widget-class super-widget fluig-style-guide" data-params="WidgetConvenios.instance(${parameters})">

	<h2 class="page-header"> 
    	<span class="fluigicon fluigicon-food fluigicon-md"></span>
        ${i18n.getTranslation('application.title')}
    </h2>

	<div id="loading_${instanceId}" class="well well-lg">
    	${i18n.getTranslation('kit_convenios.label.loading')}
    </div>
	
    <div class="row" id="convenios-wrapper_${instanceId}"></div>

    <div id="rowShowAll_${instanceId}" class="text-right">
        <button type="submit" class="btn btn-default btn-block" data-more-partners>
        	${i18n.getTranslation('kit_convenios.morepartners')}
        </button>
    </div>

	<script type="text/template" class="partners-template">
		{{#.}}
			{{>partner}}
		{{/.}}
	</script>
	
	<script type="text/template" class="partner-template">
		<div class="col-xs-12 fs-cursor-pointer {{#isPartnerDetails}}fs-no-padding{{/isPartnerDetails}}" data-partner-item data-open-partner="{{documentId}}">
		    <div class="media {{^isPartnerDetails}}fs-sm-space fs-no-padding-left fs-no-padding-right{{/isPartnerDetails}}">
		    
		    	{{#hasUrl}}
		    	<a class="pull-left" href="{{url}}" target="_blank">
		    		<img width="120" class="media-object img-responsive img-rounded" src="{{imgURL}}" alt="{{title}}">
		    	</a>
		    	{{/hasUrl}}
		    	
		    	{{^hasUrl}}
		    	<a class="pull-left">
		    		<img width="120" class="media-object img-responsive img-rounded" src="{{imgURL}}" alt="{{title}}">
		    	</a>
		    	{{/hasUrl}}
		    
			    <div class="media-body">
					<h4 class="media-heading">{{title}}</h4>
			    </div>
			    <p>
			    	{{#isPartnerDetails}}
						{{content}}
					{{/isPartnerDetails}}
					{{^isPartnerDetails}}
						{{cutContent}}
					{{/isPartnerDetails}}
			    </p>
			</div>
		</div>
	</script>
</div>
<#recover>
	<#include "/social_error.ftl">
</#attempt>