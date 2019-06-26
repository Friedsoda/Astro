package com.potatofriedbread.astro;

import android.os.Handler;

public class ServerHandler extends Handler {
    private GameController gameController;

    public ServerHandler(GameController gameController){
        super();
        this.gameController = gameController;
    }

    public void newRoom(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }

    public void joinIn(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }
    /*
    public void getAIRollServer(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }*/

    public void getOnlineRollServer(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }

    public void postOnlineRollServer(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }

    public void getOnlineMoveServer(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }

    public void postOnlineMoveServer(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }

    public void postOnlineTurnEndServer(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }

    public void getWhostTurnServer(){
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }
}
