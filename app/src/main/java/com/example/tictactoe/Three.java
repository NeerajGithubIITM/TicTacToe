package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Three extends AppCompatActivity {

    /* Player Representation:
       0 - O ==> Player 1
       1 - X ==> Player 2  */

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    String[] compliments = {"Brilliant!!", "Amazing!!", "Fabulous!!", "Fantastic", "Superb!!", "Marvellous!!", "Excellent!!", "Genius!!"};

    /* State meanings:
       0 - O
       1 - X
       2 - Null  */

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    boolean gameActive = true;
    int tappedImage = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            Toast.makeText(this, "Game Over. Play Again to start a new game", Toast.LENGTH_LONG).show();
        }
        if ((gameState[tappedImage] == 1 || gameState[tappedImage]==0) && gameActive){
            Toast.makeText(this, "Please tap on an empty square", Toast.LENGTH_LONG).show();
        }
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationX(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.to);
                TextView status = findViewById(R.id.status);
                status.setText(" Player 2's turn");
                activePlayer = 1;
            }
            else {
                img.setImageResource(R.drawable.txfinal);
                TextView status = findViewById(R.id.status);
                status.setText(" Player 1's turn");
                activePlayer = 0;
            }
            img.animate().translationXBy(1000f).setDuration(300);
        }

        // Check if any player has won

        if(gameActive){
            for (int[] winpos : winPositions) {
                if (gameState[winpos[0]] == gameState[winpos[1]] &&
                        gameState[winpos[1]] == gameState[winpos[2]] &&
                        gameState[winpos[0]] != 2) {

                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(8);
                    Toast.makeText(this, compliments[randomInt], Toast.LENGTH_LONG).show();

                    // A player has won. Find out who

                    String winner;
                    if (gameState[winpos[0]] == 0) {
                        winner = "Game Over!! Player 1 has won!!";

                    } else {
                        winner = "Game Over!! Player 2 has won!!";
                    }
                    TextView status = findViewById(R.id.status);
                    status.setText(winner);
                    TextView playagain = findViewById(R.id.reset);
                    playagain.setText("Play Again!!");
                    gameActive = false;
                    break;
                }
            }

        }

        if(gameActive){
            drawCheck();
            count2 = 0;
        }

    }

    int count2 = 0;
    public void undo(View view){
        count2+=1;
        if(!gameActive){
            Toast.makeText(this, "Game Over. Play Again to start a new game", Toast.LENGTH_LONG).show();
        }
        if(count2 == 1 && gameActive){
            gameState[tappedImage] = 2;
            if (activePlayer == 0) {
                TextView status = findViewById(R.id.status);
                status.setText(" Player 2's turn");
                activePlayer = 1;
            }
            else {
                TextView status = findViewById(R.id.status);
                status.setText(" Player 1's turn");
                activePlayer = 0;
            }
            if(tappedImage == 0){
                ((ImageView) findViewById(R.id.o11)).setImageResource(0);
            }
            if(tappedImage == 1){
                ((ImageView) findViewById(R.id.o12)).setImageResource(0);
            }
            if(tappedImage == 2){
                ((ImageView) findViewById(R.id.o13)).setImageResource(0);
            }
            if(tappedImage == 3){
                ((ImageView) findViewById(R.id.o21)).setImageResource(0);
            }
            if(tappedImage == 4){
                ((ImageView) findViewById(R.id.o22)).setImageResource(0);
            }
            if(tappedImage == 5){
                ((ImageView) findViewById(R.id.o23)).setImageResource(0);
            }
            if(tappedImage == 6){
                ((ImageView) findViewById(R.id.o31)).setImageResource(0);
            }
            if(tappedImage == 7){
                ((ImageView) findViewById(R.id.o32)).setImageResource(0);
            }
            if(tappedImage == 8){
                ((ImageView) findViewById(R.id.o33)).setImageResource(0);
            }
        }
        else if(count2 != 1){
            Toast.makeText(this, "You can only undo the last move", Toast.LENGTH_LONG).show();
        }
    }

    public void drawCheck(){
        int i = 0;
        for(i = 0; i<gameState.length; i++){
            if(gameState[i] == 2){
                i = -1;
                break;
            }

        }
        if(i > 0){
            TextView status = findViewById(R.id.status);
            status.setText("It's a draw!!");
            TextView playagain = findViewById(R.id.reset);
            playagain.setText("Play Again!!");
            gameActive = false;
        }

    }

    int count = 1;
    public void resetGame(View view){
        if(!gameActive){
            count += 1;
            TextView gameCount = findViewById(R.id.gameCount);
            String gc = "Game " + count;
            gameCount.setText(gc);
        }
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.o11)).setImageResource(0);
        ((ImageView) findViewById(R.id.o12)).setImageResource(0);
        ((ImageView) findViewById(R.id.o13)).setImageResource(0);
        ((ImageView) findViewById(R.id.o21)).setImageResource(0);
        ((ImageView) findViewById(R.id.o22)).setImageResource(0);
        ((ImageView) findViewById(R.id.o23)).setImageResource(0);
        ((ImageView) findViewById(R.id.o31)).setImageResource(0);
        ((ImageView) findViewById(R.id.o32)).setImageResource(0);
        ((ImageView) findViewById(R.id.o33)).setImageResource(0);
        TextView resetgame = findViewById(R.id.reset);
        resetgame.setText("Reset Game");
        TextView status = findViewById(R.id.status);
        status.setText("");
        count2 = 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }
}
