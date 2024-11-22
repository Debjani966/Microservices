package com.example.QuizApplication.service;


import com.example.QuizApplication.dao.QuizDao;
import com.example.QuizApplication.feign.QuizInterface;
import com.example.QuizApplication.model.QuestionWrapper;
import com.example.QuizApplication.model.Quiz;
import com.example.QuizApplication.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    @Override
    public ResponseEntity<String> createQuiz(String category, Integer quizNum, String title) {
        List<Integer> question=quizInterface.getQuestionForQuiz(category,quizNum).getBody();

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIdsList(question);
        quizDao.save(quiz);
        return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz=quizDao.findById(id).get();
        List<Integer> questionIds=quiz.getQuestionIdsList();
        List<QuestionWrapper> questionsForUser= quizInterface.getQuestions(questionIds).getBody();
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        Integer right=quizInterface.getScore(responses).getBody();

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
