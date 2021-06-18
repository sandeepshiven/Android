package com.example.braintrainer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playagainbutton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQues = 0;

    public void playAgain(View view){
        score = 0;
        numberOfQues = 0;
        timerTextView.setText("30s");
        resultTextView.setText("");
        pointsTextView.setText("0/0");
        playagainbutton.setVisibility(View.INVISIBLE);
        new CountDownTimer(3000+100, 1000){

            @Override
            public void onTick(long milsec){
                timerTextView.setText(String.valueOf(milsec/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your Score: " + score+"/"+numberOfQues);
                timerTextView.setText("0s");
                playagainbutton.setVisibility(View.VISIBLE);
            }
        }.start();

        generateQues();
    }


    public void generateQues(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(a + " + " + b);

        locationOfCorrectAnswer  = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for(int i=0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }



        button0.setText(answers.get(0).toString());
        button1.setText(answers.get(1).toString());
        button2.setText(answers.get(2).toString());
        button3.setText(answers.get(3).toString());

    }


    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;

            resultTextView.setText("Correct!");
        }
        else{

            resultTextView.setText("Wrong!");
        }
        numberOfQues++;
        pointsTextView.setText(score+"/"+numberOfQues);
        generateQues();
    }


    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playagainbutton = findViewById(R.id.playagainbutton);
        resultTextView = findViewById(R.id.resulttextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timertextView);
        sumTextView = findViewById(R.id.sumtextView);


        playAgain(playagainbutton);


    }
}