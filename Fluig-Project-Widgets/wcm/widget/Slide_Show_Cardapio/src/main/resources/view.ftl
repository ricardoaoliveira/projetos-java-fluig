<#attempt>
    <#assign parameters = "{widgetVersion : '${applicationVersion!}', instanceId: '${instanceId!}', slideShowTitle: '${slideShowTitle!}', sourceType: '${sourceType!}', instagramTargetAccount: '${instagramTargetAccount!}', instagramTargetAccountID: '${instagramTargetAccountID!}', applicationSourceClientID: '${applicationSourceClientID!}', fluigDirectoryName: '${fluigDirectoryName!}', interval: 1500, fluigDirectoryID: '${fluigDirectoryID!}', showImageTitle: '${showImageTitle!}', autoSize: '${autoSize!'false'}', resize: '${resize!'false'}'}"?html>
    <div id="SlideShowCardapio_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" data-params="SlideShowCardapio.instance(${parameters})" style="margin-top: 30px;">
    
    	<h2 class="page-header"> 
    	<span class="fluigicon fluigicon-food"></span>
    		<span id="divHeader_${instanceId}">
    			${i18n.getTranslation('application.header.title')}
    		</span>
	    </h2>
    
    	<div id="divSlideShowCardapioshow${instanceId}">
    	
    	</div>
    
    	<script type="text/template" class="template_slideshow">
            <ul id="photoList_${instanceId}">
            	
            </ul>            
        </script>
    </div>
    <script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>
<#recover>
	<#include "/social_error.ftl">
</#attempt>