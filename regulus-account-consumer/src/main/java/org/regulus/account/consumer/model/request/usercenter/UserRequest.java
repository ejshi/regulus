package org.regulus.account.consumer.model.request.usercenter;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName: UserRequest  
 * @Description: 数据请求对象 
 * @author ejshi  
 * @date 2018年1月31日 下午11:32:54  
 *
 */
@ApiModel
public class UserRequest implements java.io.Serializable{
	
	private static final long serialVersionUID = 7359244451979212850L;

	@Length(max=50, groups = {Add.class,Edit.class})
	@ApiModelProperty("昵称")
    private String nickname;
    
	@Null(groups = Edit.class)
	@Length(max=50, groups = Add.class)
	@ApiModelProperty("用户名")
    private String username;
    
	@Null(groups = Edit.class)
	@Length(max=50, groups = Add.class)
	@ApiModelProperty("密码")
    private String password;
	
	public interface Add {}
	
	public interface Edit {}

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
