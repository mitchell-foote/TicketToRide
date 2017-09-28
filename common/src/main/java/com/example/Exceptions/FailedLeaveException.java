package com.example.Exceptions;

/**
 * Created by Mitchell Foote on 9/24/2017.
 */
public class FailedLeaveException extends Exception
{
    public FailedLeaveException(){
        super();
    }

    public FailedLeaveException(String message){
        super(message);
    }
}
