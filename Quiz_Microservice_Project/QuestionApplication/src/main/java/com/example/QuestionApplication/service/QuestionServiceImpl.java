package com.example.QuestionApplication.service;

import com.example.QuestionApplication.dao.QuestionDao;
import com.example.QuestionApplication.model.Question;
import com.example.QuestionApplication.model.QuestionWrapper;
import com.example.QuestionApplication.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionDao questionDao;
    
    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions=questionDao.findAll();
        if(questions.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> questions=questionDao.findByCategory(category);
        if(questions.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Question> addQuestion(Question question) {
        Question question1=questionDao.save(question);
        return new ResponseEntity<>(question1,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Integer>> getRandomQuestionByCategory(String category, Integer num) {
        List<Integer> questions=questionDao.findRandomQuestionsByCategory(category,num);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> ids) {
        List<Question> question=new ArrayList<>();
        for (Integer i:ids)
        {
            question.add(questionDao.findById(i).get());
        }
        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        for (Question question1:question) {
            QuestionWrapper questionWrapper=new QuestionWrapper(question1.getId(),question1.getQuestionTitle(),
                    question1.getOption1(),question1.getOption2(),question1.getOption3(),question1.getOption4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> response) {
        Integer score=0;
        for (Response r:response) {
            if(r.getResponse().equals(questionDao.findById(r.getId()).get().getRightAnswer()))
            {
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
