package com.smartcold.manage.cold.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

	private int id;
	private int role;//1 普通用户 2 集团用户 3 超级管理员
	private int status;//0 禁用  1 待审核 2 已处理
	private String username;
	private String password; //管理员密码
	private String telephone;
	private String email;

	//登录安全检查信息
	private String  token;//
	private boolean loginRisk;//当前登录环境是否正常
	private String  lastlogininfo;//上的登录环境信息
	private String  cuttlogininfo;//本次登录环境信息
	private String avatar="http://139.196.189.93:8089/app/userimg.jpg";//用户头像->add
	
	private static final long serialVersionUID = -2875979349754314456L;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public boolean isLoginRisk() {
		return loginRisk;
	}

	public void setLoginRisk(boolean loginRisk) {
		this.loginRisk = loginRisk;
	}

	public String getLastlogininfo() {
		return lastlogininfo;
	}

	public void setLastlogininfo(String lastlogininfo) {
		this.lastlogininfo = lastlogininfo;
	}

	public String getCuttlogininfo() {
		return cuttlogininfo;
	}

	public void setCuttlogininfo(String cuttlogininfo) {
		this.cuttlogininfo = cuttlogininfo;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
