
package com.example.agentworkflow.config;

import com.example.agentworkflow.agent.*;
import com.example.agentworkflow.service.WorkflowEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public WorkflowEngine workflowEngine() {
        return new WorkflowEngine();
    }
    
    @Bean
    public TextAnalysisAgent textAnalysisAgent() {
        return new TextAnalysisAgent("agent1", "文本分析器");
    }
    
    @Bean
    public SentimentAnalysisAgent sentimentAnalysisAgent() {
        return new SentimentAnalysisAgent("agent2", "情感分析器");
    }
    
    @Bean
    public SummaryAgent summaryAgent() {
        return new SummaryAgent("agent3", "总结生成器");
    }
}
