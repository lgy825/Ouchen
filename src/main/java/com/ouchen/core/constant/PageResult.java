package com.ouchen.core.constant;


import java.io.Serializable;

/**
 * 接口返回结构
 */
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String resultCode = ResultConstants.RESULT_CODE_SUCCESS;	// 结果编码 0:成功 1:失败
	private String resultDesc = ResultConstants.RESULT_DESC_SUCCESS;	// 返回的结果描述
	private GenericPage<T> resultData;	// 返回的结果数据
	private long total = 0;

	public PageResult(){}

	public PageResult(GenericPage<T> resultData, String resultDesc, String resultCode) {
		this.resultData = resultData;
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
		this.total = resultData==null ? 0 : resultData.getTotalCount();
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public GenericPage<T> getResultData() {
		return resultData;
	}

	public void setResultData(GenericPage<T> resultData) {
		this.resultData = resultData;
		this.total = resultData.getTotalCount();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
