
package com.example.agentworkflow.agent;

import java.util.HashMap;
import java.util.Map;

public class SummaryAgent extends BaseAgent {
    
    public SummaryAgent(String id, String name) {
        super(id, name);
    }
    
    @Override
    public Object execute(Object input) {
        System.out.println("Agent " + name + " 开始生成总结...");
        
        Map<String, Object> context = (Map<String, Object>) input;
        Map<String, Object> textResult = (Map<String, Object>) context.get("node1");
        Map<String, Object> sentimentResult = (Map<String, Object>) context.get("node2");
        
        Map<String, Object> result = new HashMap<>();
        result.put("summary", "文本分析完成：字数=" + textResult.get("wordCount") + 
                         ", 情感=" + sentimentResult.get("sentiment"));
        result.put("agent", this.name);
        result.put("combinedResults", context);
        
        System.out.println("Agent " + name + " 执行完成，最终结果: " + result);
        return result;
    }
}
