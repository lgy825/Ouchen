package com.yunmu.core.util;

import com.google.common.collect.Maps;

import com.yunmu.back.service.sys.SysUserService;
import com.yunmu.core.base.Result;
import com.yunmu.core.constant.ResultConstants;
import com.yunmu.core.model.sys.SysMenu;
import com.yunmu.core.model.sys.SysRole;
import com.yunmu.core.model.sys.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yangbin on 2017/11/21
 */
@Component
public class MyRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(MyRealm.class);

	private static final String OR_OPERATOR = " or ";
	private static final String AND_OPERATOR = " and ";
	private static final String NOT_OPERATOR = " not ";

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
		String userName = usernamePasswordToken.getUsername();
		String password = String.valueOf(usernamePasswordToken.getPassword());

		// 查询登陆用户
		Map<String, Object> userParam = Maps.newHashMap();
		userParam.put("loginName", userName);
		List<SysUser> sysUserList = sysUserService.getUsersByCondition(userParam);
		if(sysUserList.size()==0) {
			logger.error("MyRealm查询用户失败");
			throw new RuntimeException(ResultConstants.RESULT_CDESC_FAILED);
		}

		if(CollectionUtils.isEmpty(sysUserList) ||
				sysUserList.get(0) == null) {
			throw new UnknownAccountException("用户不存在");// 没找到帐号
		} else {
			SysUser user = sysUserList.get(0);
			String pwd = user.getPassword();

			// 判断用户或所属公司或所属影院是否被禁用
			Boolean usersBeDisabledResult = sysUserService.getUsersBeDisabled(user.getId());
			if(usersBeDisabledResult==null) {
				logger.error("MyRealm查询用户失败");
				throw new RuntimeException(ResultConstants.RESULT_CDESC_FAILED);
			}
			if(usersBeDisabledResult) {
				throw new DisabledAccountException("该用户已被停用");
			}

			boolean validError;

			if(StringUtils.isBlank(pwd)) {
				throw new IncorrectCredentialsException("密码错误");
			}

			try {
				validError = !MD5Util.validPassword(password, pwd);
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				throw new IncorrectCredentialsException("密码错误");
			}

			if(validError) {
				throw new IncorrectCredentialsException("密码错误");
			}

			return new SimpleAuthenticationInfo(
					user, // 用户信息
					password, // 密码
					getName() // realm name
			);
		}
	}


	/**
	 * 支持or and not 关键词  不支持and or混用
	 * @param principals
	 * @param permission
	 * @return
	 */
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if(permission.contains(OR_OPERATOR)) {
			String[] permissions = permission.split(OR_OPERATOR);
			for(String orPermission : permissions) {
				if(isPermittedWithNotOperator(principals, orPermission)) {
					return true;
				}
			}
			return false;
		} else if(permission.contains(AND_OPERATOR)) {
			String[] permissions = permission.split(AND_OPERATOR);
			for(String orPermission : permissions) {
				if(!isPermittedWithNotOperator(principals, orPermission)) {
					return false;
				}
			}
			return true;
		} else {
			return isPermittedWithNotOperator(principals, permission);
		}
	}

	private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
		if(permission.startsWith(NOT_OPERATOR)) {
			return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
		} else {
			return super.isPermitted(principals, permission);
		}
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String sysUserId = ((SysUser)principals.getPrimaryPrincipal()).getId();

		List<SysRole> rolesResult = sysUserService.getRolesByUserId(sysUserId);
		if(ResultConstants.RESULT_CODE_SUCCESS.equals(rolesResult.size()+"")) {
			throw new RuntimeException(ResultConstants.RESULT_CDESC_FAILED);
		}
		authorizationInfo.setRoles(rolesResult.stream().map(SysRole::getRoleName).collect(Collectors.toSet()));

		List<SysMenu> menuResult = sysUserService.getMenusByUserId(sysUserId);
		if(ResultConstants.RESULT_CODE_SUCCESS.equals(menuResult.size()+"")) {
			throw new RuntimeException(ResultConstants.RESULT_CDESC_FAILED);
		}
		authorizationInfo.setStringPermissions(menuResult.stream().map(SysMenu::getShiroFlag).filter(StringUtils::isNotBlank).collect(Collectors.toSet()));

		return authorizationInfo;
	}

}
