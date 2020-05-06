package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //0=yellow,1=red
    int activePlayer = 0;
    boolean gameIsActive = true;
    int count = 0;
    int countCounters;
    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            count++;
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(700);

        }
        if((gameState[0]==gameState[1]&&gameState[1]==gameState[2]&&gameState[2]!=2)||(gameState[3]==gameState[4]&&gameState[4]==gameState[5]&&gameState[5]!=2)||(gameState[6]==gameState[7]&&gameState[7]==gameState[8]&&gameState[8]!=2)||(gameState[0]==gameState[3]&&gameState[3]==gameState[6]&&gameState[6]!=2)||(gameState[1]==gameState[4]&&gameState[4]==gameState[7]&&gameState[7]!=2)||(gameState[2]==gameState[5]&&gameState[5]==gameState[8]&&gameState[8]!=2)||
                (gameState[0]==gameState[4]&&gameState[4]==gameState[8]&&gameState[8]!=2)||(gameState[2]==gameState[4]&&gameState[4]==gameState[6]&&gameState[2]!=2)){
            gameIsActive = false;
            String winner = "Red";
            if (activePlayer==1) {
                winner = "yellow";
            }
            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
            winnerMessage.setText(winner + " has won");
            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            layout.setVisibility(View.VISIBLE);
        }
        else{
            if(count==9){gameIsActive=false;
                boolean gameIsOver=true;
                for(int counterState:gameState){
                    if(counterState==2)
                        gameIsOver=false;

                }
                if(gameIsOver){
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText("its a draw");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);


                }
            }


        }
    }


        public void playAgain (View view){
            count = 0;
            gameIsActive = true;
            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            layout.setVisibility(View.INVISIBLE);
            activePlayer = 0;
            //2 means unplayed
            for (int i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }

        }

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }

