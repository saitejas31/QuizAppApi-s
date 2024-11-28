package com.example.QuizApp.Controller;



import com.example.QuizApp.dto.AnswerDTO;
import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.AppUser;
import com.example.QuizApp.Service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Endpoint: GET /dashboard/{userId}
    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<?> getUserPerformance(@PathVariable Long userId) {
        AppUser user = quizService.getUserPerformance(userId);

        // Build the response with percentage
        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "totalQuestionsAttempted", user.getTotalQuestionsAttempted(),
                "correctAnswers", user.getCorrectAnswers(),
                "scorePercentage", user.getScorePercentage() // Add score percentage
        ));
    }


    // Endpoint: POST /quiz/take/{userId}
// Endpoint: POST /quiz/take/{userId}
    @PostMapping("/take/{userId}")
    public ResponseEntity<?> getRandomQuestion(@PathVariable Long userId) {
        try {
            Question question = quizService.getRandomQuestion(userId);
            return ResponseEntity.ok(question);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }


    // Endpoint: POST /quiz/submit/{userId}
    @PostMapping("/submit/{userId}")
    public ResponseEntity<String> submitAnswer(@PathVariable Long userId, @RequestBody AnswerDTO answerDTO) {
        boolean isCorrect = quizService.submitAnswer(userId, answerDTO);
        String responseMessage = isCorrect ? "Correct Answer" : "Incorrect Answer";
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint: POST /quiz/end/{userId}
    @PostMapping("/end/{userId}")
    public ResponseEntity<AppUser> endQuiz(@PathVariable Long userId) {
        AppUser user = quizService.endQuiz(userId);
        return ResponseEntity.ok(user);
    }
}
