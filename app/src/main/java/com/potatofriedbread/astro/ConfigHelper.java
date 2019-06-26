package com.potatofriedbread.astro;

public class ConfigHelper {
    private int localHumanNum, onlineHumanNum, AINum;
    private int gameType, hostPlayer;
    private int playerType[] = new int[4];
    private boolean host;

    public ConfigHelper(int gameType, int hostPlayer){
        this.gameType = gameType;
        this.hostPlayer = hostPlayer;
        if(gameType == Value.ONLINE_LAN){
            host = true;
        } else{
            host = false;
        }
        reset();
    }

    public void changePlayerType(int player, int type){
        if(type == Value.LOCAL_HUMAN){
            localHumanNum++;
        } else if(type == Value.ONLINE_HUMAN){
            onlineHumanNum++;
        } else if(type == Value.AI){
            AINum++;
        }
        int currentType = playerType[player];
        playerType[player] = type;
        if(currentType == Value.LOCAL_HUMAN){
            localHumanNum--;
        } else if(currentType == Value.ONLINE_HUMAN){
            onlineHumanNum--;
        } else if(currentType == Value.AI){
            AINum--;
        }
    }

    public void setHost(boolean value){
        host = value;
    }

    public boolean isHost(){
        return host;
    }

    public void setGameType(int value){
        gameType = value;
    }

    public int getGameType(){
        return gameType;
    }

    public int getPlayerType(int player){
        return playerType[player];
    }

    public int getHostPlayer(){
        return hostPlayer;
    }

    public void reset(){
        if(gameType == Value.ONLINE_LAN){
            localHumanNum =  1;
            onlineHumanNum = 3;
            AINum = 0;
            playerType[Value.RED] = Value.ONLINE_HUMAN;
            playerType[Value.YELLOW] = Value.ONLINE_HUMAN;
            playerType[Value.BLUE] = Value.ONLINE_HUMAN;
            playerType[Value.GREEN] = Value.ONLINE_HUMAN;
            playerType[hostPlayer] = Value.LOCAL_HUMAN;
        } else if(gameType == Value.LOCAL){
            localHumanNum = 1;
            onlineHumanNum = 0;
            AINum = 3;
            playerType[Value.RED] = Value.AI;
            playerType[Value.YELLOW] = Value.AI;
            playerType[Value.BLUE] = Value.AI;
            playerType[Value.GREEN] = Value.AI;
            playerType[hostPlayer] = Value.LOCAL_HUMAN;
        } else if(gameType == Value.ONLINE_SERVER){
            localHumanNum =  1;
            onlineHumanNum = 3;
            AINum = 0;
            playerType[Value.RED] = Value.ONLINE_HUMAN;
            playerType[Value.YELLOW] = Value.ONLINE_HUMAN;
            playerType[Value.BLUE] = Value.ONLINE_HUMAN;
            playerType[Value.GREEN] = Value.ONLINE_HUMAN;
        }
    }
}
