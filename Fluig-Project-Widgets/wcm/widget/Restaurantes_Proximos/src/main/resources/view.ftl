<div id="RestaurantesProximos_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="RestaurantesProximos.instance()">

	<div class="fluig-style-guide">
		
		<div id="map_${instanceId}" style="display:none;"></div>
		
		<h2 class="page-header"> 
    	<span class="fluigicon fluigicon-food"></span>
    		<span id="divHeader_${instanceId}">
    			Restaurantes
    		</span>
	    </h2>
		
		<div class="row">
			<div class="col-md-12">
				<div class="panel-group" id="accordion_${instanceId}">
				
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<button class="btn btn-default btn-block" id="btnCarregarMais_${instanceId}" onclick="renderizarMais();" style="display:none;">
					Carregar mais
				</button>
			</div>			
		</div>
		
		<div class="row">
			<div class="col-md-12">			
				<button class="btn btn-default btn-block" id="btnRecolher_${instanceId}" onclick="renderizarMenos();" style="display:none;">
					Recolher
				</button>
			</div>
		</div>
		
    </div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="templateAccordion_${instanceId}" type="text/template">
	{{#content}}
	<div class='panel panel-default'>
			<div class='panel-heading'>
				<h4 class='panel-title'>
					<a id="place_${instanceId}{{index}}" class='collapse-icon up' onclick="viewRestaurant({{index}});" data-toggle='collapse' data-parent='#accordion_${instanceId}' href='#collapse_${instanceId}{{index}}'>
					{{name}}
				</h4>
			</div>
		<div id='collapse_${instanceId}{{index}}' class='panel-collapse collapse in'>
			<div class='panel-body'>				
				<div class='row'>			
					<div class='col-xs-6 col-sm-6 col-md-6 col-lg-6'>						
						{{#hasDetail}}
						<a href="{{linkDetail}}" target='_blank'> 
							<img src="{{image}}" style="width: {{imageWidth}}px; height: {{imageHeight}}px;" />
						</a>
						{{/hasDetail}}
						
						{{^hasDetail}}
						<a> 
							<img src="{{image}}" style="width: {{imageWidth}}px; height: {{imageHeight}}px;" />
						</a>
						{{/hasDetail}}
						
					</div>			
					<div class='col-xs-6 col-sm-6 col-md-6 col-lg-6'>		
						<span>
							{{formatted_phone_number}}
							<br/><br/>
							{{formatted_address}}		
						</span>
					</div>
				</div>		
			</div>
		</div>
	</div>
	{{/content}}
</script>

<script id="templateAccordionBody_${instanceId}" type="text/template">
	{{#content}}
	<div class='panel-body'>				
		<div class='row'>			
			<div class='col-xs-6 col-sm-6 col-md-6 col-lg-6'>						
				{{#hasDetail}}
				<a href="{{linkDetail}}" target='_blank'> 
					<img src="{{image}}" style="width: {{imageWidth}}px; height: {{imageHeight}}px;" />
				</a>
				{{/hasDetail}}
				
				{{^hasDetail}}
				<a> 
					<img src="{{image}}" style="width: {{imageWidth}}px; height: {{imageHeight}}px;" />
				</a>
				{{/hasDetail}}
			</div>			
			<div class='col-xs-6 col-sm-6 col-md-6 col-lg-6'>		
				<span>
					{{formatted_phone_number}}
					<br/><br/>
					{{formatted_address}}		
				</span>
			</div>
		</div>			
	</div>
	{{/content}}
</script>

<script type="text/javascript">

	var config = {
		distancia: 500,
		minShow: 5,
		api_key: 'AIzaSyCxdnwc7OENllv7qUQfvp3sp8U8oHAZsQs',
		types: ['restaurant'],
		latitude: -23.5702177,
		longitude: -46.6928957,
		image: {
			width: 150,
			height: 150
		}
	};

	var dataset = DatasetFactory.getDataset("ds_restaurantes_proximos");
	if (dataset != null && dataset.values.length > 0) {
		
		var param_restaurantes_proximos = dataset.values[0];
		
		if (param_restaurantes_proximos.distancia != null) {
			config.distancia = parseInt(param_restaurantes_proximos.distancia);
		}
		
		if (param_restaurantes_proximos.min_show != null) {
			config.minShow = parseInt(param_restaurantes_proximos.min_show);
		}
		
		if (param_restaurantes_proximos.api_key != null) {
			config.api_key = param_restaurantes_proximos.api_key;
		}
		
		if (param_restaurantes_proximos.latitude != null) {
			config.latitude = parseFloat(param_restaurantes_proximos.latitude);
		}
		
		if (param_restaurantes_proximos.longitude != null) {
			config.longitude = parseFloat(param_restaurantes_proximos.longitude);
		}
		
		if (param_restaurantes_proximos.image_width != null) {
			config.image.width = parseFloat(param_restaurantes_proximos.image_width);
		}
		
		if (param_restaurantes_proximos.image_height != null) {
			config.image.height = parseFloat(param_restaurantes_proximos.image_height);
		}
	}

	var script = document.createElement('script');
	script.src = "https://maps.googleapis.com/maps/api/js?key=" + config.api_key + "&libraries=places&callback=initMap";
	document.getElementsByTagName('head')[0].appendChild(script);

	var buildUrlImage = function(place) {
	
		var imageUrl = null;	
	
		if (place.photos != null && place.photos.length > 0) {
			imageUrl = place.photos[0].getUrl({'maxWidth': config.image.width, 'maxHeight': config.image.height});
		} else {
			imageUrl = 'https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=' + place.reference;
		}
		
		return imageUrl;
	};
	
	var buildLinkDetail = function(place) {
		var strLink = '';
		if (place.photos != null && place.photos.length > 0) {
		   strLink = place.photos[0].html_attributions[0];
		   strLink = strLink.split('<a href=\"');
		   strLink = strLink[1].split('>');
		   strLink = strLink[0].split('\"');
		   strLink = strLink[0];
		   return strLink;
	    } 
		strLink = '#';
		return strLink;
	};

	var data = {
		content: [
		],
	};

	var map;
	
	var service;

      function initMap() {
        var pyrmont = {lat: config.latitude, lng: config.longitude};

		map = new google.maps.Map(document.getElementById('map_${instanceId}'), {
          center: pyrmont,
          zoom: 17
        });

        service = new google.maps.places.PlacesService(map);
        service.nearbySearch({
          location: pyrmont,
          radius: config.distancia,
          type: config.types,
		  opennow: true,
        }, placesCallback);
      }	
	  
      function placesCallback(places, status, pagination) {
        if (status !== google.maps.places.PlacesServiceStatus.OK) {
          return;
        } else {
          
			for (var i = 0, place; place = places[i]; i++) {
			  
				  detail = null;
				  
				  place.linkDetail = buildLinkDetail(place);
				  place.index = i;
				  place.formatted_address = '';
				  place.formatted_phone_number = '';
				  place.hasDetail= function() {
					return this.linkDetail != "#";
				  };
				  place.image= buildUrlImage(place);
				  place.imageWidth= config.image.width;
				  place.imageHeight= config.image.height;
				  
				  data.content.push(place);
			}
			
			renderizarMenos();
        }
      }
	  
  	  var currentIndex = 0;
	  
	  var viewRestaurant = function(index) {
	  
		currentIndex = index;
	  
		if (data.content[index].ok == null) {
			service.getDetails({
			  placeId: data.content[index].place_id
				}, function(detail, status) {
				  if (status === google.maps.places.PlacesServiceStatus.OK) {

					var template = $("#templateAccordionBody_${instanceId}").html();

					var bodyData = {
						content: []
					};
					
					if (detail.place_id == data.content[index].place_id) {
						data.content[index].formatted_address = detail.formatted_address;
						data.content[index].formatted_phone_number = detail.formatted_phone_number;
						if (detail.url != null) {
							data.content[index].linkDetail = detail.url;
						}				
						data.content[index].opening_hours = detail.opening_hours;
						data.content[index].price_level = detail.price_level;
						data.content[index].rating = detail.rating;
						data.content[index].ok = true;
					}
					
					bodyData.content.push(data.content[index]);
					
					var html = Mustache.render(template, bodyData);
				
					$("#collapse_${instanceId}" + index).html(html);			

					hideAllCollapse();
					
					$("#collapse_${instanceId}" + index).collapse("show");
				  }
				});
		} else {
			hideAllCollapse();
			$("#collapse_${instanceId}" + index).collapse("show");
		}
	  };
	  
	  var hideAllCollapse = function() {
		
		if (data.content != null && data.content.length > 0) {
		
			var collapse = null;
		
			for (var i=0; i<data.content.length; i++) {
				try {
					$("#collapse_${instanceId}" + i).collapse("hide");
				} catch(e) {
				
				}
			}
		}
	  };
	  
	  var renderizarMenos = function() {
	  
		var template = $("#templateAccordion_${instanceId}").html();

		var minData = {
			content: []
		};
		
		for (var i=0; i<data.content.length; i++) {
			if (i < config.minShow) {
				minData.content.push(data.content[i]);
			}
		}
		
		var html = Mustache.render(template, minData);
	
		$("#btnCarregarMais_${instanceId}").show();
		$("#btnRecolher_${instanceId}").hide();
		
		$("#accordion_${instanceId}").html(html);
		$("#accordion_${instanceId}").hide();
		hideAllCollapse();
		$("#accordion_${instanceId}").show();
		
		try {
			setTimeout(function(){ viewRestaurant(currentIndex); }, 500);
		} catch(e) {
			console.log("renderizarMenos: " + e.message);
		}		
	  };
	  
	  var renderizarMais = function() {
		var template = $("#templateAccordion_${instanceId}").html();

		var html = Mustache.render(template, data);
	
		$("#btnRecolher_${instanceId}").show();
		$("#btnCarregarMais_${instanceId}").hide();
	
		$("#accordion_${instanceId}").html(html);
		$("#accordion_${instanceId}").hide();
		hideAllCollapse();
		$("#accordion_${instanceId}").show();
		
		try {
			setTimeout(function(){ viewRestaurant(currentIndex); }, 500);
		} catch(e) {
			console.log("renderizarMenos: " + e.message);
		}	
	  };
	
</script>
