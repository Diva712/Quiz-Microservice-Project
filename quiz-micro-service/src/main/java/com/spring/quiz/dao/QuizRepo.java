package com.spring.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.quiz.modal.Quiz;



public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
