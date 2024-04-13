package service.API_LOGISTIC;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class District_API {

    public static List<District> convert(String token, int provinceId) {
        String responseData = null;
        String apiUrl = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=" + provinceId;
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

        List<District> districts = new ArrayList<>();
        try {
            JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            for (JsonElement element : dataArray) {
                JsonObject districtObject = element.getAsJsonObject();
                int provinceID = districtObject.get("ProvinceID").getAsInt();
                int districtID = districtObject.get("DistrictID").getAsInt();
                String districtName = districtObject.get("DistrictName").getAsString();
                District district = new District(provinceID, districtID, districtName);
                districts.add(district);
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
        return districts;
    }

    public static void main(String[] args) {
//        String key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTQwLjIzOC41NC4xMzYvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2ODIwOTIyNjksImV4cCI6MTY4MjA5Mjg2OSwibmJmIjoxNjgyMDkyMjY5LCJqdGkiOiJDN3J2dEpmTGVnc2tjQzZXIiwic3ViIjoiODNjNGM1MWQ2N2Q1NGM0ODg4NWE4M2JjOGViYTJkZGMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.Cbphc1ghCqN_lUmQqXA5tPWWISJtqFMHaRi5VYHNfig";
        String key = "1ec8d4c1-f724-11ee-a6e6-e60958111f48";
        int id = 212;
        List<District> districts = District_API.convert(key, id);
        for (District district : districts) {
            System.out.println(district.getProvinceID() + ": " + district.getDistrictID() + ": " + district.getDistrictName());
        }

    }

}
