package com.potatofriedbread.astro;

import android.os.Handler;

public class AudioHandler extends Handler {

    private GameController gameController;

    public AudioHandler(GameController gameController){
        super();
        this.gameController = gameController;
    }

    public void playFlyAudio(){
        super.post(new Runnable(){
            @Override
            public void run(){

            }
        });
    }

    public void playKillAudio(){
        super.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
