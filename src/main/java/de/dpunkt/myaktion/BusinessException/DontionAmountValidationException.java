package de.dpunkt.myaktion.BusinessException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DontionAmountValidationException extends RuntimeException{

	public DontionAmountValidationException(String message) {
		super(message);
		System.out.println("Created DontionAmountValidationException");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1804623957937503786L;

}
