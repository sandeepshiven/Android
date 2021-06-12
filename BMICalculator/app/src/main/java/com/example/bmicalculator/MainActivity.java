package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Metrics for calculation
    String[] weight_metrics = { "Kg", "Lbs." };
    String[] height_metrics = { "Meter", "Feet" };

    public void clickFunction(View view){
        // getting selection from weight spinner
        Spinner wspinner = (Spinner)findViewById(R.id.weightSpinner);
        String weightSpinnerText = wspinner.getSelectedItem().toString();

        // getting weight value
        EditText weightText = findViewById(R.id.weight);
        Double weight = Double.parseDouble(weightText.getText().toString());

        // getting selection from  weight spinner
        Spinner hspinner = (Spinner)findViewById(R.id.heightSpinner);
        String heightSpinnerText = hspinner.getSelectedItem().toString();

        // getting height value
        EditText heightText = findViewById(R.id.height);
        Double height = Double.parseDouble(heightText.getText().toString());

        // getting result and category text fields
        TextView result = (TextView)findViewById(R.id.result);
        TextView category = (TextView)findViewById(R.id.category);

        // converting into universal metrics
        if(heightSpinnerText == "Feet"){
            height *= 0.3048;
        }
        if(weightSpinnerText == "Lbs."){
            weight *= 0.453592;
        }

        double bmi = weight/(height*height);

        result.setText("Your BMI is: " + String.format("%.2f" ,bmi));

        // category assignment
        if(bmi < 16) {
            category.setText("Category: Severe Thinness");
        }
        else if(bmi < 17){
            category.setText("Category: Moderate Thinness");
        }
        else if(bmi < 18.5){
            category.setText("Category: Mild Thinness");
        }
        else if(bmi < 25){
            category.setText("Category: Normal");
        }
        else if(bmi < 30){
            category.setText("Category: Overweight");
        }
        else if(bmi < 35){
            category.setText("Category: Obese Class I");
        }
        else if(bmi < 40){
            category.setText("Category: Obese Class II");
        }
        else{
            category.setText("Category: Obese Class III");
        }


//        Log.i("height", String.valueOf(height));
//        Log.i("weight", String.valueOf(weight));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting weight spinner
        Spinner wspin = (Spinner) findViewById(R.id.weightSpinner);
        ArrayAdapter<String> wadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weight_metrics);
        wadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wspin.setAdapter(wadapter);

        // Setting height spinner
        Spinner hspin = (Spinner) findViewById(R.id.heightSpinner);
        ArrayAdapter<String> hadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, height_metrics);
        hadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hspin.setAdapter(hadapter);
    }
}