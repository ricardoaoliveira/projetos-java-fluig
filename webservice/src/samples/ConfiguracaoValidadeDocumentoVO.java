package samples;

import java.io.Serializable;

public class ConfiguracaoValidadeDocumentoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cod_doc;
	
	private String expira;
	
	private String validade;
	
	public String getCod_doc() {
		return cod_doc;
	}
	
	public void setCod_doc(String cod_doc) {
		this.cod_doc = cod_doc;
	}
	
	public String getExpira() {
		return expira;
	}
	
	public void setExpira(String expira) {
		this.expira = expira;
	}
	
	public String getValidade() {
		return validade;
	}
	
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
}
