package com.yunmu.core.base;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.constant.ResultConstants;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yangbin on 2017/11/21
 */
public class BaseController {

	static {
		ConvertUtils.register(new DateConverter(null), Date.class);
	}

	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	public <T> Result<T> createSuccessResult(T resultData) {
		Result<T> result = new Result<>();
		result.setResultData(resultData);
		return result;
	}

	public <T> Result<T> createSuccessResult(T resultData, String resultDesc) {
		Result<T> result = new Result<>();
		result.setResultData(resultData);
		result.setResultDesc(resultDesc);
		return result;
	}
	public Result createFailedResult(String resultDesc) {
		Result result = new Result();
		result.setResultCode(ResultConstants.RESULT_CODE_FAILED);
		result.setResultDesc(resultDesc);
		return result;
	}
	public <T> Result<T> createFailedResult(String resultDesc, T resultData) {
		return createResult(ResultConstants.RESULT_CODE_FAILED, resultDesc, resultData);
	}

	public <T> Result<T> createResult(String resultCode, String resultDesc, T resultData) {
		Result<T> result = new Result<>();
		result.setResultCode(resultCode);
		result.setResultData(resultData);
		result.setResultDesc(resultDesc);
		return result;
	}

	public <T> PageResult<T> createSuccessPageResult(GenericPage<T> resultData) {
		PageResult<T> result = new PageResult<>();
		result.setResultData(resultData);
		return result;
	}
	public PageResult createFailedPageResult(String resultDesc) {
		PageResult result = new PageResult();
		result.setResultCode(ResultConstants.RESULT_CODE_FAILED);
		result.setResultDesc(resultDesc);
		return result;
	}
	public <T> PageResult<T> createFailedPageResult(String resultDesc, GenericPage<T> resultData) {
		PageResult<T> result = new PageResult<>();
		result.setResultCode(ResultConstants.RESULT_CODE_FAILED);
		result.setResultDesc(resultDesc);
		result.setResultData(resultData);
		return result;
	}

	/**
	 * 把参数转成map
	 * @param request
	 * @return
	 */
	protected Map<String, String> paramToMap(HttpServletRequest request){
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map<String, String> returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if(null == valueObj){
				value = "";
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){
					value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1);
			}else{
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

}
