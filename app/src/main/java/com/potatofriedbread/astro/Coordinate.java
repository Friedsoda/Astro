package com.potatofriedbread.astro;

import android.support.percent.PercentRelativeLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;

public class Coordinate {

    private GameActivity gameActivity;
    private float leftX, topY, mapWidth, mapHeight, chessWidth;
    private static Coordinate instance = null;

    public static void createInstance(GameActivity gameActivity){
        instance = new Coordinate(gameActivity);
    }

    public static Coordinate getInstance(){
        return instance;
    }

    private Coordinate(final GameActivity gameActivity){
        this.gameActivity = gameActivity;
        GameController.getInstance().increaseLoadCount();
        final ImageView imageView = gameActivity.getMap();
        final Toolbar toolbar = gameActivity.getToolbar();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                GameController.getInstance().increaseLoadCount();
                leftX = imageView.getX();
                topY = imageView.getY();
                mapWidth = imageView.getWidth();
                mapHeight = imageView.getHeight();
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mapHeight > mapWidth) {
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageView.getLayoutParams());
                            layoutParams.width = (int)mapWidth;
                            layoutParams.height = (int)mapWidth;
                            layoutParams.topMargin = (int)topY;
                            layoutParams.leftMargin = (int)leftX;
                            imageView.setLayoutParams(layoutParams);
                            mapHeight = mapWidth;
                        } else if(mapWidth > mapHeight){
                            WindowManager windowManager = gameActivity.getWindowManager();
                            DisplayMetrics outMetrics = new DisplayMetrics();
                            windowManager.getDefaultDisplay().getMetrics(outMetrics);
                            float windowWidth = outMetrics.widthPixels;
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageView.getLayoutParams());
                            layoutParams.width = (int)mapHeight;
                            layoutParams.height = (int)mapHeight;
                            layoutParams.topMargin = (int)topY;
                            layoutParams.leftMargin = (int)((windowWidth - mapHeight) / 2);
                            imageView.setLayoutParams(layoutParams);
                            mapWidth = mapHeight;
                            leftX = (windowWidth - mapHeight) / 2;
                        }
                        float toolbarHeight = toolbar.getHeight();
                        topY += toolbarHeight;
                        chessWidth = mapWidth / 18;
                        System.out.println("Measured: " + leftX + " " + topY + " " + mapWidth + " " + mapHeight);
                        System.out.println("ChessWidth: " + chessWidth);
                        /*
                        System.out.println("Image: " + imageView.getWidth() + " " + imageView.getHeight());*/
                        GameController.getInstance().loadRoll();
                        GameController.getInstance().loadChess();
                        GameController.getInstance().decreaseLoadCount();
                        if(GameController.getInstance().noLoadingLeft()){
                            gameActivity.gameStart();
                        }
                    }
                });
                GameController.getInstance().decreaseLoadCount();
                if(GameController.getInstance().noLoadingLeft()){
                    gameActivity.gameStart();
                }
            }
        });
    }

    public float getChessWidth(){
        return chessWidth;
    }

    public int screenToMapX(float screenX){
        return (int)((screenX - leftX) / (chessWidth / 2));
    }

    public int screenToMapY(float screenY){
        return (int)((screenY - topY) / (chessWidth / 2));
    }

    public float mapToScreenX(int mapX){
        return mapX * chessWidth / 2 + leftX;
    }

    public float mapToScreenY(int mapY){
        return mapY * chessWidth / 2 + topY;
    }

    public boolean clickTheChess(Chess chess, View view){
        int viewX = (int)screenToMapX(view.getX()), viewY = (int)screenToMapY(view.getY());
        return chess.getX() == viewX &&
                chess.getY() == viewY;
    }
}
