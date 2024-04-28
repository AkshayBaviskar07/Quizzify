package com.telusko.quizservice.service;

import com.telusko.quizservice.exception.QuizNotFoundException;
import com.telusko.quizservice.feign.QuizInterface;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.Response;
import com.telusko.quizservice.repository.QuizRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepo;
    @Autowired
    private QuizInterface quizInterface;


    /**
     * This method handles the fallback scenario for the question service.
     * It returns a list with a single element "Dummy" when an exception occurs.
     *
     * @param e The exception that triggered the fallback
     * @return A list containing "Dummy"
     */
    private List<String> questionServiceFallBack(Exception e) {
        List<String> list = new LinkedList<>();
        list.add("Dummy");
        return list;
    }

    /**
     * This method creates a quiz with the given category, sets the number of questions and assigns title
     * @param category The category of the quiz
     * @param numQ The number of questions
     * @param title The title of the quiz
     * @return ResponseEntity with the success message and status code
     */
    public ResponseEntity<String> createQuiz(String category, Long numQ, String title) {
        List<Long> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    /**
     * This method returns quiz by its Id or throws a Exception
     * @param id The id of the quiz
     * @return ResponseEntity with the quiz and status code'
     * @throws QuizNotFoundException if the quiz is not found
     */
    @RateLimiter(name = "questionService",
                fallbackMethod = "questionServiceFallBack")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer id) {
        Optional<Quiz> quizOptional = quizRepo.findById(id);
        if(quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            List<Long> questionIds = quiz.getQuestionIds();

            ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromIds(questionIds);
            return questions;
        } else {
            throw new QuizNotFoundException("Quiz not found");
        }
    }

    /**
     * This method returns the score of the quiz
     * @param id The id of the quiz
     * @param responses The list of responses of the quiz
     * @return ResponseEntity with the score and status code
     */
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }

    /**
     * This method updates the quiz or throws exception if quiz not found
     * @param quiz The updated quiz
     * @return ResponseEntity with the success message and status code
     * @throws QuizNotFoundException if the quiz is not found
     */
    @RateLimiter(name = "questionService",
            fallbackMethod = "questionServiceFallBack")
    public ResponseEntity<String> updateQuiz(Quiz quiz) {
        Optional<Quiz> quizOptional = quizRepo.findById(quiz.getId());

        if(quizOptional.isPresent()){
            quizRepo.save(quiz);
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else{
            throw new QuizNotFoundException("Quiz not found");
        }
    }

    /**
     * This method deletes the quiz if found or throws Exception if quiz not found
     * @param id The id of the quiz
     * @return ResponseEntity with the success message and status code
     * @throws QuizNotFoundException if the quiz is not found
     */
    @RateLimiter(name = "questionService",
            fallbackMethod = "questionServiceFallBack")
    public ResponseEntity<String> deleteQuiz(Integer id) {
        Optional<Quiz> quizOptional = quizRepo.findById(id);

        if(quizOptional.isPresent()){
            Quiz quiz = quizOptional.get();
            quizRepo.deleteById(id);

            return new ResponseEntity<>("delete", HttpStatus.OK);
        } else{
            throw new QuizNotFoundException("Quiz not found");
        }
    }
}
