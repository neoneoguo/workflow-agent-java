
package com.example.agentworkflow.agent;

public abstract class BaseAgent {
    protected String id;
    protected String name;
    
    public BaseAgent(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public abstract Object execute(Object input);
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
}
