package br.com.totvs.mgw.exception;

public class MgwException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MgwException(String message) {
        super(message);
    }
	
	public MgwException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public MgwException(Throwable cause) {
        super(cause);
    }
}
