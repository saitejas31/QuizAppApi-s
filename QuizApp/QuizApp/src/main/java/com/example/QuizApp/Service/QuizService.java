package com.example.QuizApp.Service;


import com.example.QuizApp.dto.AnswerDTO;
import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.AppUser;

public interface QuizService {
    AppUser getUserPerformance(Long userId);
    Question getRandomQuestion(Long userId);
    boolean submitAnswer(Long userId, AnswerDTO answerDTO);
    AppUser endQuiz(Long userId);
}
