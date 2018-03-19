/**
 * Project Name:regulus-oauth-center
 * File Name:UserRole.java
 * Package Name:org.regulus.oauth.center.model.usercenter
 * Date:2018年3月19日下午5:15:38
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.model.usercenter;

import java.util.Collection;

import org.regulus.oauth.center.enums.UserStatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ClassName:SecurityUser <br/>
 * Function: 用户角色详情<br/>
 * Date:     2018年3月19日 下午5:15:38 <br/>
 * @author   shijun@richinfo.cn
 * @version  
 * @since    V1.0
 * @see 	 
 */
public class SecurityUser extends User implements UserDetails{
    
    private static final long serialVersionUID = 3794182104893757541L;
    
    private Collection<? extends GrantedAuthority> authorities;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return !UserStatusEnum.LOCK.getCode().equals(getUserStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserStatusEnum.NORMAL.getCode().equals(getUserStatus());
    }
}

