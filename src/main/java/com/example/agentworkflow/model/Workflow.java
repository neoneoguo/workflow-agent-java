
package com.example.agentworkflow.model;

import java.util.HashMap;
import java.util.Map;

public class Workflow {
    private String id;
    private String name;
    private Map<String, WorkflowNode> nodes;
    private Map<String, Object> context;
    
    public Workflow(String id, String name) {
        this.id = id;
        this.name = name;
        this.nodes = new HashMap<>();
        this.context = new HashMap<>();
    }
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Map<String, WorkflowNode> getNodes() { return nodes; }
    public void setNodes(Map<String, WorkflowNode> nodes) { this.nodes = nodes; }
    
    public Map<String, Object> getContext() { return context; }
    public void setContext(Map<String, Object> context) { this.context = context; }
    
    public void addNode(WorkflowNode node) {
        this.nodes.put(node.getId(), node);
    }
    
    public void addToContext(String key, Object value) {
        this.context.put(key, value);
    }
}
