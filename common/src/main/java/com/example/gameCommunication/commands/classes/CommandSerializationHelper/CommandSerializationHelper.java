package com.example.gameCommunication.commands.classes.CommandSerializationHelper;

import com.example.gameCommunication.commands.classes.commandData.client.AddDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddFaceUpTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.AddTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.ClaimRouteClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.EndGameClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.LastRoundClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.LongestTrainRouteSwitchClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.NextTurnClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.PostMessageClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.ReturnDestinationCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.SetupTrainCardsClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.client.SwapTrainCardClientCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.AddDestinationCardCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.AddFaceUpTrainCardCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.AddTrainCarCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.ClaimRouteCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.EndTurnCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.GetCommandListCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.PostMessageCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.ReturnDestinationCardCommandData;
import com.example.gameCommunication.commands.classes.commandData.server.SwapTrainCardCommandData;
import com.example.gameCommunication.commands.classes.containers.CommandContainer;
import com.google.gson.Gson;

/**
 * Created by Mitchell Foote on 10/28/2017.
 */

public class CommandSerializationHelper
{
    private static CommandSerializationHelper instance;
    public static CommandSerializationHelper getInstance(){
        if(instance == null){
            instance = new CommandSerializationHelper();
        }
        return instance;
    }
    private CommandSerializationHelper(){

    }
    public CommandContainer DeSerializeClient(CommandContainer cc, boolean isClient){
        switch(cc.getType()){
            case AddDestination:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), AddDestinationCardClientCommandData.class);
                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), AddDestinationCardCommandData.class);
                }
                break;
            }
            case AddTrainCard:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), AddTrainCardClientCommandData.class);

                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), AddTrainCarCommandData.class);

                }
                break;
            }
            case AddFaceUpTrain:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), AddFaceUpTrainCardClientCommandData.class);

                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), AddFaceUpTrainCardCommandData.class);
                }
                break;
            }
            case PostMessage:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), PostMessageClientCommandData.class);

                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), PostMessageCommandData.class);
                }
                break;
            }
            case ReturnDestinationCard: {
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), ReturnDestinationCardClientCommandData.class);

                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), ReturnDestinationCardCommandData.class);

                }
                break;
            }
            case GetCommands:{
                cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), GetCommandListCommandData.class);
                break;
            }
            case NextOrEndTurn:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), NextTurnClientCommandData.class);

                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), EndTurnCommandData.class);
                }
                break;
            }
            case SwapTrainCards:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), SwapTrainCardClientCommandData.class);
                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), SwapTrainCardCommandData.class);
                }
                break;
            }
            case SetupTrainCards:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), SetupTrainCardsClientCommandData.class);
                }
                break;
            }
            case LastRound:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), LastRoundClientCommandData.class);
                }
                break;
            }
            case LongestTrainSwitch: {
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), LongestTrainRouteSwitchClientCommandData.class);
                }
                break;
            }
            case ClaimRoute:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), ClaimRouteClientCommandData.class);
                }
                else {
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), ClaimRouteCommandData.class);
                }
                break;
            }
            case EndGame:{
                if(isClient){
                    cc.Data = new Gson().fromJson(new Gson().toJson(cc.Data), EndGameClientCommandData.class);
                }
                break;
            }
            default:{
                return null;
            }
        }
        return cc;
    }
}
