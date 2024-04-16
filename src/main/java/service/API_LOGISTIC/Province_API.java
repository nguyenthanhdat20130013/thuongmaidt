package service.API_LOGISTIC;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Province_API {
public static List<Province> convert(String token) {
    String responseData = null;
    String apiUrl = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province";
    try {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Token", token);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseData = response.toString();
        } else {
            System.out.println("Error: " + responseCode);
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    List<Province> provinces = new ArrayList<>();
    try {
        JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        for (JsonElement element : dataArray) {
            JsonObject provinceObject = element.getAsJsonObject();
            int id = provinceObject.get("ProvinceID").getAsInt();
            String name = provinceObject.get("ProvinceName").getAsString();
            Province province = new Province(id, name);
            provinces.add(province);
        }
    } catch (JsonSyntaxException e) {
        System.out.println("Error parsing JSON: " + e.getMessage());
    }
    return provinces;
}

    public static void main(String[] args) {
     String key = "1ec8d4c1-f724-11ee-a6e6-e60958111f48";
        List<Province> provinces = Province_API.convert(key);
        for (Province province : provinces) {
            System.out.println(province.getId() + ": " + province.getName());
        }

    }

}
