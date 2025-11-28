
package com.example.agentworkflow.model;

import java.util.ArrayList;
import java.util.List;

public class WorkflowNode {
    private String id;
    private String agentId;
    private List<String> dependencies;
    private LogicOperator operator;
    private Object input;
    private Object output;
    
    public WorkflowNode(String id, String agentId) {
        this.id = id;
        this.agentId = agentId;
        this.dependencies = new ArrayList<>();
    }
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }
    
    public List<String> getDependencies() { return dependencies; }
    public void setDependencies(List<String> dependencies) { this.dependencies = dependencies; }
    
    public LogicOperator getOperator() { return operator; }
    public void setOperator(LogicOperator operator) { this.operator = operator; }
    
    public Object getInput() { return input; }
    public void setInput(Object input) { this.input = input; }
    
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
    
    public void addDependency(String nodeId) {
        this.dependencies.add(nodeId);
    }
}
