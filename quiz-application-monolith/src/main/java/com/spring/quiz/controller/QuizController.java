package com.spring.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.spring.quiz.modal.QuestionWrapper;
import com.spring.quiz.modal.Response;
import com.spring.quiz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category , @RequestParam int quesNumber , @RequestParam String title ){
		
		return quizService.createQuiz(category , quesNumber , title);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuestion(@PathVariable int id){
		return quizService.getQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer>submitQuiz(@PathVariable int id , @RequestBody List<Response> response){
		return quizService.calculateResult(id , response);
	}
	
	
	
}
