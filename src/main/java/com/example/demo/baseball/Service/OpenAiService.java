package com.example.demo.baseball.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAiService {
    // 請將下列資訊換成你自己的 Azure OpenAI 設定
    private static final String ENDPOINT = "https://ielp2-may2rebp-eastus2.cognitiveservices.azure.com";
    private static final String DEPLOYMENT = "gpt-4o";
    private static final String API_VERSION = "2025-01-01-preview";
    private static final String API_KEY = "Fn1tpiNrzoOtEBec3W4eNbyXSWWjzHu95IIue0oM4uCVPthA0EmxJQQJ99BEACHYHv6XJ3w3AAAAACOGTlx3";

    public String getBatterDescription(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = ENDPOINT + "/openai/deployments/" + DEPLOYMENT + "/chat/completions?api-version=" + API_VERSION;

        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        JSONObject body = new JSONObject();
        body.put("messages", new JSONArray().put(message));
        body.put("max_tokens", 1000);
        //body.put("model", "GuanYusOpenAi");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        JSONObject result = new JSONObject(response.getBody());
        return result.getJSONArray("choices")
                     .getJSONObject(0)
                     .getJSONObject("message")
                     .getString("content")
                     .trim();
    }
}