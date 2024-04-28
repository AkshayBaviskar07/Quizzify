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

    /**
     * Retrieves all questions from the service.
     * @return ResponseEntity with a list of Question objects
     */
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> allQuestions(){
        return service.getAllQuestion();
    }

    /**
     * Add a new question to the service
     * @param Question question to be added
     * @return ResponseEntity with a string value and status code 201
     */
    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return service.addQuestion(question);
    }

    /**
     * Retrieves a questions by category
     * @param String category of the question
     * @return ResponseEntity with a list of Question objects and status code 200
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return service.getQuestionsByCategory(category);
    }

    /**
     * Updates a question in the service
     * @param Question question to be updated
     * @return ResponseEntity with a string value and status code 200
     */
    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return service.updateQuestion(question);
    }

    /**
     * Deletes a question from the service
     * @param Long id of the question to be deleted
     * @return ResponseEntity with a string value and status code 200
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        return service.deleteQuestion(id);
    }

    /**
     * This function generates a list of question IDs for a quiz.
     * @param categoryName The name of the category of questions to be included in the quiz.
     * @param numQuestions The number of questions to be included in the quiz.
     * @return A ResponseEntity containing a list of question IDs and an HTTP status code.
     */
    @GetMapping("/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                          @RequestParam Long numQuestions) {

        // Print the local server port from the environment properties
        System.out.println(environment.getProperty("local.server.port"));

        // Call the service method to get the list of question IDs for the quiz
        List<Long> questionIds = service.getQuestionsForQuiz(categoryName, numQuestions);

        // Return a ResponseEntity containing the list of question IDs and an HTTP status code
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }

    /**
     * This function takes a list of question IDs and returns a list of QuestionWrapper objects.
     * @param questionIds A list of question IDs.
     * @return A ResponseEntity containing a list of QuestionWrapper objects and an HTTP status code.
     */
    @PostMapping("/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Long> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>(service.getQuestionsFromIds(questionIds), HttpStatus.OK);
    }

    /**
     * This function takes a list of Response objects and returns the score of the quiz.
     * @param responses A list of Response objects.
     * @return A ResponseEntity containing the score of the quiz and an HTTP status code.
     */
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>( service.getScore(responses), HttpStatus.OK);
    }
}
