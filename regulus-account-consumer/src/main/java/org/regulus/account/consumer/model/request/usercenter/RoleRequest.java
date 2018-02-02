/*
 * Copyright(c) 2018 gsshijun@126.com All rights reserved.
 * distributed with this file and available online at
 * shijun
 */

package org.regulus.account.consumer.model.request.usercenter;

import org.hibernate.validator.constraints.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: RoleRequest 
 * @Description: Role,数据请求对象 
 * @author ejshi  
 * @date 2018年1月31日 下午11:05:37  
 */
@ApiModel
public class RoleRequest implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;

	
    @NotBlank(groups={Add.class,Edit.class})
	@Length(max=36, groups = {Add.class,Edit.class})
	@ApiModelProperty("id")
	private String id;
	
	
    @NotBlank(groups={Add.class,Edit.class})
	@Length(max=32, groups = {Add.class,Edit.class})
	@ApiModelProperty("角色名称")
	private String name;
	
	
    @NotBlank(groups={Add.class,Edit.class})
	@Length(max=10, groups = {Add.class,Edit.class})
	@ApiModelProperty("角色编码")
	private String code;
	
	
	public interface Add {}
    
    public interface Edit {}
		
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
