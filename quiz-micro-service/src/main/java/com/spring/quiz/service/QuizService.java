package com.spring.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.quiz.dao.QuizRepo;
import com.spring.quiz.modal.QuestionWrapper;
import com.spring.quiz.modal.Quiz;
import com.spring.quiz.modal.Response;
import com.spring.quiz.server.FeignInterface;

@Service
public class QuizService {
	
	@Autowired
	QuizRepo repo;
	
	@Autowired
	FeignInterface feignInterface;

	public ResponseEntity<String> createQuiz(String category, int quesNumber, String title) {
		// TODO Auto-generated method stub
		
//		List<QuestionModal>list = qrepo.findRandomQuestionsByCategory(category , quesNumber); 
//		
//		
		
		
		List<Integer>questionIds = feignInterface.createQuiz(category, quesNumber).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionsIds(questionIds);
		repo.save(quiz);
				
		return new ResponseEntity<>("Success" , HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(int id) {
	
		Quiz quiz = repo.findById(id).get();
		List<Integer>questionIds = quiz.getQuestionsIds();
		ResponseEntity<List<QuestionWrapper>> questions = feignInterface.getQuestionFromId(questionIds);
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> response) {
		ResponseEntity<Integer> score = feignInterface.getScore(response);
      
		return score;
	}
}
