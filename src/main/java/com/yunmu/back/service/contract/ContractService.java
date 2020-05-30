package com.yunmu.back.service.contract;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.contract.Contract;
import com.yunmu.core.model.contract.ContractExt;

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
