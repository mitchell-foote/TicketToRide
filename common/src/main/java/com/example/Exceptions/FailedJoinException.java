package com.example.Exceptions;

/**
 * Created by Mitchell Foote on 9/24/2017.
 */
public class FailedJoinException extends Exception
{
    public FailedJoinException(){
        super();
    }

    public FailedJoinException(String message){
        super(message);
    }
}
