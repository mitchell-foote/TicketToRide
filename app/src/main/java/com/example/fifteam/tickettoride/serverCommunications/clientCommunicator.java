package com.example.fifteam.tickettoride.serverCommunications;

import com.example.communication.BaseClient;
import com.example.communication.BaseRequest;
import com.example.communication.BaseResponse;
import com.example.communication.PathHolder;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class clientCommunicator extends BaseClient
{
    public BaseResponse get(String host, String port, String url, String authToken) throws Exception
    {
        BaseResponse response = new BaseResponse();
        try{
            URL targetUrl = new URL("http://" + PathHolder.getInstance().getClientHost() + ":" + port + url);
            HttpURLConnection http = (HttpURLConnection)targetUrl.openConnection();
            http.setRequestMethod("GET");
            http.addRequestProperty("Accept", "application/json");
            if(authToken != null && !authToken.isEmpty()){
                http.addRequestProperty("Authorization", authToken);
            }
            http.connect();
            Gson serializer = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                response = serializer.fromJson(respData, BaseResponse.class);
                return response;
            } else {
                InputStream respBody = http.getErrorStream();
                String respData = readString(respBody);
                response = serializer.fromJson(respData, BaseResponse.class);
                System.out.println(response.errorText);
                return response;
            }

        }
       catch (Exception e){
           e.printStackTrace();
           throw e;
       }
    }

    public BaseResponse post(String host, String port, String url, String authToken, BaseRequest body)throws Exception
    {
        BaseResponse response = new BaseResponse();
        try{
            URL targetUrl = new URL("http://" + PathHolder.getInstance().getClientHost() + ":" + port + url);
            HttpURLConnection http = (HttpURLConnection)targetUrl.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.addRequestProperty("Accept", "application/json");
            if(authToken != null && !authToken.isEmpty()){
                http.addRequestProperty("Authorization", authToken);
            }
            http.connect();
            Gson serializer = new Gson();
            String json = serializer.toJson(body);
            OutputStream reqBody = http.getOutputStream();
            writeString(json, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                response = serializer.fromJson(respData, BaseResponse.class);
                return response;
            } else {
                InputStream respBody = http.getErrorStream();
                String respData = readString(respBody);
                response = serializer.fromJson(respData, BaseResponse.class);
                return response;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
