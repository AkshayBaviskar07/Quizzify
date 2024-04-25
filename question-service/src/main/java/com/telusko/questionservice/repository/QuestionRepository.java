package com.telusko.questionservice.repository;

import com.telusko.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(String category);

    //generate a distinct random set of questions for a given category and number of questions
    @Query(value = "SELECT q.id  FROM question_tbl q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ",
           nativeQuery = true)
    List<Long> findRandomQuestionsByCategory(String category, Long numQ);
}
