package com.spring.quiz.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.quiz.dao.QuestionRepo;
import com.spring.quiz.modal.QuestionModal;
import com.spring.quiz.modal.QuestionWrapper;
import com.spring.quiz.modal.Response;


@Service
public class QuestionService {

	@Autowired
	QuestionRepo repo;
	public ResponseEntity<List<QuestionModal>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<List<QuestionModal>> getQuestionByCategory(String category) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(repo.findByCategory(category) , HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> addQuestion(QuestionModal question) {
		// TODO Auto-generated method stub
		try {
			repo.save(question);
			return new ResponseEntity<String>("success" , HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Error during adding question API" , HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<Integer>>getQuestionForQuiz(String CategoryName , Integer QuestionNumber) {
		
		
		List<Integer>questions = repo.findRandomQuestionsByCategory(CategoryName, QuestionNumber);
		return new ResponseEntity<>(questions , HttpStatus.OK);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionByIds(List<Integer> questionIds) {
		
		List<QuestionWrapper>wrappers = new ArrayList<>();
		
		List<QuestionModal>questions = new ArrayList<>();
		
		for(Integer id : questionIds) {
			QuestionModal q = repo.findById((long)id).get();
			questions.add(q);
		}
		
		for(QuestionModal question : questions) {
			QuestionWrapper wrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers , HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		// TODO Auto-generated method stub
				int score = 0;
		for(Response response : responses) {
			QuestionModal question = repo.findById(response.getId()).get();
			

			if(response.getResponse().equals(question.getRightAnswer())) {
				score++;
			}
		}
		
		return new ResponseEntity<>(score , HttpStatus.OK);
	}

}
