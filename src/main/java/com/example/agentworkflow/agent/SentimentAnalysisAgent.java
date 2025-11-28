
package com.example.agentworkflow.agent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SentimentAnalysisAgent extends BaseAgent {
    private final Random random = new Random();
    
    public SentimentAnalysisAgent(String id, String name) {
        super(id, name);
    }
    
    @Override
    public Object execute(Object input) {
        System.out.println("Agent " + name + " 开始执行情感分析...");
        
        String text = ((HashMap) input).get("input").toString();
//        String text = (String) input.get("text");
        String[] sentiments = {"积极", "中性", "消极"};
        String sentiment = sentiments[random.nextInt(sentiments.length)];
        
        Map<String, Object> result = new HashMap<>();
        result.put("sentiment", sentiment);
        result.put("confidence", random.nextDouble() * 0.5 + 0.5);
        result.put("agent", this.name);
        
        System.out.println("Agent " + name + " 执行完成，结果: " + result);
        return result;
    }
}
