package com.telusko.questionservice.service;

import com.telusko.questionservice.exception.QuestionNotFoundException;
import com.telusko.questionservice.model.Question;
import com.telusko.questionservice.model.QuestionWrapper;
import com.telusko.questionservice.model.Response;
import com.telusko.questionservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repo;


    public ResponseEntity<String> addQuestion(Question question){
        repo.save(question);
        return new ResponseEntity<String>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getAllQuestion() {
        List<Question> questionList = repo.findAll();

        if(!questionList.isEmpty()){
            // return all questions
            return new ResponseEntity<>(questionList, HttpStatus.OK);
        } else{
            // questions not present
            throw new QuestionNotFoundException("Questions not available");
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> questions = repo.findByCategory(category);

        if(!questions.isEmpty()){
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else{
            // question not present
            throw new QuestionNotFoundException("Question not available");
        }
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        Optional<Question> queOptional = repo.findById(question.getId());

        if(queOptional.isPresent()){
            repo.save(question);
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else{
            // questions not present
            throw new QuestionNotFoundException("Questions not available");
        }
    }

    public ResponseEntity<String> deleteQuestion(Long id) {
        Optional<Question> queOptional = repo.findById(id);

        if(queOptional.isPresent()){
            repo.deleteById(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        } else{
            // questions not present
            throw new QuestionNotFoundException("Questions not available");
        }
    }

    public List<Long> getQuestionsForQuiz(String categoryName, Long numQuestions) {
        List<Long> questionIds = repo.findRandomQuestionsByCategory(categoryName, numQuestions);
        return questionIds;
    }

    public List<QuestionWrapper> getQuestionsFromIds(List<Long> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Long id : questionIds) {
             questions.add(repo.findById(id).get());
        }

        for(Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setTitle(question.getTitle());
            wrapper.setAnswer1(question.getAnswer1());
            wrapper.setAnswer2(question.getAnswer2());
            wrapper.setAnswer3(question.getAnswer3());
            wrapper.setAnswer4(question.getAnswer4());

            wrappers.add(wrapper);
        }
        return wrappers;
    }

    public Integer getScore(List<Response> responses) {

        int result = 0;

        for(Response response : responses) {
            Question question = repo.findById(Long.valueOf(response.getId())).get();

            if(response.getResponse().equals(question.getRightAnswer())) {
                result++;
            }
        }
        return result;
    }
}
