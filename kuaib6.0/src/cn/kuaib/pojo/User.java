package cn.kuaib.pojo;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class User implements Serializable {
	private Integer uid; //id
	
	@NotEmpty(message="用户编码不能为空")
	private String userCode; //用户编码
	
	@NotEmpty(message="用户名称不能为空")
	private String userName; //用户名称
	
	@NotNull(message="密码不能为空")
	@Length(min=6,max=10,message="用户密码长度为6-10")
	private String userPassword; //用户密码

    private String ruserPassword; //重复验证输入的密码

	private String email;

	private String upics;
	private String ufile;

	private String picconfirm; //图片验证码

    public String getRuserPassword() {
        return ruserPassword;
    }

    public void setRuserPassword(String ruserPassword) {
        this.ruserPassword = ruserPassword;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpics() {
        return upics;
    }

    public void setUpics(String upics) {
        this.upics = upics;
    }

    public String getUfile() {
        return ufile;
    }

    public void setUfile(String ufile) {
        this.ufile = ufile;
    }

    public String getPicconfirm() {
        return picconfirm;
    }

    public void setPicconfirm(String picconfirm) {
        this.picconfirm = picconfirm;
    }
}
