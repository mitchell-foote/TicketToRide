package com.example.Exceptions;

/**
 * Created by Mitchell Foote on 9/24/2017.
 */
public class FailedLoginException extends Exception
{
    public FailedLoginException(){
        super();
    }

    public FailedLoginException(String message){
        super(message);
    }
}
