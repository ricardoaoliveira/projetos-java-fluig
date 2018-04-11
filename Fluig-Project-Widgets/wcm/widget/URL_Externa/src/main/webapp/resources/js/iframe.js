var WCMIFrame = SuperWidget.extend({
    url: null,
    applicationWidth: null,
    applicationHeight: null,
    defaultWidth: null,
    defaultHeight: null,
    $iframe: null,
    init: function () {
        this.defaultWidth = '100%';
        this.defaultHeight = 500;
        if (this.isEditMode) {
            this.onlyDigitsOnDimensionFields();
        } else {
            this.$iframe = $("#iframe_" + this.instanceId);
            this.displayIFrame();
        }
    },
    bindings: {
        local: {
            'save-preferences': ['click_savePreferences']
        }
    },

    displayIFrame: function () {
        if (this.url.indexOf('.swf', this.url.length - '.swf'.length) !== -1) {
            this.configureSwfDiv();
        } else {
            this.configureIFrame();
        }
    },

    configureSwfDiv: function () {
        var $embedDiv = $("div")
            .attr('height', this.getHeight())
            .attr('width', this.getWidth());
        var $embed = $("embed")
            .attr('height', '100%')
            .attr('width', '100%')
            .attr('wmode', 'transparent')
            .attr('src', this.url)
            .appendTo($embedDiv);

        this.$iframe.replaceWith($embedDiv);
    },

    configureIFrame: function () {
        this.$iframe.attr('src', decodeURIComponent(this.url));
        this.$iframe.attr('width', this.getWidth());
        this.$iframe.attr('height', this.getHeight());
        this.$iframe.show();
    },

    getWidth: function () {
        return (this.applicationWidth) ? this.applicationWidth : this.defaultWidth;
    },

    getHeight: function () {
        return (this.applicationHeight) ? this.applicationHeight : this.defaultHeight;
    },

    savePreferences: function () {
        var preferences = {
            applicationTitle: $("#iframeTitle_" + this.instanceId).val(),
            url: $("#iframeURL_" + this.instanceId).val(),
            applicationWidth: $("#iframeWidth_" + this.instanceId).val(),
            applicationHeight: $("#iframeHeight_" + this.instanceId).val()
        }
        WCMSpaceAPI.PageService.UPDATEPREFERENCES(
            {
                async: true,
                success: function (data) {
                    FLUIGC.toast({title: data.message, message: '', type: 'success'});
                },
                fail: function (xhr, message, errorData) {
                    FLUIGC.toast({
                        title: '${i18n.getTranslation("iframe.preferences.error")}',
                        message: errorData.message,
                        type: 'warning'
                    });
                }
            }, this.instanceId, preferences
        );
    },

    onlyDigitsOnDimensionFields: function () {
        $('#iframeWidth_' + this.instanceId + ', #iframeHeight_' + this.instanceId)
            .on('keydown', function (event) {
                /**
                 * Only allows backspace, delete, tab, percent and numbers
                 */
                if (event.which != 0 &&
                    event.which != 8 &&
                    event.which != 9 &&
                    event.which != 46 &&
                    event.which != 53 &&
                    (event.which < 48 || event.which > 57) &&
                    (event.which < 96 || event.which > 105)
                ) {
                    return false;
                }
            });
    }
});
