package com.telusko.quizservice.feign;

import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                          @RequestParam Long numQuestions);

    @PostMapping("/question/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Long> questionIds);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
