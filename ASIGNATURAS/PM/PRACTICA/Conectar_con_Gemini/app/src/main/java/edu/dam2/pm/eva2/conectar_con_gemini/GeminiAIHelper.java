package edu.dam2.pm.eva2.conectar_con_gemini;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.Content;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeminiAIHelper {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void generateText(String promptText, ResponseCallback callback) {
        executorService.submit(() -> {
            try {
                // Initialize the generative model
                GenerativeModel generativeModel = new GenerativeModel("gemini-1.5-pro-latest", API_KEY);

                // Create the correct Content object
                Content prompt = new Content.Builder()
                        .addText(promptText)
                        .build();

                Content[] prompts = new Content[]{prompt};
                // Use the updated API method
                GenerateContentResponse response = generativeModel.generateContent(prompts);
                String resultText = response.getText();

                // Return the result to the UI thread
                callback.onSuccess(resultText);
            } catch (Exception e) {
                callback.onFailure(e);
            }
        });
    }

    // Interface for callback handling
    public interface ResponseCallback {
        void onSuccess(String result);
        void onFailure(Exception e);
    }
}
