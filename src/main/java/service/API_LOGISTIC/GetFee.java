package service.API_LOGISTIC;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GetFee {
    public static Double calculateShippingFee(String token, String from_district_id, String from_ward_id, String to_district_id, String to_ward_id) throws IOException, IOException {

        // Trích xuất giá trị phí vận chuyển từ phản hồi API
        // double serviceFee = Double.parseDouble(String.valueOf(apiResponseJson.getJSONArray("data").getJSONObject(0).getDouble("service_fee")));
        return 0.0;
    }

    public static void main(String[] args) throws IOException {
        String token = Login_API.login();
        try {
            String from_district_id = "2264";
            String from_ward_id = "90816";
            String to_district_id = "2270";
            String to_ward_id = "231013";

            Double Fee = calculateShippingFee(token, from_district_id, from_ward_id, to_district_id, to_ward_id);
            System.out.println("Phí vận chuyển: " + Fee);
        } catch (IOException e) {
            System.err.println("Error calculating fee: " + e.getMessage());
        }

    }

}