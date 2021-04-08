package com.example.baseballroulette;

import android.util.Log;

import java.util.Arrays;

public class GameState {
    public int homeNumRuns=0;
    public int awayNumRuns=0;
    public int[] baserunners =  {0,0,0};
    public int outs=0;
    public int inning = 1;
    public boolean homeBatting = false;
    public String lastHitType = "";
    public static GameState currentState = null;
    private GameState(){

    }

    public static GameState getCurrentGameState(){
        if (currentState == null){
            currentState = new GameState();
        }
        return currentState;
    }

    public void hitSingle(){
        boolean runnerAdded = false;
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]++;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            }
            if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 1;
                runnerAdded = true;
            }
        }
        Log.d("hitSingle", "Single Recorded");
        Log.d("Baserunners", Arrays.toString(baserunners));
    }

    public void hitDouble(){
        boolean runnerAdded = false;
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]+=2;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            }
            if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 2;
                runnerAdded = true;
            }
        }
        Log.d("hitDouble", "Double Recorded");
        Log.d("Baserunners", Arrays.toString(baserunners));
    }

    public void hitTriple(){
        boolean runnerAdded = false;
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]+=3;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            }
            if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 3;
                runnerAdded = true;
            }
        }
        Log.d("hitTriple", "Triple Recorded");
        Log.d("Baserunners", Arrays.toString(baserunners));
    }

    public void hitHomeRun(){
        for (int i = 0; i < baserunners.length; i++){
            if(baserunners[i] > 0){
                baserunners[i]+=4;
                if (baserunners[i] > 3){
                    addRun();
                    baserunners[i] = 0;
                }
            }
        }
        addRun();
        Log.d("hitHomeRun", "Home run Recorded");
        Log.d("Baserunners", Arrays.toString(baserunners));
    }

    public void addRun() {
        if (homeBatting){
            homeNumRuns++;
        } else {
            awayNumRuns++;
        }
        //homeScoreTxt.setText(runs);
        Log.d("addRun", "run added");
    }

    public void addOut(){
        outs++;
        if (outs > 2){
            changeInning();
            outs = 0;
        }
    }

    public void changeInning(){

        for (int i = 0; i < baserunners.length; i++){
            baserunners[i] = 0;
        }

        if (inning > 3){
            endGame();
        }
        if(homeBatting){
            homeBatting = false;
            inning++;
        } else {
            homeBatting = true;
        }
    }

    public void endGame(){

    }

    public boolean isHomeBatting() {
        return homeBatting;
    }

    public int getHomeNumRuns() {
        return homeNumRuns;
    }

    public int getAwayNumRuns() {
        return awayNumRuns;
    }

    public int getOuts() {
        return outs;
    }

    public int getInning() {
        return inning;
    }

    public String getLastHitType(){
        return lastHitType;
    }

    public void processHitType(String result){
        lastHitType = result;
        if (result.equals("Single")){
            hitSingle();
            Log.d("getSingle", "Single received");
        } else if (result.equals("Double")){
            hitDouble();
            Log.d("getDouble", "double received");
        } else if (result.equals("Triple")){
            hitTriple();
            Log.d("getTriple", "triple received");
        } else if (result.equals("Home Run")){
            hitHomeRun();
            Log.d("getHomeRun", "home run received");
        } else if (result.equals("Out")){
            addOut();
            Log.d("getOut", "Out received");
        }
    }

    public boolean isRunnerOnBase(int baseNum){
        for (int index = 0; index < baserunners.length; index++){
            if (baseNum == baserunners[index]){
                return true;
            }
        }
        return false;
    }
}
