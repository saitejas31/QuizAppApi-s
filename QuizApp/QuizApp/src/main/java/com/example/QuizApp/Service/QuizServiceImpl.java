package com.example.QuizApp.Service;


import com.example.QuizApp.dto.AnswerDTO;
import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.AppUser;
import com.example.QuizApp.Repository.QuestionRepository;
import com.example.QuizApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizServiceImpl(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public AppUser getUserPerformance(Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user; // Return the user object, which includes the score percentage method
    }


    @Override
    public Question getRandomQuestion(Long userId) {
        // Check if the user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User with ID " + userId + " not found");
        }

        // Fetch a random question if the user exists
        return questionRepository.findRandomQuestion(PageRequest.of(0, 1))
                .orElseThrow(() -> new RuntimeException("No questions available"));
    }


    @Override
    public boolean submitAnswer(Long userId, AnswerDTO answerDTO) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Question question = questionRepository.findById(answerDTO.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        user.setTotalQuestionsAttempted(user.getTotalQuestionsAttempted() + 1);
        boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(answerDTO.getSelectedAnswer());

        if (isCorrect) {
            user.setCorrectAnswers(user.getCorrectAnswers() + 1);
        }

        userRepository.save(user);
        return isCorrect;
    }

    @Override
    public AppUser endQuiz(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
