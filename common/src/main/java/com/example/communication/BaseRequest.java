package com.example.communication;

/**
 * Created by Mitchell Foote on 9/22/2017.
 */
public class BaseRequest
{
    public String type;
    public Object body;
    public BaseRequest(){}
    public BaseRequest(String type, Object body){
        this.type = type;
        this.body = body;
    }
}
