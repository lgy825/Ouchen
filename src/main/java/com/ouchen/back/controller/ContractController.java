package com.ouchen.back.controller;

import com.ouchen.back.service.contract.ContractService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.constant.PageResult;
import com.ouchen.core.model.contract.Contract;
import com.ouchen.core.model.contract.ContractExt;
import com.ouchen.core.util.RegxUtils;
import com.ouchen.core.util.WorkDayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
@RequestMapping("contract")
@Controller
public class ContractController extends BaseController{

    @Autowired
    private ContractService contractService;

    @RequestMapping("/toRentContractlist")
    public String toRentContractlist() {
        return "contract/rentcontractlist";
    }

    @RequestMapping("/toTrustContractlist")
    public String toTrustContractlist() {
        return "contract/trustcontractlist";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<ContractExt> getShopPageByCondition(HttpServletRequest request,
                                                          Integer pageIndex, Integer pageSize,
                                                          String contractCode,
                                                          String beginTime, String endTime, String projectId,
                                                          String contractType) {
        Map<String, Object> params = new HashMap<>();
        params.put("contractCode", contractCode);
        //params.put("contracName", contracName);
        params.put("contractType", contractType);
        params.put("projectId", projectId);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", beginTime)) {
            params.put("beginTime", beginTime+"00:00:00");
        }
        if(RegxUtils.valid("^\\d{4}\\-\\d{2}\\-\\d{2}$", endTime)) {
            params.put("endTime", endTime+"23:59:59");
        }
//        if(projectId!=null){
//            params.put("projectId", projectId);
//        }else{
//            List<Project> projects= ShiroUtils.getAllMyCinemaList();
//            List<String> projectIds=projects.stream().map(cinema -> cinema.getId()).collect(Collectors.toList());
//            params.put("projectIds",projectIds);
//        }

        return createSuccessPageResult(contractService.getPageByCondition(params));
    }

    @RequestMapping("/toaddRent")
    public String toaddRent() {
        return "contract/newrentcontract";
    }

    @RequestMapping("/toaddTrust")
    public String toaddTrust() {
        return "contract/newtrustcontract";
    }

    @RequestMapping("/tolookRent")
    public String tolookRent(String id,Model model) {
        if (StringUtils.isBlank(id)) {
            return "contract/rentcontractlist";
        }
        model.addAttribute("contractId", id);
        return "contract/lookrentcontract";
    }

    @RequestMapping("/tolookTrust")
    public String tolookTrust(String id,Model model) {
        if (StringUtils.isBlank(id)) {
            return "contract/trustcontractlist";
        }
        model.addAttribute("contractId", id);
        return "contract/looktrustcontract";
    }


    @RequestMapping("/getRentFree")
    @ResponseBody
    public Result<Contract> getRentFree(Date contractStartTime,Integer count) {

        //Date contractStartTime=contract.getContractStartTime();
        Contract contract=new Contract();
        try {
            Date date= WorkDayUtils.getWorkDayEnd(contractStartTime,30);
            contract.setRentFreeEndTime(date);
            contract.setRentFreeStartTime(contractStartTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createSuccessResult(contract);

    }



    //addOrder
    @RequestMapping("/addContract")
    @ResponseBody
    public Result<Boolean> addContract(@RequestBody Contract contract) {
        if(StringUtils.isBlank(contract.getId())) {
            try {
                contractService.addContract(contract);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return  createSuccessResult(contractService.updateContract(contract));
        }
        return createSuccessResult(true);
    }

    @RequestMapping("/toeditRent")
    public String toeditRent(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "contract/toRentContractlist";
        }
        model.addAttribute("contractId", id);
        return "contract/newrentcontract";
    }

    @RequestMapping("/toeditTrust")
    public String toeditTrust(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "contract/toTrustContractlist";
        }
        model.addAttribute("contractId", id);
        return "contract/newtrustcontract";
    }

    @RequestMapping("/getContract")
    @ResponseBody
    public Result<Contract> get(String id){
        return createSuccessResult(contractService.getContract(id));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteRent(String id) {

        return createSuccessResult(contractService.delete(id));
    }



}
