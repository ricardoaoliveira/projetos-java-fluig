package br.com.rao.main;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileMessage;
import org.apache.camel.impl.DefaultCamelContext;

import ExemplosDocumentService.DocumentServiceClient;

public class CamelMain {

	public static void main(String[] args) throws Exception {
		
		final CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					
					//from("file:C:\\development\\camel\\aguardando\\.pdf&delay=100&delete=true")
					from("file:C:\\development\\camel\\aguardando?delete=true&idempotent=true")
					//from("file:C:\\development\\camel\\aguardando")
					.threads(1, 1)
					.process(new Processor() {
						
						@Override
						public void process(Exchange exchange) throws Exception {
							
							synchronized (camelContext) {
								
								System.out.println(exchange);
								
								@SuppressWarnings("rawtypes")
								GenericFileMessage gfm = (GenericFileMessage) exchange.getIn();
								
								@SuppressWarnings("rawtypes")
								GenericFile gf = gfm.getGenericFile();
								
								File f = (File) gf.getFile();
								
								System.out.println(f);	
							}
							
						}
					})
					.choice()
					.when(new Predicate() {
						
						@Override
						public boolean matches(Exchange exchange) {
							
							synchronized (camelContext) {
								boolean result = false;
								
								@SuppressWarnings("rawtypes")
								GenericFileMessage gfm = (GenericFileMessage) exchange.getIn();
								
								@SuppressWarnings("rawtypes")
								GenericFile gf = gfm.getGenericFile();
								
								File f = (File) gf.getFile();
								
								if (!f.getName().endsWith("docx")) {
									OcrHelper helper = OcrHelper.newInstance();
									helper.setFile(f);
									helper.execute();
									
									if (helper.getOcrDataVO().containValue("NOTA") && helper.getOcrDataVO().containValue("FISCAL")) {
										DocumentServiceClient dsc = new DocumentServiceClient();
										dsc.createDocumentByParentId(66, f);
										result = true;
									} else if (helper.getOcrDataVO().containValue("Cedente") && helper.getOcrDataVO().containValue("Sacada")) {
										DocumentServiceClient dsc = new DocumentServiceClient();
										dsc.createDocumentByParentId(65, f);
										result = true;
									}	
								} else {
									result = false;
								}
								
								return result;	
							}
							
						}
					})
					.log("Enviado para o Fluig")
					.to("file:C:\\development\\camel\\processado")
					.otherwise()
					.log("Não enviado para o GED")
					.to("file:C:\\development\\camel\\processado_nao_enviado_ged")
					.endChoice();
				}
			});
			camelContext.start();
			Thread.sleep(100000);
			camelContext.stop();
		} catch (Exception camelException) {
			camelException.printStackTrace();
		}
	}

}
