<div id="Calendario_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="Calendario.instance()">

	<div class="fluig-style-guide">
	    <h2 class="page-header"> 
	    	<span class="fluigicon fluigicon-calendar"></span>
	        Calendário
	    </h2>
    </div>


<div id='calendario_top' style="display:block">

    <div class='calendario_left'>

      <div id='theme-system-selector' class='selector'>
        Theme System:

        <select>
          <option value='bootstrap4' selected>Bootstrap 4</option>
          <option value='bootstrap3'>Bootstrap 3</option>
          <option value='jquery-ui'>jQuery UI</option>
          <option value='standard'>unthemed</option>
        </select>
      </div>

	  <br/>

      <div data-theme-system="bootstrap3" class='selector' style='display:none'>
        Theme Name:

        <select>
          <option value='' selected>Default</option>
          <option value='cosmo'>Cosmo</option>
          <option value='cyborg'>Cyborg</option>
          <option value='darkly'>Darkly</option>
          <option value='flatly'>Flatly</option>
          <option value='journal'>Journal</option>
          <option value='lumen'>Lumen</option>
          <option value='paper'>Paper</option>
          <option value='readable'>Readable</option>
          <option value='sandstone'>Sandstone</option>
          <option value='simplex'>Simplex</option>
          <option value='slate'>Slate</option>
          <option value='solar'>Solar</option>
          <option value='spacelab'>Spacelab</option>
          <option value='superhero'>Superhero</option>
          <option value='united'>United</option>
          <option value='yeti'>Yeti</option>
        </select>
      </div>

      <div data-theme-system="bootstrap4" class='selector' style='display:none'>
        Theme Name:

        <select>
          <option value='' selected>Default</option>
          <option value='cerulean'>Cerulean</option>
          <option value='cosmo'>Cosmo</option>
          <option value='cyborg'>Cyborg</option>
          <option value='darkly'>Darkly</option>
          <option value='flatly'>Flatly</option>
          <option value='journal'>Journal</option>
          <option value='litera'>Litera</option>
          <option value='lumen'>Lumen</option>
          <option value='lux'>Lux</option>
          <option value='materia'>Materia</option>
          <option value='minty'>Minty</option>
          <option value='pulse'>Pulse</option>
          <option value='sandstone'>Sandstone</option>
          <option value='simplex'>Simplex</option>
          <option value='sketchy'>Sketchy</option>
          <option value='slate'>Slate</option>
          <option value='solar'>Solar</option>
          <option value='spacelab'>Spacelab</option>
          <option value='superhero'>Superhero</option>
          <option value='united'>United</option>
          <option value='yeti'>Yeti</option>
        </select>
      </div>

      <div data-theme-system="jquery-ui" class='selector' style='display:none'>
        Theme Name:

        <select>
          <option value='black-tie'>Black Tie</option>
          <option value='blitzer'>Blitzer</option>
          <option value='cupertino' selected>Cupertino</option>
          <option value='dark-hive'>Dark Hive</option>
          <option value='dot-luv'>Dot Luv</option>
          <option value='eggplant'>Eggplant</option>
          <option value='excite-bike'>Excite Bike</option>
          <option value='flick'>Flick</option>
          <option value='hot-sneaks'>Hot Sneaks</option>
          <option value='humanity'>Humanity</option>
          <option value='le-frog'>Le Frog</option>
          <option value='mint-choc'>Mint Choc</option>
          <option value='overcast'>Overcast</option>
          <option value='pepper-grinder'>Pepper Grinder</option>
          <option value='redmond'>Redmond</option>
          <option value='smoothness'>Smoothness</option>
          <option value='south-street'>South Street</option>
          <option value='start'>Start</option>
          <option value='sunny'>Sunny</option>
          <option value='swanky-purse'>Swanky Purse</option>
          <option value='trontastic'>Trontastic</option>
          <option value='ui-darkness'>UI Darkness</option>
          <option value='ui-lightness'>UI Lightness</option>
          <option value='vader'>Vader</option>
        </select>
      </div>

      <span id='loading' style='display:none'>loading theme...</span>

    </div>

    <div class='calendario_right' style="display:none">
      <span class='credits' data-credit-id='bootstrap-standard' style='display:none'>
        <a href='https://getbootstrap.com/docs/3.3/' target='_blank'>Theme by Bootstrap</a>
      </span>
      <span class='credits' data-credit-id='bootstrap-custom' style='display:none'>
        <a href='https://bootswatch.com/' target='_blank'>Theme by Bootswatch</a>
      </span>
      <span class='credits' data-credit-id='jquery-ui' style='display:none'>
        <a href='http://jqueryui.com/themeroller/' target='_blank'>Theme by jQuery UI</a>
      </span>
    </div>

    <div class='calendario_clear'></div>
  </div>

  <div id='calendar' style="display:block"></div>

</div>

<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css' rel='stylesheet'>

<script>

$(document).ready(function() {

	var eventos = [
		{
              title: 'All Day Event',
              start: '2018-03-01'
            },
            {
              title: 'Long Event',
              start: '2018-03-07',
              end: '2018-03-10'
            },
            {
              id: 999,
              title: 'Repeating Event',
              start: '2018-03-09T16:00:00'
            },
            {
              id: 999,
              title: 'Repeating Event',
              start: '2018-03-16T16:00:00'
            },
            {
              title: 'Conference',
              start: '2018-03-11',
              end: '2018-03-13'
            },
            {
              title: 'Meeting',
              start: '2018-03-12T10:30:00',
              end: '2018-03-12T12:30:00'
            },
            {
              title: 'Lunch',
              start: '2018-03-12T12:00:00'
            },
            {
              title: 'Meeting',
              start: '2018-03-12T14:30:00'
            },
            {
              title: 'Happy Hour',
              start: '2018-03-12T17:30:00'
            },
            {
              title: 'Dinner',
              start: '2018-03-12T20:00:00'
            },
            {
              title: 'Birthday Party',
              start: '2018-03-13T07:00:00'
            },
            {
              title: 'Click for Google',
              url: 'http://google.com/',
              start: '2018-03-28'
            },
			{
			  title: 'MGW',
			  url: 'http://www.mgwativos.com.br',
			  start: '2018-04-18T08:00:00',
			  end: '2018-04-18T12:00:00',
			}
	  ];
  
    

    initThemeChooser({
	
      init: function(themeSystem) {
        $('#calendar').fullCalendar({
          themeSystem: themeSystem,
          header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay,listMonth'
          },
          defaultDate: '2018-03-12',
		  locale: 'pt-br',
          weekNumbers: true,
          navLinks: true, // can click day/week names to navigate views
          editable: false,
          eventLimit: true, // allow "more" link when too many events
          events: 
            eventos          
        });
      },

      change: function(themeSystem) {
        $('#calendar').fullCalendar('option', 'themeSystem', themeSystem);
      }

    });
	
	var selectListMonthButton = function() {
	   var btnsListMonth = $(".fc-listMonth-button");
	   if (btnsListMonth != null && btnsListMonth.length > 0) {
		 btnsListMonth[0].click();
		 $("#calendar").show();
	   }
	};
	  
	setTimeout( function() { selectListMonthButton() }, 500);
	
  });
  
</script>