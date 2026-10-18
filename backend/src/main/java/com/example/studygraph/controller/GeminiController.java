package com.example.studygraph.controller;

import com.example.studygraph.service.GeminiService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin (origins = "http://localhost:5173")
@RestController 
public class GeminiController {
    
    private final GeminiService geminiService;
    public GeminiController(GeminiService geminiService){
        this.geminiService=geminiService;
    }

    @GetMapping ("/ask-ai")
    public String askAI(@RequestParam String question){
        return geminiService.askGemini(question);
    }
}
