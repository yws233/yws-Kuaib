package cn.kuaib.service;
import javax.annotation.Resource;

import cn.kuaib.dao.UserMapper;
import cn.kuaib.pojo.Ex;
import cn.kuaib.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public User login(String userCode, String userPassword) throws Exception {
		// TODO Auto-generated method stub
        User user = null;
		user = userMapper.getLoginUser(userCode);
		//匹配密码
		if(null != user){
			if(!user.getUserPassword().equals(userPassword))
				user = null;
		}
		return user;
	}

	@Override
	public boolean addReg(User user) throws Exception{
	    logger.info("添加新用户>>>>>>>>>>>>>>>>>" + user.getUserCode());
	    boolean flag = false;
        User login = userMapper.getLoginUser(user.getUserCode());
        User pwd = userMapper.backPwd(user.getEmail());
        /*logger.info("userCode为：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + login.getUserCode());*/
        logger.info("注册邮箱为：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user.getEmail());
        String code = user.getUserCode();
        int len = code.length(); //获取用户注册名的长度，并进行限制
        logger.info("len*****************************" + len);
        int pwdlen = user.getUserPassword().length(); //对输入的密码长度进行限制
        //对输入的空值及已经存在的用户、邮箱、两次输入的密码是否一致进行判断
        if (login == null && pwd == null && user.getUserPassword().equals(user.getRuserPassword()) && len <= 10){
            if (user.getUserCode() == null || user.getUserCode() == ""
                    || user.getUserPassword() == null || user.getUserPassword() == "" ||
                    !user.getUserPassword().equals(user.getRuserPassword()) ||
                    user.getRuserPassword() == null || user.getRuserPassword() == "" ||
                    pwdlen < 6 || pwdlen > 20){
                flag = false;
            }else {
                int rows = userMapper.addRegister(user);
                if (rows > 0){
                    flag = true;
                    logger.info("》》》》》》》注册成功！");
                }else {
                    logger.info("》》》》》》》注册失败！");
                }
            }
        }
        return flag;
	}

    @Override
    public boolean addPics(User user) {
        boolean flag = false;
        int rows = userMapper.addPic(user);
        if (rows > 0){
            flag = true;
            logger.info("》》》》》》》上传成功！");
        }else {
            logger.info("》》》》》》》上传失败！");
        }
        return flag;
    }

    @Override
    public boolean delete(Integer uid) {
	    boolean flag = false;
        int rows = userMapper.deleteUser(uid);
        if (rows > 0){
            flag = true;
            logger.info("》》》》》》》删除成功！");
        }else {
            logger.info("》》》》》》》删除失败！");
        }
        return flag;
    }

    @Override
    public User backPassword(String email) {
        User user = userMapper.backPwd(email);
        if (user != null){
            if (!user.getEmail().equals(email)){
                return null;
            }
        }
        return user;
    }

    @Override
    public boolean updatePic(User user) {
        boolean flag = false;
        int rows = userMapper.updateFile(user);
        if (rows > 0){
            flag = true;
            logger.info("》》》》》》》更新成功！");
        }else {
            logger.info("》》》》》》》更新失败！");
        }
        return flag;
    }

    @Override
    public User registerUser(String userCode) {
        User user = null;
        try {
            user = userMapper.getRegister(userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteUserPics(Integer uid) {
	    boolean flag = false;
        int rows = userMapper.deletPics(uid);
        if (rows > 0){
            flag = true;
            logger.info("》》》》》》》清空文件成功！");
        }else {
            logger.info("》》》》》》》清空文件失败！");
        }
        return flag;
    }


    /*导师信息部分*/
    @Override
    public List<Ex> showExInfo() {
        List<Ex> exList = new ArrayList<Ex>();
        exList = userMapper.showEx();
        if (exList != null){
            logger.info("》》》》》》》》》》》导师信息查询成功！");
        }else {
            logger.info(">>>>>>>>>>>>>>>>>> 导师信息查询失败");
        }
        return exList;
    }

    @Override
    public boolean addExs(Ex ex) {
        int count = 0;
        boolean flag = false;
        logger.info("+++++++++++++++++查询出的名：" + userMapper.selectEx(ex.getExname()));
        if (userMapper.selectEx(ex.getExname()) == null && ex.getExname() != null && ex.getExteacher() != null && ex.getExphone() != null
                    && ex.getExname() != "" && ex.getExteacher() != "" && ex.getExphone() != ""){
            int row = userMapper.addEx(ex);
            if (row > 0){
                    logger.info(">>>>>>>>>>>>>>>>>>>>插入导师信息成功！");
                    count = count++;
                    flag = true;
                }
        } else {
            logger.info(">>>>>>>>>>>>>>>>>>>>插入导师信息失败！");
        }
        return flag;
    }

    @Override
    public int exNums() {
        int count = userMapper.exCount();
        logger.info("总信息个数是>>>>>>>>>>>>>>>>>>>>>:" + count);
        return count;
    }


    /*
    * 修改导师信息成功
    * */

    @Override
    public Integer updateExs(Ex ex) {
        logger.info("++++++++++++++++++++++++++进入service修改导师信息");
        Integer exUser = userMapper.updateEx(ex);
        if (exUser > 0){
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>更新成功！");
        }else {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>更新失败！");
        }
        return exUser;
    }

    @Override
    public Ex exExist(String exname) {
        Ex exUser = userMapper.selectEx(exname);
        logger.info("exUser》》》》》》++++++++》》》》》》》》"+exUser);
        if (exUser != null){
            logger.info(">>>>>>>>>>>>>>该用户已经存在");
            /*exUser = null;*/
        }
        return exUser;
    }

}
