
package com.example.agentworkflow.controller;

import com.example.agentworkflow.agent.*;
import com.example.agentworkflow.model.LogicOperator;
import com.example.agentworkflow.model.Workflow;
import com.example.agentworkflow.model.WorkflowNode;
import com.example.agentworkflow.service.WorkflowEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {
    
    @Autowired
    private WorkflowEngine workflowEngine;
    
    @PostMapping("/execute")
    public Map<String, Object> executeWorkflow(@RequestBody Map<String, Object> request) {
        // 创建并注册Agent
        TextAnalysisAgent agent1 = new TextAnalysisAgent("agent1", "文本分析器");
        SentimentAnalysisAgent agent2 = new SentimentAnalysisAgent("agent2", "情感分析器");
        SummaryAgent agent3 = new SummaryAgent("agent3", "总结生成器");
        
        workflowEngine.registerAgent(agent1);
        workflowEngine.registerAgent(agent2);
        workflowEngine.registerAgent(agent3);
        
        // 创建工作流DAG
        Workflow workflow = new Workflow("workflow1", "文本处理工作流");
        
        // 节点1：文本分析
        WorkflowNode node1 = new WorkflowNode("node1", "agent1");
        node1.setOperator(LogicOperator.AND);
        
        // 节点2：情感分析
        WorkflowNode node2 = new WorkflowNode("node2", "agent2");
        node2.setOperator(LogicOperator.AND);
        
        // 节点3：总结生成（依赖节点1和节点2）
        WorkflowNode node3 = new WorkflowNode("node3", "agent3");
        node3.addDependency("node1");
        node3.addDependency("node2");
        node3.setOperator(LogicOperator.AND);
        
        workflow.addNode(node1);
        workflow.addNode(node2);
        workflow.addNode(node3);
        
        // 设置初始输入
        String inputText = (String) request.get("text");
        workflow.addToContext("input", inputText);
        
        // 执行工作流
        Object result = workflowEngine.executeWorkflow(workflow);
        
        return Map.of(
            "workflowId", workflow.getId(),
            "workflowName", workflow.getName(),
            "result", result
        );
    }
    
    @GetMapping("/test")
    public String testWorkflow() {
        return "工作流引擎运行正常！";
    }
}
