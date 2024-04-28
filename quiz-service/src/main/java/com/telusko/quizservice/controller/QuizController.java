package com.telusko.quizservice.controller;


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


    /**
     * This method creates a quiz with the given category, sets the number of questions and assigns title
     * @param category The category of the quiz
     * @param numQ The number of questions
     * @param title The title of the quiz
     * @return ResponseEntity with the success message and status code
     */
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        System.out.println(environment.getProperty("local.server.port"));
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

   /**
    * This method returns quiz by its Id
    * @param id The id of the quiz
    * @return ResponseEntity with the quiz and status code'
    */
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        System.out.println(environment.getProperty("local.server.port"));
        return quizService.getQuizById(id);
    }

    /**
     * This method returns the score of the quiz
     * @param id The id of the quiz
     * @param responses The list of responses of the quiz
     * @return ResponseEntity with the score and status code
     */
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

    /**
     * This method updates the quiz or throws exception if quiz not found
     * @param quiz The updated quiz
     * @return ResponseEntity with the success message and status code
     */
    @PutMapping("update")
    public ResponseEntity<String> updateQuiz(@RequestBody Quiz quiz){
        return quizService.updateQuiz(quiz);
    }

    /**
     * This method deletes the quiz or throws exception if quiz not found
     * @param id The id of the quiz
     * @return ResponseEntity with the success message and status code
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer id){
        return quizService.deleteQuiz(id);
    }
}
