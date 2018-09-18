package com.smart.web;

import com.smart.domain.User;
import com.smart.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController
{
    private LoginService loginService;

    /**
     * 负责处理/index.html的请求
     * @return
     */
    @RequestMapping(value="/studentLogin.html")
    public String studentLoginPage()
    {
        return "studentLogin";
    }

    /**
     * 负责处理/index.html的请求
     * @return
     */
    @RequestMapping(value="/teacherLogin.html")
    public String teacherLoginPage()
    {
        return "teacherLogin";
    }

    /**
     * 负责处理/sloginCheck.html的请求
     */
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginParam loginParam)
    {
        boolean isValidUser = loginService.hasMatchUser(loginParam.getUserName(),
                loginParam.getPassword(), loginParam.getIdentity());

        if (!isValidUser)
        {
            return new ModelAndView(loginParam.getIdentity() + "Login", "error", "userName or password error!");
        }
        else
        {
            String dateStr = (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
            loginService.loginSuccess(loginParam.getUserName(), request.getLocalAddr(), dateStr);
            request.getSession().setAttribute("userName", loginParam.getUserName());
            request.getSession().setAttribute("identity", loginParam.getIdentity());

            return new ModelAndView(loginParam.getIdentity() + "Main");
        }
    }

    @Autowired
    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }
}
