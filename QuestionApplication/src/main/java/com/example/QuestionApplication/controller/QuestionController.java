package com.example.QuestionApplication.controller;

import com.example.QuestionApplication.model.Question;
import com.example.QuestionApplication.model.QuestionWrapper;
import com.example.QuestionApplication.model.Response;
import com.example.QuestionApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("questionsByCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer noQuestions){
        System.out.println(questionService.getRandomQuestionByCategory(category,noQuestions));
        return questionService.getRandomQuestionByCategory(category,noQuestions);
    }
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> ids)
    {
        System.out.println(environment.getProperty("localhost.server.port"));
        return questionService.getQuestionsFromIds(ids);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response)
    {

        return questionService.getScore(response);
    }

}
