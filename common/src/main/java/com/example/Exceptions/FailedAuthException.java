package com.example.Exceptions;

/**
 * Created by Mitchell Foote on 9/24/2017.
 */
public class FailedAuthException extends Exception
{
    public FailedAuthException(){
        super();
    }

    public FailedAuthException(String message){
        super(message);
    }
}
