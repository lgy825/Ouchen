package com.yunmu.core.exception;

/**
 * 数据异常类
 * @author Lomis
 * @version 2015年11月26日 09:02:46
 *
 */
public class DataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;	// 错误编码
	private String errorDesc;	// 错误信息

	/** 异常源  */
	private Throwable exception;
	
	public DataException() {
	}
	/**
	 * 构造函数
	 * @param e 异常源
	 */
	public DataException(Throwable e){
		super(e);
		this.exception = e;
	}

	public DataException(Throwable e, String errorCode, String errorDesc){
		super(e);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public DataException(String errorDesc) {
		super(errorDesc);
		this.errorDesc = errorDesc;
	}
	public DataException(String errorCode, String errorDesc) {
		super(errorDesc);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

}
