/*
 * Copyright(c) 2018 gsshijun@126.com All rights reserved.
 * distributed with this file and available online at
 * shijun
 */

package org.regulus.account.consumer.model.query.usercenter;

import org.regulus.common.model.PageModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: RoleQuery 
 * @Description: Role,查询对象
 * @author ejshi  
 * @date 2018年1月31日 下午11:05:37  
 */
@ApiModel
public class RoleQuery extends PageModel {
    
    private static final long serialVersionUID = 1L;

	
	@ApiModelProperty("id")
	private String id;
	
	
	@ApiModelProperty("角色名称")
	private String name;
	
	
	@ApiModelProperty("角色编码")
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
