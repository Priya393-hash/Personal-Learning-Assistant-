package com.example.studygraph.service;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport;

@Configuration 
public class StudyGraphMcpConfig {
    
   @Bean 
   @Lazy 
   public McpClient mcpClient(){
    var transport = StreamableHttpMcpTransport.builder()
    .url("http://localhost:8080/mcp")
    .build();
    return DefaultMcpClient.builder()
    .key("studygraph-client")
    .transport(transport)
    .build();
   }

   @Bean 
   @Lazy 
   public McpToolProvider mcpToolProvider(@Lazy McpClient mcpClient){
    return McpToolProvider.builder()
    .mcpClients(mcpClient)
    .build();
   }
}
