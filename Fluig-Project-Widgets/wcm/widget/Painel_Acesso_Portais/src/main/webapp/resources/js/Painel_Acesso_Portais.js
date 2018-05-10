var PainelAcessoPortais = SuperWidget.extend({

	init: function() {
    	console.log(this.instanceId);
    	this.initView(this.instanceId);
    },
    
    initView: function(vInstanceId) {
    	var data = this.searchData();
    	
    	console.log("data: ");
    	console.log(data);
    	
    	if (data.content.length > 0) {
    		var template = $("#template_" + vInstanceId).html();

    		for (var i=0; i<data.content.length; i++) {
    			data.content[i].imageUrl = 
    				this.getImageUrl(data.content[i].companyid, 
    							data.content[i].documentid, 
    							data.content[i].version);
    		}
    		
    		try {
    			var html = Mustache.render(template, data);
        		
        		$("#container_" + vInstanceId).append(html);
    		} catch(e) {
    			console.log(e);
    		}
    	}
    },
    
    getImageUrl: function(companyid, documentid, version) {
    	var imageUrl = WCMAPI.getServerURL() + 
    		"/webdesk/streamcontrol/padrao.png?WDCompanyId=" + companyid +
    		"&WDNrDocto=" + documentid + "&WDNrVersao=" + version;
    		
    	return imageUrl;
    },

    searchUserGroups: function() {
    	var c1 = DatasetFactory.createConstraint("colleagueGroupPK.colleagueId", WCMAPI.getUserCode(), WCMAPI.getUserCode(), ConstraintType.MUST);
    	var constraints   = new Array(c1);
    	var sortingFields = new Array(); 
    	var dataset = DatasetFactory.getDataset("colleagueGroup", null, constraints, sortingFields);
    	return dataset;
    },
    
    searchData: function() {
		var data = {
			content: []
		};
		
		var grupos = this.searchUserGroups();
		console.log("grupos: ");
		console.log(grupos);
		
		var constraints = new Array();
		
		var fields = new Array("id", "companyid", "titulo", "url", "version", "grupo");
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("dsPainelAcessoPortais", fields, constraints, sortingFields);
		
		var isAdmin = this.userIsRoleAdmin();
		
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			var item = null;
			for (var i=0; i<dataset.values.length; i++) {
				item = dataset.values[i];

				if (isAdmin) {
					data.content.push(item);
				} else {
					if (this.containGroup(item.grupo, grupos.values)) {
						data.content.push(item);		
					}
				}
			}
		}
		
		return data;
	},    
	
	containGroup: function(groupId, groups) {
		if (groups != null && groups.length > 0) {
			for (var i=0; i<groups.length; i++) {
				if ( groupId == groups[i]["colleagueGroupPK.groupId"] ) {
					return true;
				}
			}
		}		
		return false;
	},
	
	userIsRoleAdmin: function() {
		var ROLE = "admin";
		var colleagueId = WCMAPI.getUserCode();
		var c1 = DatasetFactory.createConstraint("workflowColleagueRolePK.colleagueId", colleagueId, colleagueId, ConstraintType.MUST);
		var c2 = DatasetFactory.createConstraint("workflowColleagueRolePK.roleId", ROLE, ROLE, ConstraintType.MUST);
		var constraints   = new Array(c1,c2);
		var sortingFields = new Array(); 
		var dataset = DatasetFactory.getDataset("workflowColleagueRole", null, constraints, sortingFields);

		if (dataset != null && dataset.values.length > 0) {
			
			return true;	
		}

		return false;
	},
  
});
