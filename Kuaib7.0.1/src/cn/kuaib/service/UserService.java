package cn.kuaib.service;
import cn.kuaib.pojo.Ex;
import cn.kuaib.pojo.User;

import java.util.List;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public User login(String userCode, String userPassword) throws Exception;

	/*
	* 注册用户
	* */
	public boolean addReg(User user) throws Exception;

	/*
	* 增加用户及文件上传
	* */
	public boolean addPics(User user);

	/**
	 *删除用户
	 */
	public boolean delete(Integer uid);

	/*
	* 找回密码
	* */
	public User backPassword(String email);

	/*
	* 更新上传文件用户信息
	* */
	public boolean updatePic(User user);

	/*
	* 注册异步验证
	* */
	public User registerUser(String userCode) throws Exception;

	/*
	* 删除文件
	* */
	public boolean deleteUserPics(Integer uid);

	/*
	* 导师信息部分
	* */
	public List<Ex> showExInfo();

	/*
	* 增加导师信息
	* */
	public boolean addExs(Ex ex);

	/*
	* 查询信息个数
	* */
	public int exNums();

	/*
	* 修改导师信息
	* */
	public Integer updateExs(Ex ex);

	/*
	* 昵称是否存在
	* */
	public Ex exExist(String exname);

	/*
	* 更新文本信息
	* */
	public boolean upText(Integer uid,String wangtext);

	/*
	* 查找登录用户
	* */
	/*public User loginUser(Integer uid);*/
}
