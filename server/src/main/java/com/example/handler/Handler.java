package com.example.handler;


import com.example.communication.BaseResponse;
import com.example.communication.commands.CommandResponse;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public abstract class Handler {

    /**  Sets responses with user errors, based on bad input. Exception e contains the exact error
     *
     */
    protected void userError(HttpExchange httpExch, Exception e){
        try{
            OutputStream resBody = httpExch.getResponseBody();
            httpExch.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

            BaseResponse errorResponse = makeErrorResponse(e);
            String strResponse = new Gson().toJson(errorResponse);

            writeString(strResponse, resBody);
            resBody.close();
        }
        catch(IOException er){
            e.printStackTrace();
            OutputStream resBody = httpExch.getResponseBody();
            httpExch.close();
        }
    }
    protected void returnCommandResponse(HttpExchange http, CommandResponse commandResponse, String type) throws IOException
    {
        OutputStream resBody = http.getResponseBody();
        http.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        BaseResponse response = new BaseResponse();
        response.type = type;
        response.hasError = false;
        response.response = commandResponse;
        String strResponse = new Gson().toJson(response);
        writeString(strResponse,resBody);
        resBody.close();
    }

    /** Sets responses with server errors. Exception e contains the exact error
     */
    protected void serverError(HttpExchange httpExch, Exception e){
        try{
            OutputStream resBody = httpExch.getResponseBody();
            httpExch.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);


            BaseResponse errorResponse = makeErrorResponse(e);
            String strResponse = new Gson().toJson(errorResponse);

            writeString(strResponse, resBody);
            resBody.close();
        }
        catch(IOException er){
            e.printStackTrace();
            httpExch.close();
        }
    }

    /** Sets responses with bad url paths
     */
    protected void badURLError(HttpExchange httpExch){
        try {
            httpExch.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            writeString(new Gson().toJson(new Exception("error: bad url")), httpExch.getResponseBody());
            httpExch.getResponseBody().close();
        }
        catch (IOException er){
            httpExch.close();
        }
    }

    private BaseResponse makeErrorResponse(Exception e) {
        BaseResponse response = new BaseResponse();
        response.hasError = true;
        response.type = "string";
        response.errorText = e.getMessage();
        response.response = e;

        return response;
    }


    protected void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
    protected String readString(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

}
