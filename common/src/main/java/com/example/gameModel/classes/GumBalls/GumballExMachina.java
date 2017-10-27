package com.example.gameModel.classes.GumBalls;

/**
 * Created by Mitchell Foote on 10/23/2017.
 */

public class GumballExMachina
{
    int money = 0;
    int gumballs = 0;
    boolean hasQuarter = false;
    State currentState;

}

abstract class State
{
    GumballExMachina holder;
    State(){}
    State(GumballExMachina pointer){
        holder = pointer;
    }
    void addGumballs(int Count)
    {

    }
    void insertQuarter()
    {

    }
    void removeQuarter()
    {

    }
    void turnHandle(){}

}

class NoBallsNoQuarter extends State{
    private static NoBallsNoQuarter instance;
    private NoBallsNoQuarter(GumballExMachina machina){
        holder = machina;
    }
    public static NoBallsNoQuarter getReference(GumballExMachina machina){
        if(instance == null){
            instance = new NoBallsNoQuarter(machina);
        }
        return instance;
    }
    @Override
    void addGumballs(int Count){
        holder.gumballs =+ Count;
        holder.currentState = YesBallsNoQuarter.getReference(holder);
    }
    @Override
    void insertQuarter(){
        holder.hasQuarter = true;
        holder.currentState = NoBallsYesQuarter.getReference(holder);
    }
    @Override
    void removeQuarter() {
        return;
    }
    @Override
    void turnHandle(){
        return;
    }

}

class YesBallsYesQuarter extends State{
    private static YesBallsYesQuarter instance;
    private YesBallsYesQuarter(GumballExMachina machina){
        holder = machina;
    }
    public static YesBallsYesQuarter getReference(GumballExMachina machina){
        if(instance == null){
            instance = new YesBallsYesQuarter(machina);
        }
        return instance;
    }
    @Override
    void addGumballs(int Count){
        holder.gumballs += Count;
    }
    @Override
    void insertQuarter(){
        return;
    }
    @Override
    void removeQuarter()
    {
        holder.hasQuarter = false;
        holder.currentState = YesBallsNoQuarter.getReference(holder);
    }
    @Override
    void turnHandle(){
        holder.gumballs -= 1;
        holder.money += 1;
        if (holder.gumballs == 0){
            holder.currentState = NoBallsNoQuarter.getReference(holder);
        } else {
            holder.currentState = YesBallsNoQuarter.getReference(holder);
        }
    }
}

class NoBallsYesQuarter extends State {
    private static NoBallsYesQuarter instance;
    private NoBallsYesQuarter(GumballExMachina machina){
        holder = machina;
    }
    public static NoBallsYesQuarter getReference(GumballExMachina machina){
        if(instance == null){
            instance = new NoBallsYesQuarter(machina);
        }
        return instance;
    }
    @Override
    void addGumballs(int Count){
        holder.gumballs =+ Count;
        holder.currentState = YesBallsYesQuarter.getReference(holder);
    }
    @Override
    void insertQuarter(){
        return;
    }
    @Override
    void removeQuarter() {
        holder.hasQuarter = false;
        holder.currentState = NoBallsNoQuarter.getReference(holder);
    }
    @Override
    void turnHandle(){
        holder.hasQuarter = false;
        holder.money += 1;
        holder.currentState = NoBallsNoQuarter.getReference(holder);
    }
}

class YesBallsNoQuarter extends State {
    private static YesBallsNoQuarter instance;
    private YesBallsNoQuarter(GumballExMachina machina){
        holder = machina;
    }
    public static YesBallsNoQuarter getReference(GumballExMachina machina){
        if(instance == null){
            instance = new YesBallsNoQuarter(machina);
        }
        return instance;
    }
    @Override
    void addGumballs(int Count){
        holder.gumballs += Count;
    }
    @Override
    void insertQuarter(){
        holder.hasQuarter = true;
        holder.currentState = YesBallsYesQuarter.getReference(holder);
    }
    @Override
    void removeQuarter() {
        return;
    }
    @Override
    void turnHandle(){
        return;
    }
}
