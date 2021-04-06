package com.example.baseballroulette;

public class GameState {
    public int runs=0;
    public int[] baserunners =  {0,0,0};
    public int outs=0;
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
            } else if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 1;
                runnerAdded = true;
            }
        }
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
            } else if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 2;
                runnerAdded = true;
            }
        }
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
            } else if(runnerAdded == false && baserunners[i] == 0){
                baserunners[i] = 3;
                runnerAdded = true;
            }
        }
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
            addRun();
        }
    }

    public void addRun() {
        runs++;
        //homeScoreTxt.setText(runs);
    }

    public void addOut(){
        outs++;
        /*if (outs > 2){
            endGame();
        }*/
    }

    public int getRuns() {
        return runs;
    }

    public int getOuts() {
        return outs;
    }

    public void getHitResult(String result){
        if (result == "Single"){
            hitSingle();
        } else if (result == "Double"){
            hitDouble();
        } else if (result == "Triple"){
            hitTriple();
        } else if (result == "Home Run"){
            hitHomeRun();
        } else if (result == "Out"){
            addOut();
        }
    }
}
