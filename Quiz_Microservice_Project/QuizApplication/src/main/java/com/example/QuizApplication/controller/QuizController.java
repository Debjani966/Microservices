package com.example.QuizApplication.controller;


import com.example.QuizApplication.model.QuestionWrapper;
import com.example.QuizApplication.model.QuizDTO;
import com.example.QuizApplication.model.Response;
import com.example.QuizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO)
    {
        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getQuizNum(),quizDTO.getTitle());
    }

    @GetMapping("getQuizQuestions/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submitAnswer/{id}")
    public ResponseEntity<Integer> calculate(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
        return quizService.calculateScore(id,responses);
    }
}
