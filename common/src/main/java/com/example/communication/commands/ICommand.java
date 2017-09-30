package com.example.communication.commands;

/**
 * Created by Mitchell Foote on 9/30/2017.
 */
public interface ICommand
{
    public void execute() throws Exception;
    public CommandResponse translate();
}
