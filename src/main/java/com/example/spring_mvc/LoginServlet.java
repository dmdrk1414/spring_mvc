package com.example.spring_mvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginServlet {

  private static final long serialVersionUID = 1L;

  @GetMapping("/LoginServlet")
  public String doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    return "/login.html";
  }

  @PostMapping("/LoginServlet")
  public String doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    return "redirect:/login.html";
  }
}