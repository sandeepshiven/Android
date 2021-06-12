package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean flag = true;
//==============================================================================================

//  FADING ANIMATION

    /*public void fade(View view){

        ImageView spidey = findViewById(R.id.spidey);
        ImageView ironman = findViewById(R.id.ironman);

        if(flag){
            spidey.animate().alpha(0f).setDuration(2000);
            ironman.animate().alpha(1f).setDuration(2000);
            flag = false;
        }
        else{
            spidey.animate().alpha(1f).setDuration(2000);
            ironman.animate().alpha(0f).setDuration(2000);
            flag = true;
        }

    }*/
//============================================================================================

// TRANSLATION
//
    public void  translate(View view){

        ImageView spidey = findViewById(R.id.spidey);
        ImageView ironman = findViewById(R.id.ironman);

        if(flag){
            ironman.setTranslationX(-2000f);
            spidey.animate().translationXBy(2000f).setDuration(500);
            ironman.animate().translationXBy(2000f).setDuration(500);
            flag = false;
        }
        else{
            spidey.setTranslationX(-2000f);
            ironman.animate().translationXBy(2000f).setDuration(500);
            spidey.animate().translationXBy(2000f).setDuration(500);

            flag = true;
        }

    }
//==========================================================================================================

    // ROTATION

//    public void  rotate(View view) {
//
//
//        ImageView spidey = findViewById(R.id.spidey);
//        //ImageView ironman = findViewById(R.id.ironman);
//        spidey.setRotation(0f);
//
//        spidey.animate().rotation(180000f).setDuration(2000);
//
//        Log.i("hi", String.valueOf(spidey.getRotation()));
//
//    }

//============================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ironman = findViewById(R.id.ironman);
        ironman.setTranslationX(-2000f);

    }
}