package com.smart.dao;

import com.smart.domain.Score;
import com.smart.web.AddParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ScoreDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Score getScoreByCourseName(String courseName, String studentName)
    {
        String sql = "select * from score where coursename = ? and studentname = ?";
        Object[] args = new Object[]{courseName, studentName};

        final Score score = new Score();
        jdbcTemplate.query(sql, args, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                score.setId(resultSet.getInt("id"));
                score.setCourseName(resultSet.getString("courseName"));
                score.setStudentName(resultSet.getString("studentName"));
                score.setTeacherName(resultSet.getString("teacherName"));
                score.setScore(resultSet.getInt("score"));
            }
        });

        return score;
    }

    public String insertScore(AddParam addParam)
    {
        if (addParam.getStudentName().trim().equals("") || addParam.getCourseName().trim().equals("") || addParam.getScore().trim().equals(""))
        {
            return "学生名、课程名、成绩任何一个都不能缺少";
        }

        String sql = "insert into score(teachername, studentname, coursename, score) values(?,?,?,?)";
        Object[] args = {addParam.getTeacherName(), addParam.getStudentName(), addParam.getCourseName(), Integer.parseInt((addParam.getScore().trim().equals("")) ? "0" : addParam.getScore().trim())};
        jdbcTemplate.update(sql, args);

        return "插入成功";
    }

    public List<Score> getScoresByTeacherName(String teacherName) {
        String sql = "select * from score where teachername = ?";
        Object[] args = new Object[]{teacherName};

        //List<Map<String, Object>> scores = jdbcTemplate.queryForList(sql, args);

        //List<Score> scores = jdbcTemplate.queryForList(sql, args, Score.class);

        //System.out.println(scores);
        //return  scores;
        //return null;
        final List<Score> scores = new ArrayList<Score>();
        jdbcTemplate.query(sql, args, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                Score score = new Score();
                score.setId(resultSet.getInt("id"));
                score.setCourseName(resultSet.getString("courseName"));
                score.setStudentName(resultSet.getString("studentName"));
                score.setTeacherName(resultSet.getString("teacherName"));
                score.setScore(resultSet.getInt("score"));
                scores.add(score);
            }
        });

        return scores;
    }

    public String deleteScore(int scoreId)
    {
        String deleteSql = "delete from score where id = ?";
        Object[] args = new Object[]{scoreId};

        System.out.println("deleteSQL : " + deleteSql);
        System.out.println("deleteSQLParam : scoreId = " + scoreId);
        int deleteRet = jdbcTemplate.update(deleteSql, args);
        System.out.println("deleteRet = " + deleteRet);

        String retMsg = "";
        if (deleteRet == 1)
        {
            retMsg = "删除成功";
        }
        else
        {
            retMsg = "删除失败";
        }

        return  retMsg;
    }

    public Score getScoreById(int id)
    {
        String sql = "select * from score where id = ?";
        Object[] args = new Object[]{id};

        final Score score = new Score();
        jdbcTemplate.query(sql, args, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                score.setId(resultSet.getInt("id"));
                score.setTeacherName(resultSet.getString("teacherName"));
                score.setStudentName(resultSet.getString("studentName"));
                score.setCourseName(resultSet.getString("courseName"));
                score.setScore(resultSet.getInt("score"));
            }
        });

        return score;
    }

    public void modifyScore(Score modifiedScore)
    {
        System.out.println("modifyScore: " + modifiedScore);
        String sql = "update score set studentName = ?, courseName = ?, score = ? where id = ?";
        Object[] args = new Object[]{modifiedScore.getStudentName(), modifiedScore.getCourseName(), modifiedScore.getScore(), modifiedScore.getId()};
        jdbcTemplate.update(sql, args);
    }
}
