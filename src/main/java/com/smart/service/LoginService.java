package com.smart.service;

import com.smart.domain.User;
import com.smart.domain.LoginLog;
import com.smart.dao.UserDao;
import com.smart.dao.LoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoginService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    /**
     * 用户登陆时验证用户登陆信息
     * @param userName
     * @param password
     * @param identity
     * @return
     */
    public boolean hasMatchUser(String userName, String password, String identity)
    {
        int matchCount = userDao.getMatchCount(userName, password, identity);

        return matchCount > 0;
    }

    /**
     * 登陆成功后，记录登陆日志
     * @param userName
     * @param ip
     * @param dateStr
     */
    @Transactional
    public void loginSuccess(String userName, String ip, String dateStr)
    {
        LoginLog loginLog = new LoginLog();;
        loginLog.setUserName(userName);
        loginLog.setIp(ip);
        loginLog.setDateStr(dateStr);

        loginLogDao.insertLoginLog(loginLog);
    }
}
