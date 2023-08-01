package com.backend.exceptions;

public class PariveshException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PariveshException() {
		super();
	}

	/**
	 * Constructor
	 */
	public PariveshException(String msg, Exception e) {
		super(msg, e);

	}

	public PariveshException(String msg) {
		super(msg);
		
	}

	/**
	 * Override finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

}
