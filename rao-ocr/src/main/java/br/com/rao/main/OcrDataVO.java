package br.com.rao.main;

import java.util.ArrayList;
import java.util.List;

public class OcrDataVO {

	private List<OcrValueVO> values;
	
	public static OcrDataVO newInstance() {
		return new OcrDataVO();
	}

	public OcrDataVO addValue(Integer line, Integer column, String value) {
		getValues().add(OcrValueVO.newInstance().setLine(line).setColumn(column).setValue(value));
		return this;
	}
	
	public List<OcrValueVO> getValues() {
		if (values == null) {
			values = new ArrayList<OcrValueVO>();
		}
		return values;
	}

	public void setValues(List<OcrValueVO> value) {
		this.values = value;
	}

	public String getValueAfter(String valueRef) {
		String result = null;
		
		boolean hasGetValue = false;
		
		for (OcrValueVO ocrValue : getValues()) {
			if (hasGetValue) {
				result = ocrValue.getValue();
				return result;
			} 
			if (ocrValue.getValue().equals(valueRef)) {
				hasGetValue = true;
			}
		}
		
		return result;
	}
	
	public String getValueAfterByIndex(String valueRef, Integer index) {
		String result = null;
		
		boolean hasGetValue = false;
		
		Integer indexResult = 0;
		
		for (OcrValueVO ocrValue : getValues()) {
			if (hasGetValue) {
				result = ocrValue.getValue();
				return result;
			} 
			if (ocrValue.getValue().equals(valueRef)) {
				indexResult++;
				if (indexResult.equals(index)) {
					hasGetValue = true;	
				}
			}
		}
		
		return result;
	}
	
	public boolean containValue(String value) {
		for (OcrValueVO ocrValue : getValues()) {
			if (ocrValue.getValue().equals(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getPlainText() {
		StringBuilder sb = new StringBuilder();
		
		Integer line = 0;
		for (OcrValueVO value : values) {
			if (line.equals(value.getLine())) {
				sb.append(" " + value.getLine() + ":" + value.getColumn() + "=" + value.getValue());
			} else {
				line = value.getLine();
				sb.append("\n");
				sb.append(" " + value.getLine() + ":" + value.getColumn() + "=" + value.getValue());
			}
		}
		
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "OcrDataVO [value=" + values + "]";
	}
	
}
