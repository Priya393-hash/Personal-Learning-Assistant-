package com.example.studygraph.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.model.openai.OpenAiChatModel;

@Service 
public class GeminiService {
    private final StudyGraphAssistant assistant;
    
    public GeminiService( @Lazy McpToolProvider mcpToolProvider){
        OpenAiChatModel model = OpenAiChatModel.builder()
            .apiKey(System.getenv("GROQ_API_KEY"))
            .baseUrl("https://api.groq.com/openai/v1")
            .modelName("openai/gpt-oss-120b")
            .build();

            assistant = AiServices.builder(StudyGraphAssistant.class)
                .chatModel(model)
                .toolProvider(mcpToolProvider)
                .build();
    }

    public String askGemini(String prompt){
        return assistant.chat(prompt);
    }
    
}
