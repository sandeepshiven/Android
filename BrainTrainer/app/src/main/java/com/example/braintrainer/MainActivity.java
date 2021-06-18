package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    androidx.gridlayout.widget.GridLayout gridLayout;
    TextView scoreView;
    TextView timerText;
    TextView quesText;
    TextView scoreKeeper;
    Button playagainButton;
    Button startbutton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    CountDownTimer countDownTimer;
    boolean isGameOn = false;
    boolean isRunning = false;
    boolean fix = true;
    Random rand = new Random();
    int ans;
    int turns = 0;
    int correct = 0;


    public void setupGame(){
        if(!(isGameOn)){
            linearLayout.setVisibility(View.INVISIBLE);
            gridLayout.setVisibility(View.INVISIBLE);
            scoreView.setVisibility(View.INVISIBLE);
            playagainButton.setVisibility(View.INVISIBLE);
            startbutton.setVisibility(View.VISIBLE);
            scoreKeeper.setText("0/0");
            isGameOn = !(isGameOn);
            turns = 0;
            correct = 0;
        }
        else{
            linearLayout.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.VISIBLE);
            startbutton.setVisibility(View.INVISIBLE);
            isGameOn = !(isGameOn);
        }
    }

    public void goGame(View view){
        setupGame();
        startTimer();
    }

    public void playagain(View view){
        setupGame();
        countDownTimer.cancel();
    }
    public void startTimer(){

        countDownTimer = new CountDownTimer(30000+100, 1000) {

            @Override
            public void onTick(long milsec){
                timerText.setText((int)milsec/1000 + "s");
                isRunning = true;

            }

            @Override
            public void onFinish() {
                timerText.setText(0 + "s");
                playagainButton.setVisibility(View.VISIBLE);
                isRunning = false;
                scoreView.setText("Your Score is: " + correct+"/"+turns);
                scoreView.setTextColor(Color.GRAY);
            }




        }.start();

        setRandomNumbers();

    }


    public void setRandomNumbers(){
        if(isRunning || fix) {
            int a = rand.nextInt(11);
            int b = rand.nextInt(11);
            ans = a+b;
            quesText.setText(a + " + " + b);
            int num1 = rand.nextInt(21);
            int num2 = rand.nextInt(21);
            int num3 = rand.nextInt(21);
            int num4 = rand.nextInt(21);
            int randbutton = rand.nextInt(4);
            button1.setTag(num1);
            button2.setTag(num2);
            button3.setTag(num3);
            button4.setTag(num4);
            button1.setText(String.valueOf(num1));
            button2.setText(String.valueOf(num2));
            button3.setText(String.valueOf(num3));
            button4.setText(String.valueOf(num4));
            if(randbutton == 0){
                button1.setTag(ans);
                button1.setText(String.valueOf(ans));
            }
            else if(randbutton==1){
                button2.setTag(ans);
                button2.setText(String.valueOf(ans));
            }
            else if(randbutton==2){
                button3.setTag(ans);
                button3.setText(String.valueOf(ans));
            }
            else{
                button4.setTag(ans);
                button4.setText(String.valueOf(ans));
            }
            fix = false;
            scoreKeeper.setText(correct + "/" + turns);

        }
    }

    public void buttonTapped(View view) {

        if (isRunning) {
            int tapped = Integer.parseInt(view.getTag().toString());
            if (tapped == ans) {
                scoreView.setText("Correct!!");
                scoreView.setTextColor(Color.GREEN);
                turns++;
                correct++;

            } else {
                scoreView.setText("Incorrect!!");
                scoreView.setTextColor(Color.RED);
                turns++;
            }
            setRandomNumbers();
            scoreView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearlayout);

        gridLayout = findViewById(R.id.gridLayout);

        scoreView = findViewById(R.id.scoreView);
        startbutton = findViewById(R.id.startbutton);

        playagainButton = findViewById(R.id.playagainButton);
        timerText = findViewById(R.id.timerText);
        quesText = findViewById(R.id.quesText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        scoreKeeper = findViewById(R.id.scorekeeper);

        setupGame();



    }
}