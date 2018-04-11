<#import "/wcm.ftl" as wcm/>

<@wcm.header />

<!-- WCM Wrapper content -->
<div class="wcm-wrapper-content">

    <@wcm.menu />

	<!-- Wrapper -->
	<div class="wcm-all-content">
		<div id="wcm-content" class="clearfix wcm-background">

	        <!-- Onde deverá estar a barra de formatação -->
	        <#if pageRender.isEditMode()=true>
			<div name="formatBar" id="formatBar"></div>
            <!-- Div geral -->
            <!-- Há CSS distinto para Edição/Visualização -->
			<div id="edicaoPagina" class="clearfix">
			<#else>
			<div id="visualizacaoPagina" class="clearfix">
	        </#if>	

				<!-- Titulo da página -->
				<div class="slotfull layout-1-1">
					<span class="titleArea">${i18n.getTranslation('wcm.layoutfixedslot.title')}</span>
					<h2 class="pageTitle">${pageTitle}</h2>
				</div>				
	
				
				<div id="all-slots-left" class="layout-2-3left clearfix">
					<!-- Slot 1 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull1">
                        <@wcm.renderSlot id="SlotA" editableSlot="true"/>
					</div>
		
					<!-- Slot 2 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull2">
                        <@wcm.renderSlot id="SlotB" editableSlot="true"/>
					</div>
					
					<div class="clearfix">
						<!-- Slot 3 -->
						<div style="width:49%!important" class="editable-slot slotfull layout-1-3" id="slotFull3">
                            <@wcm.renderSlot id="SlotC" editableSlot="true"/>
						</div>
						
						<!-- Slot 4 -->
						<div style="width:49%!important" class="editable-slot slotfull layout-1-3" id="slotFull4">
                            <@wcm.renderSlot id="SlotD" editableSlot="true"/>
						</div>
						
					</div>
					
					<div class="clearfix">
						<!-- Slot 5 -->
						<div style="width:49%!important" class="editable-slot slotfull layout-1-3" id="slotFull5">
                            <@wcm.renderSlot id="SlotE" editableSlot="true"/>
						</div>
						
						<!-- Slot 6 -->
						<div style="width:49%!important" class="editable-slot slotfull layout-1-3" id="slotFull6">
                            <@wcm.renderSlot id="SlotF" editableSlot="true"/>
						</div>
					</div>
				</div>
				
				<div id="all-slots-right">
					<!-- Slot 7 -->
					<div class="editable-slot slotfull layout-1-1 fixed slotFixedDim" id="slotFull7">
                        <@wcm.renderSlot id="SlotG" editableSlot="true" class="slotFixedSubDim" />
					</div>
		
					<!-- Slot 8 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull8">
                        <@wcm.renderSlot id="SlotH" editableSlot="true"/>
					</div>
					<!-- Slot 9 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull9">
                        <@wcm.renderSlot id="SlotI" editableSlot="true"/>
					</div>
					<!-- Slot 10 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull10">
                        <@wcm.renderSlot id="SlotJ" editableSlot="true"/>
					</div>
					
					<!-- Slot 11 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull11">
                        <@wcm.renderSlot id="SlotK" editableSlot="true"/>
					</div>
					
					<!-- Slot 12 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull12">
                        <@wcm.renderSlot id="SlotL" editableSlot="true"/>
					</div>
					<!-- Slot 13 -->
					<div class="editable-slot slotfull layout-1-1" id="slotFull13">
                        <@wcm.renderSlot id="SlotM" editableSlot="true"/>
					</div>
				</div>				
			</div>
			<!-- FIM DAS WIDGETS DO LAYOUT -->

            <@wcm.footer layoutuserlabel="wcm.layoutfixedslot.user" />

			<script type="text/javascript">
				$(document).ready(function() {
					if(document.URL.indexOf('edit=true') == -1){						
						var spaceHeight = document.getElementById('slotFull7').offsetHeight;					
						document.getElementById('slotFull8').style.marginTop = spaceHeight - 100 + 'px';
					}					
			    });
			</script>
		</div>
	</div>
</div>

