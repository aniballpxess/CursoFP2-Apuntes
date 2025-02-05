package edu.dam2.ad.ejercicios.emojiapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmojiAPI {
    private static final String API_KEY = "e61159ab016815cd9bd211e4c4624f96bc4d6ff6";
    private static final String BASE_URL = "https://emoji-api.com";

    // To fetch emojis of a given category
    public static List<Emoji> fetchEmojisByCategory(String category) {
        List<Emoji> emojiList = new ArrayList<>();
        try {
            // Establish the connection to the API
            String urlString = BASE_URL + "/categories/" + category + "?access_key=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            conn.disconnect();

            // Parse to JSON the response
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String character = obj.getString("character");
                String name = obj.getString("unicodeName");
                String group = obj.getString("group");
                String subGroup = obj.getString("subGroup");
                emojiList.add(new Emoji(character, name, group, subGroup));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emojiList;
    }
}
