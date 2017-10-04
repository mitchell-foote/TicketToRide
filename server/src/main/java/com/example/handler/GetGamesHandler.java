package com.example.handler;

import com.example.Exceptions.FailedAuthException;
import com.example.communication.BaseResponse;
import com.example.communication.IServerAccessor;
import com.example.model.ServerFacade;
import com.example.model.classes.login.BaseGameSummary;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by ninjup on 10/1/17.
 */

public class GetGamesHandler extends Handler implements HttpHandler {

    private IServerAccessor serverFacade = new ServerFacade();

    @Override
    public void handle(HttpExchange httpExch) throws IOException {

        System.out.println("Get Games handler reached");

        if (httpExch.getRequestMethod().toLowerCase().equals("get")) {
            OutputStream responseBody = httpExch.getResponseBody();
            String authToken = httpExch.getRequestHeaders().getFirst("Authorization");

            try {
                List<BaseGameSummary> games = serverFacade.getGames(authToken);
                httpExch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                BaseResponse response = new BaseResponse();
                response.type = "list";
                response.response = games;
                response.hasError = false;

                writeString(new Gson().toJson(response), responseBody);
                responseBody.close();
            } catch (FailedAuthException e) {
                userError(httpExch, e);
            } catch (Exception e) {
                userError(httpExch, e);
            }
        } else {
            userError(httpExch, new Exception("Error: wrong request method used"));
        }

    }
}
