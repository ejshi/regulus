package org.regulus.demo.web.model.usercenter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName: UserResponse  
 * @Description: 数据相应对象
 * @author ejshi  
 * @date 2018年1月31日 下午11:33:37  
 *
 */
@ApiModel
public class UserResponse implements java.io.Serializable{

	private static final long serialVersionUID = -3621671614565040689L;

	@ApiModelProperty("昵称")
    private String nickname;
    
	@ApiModelProperty("用户名")
    private String username;
    
	@ApiModelProperty("密码")
    private String password;

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
	
	
}
