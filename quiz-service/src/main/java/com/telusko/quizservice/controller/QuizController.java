package com.telusko.quizservice.controller;


import com.netflix.discovery.converters.Auto;
import com.telusko.quizservice.dto.QuizDto;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.Response;
import com.telusko.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private Environment environment;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        System.out.println(environment.getProperty("local.server.port"));
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    // find quiz by id
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        System.out.println(environment.getProperty("local.server.port"));
        return quizService.getQuizById(id);
    }

    // submit the quiz
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

    //update quiz
    @PutMapping("update")
    public ResponseEntity<String> updateQuiz(@RequestBody Quiz quiz){
        return quizService.updateQuiz(quiz);
    }

    // delete quiz
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer id){
        return quizService.deleteQuiz(id);
    }
}
