package com.example.fifteam.tickettoride.serverCommunications;

import com.example.communication.BaseClient;
import com.example.communication.BaseRequest;
import com.example.communication.BaseResponse;
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
    public BaseResponse get(String host, String port, String url, String authToken, BaseRequest body) throws Exception
    {
        BaseResponse response = new BaseResponse();
        try{
            URL targetUrl = new URL("http://" + host + ":" + port + url);
            HttpURLConnection http = (HttpURLConnection)targetUrl.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            http.addRequestProperty("Accept", "application/json");
            if(authToken != null && !authToken.isEmpty()){
                http.addRequestProperty("Authorization", authToken);
            }
            http.connect();
            Gson serializer = new Gson();
            String json = serializer.toJson(body);
            OutputStream reqBody = http.getOutputStream();
            writeString(json,reqBody);
            reqBody.close();
           // if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                response = serializer.fromJson(respData, BaseResponse.class);
            //}
/*            else{
                response.hasError = true;
                response.type = "HttpReturnedBadCode";
                response.errorText = "The Http Request returned something other than a 200 OK. ";
                response.response = new Exception("ERROR: " + http.getResponseCode());
            } */
        }
       catch (Exception e){
           e.printStackTrace();
           throw e;
       }

        return response;
    }
    public BaseResponse post(String host, String port, String url, String authToken, BaseRequest body)throws Exception
    {
        BaseResponse response = new BaseResponse();
        try{
            URL targetUrl = new URL("http://" + host + ":" + port + url);
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
            writeString(json,reqBody);
            reqBody.close();
 //           if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                response = serializer.fromJson(respData, BaseResponse.class);
   //         }
/*            else{
                response.hasError = true;
                response.type = "HttpReturnedBadCode";
                response.errorText = "The Http Request returned something other than a 200 OK. ";
                response.response = new Exception("ERROR: " + http.getResponseCode());
            } */
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return response;
    }
}
