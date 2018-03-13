package edu.ilstu.biology.flytranscriptionwebapp.validation;

import edu.ilstu.biology.flytranscriptionwebapp.model.Error;

public class InvalidGeneFormException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2283478639063112187L;
	
	private Error error;
	
	public InvalidGeneFormException(Error error){
		this.error = error;
	}
	
	public InvalidGeneFormException(Error error, String message){
		super(message);
		this.error = error;
	}
	
	public Error getError(){
		return error;
	}
	
	public void setError(Error error){
		this.error = error;
	}
}
