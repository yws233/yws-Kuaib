package cn.kuaib.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.kuaib.pojo.User;
import cn.kuaib.service.UserService;
import cn.kuaib.tools.Constants;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
private Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login.html")
	public String login(){
		logger.debug("LoginController welcome Kuaib==================");
		// 随机显示登录页文字


		return "login";
	}
	
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpServletRequest request,HttpSession session) throws Exception{
		logger.debug("doLogin====================================");
        //调用service方法，进行用户匹配
		User user = userService.login(userCode,userPassword);
		String loginPic = request.getParameter("picconfirm"); //获取验证值
		Boolean confirm = loginPic.equals("验证成功!");
		logger.info("######piccccccccccccccccccc:" + loginPic);
		logger.info("######piccccccccccccccccccc:" + confirm);
		if (null != user){
            if(user.getUserCode() != "" && user.getUserCode() != null
					&& user.getUserPassword() != null && user.getUserPassword() != ""
                    && loginPic.equals("验证成功!")){//登录成功
                //放入session
                session.setAttribute(Constants.USER_SESSION, user);
                //放入密码，文件上传使用
                session.setAttribute(Constants.USER_PASS,user);

                return "redirect:/sys/main.html"; //进入拦截器进行验证
            }
		}else{
			//页面跳转（login.jsp）带出提示信息--转发
			request.setAttribute("error", "*用户名或密码不正确");
			return "login";
		}
		return "login";
	}

	/*
    * 用户注册
    * */
	@RequestMapping(value = "adduser.html", method = RequestMethod.POST)
	public String addUser(User user, HttpSession session){
		try {
			if (userService.addReg(user) == true){
                session.setAttribute("regtrue", "注册成功！"); //放置注册成功提示
				return "redirect:/login.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "register";
	}

	/*
	* 异步判断注册用户是否重名
	* */
	@RequestMapping(value = "/userCodeExist.html")
	@ResponseBody //异步处理结果直接写入HTTP ResponseBody中
	public Object userCodeIsExist(@RequestParam String userCode){
	    logger.info("///////////*****************////////////进入异步判断");
		/*HashMap<String, String> resultMap = new HashMap<String, String>();*/
		String cjson = null;
        logger.debug("userCodeIsExit userCode===================== "+userCode);
		if (StringUtils.isNullOrEmpty(userCode)){
			/*resultMap.put("userCode", "exist");*/ //如果用户已经存在
			logger.info("//////************************进入null:");
            return "nullcode";
		}else {
			try {
				User user = userService.registerUser(userCode);
				if (null != user){
				    cjson = "exist"; //用户不为空则已存在
                }else {
				    // 注册用户长度不能长于10
                    int len = userCode.length();
                    if (len >= 10){
                        cjson = "toolen"; //用户输入的长度超过10则提示
                    }else {
                        cjson = "noexist"; //用户为空，则可以注册
                    }
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + JSONArray.toJSONString(resultMap));
		return JSONArray.toJSONString(resultMap);*/
		logger.info("//////************************cjson:" + cjson);
		return cjson;
	}

	/*
	* 异步判断用户邮箱是否存在
	* */
	@RequestMapping(value = "/userpwdexist.html")
    @ResponseBody
    public String userPasswordExist(@RequestParam String email){
	    logger.info("进入邮箱异步判断>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    String ejson = null;
        if (StringUtils.isNullOrEmpty(email)){
            return "nullpwd";
        }else {
            User user = userService.backPassword(email);
            if (null != user){
                ejson = "existpwd";
            }else {
                ejson = "noexistpwd";
            }
        }
        logger.info("//////************************cjson:" + ejson);
        return ejson;
    }

	/*
	*  跳转至找回密码页面
	* */
	@RequestMapping(value = "back.html")
    public String back(){
	    return "forget";
    }

	/*
	* 找回密码
	* */
	@RequestMapping(value = "backpassword.html",method = RequestMethod.POST)
	public String backPass(@RequestParam String email, HttpSession session){
	    User user = userService.backPassword(email);
	    if (user != null){
	        session.setAttribute(Constants.PASSWORD,user.getUserPassword());
	        return "backpwd";
        }
        session.setAttribute(Constants.SYS_MESSAGE,"*您的邮箱不存在,请先注册");
		return "forget";
	}
	/*
	* 跳转到登录页面
	* */
	@RequestMapping(value = "backlogin.html")
	public String backLogin(){
		return "login";
	}

	@RequestMapping(value = "register.html")
    public String skipRegister(){
	    return "register";
    }

	@RequestMapping(value="/sys/main.html")
	public String main(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		session.setAttribute(Constants.DATA_NOW,df.format(new Date()));
    	return "main";
	}

}
