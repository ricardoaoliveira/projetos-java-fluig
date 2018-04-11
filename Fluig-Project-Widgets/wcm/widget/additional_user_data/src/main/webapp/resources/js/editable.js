var Editable = SuperWidget.extend({
	instanceId: null,

	i18n: {
		'save.success': '${i18n.getTranslation("sample.editable.save.success")}',
		'generic.error': '${i18n.getTranslation("error.generic")}',
	},
	
	bindings: {
		local: {},
		global: {}
	},

	init: function() {
		this.getEditableInfo();
	},
	
	getEditableInfo: function() {
		var that = this, technology = [], editableInfo = {}, editableContent;
		
		that.getUserMetadata(function(metadata) {
			editableInfo.technology = that.getProgrammingLanguages().slice();
			editableInfo.technologyDefault = metadata.technology;
			editableInfo.developer = metadata.developer;
			editableInfo.description = metadata.description;
			
			editableContent = Mustache.render(that.templates['editable-template'], {
				developer: editableInfo.developer,
				technology: editableInfo.technology,
				description: editableInfo.description
			});
			that.DOM.append(editableContent);

			// inicializa os campos editaveis
			that.initFormEditable(editableInfo);
		});
	},
	
	initFormEditable: function(data) {
		var that = this;
		
		that.developer = FLUIGC.editable('#editable-developer-' + that.instanceId).on('fssave.editable', function(ev) {
			that.savePreferences(ev.params, 'developer');
		});
		
		that.technology = FLUIGC.editable('#editable-technology-' + that.instanceId, {
			type: 'select',
			value: data.technologyDefault,
			source: data.technology
		}).on('fssave.editable', function(ev) {
			that.savePreferences(ev.params, 'technology');
		});
		
		that.description = FLUIGC.editable('#editable-description-' + that.instanceId, {
			type: 'textarea'
		}).on('fssave.editable', function(ev) {
			that.savePreferences(ev.params, 'description');
		});
	},
	
	getUserMetadata: function(callback) {
		var that = this;
		return this.serviceGetUserMetadata(function(err, data) {
			if (err) {
				that.showErrorMessage(err);
				return false;
			}
			data = data && data.content ? data.content : {};
			callback(data);
		});
	},
	
	savePreferences: function(params, type) {
		var that = this, userData = {
			developer: this.developer.getValue(true),
			technology: this.technology.getValue(true),
			description: this.description.getValue(true)
		}
		if (userData[type] !== undefined) {
			userData[type] = params.newValue;
		}
		this.serviceUpdateUserData(userData, function(err, data) {
			if (err) {
				that.showErrorMessage(err);
				return false;
			}
			that.showToastMessage(that.i18n['save.success']);
		});
	},
	
	serviceUpdateUserData: function(data, cb) {
		var options = {
			url: '/api/public/2.0/users/updateUserData',
			contentType: 'application/json',
			dataType: 'json',
			type: 'POST',
			data: JSON.stringify(data),
			loading: false
		};
		FLUIGC.ajax(options, cb);
	},
	
	serviceGetUserMetadata: function(cb) {
		var options = {
			url: ECM.restUrl + "userpreferences/findGeneralInformation",			
			contentType: 'application/json',
			loading: false
		};
		return FLUIGC.ajax(options, cb);
	},
	
	getProgrammingLanguages: function() {
		return [{value: 'java', text: 'Java'},
		        {value: 'js', text: 'Javascript'},
		        {value: 'py', text: 'Python'},
		        {value: 'rb', text: 'Ruby'}];
	},
	
	showToastMessage: function(message, type, title) {
		message = message || '';
		type = type || 'success';
		title = title || '';

		FLUIGC.toast({
			title: title,
			message: message,
			type: type
		});
	},
	
	showErrorMessage: function(err) {
		var message;
		try {
			err = JSON.parse(err.responseText);
			message = err.message.message;
		} catch (e) {
			message = err.message || this.i18n['generic.error'];
		}
		this.showToastMessage(message, 'danger');
	}

});
