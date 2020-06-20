package com.ouchen.back.service.contract;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.contract.Contract;
import com.ouchen.core.model.contract.ContractExt;

import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
public interface ContractService {

    GenericPage<ContractExt> getPageByCondition(Map<String, Object> params);

    boolean addContract(Contract contract);

    boolean updateContract(Contract contract);

    Contract getContract(String id);

    boolean delete(String contractId);


}
