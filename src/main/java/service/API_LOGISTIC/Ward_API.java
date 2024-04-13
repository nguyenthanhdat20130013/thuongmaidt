package service.API_LOGISTIC;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Ward_API {
    public static List<Ward> convert(String token, int districtId) {
        String responseData = null;
        String apiUrl = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + districtId;
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

        List<Ward> wardList = new ArrayList<>();
        try {
            JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            for (JsonElement element : dataArray) {
                JsonObject wardObject = element.getAsJsonObject();
                int wardCode = wardObject.get("WardCode").getAsInt();
                int districtID = wardObject.get("DistrictID").getAsInt();
                String wardName = wardObject.get("WardName").getAsString();
                Ward ward = new Ward(wardCode, districtID, wardName);
                wardList.add(ward);
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
        return wardList;
    }

    public static void main(String[] args) {
//        String key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTQwLjIzOC41NC4xMzYvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2ODIxMzIzNDUsImV4cCI6MTY4MjEzMjk0NSwibmJmIjoxNjgyMTMyMzQ1LCJqdGkiOiJYbUU4WGRWM3pVWTExdUhtIiwic3ViIjoiODNjNGM1MWQ2N2Q1NGM0ODg4NWE4M2JjOGViYTJkZGMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.daJ66yE79N9UixR2JtjPEXaUFD5d-Kj8olRp6dcLxQY";
        String key ="1ec8d4c1-f724-11ee-a6e6-e60958111f48";
        int id = 2084;
        List<Ward> districts = Ward_API.convert(key, id);
        for (Ward district : districts) {
            System.out.println(district.getWardCode() + ": " + district.getDistrictID() + ": " + district.getWardName());
        }
    }
}
