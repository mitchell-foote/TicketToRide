package com.example.fifteam.tickettoride.presenters.inGamePresenters;

import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.views.inGameViews.ChatFragment;
import com.example.gameModel.classes.ChatEntry;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kcwillmore on 10/31/17.
 */

public class ChatPresenter implements Observer{

    private ChatFragment view;

    public ChatPresenter(ChatFragment view) {
        this.view = view;
        ClientGamePresenterFacade.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        view.updateAdapter();
    }

    public List<ChatEntry> getChats(){
        return ClientGamePresenterFacade.getInstance().getChatHistory();
    }

    public void sendChat(String toSend){
        ClientGamePresenterFacade.getInstance().postChat(toSend);
    }
}
