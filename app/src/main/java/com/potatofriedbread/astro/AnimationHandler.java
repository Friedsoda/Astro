package com.potatofriedbread.astro;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;

public class AnimationHandler extends Handler {

    private GameController gameController;
    //TODO: Maybe animator classes that inherit ObjectAnimator.
    private ObjectAnimator rollAnimator, takeOffAnimator, moveAnimator, jumpAnimator, killAnimator, terminateAnimator;

    public AnimationHandler(GameController gameController){
        super();
        this.gameController = gameController;
        //TODO: Load animators.
    }

    //骰子动画
    public void postRollAnimation(final int rollNum){
        gameController.increaseAnimationCount();
        super.post(new Runnable() {
            @Override
            public void run() {
                //TODO: Add attributes.
                rollAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ConfigHelper configHelper = gameController.getConfigHelper();
                        int whoseTurn = gameController.getWhoseTurn();
                        if(configHelper.getPlayerType(whoseTurn) == Value.LOCAL_HUMAN){
                            if(!gameController.canMove())gameController.turnEnd();
                            else{
                                gameController.getControlHandler().changeStateToMove(rollNum);
                            }
                        }
                    }
                });
                rollAnimator.start();
            }
        });
    }

    //起飞动画
    public void postTakeOffAnimator(final Chess chess){
        gameController.increaseAnimationCount();
        super.post(new Runnable(){
            @Override
            public void run(){
                //TODO: Add attributes.
                takeOffAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        System.out.println("Take off animation complete.");
                        gameController.decreaseAnimationCount();
                        gameController.turnEnd();
                    }
                });
                takeOffAnimator.start();
            }
        });
    }

    //沿着路线移动的动画
    public void postMoveAnimation(final Chess chess, final int from, final int step){
        gameController.increaseAnimationCount();
        super.post(new Runnable(){
            @Override
            public void run(){
                //TODO: Add attributes. 注意from + step > TERMINAL时会有前进+回退的动作
                moveAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        gameController.decreaseAnimationCount();
                        System.out.println("Moving animation complete.");
                        gameController.chessStatusJudge(chess);
                        if(gameController.noAnimationLeft()){
                            gameController.turnEnd();
                        }
                    }
                });
                moveAnimator.start();
            }
        });
    }

    //直线移动动画，适用于跳或飞
    public void postJumpAnimation(final Chess chess, final int from, final int to, final int jumpNum){
        gameController.increaseAnimationCount();
        super.post(new Runnable(){
            @Override
            public void run(){
                //TODO: Add attributes.
                jumpAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        gameController.decreaseAnimationCount();
                        if(from == Value.FLY_POINT){
                            gameController.flyKillJudge(chess);
                        }
                        final int newJumpNum = jumpNum - 1;
                        gameController.killJudge(chess);
                        if(newJumpNum > 0) {
                            if(from == Value.FLY_POINT){
                                gameController.chessJump(chess, newJumpNum); // 飞完再跳
                            } else if(to == Value.FLY_POINT){
                                gameController.chessFly(chess, newJumpNum); // 跳完再飞
                            } // else no new jump
                        }
                        if(gameController.noAnimationLeft()){
                            gameController.turnEnd();
                        }
                    }
                });
                jumpAnimator.start();
            }
        });
    }

    //击杀动画，chess为被击杀的棋子
    public void postKillAnimation(final Chess chess, final int from){
        gameController.increaseAnimationCount();
        super.post(new Runnable(){
            @Override
            public void run(){
                //TODO: Add attributes.
                killAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        gameController.decreaseAnimationCount();
                        if(gameController.noAnimationLeft()){
                            gameController.turnEnd();
                        }
                    }
                });
                killAnimator.start();
            }
        });
    }

    //到达终点动画
    public void postTerminateAnimation(final Chess chess, int from){
        gameController.increaseAnimationCount();
        super.post(new Runnable(){
            @Override
            public void run(){
                //TODO: Add attributes.
                terminateAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        gameController.decreaseAnimationCount();
                        //if(gameController.noAnimationLeft()){
                        if(!gameController.gameOverJudge()){
                            gameController.turnEnd();
                        }
                        //}
                    }
                });
                terminateAnimator.start();
                if(gameController.noAnimationLeft()){
                    gameController.turnEnd();
                }
            }
        });
    }
}
