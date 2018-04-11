<div id="WCMIFrame_${instanceId}" class="WCMIFrameEdit super-widget wcm-widget-class fluig-style-guide"
     data-params="WCMIFrame.instance()">
    <form role="form" id="editForm_${instanceId}" name="editForm_${instanceId}">
        <div class="form-group">
            <label for="iframeTitle_${instanceId}">${i18n.getTranslation('iframe.title')}</label>
            <input type="text" class="form-control" id="iframeTitle_${instanceId}" name="iframeTitle_${instanceId}"
                   value="${(applicationTitle)!(i18n.getTranslation("application.title"))}"/>
		</div>
        <div class="form-group">
            <label for="iframeURL_${instanceId}">${i18n.getTranslation('iframe.url')}</label>
            <input type="text" class="form-control" id="iframeURL_${instanceId}" name="iframeURL_${instanceId}"
                   value="${url!}"/>
        </div>
        <div class="form-group">
            <label for="iframeWidth_${instanceId}">${i18n.getTranslation('iframe.width')}</label>
            <input type="text" class="form-control" id="iframeWidth_${instanceId}" name="iframeWidth_${instanceId}"
                   value="${applicationWidth!}" />
        </div>
        <div class="form-group">
            <label for="iframeHeight_${instanceId}">${i18n.getTranslation('iframe.height')}</label>
            <input type="text" class="form-control" id="iframeHeight_${instanceId}" name="iframeHeight_${instanceId}"
                   value="${applicationHeight!}" />
        </div>
        <button type="submit" class="btn btn-default"
                data-save-preferences>${i18n.getTranslation('iframe.salvar')}</button>
    </form>
</div>
