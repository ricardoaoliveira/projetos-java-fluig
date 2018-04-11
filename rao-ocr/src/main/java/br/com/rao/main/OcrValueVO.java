package br.com.rao.main;

import java.io.Serializable;

public class OcrValueVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer line;
	
	private Integer column;
	
	private String value;
	
	public static OcrValueVO newInstance() {
		return new OcrValueVO();
	}

	public Integer getLine() {
		return line;
	}

	public OcrValueVO setLine(Integer line) {
		this.line = line;
		return this;
	}

	public Integer getColumn() {
		return column;
	}

	public OcrValueVO setColumn(Integer column) {
		this.column = column;
		return this;
	}

	public String getValue() {
		return value;
	}

	public OcrValueVO setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return "OcrValueVO [line=" + line + ", column=" + column + ", value=" + value + "]";
	}
	
}
