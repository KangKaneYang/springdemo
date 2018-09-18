package com.smart.web;

import com.smart.domain.Score;
import com.smart.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScoreController {
    private ScoreService scoreService;

    @Autowired
    public void setScoreService(ScoreService scoreService)
    {
        this.scoreService = scoreService;
    }

    @RequestMapping(value = "/queryScore.html")
    //@ResponseBody
    public ModelAndView queryScore(HttpServletRequest request, QueryParam queryParam)
    {
        try {
            request.setCharacterEncoding("UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Score score = scoreService.getScoreByCourseName(queryParam.getCourseName(), queryParam.getUserName());
        //int score = 90;
        score.setCourseName(queryParam.getCourseName());

        ModelAndView mv = new ModelAndView();
        mv.addObject("score", score);
        mv.setViewName("returnScore");
        return mv;
    }

    @RequestMapping(value="/addScore.html")
    public  ModelAndView addScore(HttpServletRequest request, AddParam addParam)
    {
        try {
            request.setCharacterEncoding("UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String insertMsg = scoreService.insertScore(addParam);
        List<Score> scores = scoreService.getScoresByTeacherName(addParam.getTeacherName());

        ModelAndView mv =  new ModelAndView("teacherScore");
        mv.addObject("scores", scores);
        mv.addObject("insertMessage", insertMsg);

        return mv;
    }

    @RequestMapping(value="/getInitScore.html")
    public ModelAndView getInitScore(HttpServletRequest request, AddParam addParam)
    {
        try {
            request.setCharacterEncoding("UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        List<Score> scores = scoreService.getScoresByTeacherName(addParam.getTeacherName());
        ModelAndView mv =  new ModelAndView("teacherScore");
        mv.addObject("scores", scores);

        return mv;
    }

    @RequestMapping(value="/deleteScore.html")
    public ModelAndView deleteScore(HttpServletRequest request, String scoreId)
    {
        try {
            request.setCharacterEncoding("UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String deleteMessage = scoreService.deleteScore(Integer.parseInt(scoreId.trim()));
        ModelAndView mv =  new ModelAndView("deleteInfo");
        mv.addObject("deleteMessage", deleteMessage);

        return mv;
    }

    @RequestMapping(value="/confirmModify.html")
    public void confirmModify(HttpServletRequest request, Score modifiedScore)
    {

        scoreService.modifyScore(modifiedScore);
    }

    @RequestMapping(value="/cancelModify.html")
    public ModelAndView cancelModify(HttpServletRequest request, String id)
    {
        Score scoreToBeModified= scoreService.getScoreById(Integer.parseInt(id));
        ModelAndView mv =  new ModelAndView("cancelDelete");
        mv.addObject("scoreToBeModified", scoreToBeModified);
        return mv;
    }
}
