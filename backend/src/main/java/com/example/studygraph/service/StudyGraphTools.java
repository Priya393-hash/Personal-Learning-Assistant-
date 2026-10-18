package com.example.studygraph.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.studygraph.FalkorDBService;

import dev.langchain4j.agent.tool.Tool;

@Component 
public class StudyGraphTools {
    private final FalkorDBService graphService;
    public StudyGraphTools(FalkorDBService graphService){
        this.graphService=graphService;
    }

    @Tool ("Get prerequisite relationships from the StudyGraph knowledge graph")
    public List<String> getLearningPath(){
        System.out.println("StudyGraphTool is called");
        return graphService.getLearningPath();
    }
}
