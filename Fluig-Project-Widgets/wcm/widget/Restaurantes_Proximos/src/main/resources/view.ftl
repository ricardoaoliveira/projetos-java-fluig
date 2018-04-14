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
					<a id="place_${instanceId}{{index}}" class='collapse-icon up' data-toggle='collapse' data-parent='#accordion_${instanceId}' href='#collapse_${instanceId}{{index}}'>
					{{name}}
				</h4>
			</div>
		<div id='collapse_${instanceId}{{index}}' class='panel-collapse collapse'>
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
        }, processResults);
      }	
	  
      function processResults(results, status, pagination) {
        if (status !== google.maps.places.PlacesServiceStatus.OK) {
          return;
        } else {
          createMarkers(results);
        }
      }
	  
      function createMarkers(places) {
        var bounds = new google.maps.LatLngBounds();
        var placesList = document.getElementById('places_${instanceId}');

		var placeHelper = null;
		var placeDetail = null;
		
        for (var i = 0, place; place = places[i]; i++) {
		  
		  var image = {
            url: place.icon,
            size: new google.maps.Size(71, 71),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(17, 34),
            scaledSize: new google.maps.Size(25, 25)
          };

          var marker = new google.maps.Marker({
            map: map,
            icon: image,
            title: place.name,
            position: place.geometry.location
          });
		  
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
		  
		  bounds.extend(place.geometry.location);
        }
		
		map.fitBounds(bounds);
		
		fillDetails();
      }
	  
	  var countDataContent = 0;
	  var countFillDetails = 0;
	  
	  var details = [];
	  
	  var fillDetails = function() {
	  
	    countDataContent = data.content.length;
		countFillDetails = 0;
		
		var placeHelper = null;
		
	    for (var i=0; i<data.content.length; i++) {
			service.getDetails({
			  placeId: data.content[i].place_id
				}, function(resp, status) {
				  if (status === google.maps.places.PlacesServiceStatus.OK) {
					details.push(resp);
				  }
				  countFillDetails++;
				  
				  if (countFillDetails == countDataContent) {
				    updateData();					
				  }
				});
		}
	  };
	  
	  var updateData = function() {
	  
	    var detail = null;
	   
		for (var i=0; i<data.content.length; i++) {
			detail = getDetail(data.content[i].place_id);
			console.log(detail);
			if (detail != null) {
				data.content[i].formatted_address = detail.formatted_address;
				data.content[i].formatted_phone_number = detail.formatted_phone_number;
				if (detail.url != null) {
					data.content[i].linkDetail = detail.url;
				}				
				data.content[i].opening_hours = detail.opening_hours;
				data.content[i].price_level = detail.price_level;
				data.content[i].rating = detail.rating;
			}
		}
		
		renderizarMenos();
	  };
	  
	  var getDetail = function(place_id) {
		for (var i=0; i<details.length; i++) {
			if (details[i].place_id = place_id) {
				return details[i];
			}
		}		
		return null;
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
		
		try {
			document.getElementById("place_${instanceId}0").click();
		} catch(e) {
		
		}		
	  };
	  
	  var renderizarMais = function() {
		var template = $("#templateAccordion_${instanceId}").html();

		var html = Mustache.render(template, data);
	
		$("#btnRecolher_${instanceId}").show();
		$("#btnCarregarMais_${instanceId}").hide();
	
		$("#accordion_${instanceId}").html(html);
	  };
	
</script>
