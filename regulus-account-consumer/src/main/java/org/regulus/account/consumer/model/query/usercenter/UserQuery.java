package org.regulus.account.consumer.model.query.usercenter;

import org.regulus.common.model.PageModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: UserQuery  
 * @Description: 查询对象
 * @author ejshi  
 * @date 2018年1月31日 下午11:05:37  
 */
@ApiModel
public class UserQuery extends PageModel {
	private static final long serialVersionUID = -2840366087876556526L;

	@ApiModelProperty("用户名")
	private String username;
	
	/**
     * 用户状态 01：正常 02：冻结  03：注销
     */
	@ApiModelProperty("用户状态")
    private String userStatus;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
}
