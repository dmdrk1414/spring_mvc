package com.example.spring_mvc.repository;


import com.example.spring_mvc.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}

