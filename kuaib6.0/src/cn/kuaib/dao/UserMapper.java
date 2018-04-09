package cn.kuaib.dao;
import cn.kuaib.pojo.Ex;
import org.apache.ibatis.annotations.Param;
import cn.kuaib.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserMapper {
	/**
	 * 通过userCode获取User
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getLoginUser(@Param("userCode") String userCode)throws Exception;

	/*
	* 注册用户
	* */
	public int addRegister(User user) throws Exception;

	/*
	* 增加用户
	* */
	public int addPic(User user);

	/*
	* 删除用户
	* */
	public int deleteUser(@Param("uid") Integer uid);

	/*
	* 忘记密码查询
	* */
	public User backPwd(@Param("email") String email);

	/*
	* 上传文件
	* */
	public int updateFile(User user);

	/*
	* 获取userCode在注册页面进行异步验证
	* */
	public User getRegister(@Param("userCode") String userCode) throws Exception;

	/*
	* 删除文件
	* */
    public int deletPics(@Param("uid") Integer uid);



    /*
    * 导师信息信息表
    * */
    public List<Ex> showEx();

    /*
    * 增加导师信息
    * */
    public int addEx(Ex ex);

    /*
    * 查询总个数
    * */

    public Integer exCount();


    /*
    * 修改用户信息
    * */
    public Integer updateEx(Ex ex);

    /*
    * 查询昵称是否存在
    * */
    public Ex selectEx(@Param("exname") String exname);


}
