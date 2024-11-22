package com.example.QuestionApplication.service;


import com.example.QuestionApplication.model.Question;
import com.example.QuestionApplication.model.QuestionWrapper;
import com.example.QuestionApplication.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<List<Question>> getAllQuestions();

    ResponseEntity<List<Question>> getQuestionsByCategory(String category);
    ResponseEntity<Question> addQuestion(Question question);
    ResponseEntity<List<Integer>> getRandomQuestionByCategory(String category,Integer num);

    ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> ids);

    ResponseEntity<Integer> getScore(List<Response> response);
}
