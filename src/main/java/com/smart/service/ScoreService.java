package com.smart.service;

import com.smart.dao.ScoreDao;
import com.smart.domain.Score;
import com.smart.web.AddParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    private ScoreDao scoreDao;

    @Autowired
    public void setScoreDao(ScoreDao scoreDao)
    {
        this.scoreDao = scoreDao;
    }

    @Transactional
    public Score getScoreByCourseName(String courseName, String studentName)
    {
        return scoreDao.getScoreByCourseName(courseName, studentName);
    }

    @Transactional
    public String insertScore(AddParam addParam)
    {
        String msg = scoreDao.insertScore(addParam);
        return msg;
    }

    @Transactional
    public List<Score> getScoresByTeacherName(String teacherName)
    {
        List<Score> scores = scoreDao.getScoresByTeacherName(teacherName);
        return scores;
    }


    @Transactional
    public String deleteScore(int scoreId)
    {
        return scoreDao.deleteScore(scoreId);
    }


    public Score getScoreById(int id)
    {
        return  scoreDao.getScoreById(id);
    }

    @Transactional
    public void modifyScore(Score modifiedScore)
    {
        scoreDao.modifyScore(modifiedScore);
    }
}
