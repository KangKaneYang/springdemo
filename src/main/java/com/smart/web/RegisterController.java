package com.smart.web;

import com.smart.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class RegisterController
{
    private RegisterService registerService;

    /**
     * 负责处理/index.html的请求
     * @return
     */
    @RequestMapping(value="/register.html")
    public String studentRegister()
    {
        return "register";
    }

    /**
     * 负责处理/loginCheck.html的请求
     */
    @RequestMapping(value = "/registerCheck.html")
    public ModelAndView registerCheck(HttpServletRequest request, RegisterParam registerParam) throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");
        System.out.println("userName = " + request.getParameter("userName") + ", password = " + request.getParameter("password"));

        if (registerParam.getUserName() == null || registerParam.getUserName().trim().equals("") || registerParam.getPassword() == null || registerParam.getPassword().trim().equals(""))
        {
            return new ModelAndView("register", "msg", "用户名和密码都不能为空！");
        }

        System.out.println("userName = " + registerParam.getUserName() + ", password = " + registerParam.getPassword());

        boolean isSuccess = registerService.addUser(registerParam.getUserName(), registerParam.getPassword(), registerParam.getIdentity());

        if (!isSuccess)
        {
            return new ModelAndView("register", "msg", "userName has been used, please choose another one!");
        }
        else
        {

            return new ModelAndView("register", "msg", registerParam.getIdentity() + " " + registerParam.getUserName() + " registered successfully. please login in.");
        }
    }

    @Autowired
    public void setLoginService(RegisterService registerService)
    {
        this.registerService = registerService;
    }
}
