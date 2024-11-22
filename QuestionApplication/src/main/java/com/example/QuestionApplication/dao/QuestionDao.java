package com.example.QuestionApplication.dao;

import com.example.QuestionApplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT id FROM Question where category=:category order by Random() Limit :quizNum",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, Integer quizNum);

    Optional<Question> findById(Integer id);
}
