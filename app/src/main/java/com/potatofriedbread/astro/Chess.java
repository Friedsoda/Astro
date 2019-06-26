package com.potatofriedbread.astro;

import android.widget.ImageView;

import java.io.Serializable;

public class Chess implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean flying, completed;
    private int nowPos, player, chessNum;
    private ImageView img;

    // Load chess image.
    public Chess(int player, int chessNum, ImageView img){
        reset();
        this.player = player;
        this.chessNum = chessNum;
        this.img = img;
    }

    public void reset(){
        completed = false;
        flying = false;
        nowPos = 0;
    }
    /*
    public void move(int posX, int posY){
        img.setX(Coordinate.getInstance().mapToScreenX(posX));
        img.setY(Coordinate.getInstance().mapToScreenY(posY));
    }*/

    public void move(int step){
        //max: 57
        int overflow = nowPos + step - Value.TERMINAL;
        if(overflow < 0){
            overflow = 0;
        }
        if(isFlying()){
            //move(Value.PATHS_X[player][nowPos + step - overflow], Value.PATHS_Y[player][nowPos + step - overflow]);
            nowPos += step - overflow;
        }
    }

    public int getX(){
        return Coordinate.getInstance().screenToMapX((int)img.getX());
    }

    public int getY(){
        return Coordinate.getInstance().screenToMapY((int)img.getY());
    }

    public int getPlayer(){
        return player;
    }

    public int getChessNum() {
        return chessNum;
    }

    public int getNowPos(){
        return nowPos;
    }

    public boolean isCompleted(){
        return completed;
    }

    public boolean isFlying() {
        return flying;
    }

    public void completeTour(){
        nowPos = 0;
        setFlying(false);
        setCompleted(true);
    }

    public void takeOff(){
        nowPos = 0;
        //move(Value.PATHS_X[player][nowPos], Value.PATHS_Y[player][nowPos]);
        setFlying(true);
    }

    public void killed(){
        nowPos = 0;
        setFlying(false);
        System.out.println("Chess are killed.");
    }

    public ImageView getImg(){
        return img;
    }

    public void setFlying(boolean value){
        flying = value;
    }

    public void setCompleted(boolean value){
        completed = value;
    }
}
