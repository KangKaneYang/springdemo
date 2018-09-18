package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    //自动注入JdbcTemplate的Bean
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 登陆时验证用户信息
     * 根据用户输入的用户名和密码来认证用户是否合法，这是一种非常简单的认证方法
     * @param userName
     * @param password
     * @return
     */
    public int getMatchCount(String userName, String password, String tableName)
    {
        String sqlStr = "select count(*) from " + tableName + " where username=? and password=?";

        return jdbcTemplate.queryForObject(sqlStr, new Object[]{userName, password}, Integer.class);
    }

    /**
     * 用户注册信息持久化
     * @param userName
     * @param password
     * @param identity
     * @return
     */
    public boolean addUser(String userName, String password, String identity)
    {
        if (userExists(userName, identity))
        {
            return false;
        }

        String tableName = identity;
        String insertUserSql = "insert into " + tableName + "(username, password) values ('" + userName + "','" + password + "')";
        jdbcTemplate.update(insertUserSql);

        return true;
    }

    private boolean userExists(String userName, String identity)
    {
        String tableName = identity;
        String querySql = "select count(*) from " + tableName + " where username = ?";

        return jdbcTemplate.queryForObject(querySql, new Object[]{userName}, Integer.class) > 0 ;
    }
}
