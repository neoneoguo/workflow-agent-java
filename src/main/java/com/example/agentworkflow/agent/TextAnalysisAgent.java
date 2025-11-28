
package com.example.agentworkflow.agent;

import java.util.HashMap;
import java.util.Map;

public class TextAnalysisAgent extends BaseAgent {
    
    public TextAnalysisAgent(String id, String name) {
        super(id, name);
    }
    
    @Override
    public Object execute(Object input) {
        System.out.println("Agent " + name + " 开始执行文本分析...");
        
        String text = ((HashMap<?, ?>) input).get("input").toString();
        Map<String, Object> result = new HashMap<>();
        result.put("wordCount", text.split("\\s+").length);
        result.put("charCount", text.length());
        result.put("agent", this.name);
        
        System.out.println("Agent " + name + " 执行完成，结果: " + result);
        return result;
    }
}
