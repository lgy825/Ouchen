package com.ouchen.back.service.contract.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ouchen.back.service.contract.ContractService;
import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.dao.contract.ContractMapper;
import com.ouchen.core.dao.contract.ContractMapperExt;
import com.ouchen.core.dao.customer.CustomerMapper;
import com.ouchen.core.dao.personnel.PersonnelMapper;
import com.ouchen.core.dao.sys.SysUserMapper;
import com.ouchen.core.model.contract.Contract;
import com.ouchen.core.model.contract.ContractExt;
import com.ouchen.core.util.IdUtils;
import com.ouchen.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapperExt contractMapperExt;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private PersonnelMapper personnelMapper;



    @Override
    public GenericPage<ContractExt> getPageByCondition(Map<String, Object> params) {
        int pageIndex = 1, pageSize = 10;
        if(params.containsKey("pageIndex")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(params.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(params.containsKey("pageSize")) {
            if(params.get("pageIndex") != null &&
                    StringUtils.isNotBlank(params.get("pageSize").toString())) {
                pageSize = Integer.valueOf(params.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<ContractExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<ContractExt> contractExts=contractMapperExt.getRentContractPage(params);
        for(ContractExt contractExt:contractExts){

            contractExt.setCustomerName(customerMapper.selectByPrimaryKey(contractExt.getCustomerCode()).getCustomerName());
            contractExt.setPersonnelName(sysUserMapper.selectByPrimaryKey(contractExt.getPersonnelCode()).getUserName());
            contractExt.setCreateName(sysUserMapper.selectByPrimaryKey(contractExt.getCreateBy()).getUserName());
        }

        return new GenericPage<>(pageIndex, pageSize, contractExts, pageInfo.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addContract(Contract contract) {

        contract.setCreateTime(new Date());
        contract.setCreateBy(ShiroUtils.getUserId());
        contract.setId(IdUtils.getId(11));
        //contract.setContractType(10);
        contract.setDelFlag(0);
        contract.setContractStatus(10);

        contractMapper.insertSelective(contract);
        return true;
    }

    @Override
    public boolean updateContract(Contract contract) {

        contract.setUpdateBy(ShiroUtils.getUserId());
        contract.setUpdateTime(new Date());
        contractMapper.updateByPrimaryKeySelective(contract);
        return true;
    }

    @Override
    public Contract getContract(String id) {
        return contractMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean delete(String contractId) {
        Contract contract=new Contract();
        contract.setDelFlag(1);
        contract.setUpdateBy(ShiroUtils.getUserId());
        contract.setUpdateTime(new Date());
        contract.setId(contractId);
        contractMapper.updateByPrimaryKey(contract);
        return true;
    }
}
