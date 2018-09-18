package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;

    /**
     * 更新用户登陆的日志表
     * 当用户登陆成功以后，用来更新用户登陆日志表，记录用户的本次登陆。
     * @param loginLog
     */
    public void insertLoginLog(LoginLog loginLog)
    {
        String insert_login_log_sql = "insert into loginLog(userName, ip, logtime) values(?,?,?)";

        Object[] args = {loginLog.getUserName(), loginLog.getIp(), loginLog.getDateStr()};
        jdbcTemplate.update(insert_login_log_sql, args);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
}
