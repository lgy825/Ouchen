package com.yunmu.back.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yunmu.back.service.company.CompanyService;
import com.yunmu.back.service.project.ProjectService;
import com.yunmu.back.service.sys.SysUserService;
import com.yunmu.core.base.BaseController;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.PageResult;
import com.yunmu.core.model.company.Company;
import com.yunmu.core.model.company.CompanyExt;
import com.yunmu.core.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/company")
@Controller
public class CompanyController extends BaseController {
    private static  final Logger log= LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProjectService projectService;


    @RequestMapping("/toCompanylist")
    public String toCompanyList(){
        return "company/companylist";
    }

    @RequestMapping("/toAddCompany")
    public String toAddCompany(){
        return "company/newcompany";
    }

    @RequestMapping("/toEdit")
    public String toEditCompany(Model model,String id){
        model.addAttribute("companyId",id);
        return "company/newcompany";
    }

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<CompanyExt> getPageByCondition(HttpServletRequest request, @RequestParam("pageIndex") Integer pageIndex,
                                                     @RequestParam("pageSize") Integer pageSize, String  companyName){
        Map<String,Object> param=new HashMap<String,Object>();

        param.put("pageIndex",pageIndex+1);
        param.put("pageSize",pageSize);
        param.put("companyName",companyName);
        return createSuccessPageResult(companyService.getPageByCondition(param));
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result<Boolean> saveCompany(@RequestBody  Company company) {
        if (StringUtils.isBlank(company.getId())) {
            company.setId(IdUtils.getId(11));
            company.setCompanyCode(UUID.randomUUID().toString().replace("-",""));
            try {
                companyService.insert(company);
            } catch (Exception e1) {
                return createFailedResult(e1.getMessage(), false);
            }
        } else {
            return createSuccessResult(companyService.update(company));
        }
        return createSuccessResult(true);
    }

    @RequestMapping(value="/uploadImg")
    @ResponseBody
    public Result uploadImg(Model model,@RequestParam(value="myfile",required = false) MultipartFile[] logoFile){
        MultipartFile[] file = null;
        Map<String, String> uploadResult= Maps.newHashMap();
        try {
            if(logoFile.length!=0){
                file = logoFile;
            }
            StringBuffer absolute=new StringBuffer();
            List<String> absoluteList= Lists.newArrayList();
            List<String> relativePathList=Lists.newArrayList();
            for(int i=0;i<file.length;i++){
                InputStream in=file[i].getInputStream();
//                Result<String> ossResult= OSSFileUploadUtils.upload(in,"I");
//                if(ossResult.getResultCode().equals("0")){
//                    String  dir=ossResult.getResultData();
//                    absolute.append(imageUrl).append(dir);
//                    absoluteList.add(absolute.toString());
//                    relativePathList.add(dir);
//                }
            }
            if(!ObjectUtils.isEmpty(absoluteList)&&!ObjectUtils.isEmpty(relativePathList)){
                uploadResult.put("url",StringUtils.join(absoluteList.toArray(),","));
                uploadResult.put("path",StringUtils.join(relativePathList.toArray(),","));
                uploadResult.put("resultCode","0");
                return  createSuccessResult(uploadResult);
            }else {
                uploadResult.put("resultCode","1");
                uploadResult.put("resultDesc","文件上传失败,未上传成功！");
            }
        } catch (IOException e) {
            uploadResult.put("resultCode", "1");
            uploadResult.put("resultDesc", "文件上传异常");
            log.error("文件上传失败", e);
        }
        return createFailedResult(uploadResult.get("resultDesc"));
    }


    @RequestMapping("/get")
    @ResponseBody
    public Result<Company> update(String id) {
        if (StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(companyService.getCompanyById(id));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(String id) {
        if(sysUserService.getSysUserByCompanyCode(id)>0){
            return createFailedResult("该公司还有员工没有删除，不能删除此公司");
        }
        if(projectService.getSysUserByCompanyCode(id)>0){
            return createFailedResult("该公司还有项目没有删除，不能删除此公司");
        }
        return createSuccessResult(companyService.deleteByPrimaryKey(id));
    }

}
