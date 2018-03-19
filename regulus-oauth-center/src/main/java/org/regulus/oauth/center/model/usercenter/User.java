/**
 * Project Name:regulus-account-api
 * File Name:User.java
 * Package Name:org.regulus.account.api.model
 * Date:2018年1月26日上午11:15:45
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.oauth.center.model.usercenter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClassName:User <br/>
 * Function: 用户 <br/>
 * Date:     2018年1月26日 上午11:15:45 <br/>
 * @author   shijun@richinfo.cn
 */
@Table(name="t_user")
public class User implements java.io.Serializable{
    
    private static final long serialVersionUID = -180073911553486569L;

    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "nickname")
    private String nickname;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    /**
     * 用户状态 01：正常 02：冻结  03：注销
     */
    @Column(name = "user_status")
    private String userStatus;
    
    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

