package com.yunmu.core.dao.contract;

import com.yunmu.core.model.contract.ContractExt;

import java.util.List;
import java.util.Map;

public interface ContractMapperExt {

    List<ContractExt> getRentContractPage(Map<String, Object> params);

}