package com.example.spring_mvc.service;

import com.example.spring_mvc.domain.Article;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.spring_mvc.dto.AddArticleRequest;
import com.example.spring_mvc.dto.UpdateArticleRequest;
import com.example.spring_mvc.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

  private final BlogRepository blogRepository;

  public Article save(AddArticleRequest request) {
    return blogRepository.save(request.toEntity());
  }

  public List<Article> findAll() {
    return blogRepository.findAll();
  }

  public Article findById(long id) {
    return blogRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
  }

  public void delete(long id) {
    blogRepository.deleteById(id);
  }

  @Transactional
  public Article update(long id, UpdateArticleRequest request) {
    Article article = blogRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

    article.update(request.getTitle(), request.getContent());

    return article;
  }
}
