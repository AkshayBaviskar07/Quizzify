package com.telusko.questionservice.controller;

import com.netflix.discovery.converters.Auto;
import com.telusko.questionservice.model.Question;
import com.telusko.questionservice.model.QuestionWrapper;
import com.telusko.questionservice.model.Response;
import com.telusko.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService service;
    @Autowired
    private Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> allQuestions(){
        return service.getAllQuestion();
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return new ResponseEntity<String>(String.valueOf(service.addQuestion(question)), HttpStatus.CREATED);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return service.getQuestionsByCategory(category);
    }
    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return service.updateQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        return service.deleteQuestion(id);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                          @RequestParam Long numQuestions) {
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>(service.getQuestionsForQuiz(categoryName, numQuestions), HttpStatus.OK);
    }

    @PostMapping("/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Long> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>(service.getQuestionsFromIds(questionIds), HttpStatus.OK);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>( service.getScore(responses), HttpStatus.OK);
    }
}
