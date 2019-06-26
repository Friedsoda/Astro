package com.potatofriedbread.astro;

public class AgentAI {
    public static void go(Chess[][] chessList, int player, int rollNum){
        Chess chess = chooseAChess(chessList, player, rollNum);
        GameController.getInstance().go(chess);
    }

    private static Chess chooseAChess(Chess[][] chessList, int player, int rollNum){
        Chess[] yourChess = chessList[player];
        if(Value.TAKE_OFF_NUM.contains(rollNum)){
            for(int i = 0; i < yourChess.length; ++i){
                if(!yourChess[i].isFlying()){
                    return yourChess[i];
                }
            }
        }
        for(int i = 0; i < yourChess.length; ++i){
            if(yourChess[i].getNowPos() + rollNum == Value.TERMINAL){
                return yourChess[i];
            }
        }
        for(int i = 0; i < yourChess.length; ++i){
            if(yourChess[i].isFlying() && Value.JUMP_POINT.contains(yourChess[i].getNowPos() + rollNum)){
                return yourChess[i];
            }
        }
        int maxIndex = 0, maxPos = 0;
        for(int i = 0; i < yourChess.length; ++i){
            if(yourChess[i].isFlying()){
                if(yourChess[i].getNowPos() >= maxPos){
                    maxPos = yourChess[i].getNowPos();
                    maxIndex = i;
                }
            }
        }
        return yourChess[maxIndex];
    }
}
