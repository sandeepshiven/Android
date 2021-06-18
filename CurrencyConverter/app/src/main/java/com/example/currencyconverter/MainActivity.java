package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertDollar(View view){
        EditText dollar = findViewById(R.id.amount);
        int rupee = Integer.parseInt(dollar.getText().toString());
        Toast.makeText(this, "Rs "+ String.format("%.2f", rupee*75.49), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}