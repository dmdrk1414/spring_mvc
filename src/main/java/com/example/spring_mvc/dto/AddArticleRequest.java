package com.example.spring_mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.spring_mvc.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

  private String title;

  private String content;

  public Article toEntity() {
    return Article.builder()
        .title(title)
        .content(content)
        .build();
  }
}
