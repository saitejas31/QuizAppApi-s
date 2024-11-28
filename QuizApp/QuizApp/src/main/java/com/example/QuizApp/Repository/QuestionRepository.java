package com.example.QuizApp.Repository;


import com.example.QuizApp.Model.Question;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query("SELECT q FROM Question q ORDER BY function('RAND')")
    Optional<Question> findRandomQuestion(PageRequest pageable);

}
