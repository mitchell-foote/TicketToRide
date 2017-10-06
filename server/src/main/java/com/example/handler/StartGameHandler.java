package com.example.handler;

import com.example.Exceptions.FailedLoginException;
import com.example.communication.BaseRequest;
import com.example.communication.BaseResponse;
import com.example.communication.IServerAccessor;
import com.example.communication.Requests.StartGameRequest;
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

public class StartGameHandler extends Handler implements HttpHandler {

    private IServerAccessor serverFacade = new ServerFacade();

    @Override
    public void handle(HttpExchange httpExch) throws IOException {

        System.out.println("Start Game handler reached");

        try {
            if (httpExch.getRequestMethod().toLowerCase().equals("post")) {
                InputStream requestBody = httpExch.getRequestBody();
                OutputStream responseBody = httpExch.getResponseBody();

                String authToken = httpExch.getRequestHeaders().getFirst("Authorization");

                String requestData = this.readString(requestBody);
                BaseRequest data = new Gson().fromJson(requestData, BaseRequest.class);
                data.body = new Gson().fromJson(new Gson().toJson(data.body), StartGameRequest.class);
                String gameId = ((StartGameRequest)data.body).gameId;

                try {

                    if (serverFacade.startGame(gameId, authToken)) {
                        BaseResponse response = new BaseResponse();
                        response.type = "boolean";
                        response.response = true;
                        response.hasError = false;
                        httpExch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        writeString(new Gson().toJson(response), responseBody);
                        responseBody.close();
                    } else {
                        userError(httpExch, new Exception("Start game failed"));
                    }
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