package com.telusko.questionservice.model;

import jakarta.persistence.*;

@Entity
@Table(name="question_tbl")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "question_title")
    private String title;
    @Column(name = "answer1", nullable = false)
    private String answer1;
    @Column(name = "answer2", nullable = false)
    private String answer2;
    @Column(name = "answer3", nullable = false)
    private String answer3;
    @Column(name = "answer4", nullable = false)
    private String answer4;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "right_answer", nullable = false)
    private String rightAnswer;
    @Column(name = "difficulty_level", nullable = false)
    private String difficultyLevel;

    public Question() {
    }

    public Question(String title,
                    String answer1,
                    String answer2,
                    String answer3,
                    String answer4,
                    String category,
                    String rightAnswer,
                    String difficultyLevel) {
        this.title = title;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.category = category;
        this.rightAnswer = rightAnswer;
        this.difficultyLevel = difficultyLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public String toString() {
        return "Question{" +
                ", title='" + title + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", category='" + category + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }
}
