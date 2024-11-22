package com.example.QuizApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDTO {
    String category;
    Integer quizNum;
    String title;
}
