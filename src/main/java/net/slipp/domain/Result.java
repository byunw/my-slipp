package net.slipp.domain;

public class Result{
		
	//fields
	private Boolean valid;
	private String errorMessage;
	
	//constructor
	private Result(boolean valid,String errorMessage) {
		this.valid=valid;
		this.errorMessage=errorMessage;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	
	public static Result ok() {
		return new Result(true,null);
		
	}
	
	public static Result fail(String errorMessage) {
		return new Result(false,errorMessage);
	}
	
	

}
