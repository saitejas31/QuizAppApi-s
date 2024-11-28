package com.example.QuizApp.questions;


import com.example.QuizApp.Model.AppUser;
import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Repository.QuestionRepository;
import com.example.QuizApp.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public DataLoader(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert 5 users
        if (userRepository.count() == 0) {
            for (int i = 1; i <= 5; i++) {
                AppUser user = new AppUser();
                user.setUsername("user" + i);
                user.setTotalQuestionsAttempted(0);
                user.setCorrectAnswers(0);
                userRepository.save(user);
            }
            System.out.println("5 users added!");
        }

        // Insert 10 questions
        if (questionRepository.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                Question question = new Question();
                question.setQuestionText("Sample question " + i);
                question.setOptions(Arrays.asList("Option A", "Option B", "Option C", "Option D"));
                question.setCorrectAnswer("Option A");
                questionRepository.save(question);
            }
            System.out.println("10 questions added!");
        }
    }
}
