package com.potatofriedbread.astro;

import android.os.Handler;
import android.os.Message;

public class ControlHandler extends Handler {

    private GameController gameController;

    public ControlHandler(GameController gameController){
        super();
        this.gameController = gameController;
    }

    public void getAIRoll(){
        super.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameController.roll();
            }
        }, 1000);
    }

    public void getAIMove(final int rollNum){
        super.postDelayed(new Runnable() {
            @Override
            public void run() {
                AgentAI.go(gameController.getChessList(), gameController.getWhoseTurn(), rollNum);
            }
        }, 1000);
    }

    public void changeStateToMove(final int rollNum){
        super.post(new Runnable() {
            @Override
            public void run() {
                gameController.setState(Value.STATE_MOVE_CHESS);
                if(gameController.getConfigHelper().getPlayerType(gameController.getWhoseTurn()) == Value.LOCAL_HUMAN){
                    //Do nothing.
                } else if(gameController.getConfigHelper().getPlayerType(gameController.getWhoseTurn()) == Value.AI){
                    if(gameController.getConfigHelper().getGameType() == Value.LOCAL) {
                        gameController.getControlHandler().getAIMove(rollNum);
                    } else if(gameController.getConfigHelper().getGameType() == Value.ONLINE_LAN){
                        if(gameController.getConfigHelper().isHost()) {
                            gameController.getLANHandler().postAIMoveLAN(rollNum);
                        } else{
                            gameController.getLANHandler().getOnlineMoveLAN();
                        }
                    } else if(gameController.getConfigHelper().getGameType() == Value.ONLINE_SERVER){
                        gameController.getServerHandler().getOnlineMoveServer();
                    }
                } else if(gameController.getConfigHelper().getPlayerType(gameController.getWhoseTurn()) == Value.ONLINE_HUMAN) {
                    if(gameController.getConfigHelper().getGameType() == Value.ONLINE_LAN){
                        if(gameController.getConfigHelper().isHost()) {
                            //Do nothing.
                        } else {
                            gameController.getLANHandler().getOnlineMoveLAN();
                        } // else {}// do nothing
                    } else if(gameController.getConfigHelper().getGameType() == Value.ONLINE_SERVER){
                        gameController.getServerHandler().getOnlineMoveServer();
                    }
                }
            }
        });
    }
}
