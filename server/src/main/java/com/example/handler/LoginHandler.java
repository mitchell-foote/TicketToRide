package com.example.handler;

import com.example.Exceptions.FailedLoginException;
import com.example.communication.BaseRequest;
import com.example.communication.BaseResponse;
import com.example.communication.IServerAccessor;
import com.example.model.ServerFacade;
import com.example.model.classes.users.LoginRequest;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by ninjup on 10/1/17.
 */

public class LoginHandler extends Handler implements HttpHandler {

    private IServerAccessor serverFacade = new ServerFacade();

    @Override
    public void handle(HttpExchange httpExch) throws IOException {

        System.out.println("Login handler reached");

        try {
            if (httpExch.getRequestMethod().toLowerCase().equals("post")) {
                InputStream requestBody = httpExch.getRequestBody();
                httpExch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream responseBody = httpExch.getResponseBody();

                String requestData = this.readString(requestBody);
                BaseRequest data = new Gson().fromJson(requestData, BaseRequest.class);

                data.body = new Gson().fromJson(data.body.toString(), LoginRequest.class);
                LoginRequest loginInfo;
                if(data.body instanceof LoginRequest){
                    loginInfo = (LoginRequest) data.body;
                }
                else {
                    userError(httpExch, new Exception("Bad Request Type"));
                    return;
                }

                try {
                    String authToken = serverFacade.login(loginInfo.getUserName(), loginInfo.getPassword());

                    BaseResponse response = new BaseResponse();
                    response.type = "string";
                    response.response = authToken;
                    response.hasError = false;
                    writeString(new Gson().toJson(response), responseBody);

                    responseBody.close();
                } catch (FailedLoginException e) {
                    userError(httpExch, e);
                } catch (Exception e) {
                    userError(httpExch, e);
                }
            } else {
                userError(httpExch, new Exception("Error: wrong request method used"));
            }
        } catch (IOException e) {
            serverError(httpExch, e);
            e.printStackTrace();
        }
    }
}