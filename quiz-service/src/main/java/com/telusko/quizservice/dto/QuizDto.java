package com.telusko.quizservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuizDto {
    String category;
    Long numQuestions;
    String title;
}
