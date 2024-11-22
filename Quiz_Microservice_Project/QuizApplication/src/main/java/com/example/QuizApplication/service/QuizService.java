package com.example.QuizApplication.service;


import com.example.QuizApplication.model.QuestionWrapper;
import com.example.QuizApplication.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    ResponseEntity<String> createQuiz(String category, Integer quizNum, String title);

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

    ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses);
}
