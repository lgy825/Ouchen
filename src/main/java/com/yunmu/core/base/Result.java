package com.yunmu.core.base;



import com.yunmu.core.constant.ResultConstants;

import java.io.Serializable;

/**
 * 接口返回结构
 * @author Lomis
 *
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String resultCode = ResultConstants.RESULT_CODE_SUCCESS;	// 结果编码 0:成功 1:失败
	private String resultDesc = ResultConstants.RESULT_DESC_SUCCESS;	// 返回的结果描述
	private T resultData;	// 返回的结果数据

	public Result(){}

	public Result(String resultCode, String resultDesc) {
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
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

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}
}
