/*
 * Copyright(c) 2018 gsshijun@126.com All rights reserved.
 * distributed with this file and available online at
 * shijun
 */

package org.regulus.oauth.center.model.usercenter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClassName: Role<br/>
 * Function: Role <br/>
 * Date:     2018年1月26日 上午11:15:45 <br/>
 * @author   ejshi
 */
@Table(name="t_role")
public class Role implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
	 * id
	 */
    @Id
	@Column(name = "id")
	private String id;
	
	/**
	 * 角色名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 角色编码
	 */
	@Column(name = "code")
	private String code;
	
		
	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	
}
