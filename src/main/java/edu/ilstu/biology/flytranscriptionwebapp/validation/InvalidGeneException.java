package edu.ilstu.biology.flytranscriptionwebapp.validation;

import edu.ilstu.biology.flytranscriptionwebapp.model.Error;

public class InvalidGeneException extends RuntimeException {

	private static final long serialVersionUID = -6015676688697185592L;
	
	private Error error;
	
	public InvalidGeneException(Error error){
		this.error = error;
	}
	
	public InvalidGeneException(Error error, String message){
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
