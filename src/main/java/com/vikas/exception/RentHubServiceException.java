package com.vikas.exception;

/**
 * Class      : AuthServiceException
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Feb 18, 2026
 * Version    : 1.0
 */

public class RentHubServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public RentHubServiceException(String msg) {
		super(msg);
	}

}
