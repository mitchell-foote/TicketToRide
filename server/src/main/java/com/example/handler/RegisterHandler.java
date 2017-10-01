package com.example.handler;

import com.example.Exceptions.FailedLoginException;
import com.example.communication.BaseRequest;
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

public class RegisterHandler extends Handler implements HttpHandler {

    private IServerAccessor serverFacade = new ServerFacade();

    @Override
    public void handle(HttpExchange httpExch) throws IOException {

        try {
            if (httpExch.getRequestMethod().toLowerCase().equals("post")) {
                InputStream requestBody = httpExch.getRequestBody();
                OutputStream responseBody = httpExch.getResponseBody();

                String requestData = this.readString(requestBody);
                BaseRequest data = new Gson().fromJson(requestData, BaseRequest.class);
                LoginRequest loginInfo = (LoginRequest) data.body;

                try {
                    String authToken = serverFacade.register(loginInfo.getUserName(), loginInfo.getPassword());
                    httpExch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    writeString(authToken, responseBody);
                    responseBody.close();
                }  catch (FailedLoginException e) {
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
