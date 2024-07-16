package com.company.service;

import com.company.util.RandomUtil;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SMSService {

    private String smsUrl = "https://notify.eskiz.uz/";
    private String myEskizUzEmail = "if.magiic3105@gmail.com";
    private String myEskizUzPassword = "xLgFWgRMEeagUeEAJRs0zHiBvhvvfxgvHzThXBZE";

    public void sendSMS(String phone) {

        String randomSMSCode = RandomUtil.getRandomSMSCode();

        String message = "Bu Eskiz dan test";

        send(phone, message);
    }

    public void send(String phone, String message) {

        String token = "Bearer " + getToken();
        String prPhone = phone;
        if (prPhone.startsWith("+")) {
            prPhone = prPhone.substring(1);
        }

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("mobile_phone", prPhone)
                .addFormDataPart("message", message)
                .addFormDataPart("from", "4546")
                .build();

        Request request = new Request.Builder()
                .url(smsUrl + "api/message/sms/send")
                .method("POST", body)
                .header("Authorization", token)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                System.out.println(response);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    private String getToken() {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email", myEskizUzEmail)
                .addFormDataPart("password", myEskizUzPassword)
                .build();
        Request request = new Request.Builder()
                .url(smsUrl + "api/auth/login")
                .method("POST", body)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException();
            } else {
                JSONObject object = new JSONObject(response.body().string());
                JSONObject data = object.getJSONObject("data");
                Object token = data.get("token");
                return token.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
