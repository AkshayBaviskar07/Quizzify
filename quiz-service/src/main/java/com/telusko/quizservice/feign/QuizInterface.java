package com.telusko.quizservice.feign;

import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    /**
     * Retrieves questions for a given category
     * @param categoryName  - name of the category
     * @param numQuestions  - number of questions to be retrieved
     * @return List<Long> - list of question ids
     */
    @GetMapping("/question/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                          @RequestParam Long numQuestions);

    /**
     * Retrieves questions for a given list of question ids
     * @param questionIds - list of question ids
     * @return List<QuestionWrapper> - list of questions
     */
    @PostMapping("/question/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Long> questionIds);

    /**
     * Retrieves score for a given list of responses
     * @param responses - list of responses
     * @return Integer - score
     */
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
