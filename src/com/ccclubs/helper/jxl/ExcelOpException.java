package com.ccclubs.helper.jxl;


public class ExcelOpException extends Exception{

	/**
	 * @see ExcelOpException
	 * @category excel操作异常
	 */
	private static final long serialVersionUID = -600941587132793444L;

	public ExcelOpException() {
		super();
	}

	public ExcelOpException(String message) {
		super(message);
	}

	public ExcelOpException(Throwable cause) {
		super(cause);
	}

	public ExcelOpException(String message, Throwable cause) {
		super(message, cause);
	}

}
