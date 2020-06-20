package com.ouchen.core.dao.contract;

import com.ouchen.core.model.contract.ContractExt;

import java.util.List;
import java.util.Map;

public interface ContractMapperExt {

    List<ContractExt> getRentContractPage(Map<String, Object> params);

}