package com.example.QuizApp.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "app_user")  // Avoid using 'user'
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int totalQuestionsAttempted;
    private int correctAnswers;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalQuestionsAttempted() {
        return totalQuestionsAttempted;
    }

    public void setTotalQuestionsAttempted(int totalQuestionsAttempted) {
        this.totalQuestionsAttempted = totalQuestionsAttempted;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public double getScorePercentage() {
        if (totalQuestionsAttempted == 0) {
            return 0.0;
        }
        return ((double) correctAnswers / totalQuestionsAttempted) * 100;
    }
}

