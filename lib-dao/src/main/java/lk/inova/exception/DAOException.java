package lk.inova.exception;

import lk.inova.util.JsonUtil;

public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 609214268006549691L;
	private String errorCode;
	private String errorMessage;
	
	
	
	
	public DAOException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
	public DAOException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}


	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
