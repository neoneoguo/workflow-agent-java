
package com.example.agentworkflow.service;

import com.example.agentworkflow.agent.BaseAgent;
import com.example.agentworkflow.model.LogicOperator;
import com.example.agentworkflow.model.Workflow;
import com.example.agentworkflow.model.WorkflowNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkflowEngine {
    private final Map<String, BaseAgent> agents = new HashMap<>();
    
    public void registerAgent(BaseAgent agent) {
        agents.put(agent.getId(), agent);
    }
    
    public Object executeWorkflow(Workflow workflow) {
        System.out.println("开始执行工作流: " + workflow.getName());
        
        Map<String, Object> executionResults = new HashMap<>();
        Set<String> executedNodes = new HashSet<>();
        
        // 执行所有可执行的节点
        while (executedNodes.size() < workflow.getNodes().size()) {
            boolean progressMade = false;
            
            for (WorkflowNode node : workflow.getNodes().values()) {
                if (!executedNodes.contains(node.getId()) && 
                    canExecute(node, executedNodes, workflow.getNodes())) {
                    
                    System.out.println("执行节点: " + node.getId());
                    Object input = prepareInput(node, executionResults, workflow.getContext());
                    Object output = executeAgent(node.getAgentId(), input);
                    
                    executionResults.put(node.getId(), output);
                    executedNodes.add(node.getId());
                    progressMade = true;
                }
            }
            
            if (!progressMade) {
                throw new RuntimeException("工作流执行陷入死锁，可能存在循环依赖");
            }
        }
        
        return executionResults;
    }
    
    private boolean canExecute(WorkflowNode node, Set<String> executedNodes, 
                                            Map<String, WorkflowNode> allNodes) {
        if (node.getDependencies().isEmpty()) {
            return true;
        }
        
        List<Boolean> dependencyResults = new ArrayList<>();
        for (String depId : node.getDependencies()) {
            dependencyResults.add(executedNodes.contains(depId));
        }
        
        return evaluateLogicExpression(dependencyResults, node.getOperator());
    }
    
    private boolean evaluateLogicExpression(List<Boolean> operands, LogicOperator operator) {
        if (operator == null) {
            // 默认为AND
            return operands.stream().allMatch(Boolean::booleanValue);
        }

        return switch (operator) {
            case AND -> operands.stream().allMatch(Boolean::booleanValue);
            case OR -> operands.stream().anyMatch(Boolean::booleanValue);
            case NOT -> operands.size() == 1 && !operands.get(0);
            default -> false;
        };
    }
    
    private Object prepareInput(WorkflowNode node, Map<String, Object> executionResults, 
                                  Map<String, Object> globalContext) {
        // 合并全局上下文和依赖节点的输出作为输入
        Map<String, Object> input = new HashMap<>(globalContext);
        
        for (String depId : node.getDependencies()) {
            input.put(depId, executionResults.get(depId));
        }
        
        return input;
    }
    
    private Object executeAgent(String agentId, Object input) {
        BaseAgent agent = agents.get(agentId);
        if (agent == null) {
            throw new RuntimeException("未找到Agent: " + agentId);
        }
        return agent.execute(input);
    }
}
