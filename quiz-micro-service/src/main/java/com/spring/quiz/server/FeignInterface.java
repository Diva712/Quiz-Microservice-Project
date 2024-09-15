package com.spring.quiz.server;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.quiz.modal.QuestionWrapper;
import com.spring.quiz.modal.Response;

@FeignClient("QUESTION-MICRO-SERVICE")
public interface FeignInterface {
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> createQuiz(@RequestParam String QuestionCategory ,@RequestParam Integer numberOfQuestion);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer>getScore(@RequestBody List<Response>responses);
}
