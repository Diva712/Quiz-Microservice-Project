package com.spring.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.quiz.modal.QuestionModal;
import com.spring.quiz.modal.QuestionWrapper;
import com.spring.quiz.modal.Response;
import com.spring.quiz.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allquestions")
	public ResponseEntity<List<QuestionModal>> getAllQuestions() {
		
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<QuestionModal>> gqtQuestionByCategory(@PathVariable String category){
		
		return questionService.getQuestionByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody QuestionModal question) {
		return questionService.addQuestion(question);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> createQuiz(@RequestParam String QuestionCategory ,@RequestParam Integer numberOfQuestion){
		return questionService.getQuestionForQuiz(QuestionCategory , numberOfQuestion);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer>questionIds){
		return questionService.getQuestionByIds(questionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer>getScore(@RequestBody List<Response>responses){
		return questionService.getScore(responses);
	}
	
}
