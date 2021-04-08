package com.example.baseballroulette;

import android.util.Log;

import java.util.Arrays;

public class GameState {

    public static final int MAX_INNINGS = 1;
    public int homeNumRuns=0;
    public int awayNumRuns=0;
    public int[] baserunners =  {0,0,0};
    public int outs=0;
    public int inning = 1;
    public boolean homeBatting = false;
    public String lastHitType = "";
    public static GameState currentState = null;
    public int[] homeHitTypes = new int[4];
    public int[] awayHitTypes = new int[4];
    public double homeABs;
    public double awayABs;
    public int homeHits;
    public int awayHits;
    public double homeBA;
    public double awayBA;


    public int[] getHomeHitTypes() {
        return homeHitTypes;
    }

    public int[] getAwayHitTypes() {
        return awayHitTypes;
    }

    public int getHomeHits() {
        return homeHits;
    }

    public int getAwayHits() {
        return awayHits;
    }

    public double getHomeBA() {
        return homeBA;
    }

    public double getAwayBA() {
        return awayBA;
    }

    public double getHomeABs() {
        return homeABs;
    }

    public double getAwayABs() {
        return awayABs;
    }

    private GameState(){
        homeNumRuns = 0;
        awayNumRuns = 0;
        outs= 0;
        inning = 1;
        homeBatting = false;
        lastHitType = "";
        homeABs = 0;
        homeHits = 0;
        homeBA = 0.0;
        awayABs = 0;
        awayHits = 0;
        awayBA = 0.0;
        for (int i = 0; i < baserunners.length; i++){
            baserunners[i] = 0;
        }
        for (int i = 0; i < homeHitTypes.length; i++){
            homeHitTypes[i] = 0;
            awayHitTypes[i] = 0;
        }

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


        if(homeBatting){
            homeBatting = false;
            inning++;
        } else {
            homeBatting = true;
        }
        if (inning > MAX_INNINGS){
            endGame();
        }
    }

    public void endGame(){
        homeBA = homeHits/homeABs;
        awayBA = awayHits/awayABs;
        currentState = new GameState();
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
        if (homeBatting){
            homeABs++;
            if(!(result.equals("Out"))){
                homeHits++;
            }
        } else {
            awayABs++;
            if(!(result.equals("Out"))){
                awayHits++;
            }
        }
        lastHitType = result;
        if (result.equals("Single")){
            hitSingle();
            Log.d("getSingle", "Single received");
            if(homeBatting){
                homeHitTypes[0]++;
            } else{
                awayHitTypes[0]++;
            }
        } else if (result.equals("Double")){
            hitDouble();
            Log.d("getDouble", "double received");
            if(homeBatting){
                homeHitTypes[1]++;
            } else{
                awayHitTypes[1]++;
            }
        } else if (result.equals("Triple")){
            hitTriple();
            Log.d("getTriple", "triple received");
            if(homeBatting){
                homeHitTypes[2]++;
            } else{
                awayHitTypes[2]++;
            }
        } else if (result.equals("Home Run")){
            hitHomeRun();
            Log.d("getHomeRun", "home run received");
            if(homeBatting){
                homeHitTypes[3]++;
            } else{
                awayHitTypes[3]++;
            }
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
