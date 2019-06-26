package com.potatofriedbread.astro;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.animation.ObjectAnimator;

public class GameActivity extends AppCompatActivity {

    private int localPlayer;
    private GameController gameController;
    private ImageButton play;
    private ImageButton charge;
    private ImageView map;
    private Toolbar toolbar;
    private PlayerView[] playerView;
    private Coordinate coordinate;
    private ImageView roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //加上其他Activity之后，这个函数会放在其他地方
        try {
            GameController.getInstance().initGame();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Fail to initialize game.");
        }
        localPlayer = Value.RED; // 以后是在房间里选

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        play = findViewById(R.id.play);
        charge = findViewById(R.id.charge);
        map = findViewById(R.id.map);
        roll = findViewById(R.id.roll);
        playerView = new PlayerView[]{
                new PlayerView(R.id.user2, R.id.icon2, R.id.username2, R.id.charge2),
                new PlayerView(R.id.user4, R.id.icon4, R.id.username4, R.id.charge4),
                new PlayerView(R.id.user3, R.id.icon3, R.id.username3, R.id.charge3),
                new PlayerView(R.id.user1, R.id.icon1, R.id.username1, R.id.charge1)
        };
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onePlay();
            }
        });
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCharge();
            }
        });
        gameController = GameController.getInstance();
        gameController.setGameActivity(this);
        Coordinate.createInstance(this);
        coordinate = Coordinate.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                //也许还能把玩家自动设为托管
                finish();
            case R.id.setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Toast.makeText(this, "music", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        coordinate = null;
    }

    public ImageView getMap(){
        return map;
    }
    public Toolbar getToolbar() { return toolbar; }

    private void onePlay(){
        Log.i("TEST", "Roll.");
        //TODO
//        //6张骰子图片的数组
//        Resources res = getResources();
//        TypedArray rollImage = res.obtainTypedArray(R.array.roll_images);
//        int len = rollImage.length();
//        int[] rollIds = new int[len];
//        for (int i = 0; i < len; i++){
//            rollIds[i] = rollImage.getResourceId(i, 0);//资源的id
//        }
        //在这里试一下骰子的animation，您可以把它移走zzzz
        ObjectAnimator.ofFloat(roll, "translationX", 0f, 600f)
                .setDuration(1000).start();
    }

    private void setCharge(){
        Log.i("TEST", "Charge.");
        int playerType = gameController.getConfigHelper().getPlayerType(localPlayer);
        if(playerType == Value.AI){
            gameController.getConfigHelper().changePlayerType(localPlayer, Value.LOCAL_HUMAN);
            playerView[localPlayer].charge.setText("");
        } else if(playerType == Value.LOCAL_HUMAN){
            gameController.getConfigHelper().changePlayerType(localPlayer, Value.AI);
            playerView[localPlayer].charge.setText("(托管中)");
        }
    }

    public void gameStart(){
        gameController.gameStart(Value.LOCAL, localPlayer);
    }

    public class PlayerView{
        public RelativeLayout user;
        public ImageView icon;
        public TextView username;
        public TextView charge;

        public PlayerView(int idUser, int idIcon, int idUsername, int idCharge){
            user = findViewById(idUser);
            icon = findViewById(idIcon);
            username = findViewById(idUsername);
            charge = findViewById(idCharge);
        }
    }
}
