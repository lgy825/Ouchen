package com.ouchen.core.util;


import com.ouchen.core.model.project.Project;
import com.ouchen.core.model.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ouchen.core.constant.SessionConstants.SESSION_KEY_ALL_MY_CINEMA;


/**
 * 获取当前用户信息 ClassName:ShiroUtils
 *
 * @author yangbin
 * @create 2017-11-24 17:16
 */
public class ShiroUtils {

        /*
         * 判断是否存在某权限
         */
        public static Boolean hasPermission(String permission) {
            Subject currentUser = SecurityUtils.getSubject();
            return currentUser.isPermitted(permission);
        }

        /**
         * 获取用户id
         */
        public static String getUserId() {
            Subject currentUser = SecurityUtils.getSubject();
            SysUser user = (SysUser) currentUser.getPrincipal();
            return user.getId();
        }

        /**
         * 获取cinemaid
         */
        public static String getProjectCode() {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            List projects = (List) session.getAttribute(SESSION_KEY_ALL_MY_CINEMA);
            if(projects != null && projects.size() > 0){
                return ((Project)projects.get(0)).getId();
            }
            return null;
        }

        /**
         * 获取授权的影院
         */
        public static List<Project> getAllMyCinemaList() {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            Object result = session.getAttribute(SESSION_KEY_ALL_MY_CINEMA);
            if(result == null) {
                return new ArrayList<>();
            } else {
                return (List<Project>)result;
            }
        }



        /**
         * 获取用户
         */
        public static SysUser getUser() {
            Subject currentUser = SecurityUtils.getSubject();
            return  (SysUser) currentUser.getPrincipal();
        }

    /**
     * 判断当前用户权限拥有的cinemaCode是否全部包含cinemaCodes
     * @param cinemaCodes
     * @return
     */
    public static boolean permissionMatch(Set<String> cinemaCodes) {
        List<String> userAllProjectCodes = getAllMyCinemaList().stream().map(project -> project.getId()).collect(Collectors.toList());
        if(userAllProjectCodes != null && cinemaCodes != null){
            // 数据权限数量大于用户权限数量
            if(userAllProjectCodes.size() < cinemaCodes.size()){
                return false;
            }else{
                if(userAllProjectCodes.size() == 0){
                    return false;
                }
                for(String code : cinemaCodes){
                    if(!userAllProjectCodes.contains(code)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }



    /**
     * 判断cinemaCodes是否包含判断当前用户权限拥有的cinemaCode中至少一个
     * @param cinemaCodes
     * @return
     */
    public static boolean permissionContains(Set<String> cinemaCodes) {
        List<Project> userAllProjectCodes = getAllMyCinemaList();
        if(userAllProjectCodes != null && cinemaCodes != null){
            if(userAllProjectCodes.size() == 0){
                return false;
            }
            // 数据权限为0
            if(cinemaCodes.size() == 0){
                return true;
            }
            for(Project project : userAllProjectCodes){
                if(cinemaCodes.contains(project.getId())){
                    return true;
                }
            }
        }
        return false;
    }
}