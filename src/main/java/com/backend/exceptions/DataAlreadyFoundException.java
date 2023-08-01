package com.backend.exceptions;

public class DataAlreadyFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAlreadyFoundException() {
		super();
	}

	/**
	 * Constructor
	 */
	public DataAlreadyFoundException(String msg, Exception e) {
		super(msg, e);

	}

	public DataAlreadyFoundException(String msg) {
		super(msg);

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

}
