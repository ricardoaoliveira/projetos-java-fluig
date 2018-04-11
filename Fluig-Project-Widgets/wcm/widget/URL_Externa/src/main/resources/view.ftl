<#setting url_escaping_charset="UTF-8">
<#assign parametros = '{"url": "${url?url}", "applicationWidth": "${applicationWidth!}", "applicationHeight": "${applicationHeight!}", "applicationTitle": "${(applicationTitle)!(i18n.getTranslation("application.title"))}"}'?html>
<div id="WCMIFrame_${instanceId}" class="WCMIFrame super-widget wcm-widget-class fluig-style-guide"
     data-params="WCMIFrame.instance(${parametros})">
    <iframe id="iframe_${instanceId?c}" name="iframe_${instanceId?c}" width="" height=""></iframe>
</div>