package com.yunmu.back.service.sys.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.yunmu.back.service.sys.SysUserService;
import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.constant.PermisionConstants;
import com.yunmu.core.dao.company.CompanyMapper;
import com.yunmu.core.dao.company.CompanyMapperExt;
import com.yunmu.core.dao.project.ProjectMapper;
import com.yunmu.core.dao.sys.*;
import com.yunmu.core.exception.DataException;
import com.yunmu.core.model.company.Company;
import com.yunmu.core.model.company.CompanyExample;
import com.yunmu.core.model.project.Project;
import com.yunmu.core.model.project.ProjectExample;
import com.yunmu.core.model.sys.*;
import com.yunmu.core.util.IdUtils;
import com.yunmu.core.util.MD5Util;
import com.yunmu.core.util.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger logger= LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapperExt sysUserMapperExt;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserProjectMapper sysUserProjectMapper;

    @Autowired
    private SysUserProjectMapperExt sysUserProjectMapperExt;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private CompanyMapperExt companyMapperExt;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public GenericPage<SysUserExt> getPageByCondition(Map<String, Object> params) {
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
        Page<SysUserExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<SysUserExt> sysUserExts=sysUserMapperExt.getSysUserPage(params);

        if(sysUserExts!=null){
            for(SysUserExt sysUserExt:sysUserExts){
                String projectName="";
                if(sysUserExt.getChooseProjectId()==1){
                    projectName="公司全部项目";
                }else{
                    List<String> projectIds=sysUserProjectMapperExt.getProjectIdsByUserId(sysUserExt.getId());
                    if(projectIds.size()>0){
                        for(int i=0;i<projectIds.size();i++){
                            if(i<2){
                                projectName+=projectMapper.selectByPrimaryKey(projectIds.get(i)).getProjectName();
                                if(i<projectIds.size()-2){
                                    projectName+=",";
                                }
                            }else{
                                projectName+="...";
                                break;
                            }
                        }

                    }
                }
                sysUserExt.setProjectName(projectName);
                if(companyMapperExt.getCompanyByCode(sysUserExt.getCompanyCode())!=null){
                    sysUserExt.setCompanyName(companyMapperExt.getCompanyByCode(sysUserExt.getCompanyCode()).getCompanyName());
                }else{
                    sysUserExt.setCompanyName("--");
                }

            }
        }
        return new GenericPage<>(pageIndex, pageSize, sysUserExts, pageInfo.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(SysUserExt sysUserExt) {

        if(sysUserExt != null) {
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(sysUserExt, sysUser);
            String userId = IdUtils.uuid2();
            sysUser.setId(userId);
            try {
                sysUser.setPassword(MD5Util.getEncryptedPwd(sysUserExt.getPassword()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                logger.error("加密密码失败:"+ sysUser.getPassword(), e);
                throw new DataException("保存用户失败");
            }
            sysUser.setCreateTime(new Date());
            sysUser.setDelFlag(0);
            sysUser.setStatus(0);
            sysUser.setUpdateTime(new Date());
            sysUser.setCreateBy(ShiroUtils.getUser().getUserName());

            // TODO 判断是否影核账号

            SysUserExample sysUserExample = new SysUserExample();
            SysUserExample.Criteria sysUserCri = sysUserExample.createCriteria();
            sysUserCri.andDelFlagEqualTo(0);

            sysUserCri.andLoginNameEqualTo(sysUser.getLoginName());
            if(sysUserMapper.countByExample(sysUserExample) > 0) {
                throw new DataException("用户名已存在");
            }

            sysUserMapper.insertSelective(sysUser);

            if(sysUser.getChooseProjectId() == 2) {
                List<SysUserProject> sysUserCinemaList = null;
                if (org.apache.commons.lang3.StringUtils.isNotBlank(sysUserExt.getCinemas())) {
                    sysUserCinemaList = new ArrayList<>();
                    if (sysUserExt.getCinemas().contains(",")) {
                        String[] cinemaCodes = sysUserExt.getCinemas().split(",");
                        for (String ccode : cinemaCodes) {
                            SysUserProject sysUserCinema = new SysUserProject();
                            sysUserCinema.setUserId(userId);
                            sysUserCinema.setProjectId(ccode);
                            sysUserCinemaList.add(sysUserCinema);
                        }
                    } else {
                        SysUserProject sysUserCinema = new SysUserProject();
                        sysUserCinema.setUserId(userId);
                        sysUserCinema.setProjectId(sysUserExt.getCinemas());
                        sysUserCinemaList.add(sysUserCinema);
                    }
                } else {
                    throw new DataException("请选择影院后提交");
                }
                sysUserMapperExt.insertBatchUserCinema(sysUserCinemaList);
            }

            List<SysUserRole> sysUserRoleList = null;
            if(org.apache.commons.lang3.StringUtils.isNotBlank(sysUserExt.getRoles())) {
                sysUserRoleList = new ArrayList<>();
                if(sysUserExt.getRoles().contains(",")) {
                    String[] roles = sysUserExt.getRoles().split(",");
                    for(String ccode: roles) {
                        SysUserRole sysUserRoleKey = new SysUserRole();
                        sysUserRoleKey.setUserId(userId);
                        sysUserRoleKey.setRoleId(ccode);
                        sysUserRoleList.add(sysUserRoleKey);
                    }
                } else {
                    SysUserRole sysUserRoleKey = new SysUserRole();
                    sysUserRoleKey.setUserId(userId);
                    sysUserRoleKey.setRoleId(sysUserExt.getRoles());
                    sysUserRoleList.add(sysUserRoleKey);
                }
            } else {
                throw new DataException("请选择角色后提交");
            }
            sysUserMapperExt.insertBatchUserRole(sysUserRoleList);
        }
        return true;
    }

    @Override
    public Boolean update(SysUserExt sysUserExt) {

        if(sysUserExt != null) {
            String userId = sysUserExt.getId();
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);

            int compareCount = 0;
            if(sysUser.getLoginName().equals(sysUserExt.getLoginName())) {
                compareCount = 1;
            }
            sysUserExt.setCompanyCode(null);
            BeanUtils.copyProperties(sysUserExt, sysUser);
            sysUser.setUpdateTime(new Date());
            if(org.apache.commons.lang3.StringUtils.isNotBlank(sysUser.getPassword())) {
                try {
                    sysUser.setPassword(MD5Util.getEncryptedPwd(sysUserExt.getPassword()));
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    logger.error("加密密码失败:" + sysUser.getPassword(), e);
                    throw new DataException("保存用户失败");
                }
            } else {
                sysUser.setPassword(null);
            }

            SysUserExample sysUserExample = new SysUserExample();
            SysUserExample.Criteria sysUserCri = sysUserExample.createCriteria();
            sysUserCri.andDelFlagEqualTo(0);
            sysUserCri.andLoginNameEqualTo(sysUser.getLoginName());

            if(sysUserMapper.countByExample(sysUserExample) > compareCount) {
                throw new DataException("用户名已存在");
            }
            sysUser.setUpdateBy(ShiroUtils.getUser().getUserName());

            sysUserMapper.updateByPrimaryKeySelective(sysUser);


            SysUserProjectExample projectExample = new SysUserProjectExample();
            SysUserProjectExample.Criteria cinemaCri = projectExample.createCriteria();
            cinemaCri.andUserIdEqualTo(userId);
            sysUserProjectMapper.deleteByExample(projectExample);
            if(sysUser.getChooseProjectId() == 2) {
                List<SysUserProject> sysUserCinemaList = null;
                if (org.apache.commons.lang3.StringUtils.isNotBlank(sysUserExt.getCinemas())) {
                    sysUserCinemaList = new ArrayList<>();
                    if (sysUserExt.getCinemas().contains(",")) {
                        String[] cinemaCodes = sysUserExt.getCinemas().split(",");
                        for (String ccode : cinemaCodes) {
                            SysUserProject sysUserProject = new SysUserProject();
                            sysUserProject.setUserId(userId);
                            sysUserProject.setProjectId(ccode);
                            sysUserCinemaList.add(sysUserProject);
                        }
                    } else {
                        SysUserProject sysUserCinema = new SysUserProject();
                        sysUserCinema.setUserId(userId);
                        sysUserCinema.setProjectId(sysUserExt.getCinemas());
                        sysUserCinemaList.add(sysUserCinema);
                    }
                } else {
                    throw new DataException("请选择项目后提交");
                }
                sysUserMapperExt.insertBatchUserCinema(sysUserCinemaList);
            }

//            List<SysUserRole> sysUserRoleList = null;
//            if(org.apache.commons.lang3.StringUtils.isNotBlank(sysUserExt.getRoles())) {
//                sysUserRoleList = new ArrayList<>();
//                if(sysUserExt.getRoles().contains(",")) {
//                    String[] roles = sysUserExt.getRoles().split(",");
//                    for(String ccode: roles) {
//                        SysUserRole sysUserRoleKey = new SysUserRole();
//                        sysUserRoleKey.setUserId(userId);
//                        sysUserRoleKey.setRoleId(ccode);
//                        sysUserRoleList.add(sysUserRoleKey);
//                    }
//                } else {
//                    SysUserRole sysUserRoleKey = new SysUserRole();
//                    sysUserRoleKey.setUserId(userId);
//                    sysUserRoleKey.setRoleId(sysUserExt.getRoles());
//                    sysUserRoleList.add(sysUserRoleKey);
//                }
//            } else {
//                throw new DataException("请选择角色后提交");
//            }
            SysUserRoleExample roleExample = new SysUserRoleExample();
            SysUserRoleExample.Criteria roleCri = roleExample.createCriteria();
            roleCri.andUserIdEqualTo(userId);
            sysUserRoleMapper.deleteByExample(roleExample);
            //sysUserMapperExt.insertBatchUserRole(sysUserRoleList);
        }
        return true;
    }

    @Override
    public Boolean nameExist(SysUserExt sysUserExt) {
        int compare = 0;
        if(org.apache.commons.lang3.StringUtils.isNotBlank(sysUserExt.getId())) {
            // 修改时候判断
            SysUser dbUser = sysUserMapper.selectByPrimaryKey(sysUserExt.getId());
            if(dbUser.getLoginName().equals(sysUserExt.getLoginName())) {
                compare = 1;
            }
        }

        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria sysUserCri = sysUserExample.createCriteria();
        sysUserCri.andDelFlagEqualTo(0);
        sysUserCri.andLoginNameEqualTo(sysUserExt.getLoginName());

        if(sysUserMapper.countByExample(sysUserExample) > compare) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SysUserExt getUserExt(String id) {
        SysUserExt sysUserExt = new SysUserExt();

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(sysUser, sysUserExt);

        SysUserProjectExample projectExample = new SysUserProjectExample();
        SysUserProjectExample.Criteria cinemaCri = projectExample.createCriteria();
        cinemaCri.andUserIdEqualTo(id);
        List<SysUserProject> sysUserCinemaList = sysUserProjectMapper.selectByExample(projectExample);
        if(sysUserCinemaList != null && !sysUserCinemaList.isEmpty()) {
            List<String> ccodes = sysUserCinemaList.stream().map(SysUserProject::getProjectId).collect(Collectors.toList());
            sysUserExt.setCinemas(Joiner.on(",").join(ccodes));
        }

        SysUserRoleExample roleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria roleCri = roleExample.createCriteria();
        roleCri.andUserIdEqualTo(id);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectByExample(roleExample);
        if(sysUserRoleList != null && !sysUserRoleList.isEmpty()) {
            List<String> ccodes = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            sysUserExt.setRoles(Joiner.on(",").join(ccodes));
        }
        return sysUserExt;
    }

    @Override
    public List<Project> getProjectByUserId(String userId) {
        SysUserProjectExample sysUserProjectExample=new SysUserProjectExample();
        SysUserProjectExample.Criteria criteria=sysUserProjectExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<SysUserProject> sysUserProjects=sysUserProjectMapper.selectByExample(sysUserProjectExample);

        //根据获取的项目id获取项目列表
        List<String> projectIds=new ArrayList<>();
        for(SysUserProject sysUserProject:sysUserProjects){
            projectIds.add(sysUserProject.getProjectId());
        }
        ProjectExample projectExample=new ProjectExample();
        ProjectExample.Criteria criteria1=projectExample.createCriteria();
        criteria1.andIdIn(projectIds);
        criteria1.andDelFlagEqualTo(0);
        List<Project> projects=projectMapper.selectByExample(projectExample);
        return projects;
    }

    @Override
    public List<SysUser> getUsersByCondition(Map<String, Object> param) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        if(param != null) {
            if(param.containsKey("loginName")) {
                criteria.andLoginNameEqualTo(String.valueOf(param.get("loginName")));
            }
        }
        List<SysUser> sysUsers=sysUserMapper.selectByExample(example);
        return sysUsers;
    }

    @Override
    public boolean getUsersBeDisabled(String userId) {
        if(org.apache.commons.lang3.StringUtils.isBlank(userId)) {
            return true;
        }
        // 判断用户状态
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if(sysUser == null ||
                org.apache.commons.lang3.StringUtils.isBlank(sysUser.getCompanyCode()) ||
                PermisionConstants.DISABLED_YES == sysUser.getStatus()) {
            return true;
        }
        // 判断公司状态
        CompanyExample companyExample=new CompanyExample();
        CompanyExample.Criteria criterion=companyExample.createCriteria();
        criterion.andCompanyCodeEqualTo(sysUser.getCompanyCode());
        criterion.andDelFlagEqualTo(0);
        List<Company> company=companyMapper.selectByExample(companyExample);
        if(company == null || company.size()==0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean updatePassWord(SysUser sysUser) {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(sysUser.getPassword())) {
            try {
                sysUser.setPassword(MD5Util.getEncryptedPwd(sysUser.getPassword()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                logger.error("加密密码失败:" + sysUser.getPassword(), e);
                throw new DataException("保存用户失败");
            }
        } else {
            sysUser.setPassword(null);
        }
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return true;
    }

    @Override
    public int getSysUserByCompanyCode(String companyCode) {
        SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andDelFlagEqualTo(0);
        return sysUserMapper.selectByExample(sysUserExample).size();
    }

    @Override
    public List<SysRole> getRolesByUserId(String userId) {
        return sysUserMapperExt.getRolesByUserId(userId);
    }

    @Override
    public List<SysMenu> getMenusByUserId(String userId) {
        return sysUserMapperExt.getMenusByUserId(userId);
    }

    @Override
    public boolean selectWeakPass(String password) {
        String userId=ShiroUtils.getUserId();

//        SysUser sysUser=sysUserMapper.selectByPrimaryKey(userId);
//        if(org.apache.commons.lang3.StringUtils.isNotBlank(password)) {
//            try {
//                String temp=MD5Util.getEncryptedPwd(sysUser.getPassword());
//                if(temp.equals(sysUser.getPassword())){
//                    return true;
//                }
//            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//                logger.error("加密密码失败:" + sysUser.getPassword(), e);
//                throw new DataException("密码校验失败");
//            }
//        }
        return true;
    }


}
