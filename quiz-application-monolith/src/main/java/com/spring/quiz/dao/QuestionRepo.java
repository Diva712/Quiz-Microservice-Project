package com.spring.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.quiz.modal.QuestionModal;

@Repository
public interface QuestionRepo extends JpaRepository<QuestionModal, Long>{

	List<QuestionModal> findByCategory(String category);
	
	 @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :quesNumber", nativeQuery = true)
	 List<QuestionModal> findRandomQuestionsByCategory(String category, int quesNumber);

	 
}
