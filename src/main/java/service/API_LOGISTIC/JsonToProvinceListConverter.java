package service.API_LOGISTIC;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonToProvinceListConverter {
    String responseData = null;
    public String getTinh() {

        String apiUrl = "http://140.238.54.136/api/province";
        String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTQwLjIzOC41NC4xMzYvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2ODIwODUzMTgsImV4cCI6MTY4MjA4NTkxOCwibmJmIjoxNjgyMDg1MzE4LCJqdGkiOiI2NEhTOXNDYXFhOFJkQ3ZwIiwic3ViIjoiODNjNGM1MWQ2N2Q1NGM0ODg4NWE4M2JjOGViYTJkZGMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.ithmjAD7x4d6fGcYsCQmR9OIA4UJhHRXcvM0y9Ukg3o";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
               responseData = String.valueOf(response);
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return responseData;
    }

    public static List<Province> convert(String json) {

        List<Province> provinces = new ArrayList<>();

        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        if (jsonObject.has("original")) {
            JsonObject originalJson = jsonObject.getAsJsonObject("original");
            if (originalJson.has("data") && originalJson.get("data").isJsonArray()) {
                JsonArray jsonArray = originalJson.getAsJsonArray("data");
                for (JsonElement jsonElement : jsonArray) {
                    JsonObject provinceJson = jsonElement.getAsJsonObject();
                    int id = provinceJson.get("ProvinceID").getAsInt();
                    String name = provinceJson.get("ProvinceName").getAsString();
                    Province province = new Province(id, name);
                    provinces.add(province);
                }
            }
        }

        return provinces;
    }


    public static class Province {
        private int id;
        private String name;

        public Province(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        JsonToProvinceListConverter js = new JsonToProvinceListConverter();
        String json = js.getTinh();

        System.out.println(json);
                // "{\"headers\":{},\"original\":{\"message\":\"success\",\"status\":200,\"data\":[{\"ProvinceID\":269,\"ProvinceName\":\"Lào Cai\"},{\"ProvinceID\":201,\"ProvinceName\":\"Hà Nội\"}]},\"exception\":null}";

                List<JsonToProvinceListConverter.Province> provinces = JsonToProvinceListConverter.convert(json);

        for (JsonToProvinceListConverter.Province province : provinces) {
            System.out.println(province.getId() + ": " + province.getName());
        }

    }
}
