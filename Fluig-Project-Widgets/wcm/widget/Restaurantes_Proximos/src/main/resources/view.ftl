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
			<button class="btn btn-default btn-block" id="btnCarregarMais_${instanceId}" onclick="renderizarMais();" style="display:none;">
				Carregar mais
			</button>			
			<button class="btn btn-default btn-block" id="btnRecolher_${instanceId}" onclick="renderizarMenos();" style="display:none;">
				Recolher
			</button>			
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
					<div class='col-md-12'>
						
						{{#hasDetail}}
						<a href="{{linkDetail}}" target='_blank'> 
							<img src="{{image}}" style="width: 150px; height: 150px;" />
						</a>
						{{/hasDetail}}
						
						{{^hasDetail}}
						<a> 
							<img src="{{image}}" style="width: 150px; height: 150px;" />
						</a>
						{{/hasDetail}}
						
					</div>				
				</div>			
				<div class="row">
					<div class='col-md-12'>
						<span>
							{{vicinity}}
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
		longitude: -46.6928957
	};

	var script = document.createElement('script');
	script.src = "https://maps.googleapis.com/maps/api/js?key=" + config.api_key + "&libraries=places&callback=initMap";
	document.getElementsByTagName('head')[0].appendChild(script);

	var buildUrlImage = function(place) {
	
		var imageUrl = null;	
	
		if (place.photos != null && place.photos.length > 0) {
			imageUrl = place.photos[0].getUrl({'maxWidth': 150, 'maxHeight': 150});
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
		  
		  placeHelper = {
			name: place.name,
			image: buildUrlImage(place),
			linkDetail: buildLinkDetail(place),
			vicinity: place.vicinity,
			index: i,
			hasDetail: function() {
				return this.linkDetail != "#";
			}
		  };
		  
		  data.content.push(placeHelper);
		  
		  bounds.extend(place.geometry.location);
        }
		
		map.fitBounds(bounds);
		
		renderizarMenos();
      }
	  
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
