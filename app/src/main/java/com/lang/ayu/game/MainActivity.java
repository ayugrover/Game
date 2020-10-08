package com.lang.ayu.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 - yellow, 1 = red
    int activePlayer =0;
    // 2 means unplayed
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void  playAgain(View view)
    {
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer =0;
        gameIsActive = true;
        for(int i=0;i<gameState.length;i++)
        {
           gameState[i] =2;
        }
        System.out.println("!!!Print again clicked!!!!!!!!");
        android.support.v7.widget.GridLayout grid = (GridLayout) findViewById(R.id.myGrid);
        for(int i =0; i<grid.getChildCount();i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
            System.out.println("Print again ??????");
        }

    }
    public void  dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        System.out.println("Print  $$$$##");
        if(gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
        }
        counter.setMaxHeight(70);
        counter.animate().translationYBy(1000f).rotation(180).setDuration(300);

        for(int[] winningPos : winningPos)
        {
            if(gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[1]] == gameState[winningPos[2]] && gameState[winningPos[0]] != 2){
                String player ="Red";
                if(gameState[winningPos[0]] == 0)
                        player = "Blue";

                 TextView winText = (TextView)findViewById(R.id.playAgainText);
                 winText.setText(player + " has Won !!");
                 gameIsActive = false;
                 LinearLayout playAgain = (LinearLayout)findViewById(R.id.playAgainLayout);
                 playAgain.setVisibility(view.VISIBLE);
            }else
            {
                boolean gameIsOver = true;
                for(int state : gameState)
                {
                    if(state == 2)
                        gameIsOver =false;
                }
                if(gameIsOver)
                {
                    TextView winText = (TextView)findViewById(R.id.playAgainText);
                    winText.setText("It's a Draw !!");
                    gameIsActive = false;
                    LinearLayout playAgain = (LinearLayout)findViewById(R.id.playAgainLayout);
                    playAgain.setVisibility(view.VISIBLE);
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
