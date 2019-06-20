package com.sc.crud.utils;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class shiroRealm extends AuthorizingRealm{
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1 把 authentoken 转化成 usernamepasword token
			UsernamePasswordToken t = (UsernamePasswordToken)token;
		//2 token张获取username
			String username = t.getUsername();
		//3 用数据库查询username 
			//假设我们已经查出来了
		//4 如果用户不在 抛出 unknowExce
			if("unknow".equals(username)) {
				throw new UnknownAccountException("没有权限登陆");
			}
			
			if("error".equals(username)) {
				throw new LockedAccountException("该用户被锁定");
			}
		//返回认证信息
				Object principal = username;
				Object credentials = "123456";
				String realmName = getName();
				SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName);
		
		return simpleAuthenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//获取信息
		Object p = principal.getPrimaryPrincipal();
		//将一个hashset装进权限并且放进返回信息里面去
		Set<String> roles= new HashSet<>();
		roles.add("user");
		if("admin".equals(p)) {
			roles.add("admin");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		return info;
	}

	
}
