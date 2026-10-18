package com.example.studygraph.service;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import com.example.studygraph.FalkorDBService;
import java.util.List;

@Component 
public class StudyGraphMcpTools {
    private final FalkorDBService falkorDBService;
    public StudyGraphMcpTools(FalkorDBService falkorDBService){
        this.falkorDBService=falkorDBService;
    }
    @McpTool (
        name="get_learning_path",
        description="Get prerequisite learning paths from the StudyGraph Knowledge graph"
    )
    public List<String> getLearningPath(){
        System.out.println("MCP tool called");
        return falkorDBService.getLearningPath();
    }
}
