package br.com.rao.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class OcrHelper {

	private File file;
	
	private Map<String, String> content;
	
	private String result;
	
	private OcrDataVO ocrDataVO;
	
	public static OcrHelper newInstance() {
		return new OcrHelper();
	}
	
	public OcrHelper setFile(File file) {
		this.file = file;
		return this;
	}
	
	public OcrHelper execute() {
		
		try {
			
			if (file != null && file.isFile() && file.exists() && file.canRead()) {
		        
				ITesseract instance = new Tesseract();
		        
		        instance.setDatapath("C:\\development\\tessdata\\tessdata-3.04.00");
	        	instance.setLanguage("por");
	            
	        	this.result = instance.doOCR(file);
	            
	            if (result != null) {
	            	populateContent();
	            }
			}	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	private OcrHelper populateContent() {
		this.content = new HashMap<String, String>();
		
		this.ocrDataVO = new OcrDataVO();
		
		String lines[] = result.split("\n");
        
		if (lines != null && lines.length > 0) {
        	int lineCount = 0;
        	for (String line : lines) {
        		++lineCount;
        		String cols[] = line.split("\\s+");
                if (cols != null && cols.length > 0) {
                    int colCount = 0;
                	for (String col : cols) {
                    	++colCount;
                    	this.content.put("" + lineCount + ":" + colCount, col);
                    	this.ocrDataVO.addValue(lineCount, colCount, col);
                	}
                }
            }	
        }
        
		return this;
	}
	
	public Map<String, String> getContent() {
		return this.content;
	}
	
	public String getContentToString() {
		StringBuilder sb = new StringBuilder();
		
		if (getContent() != null) {
			
		}
		
		return sb.toString();
	}
	
	public String getResult() {
		return this.result;
	}
	
	public OcrDataVO getOcrDataVO() {
		return ocrDataVO;
	}

	public static void main(String args[]) {
		OcrHelper helper = OcrHelper.newInstance();
		helper.setFile(new File("C:\\development\\timesheet\\09-03-2018\\tessa\\Downloads\\Rita\\2018-01-31 - CIEE NF 1060524.pdf"));
		helper.execute();
		
		System.out.println(helper.getContent().toString());
		
		System.out.println(helper.getOcrDataVO().getPlainText());
		
		System.out.println("CNPJ: " + helper.getOcrDataVO().getValueAfter("CPFICNPJ:"));
		
		System.out.println("CNPJ: " + helper.getOcrDataVO().getValueAfterByIndex("CPFICNPJ:", 2));
		
		System.out.println("Contain 61.600.839f0001-55? " + helper.getOcrDataVO().containValue("61.600.839f0001-55"));
	}
	
}
