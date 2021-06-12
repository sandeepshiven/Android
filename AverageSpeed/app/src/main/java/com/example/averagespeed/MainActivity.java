package com.example.averagespeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView oldView;
    TextView currView;
    TextView resultView;
    double wayLatitude = 0.0, wayLongitude = 0.0;
    LocationCallback mLocationCallback;
    LocationRequest locationRequest;
    static Location startPoint;
    static Location endPoint;
    Button mapButton;

    boolean oldNewSwitch = false;
    boolean taskComplete = false;

    public void getMap(View view){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();

    }

    public void getAverageSpeed(View view) {

        if (taskComplete) {
            double distance = startPoint.distanceTo(endPoint);
            resultView.setText(String.valueOf(distance / 60) + " m/s");
            Log.i("Distance", String.valueOf(distance));
        }
    }


    public void startTimer(View view){

        if(!oldNewSwitch && !taskComplete) {

            final int[] time = {60};
            final TextView timerView = findViewById(R.id.timerView);

            CountDownTimer countDownTimer = new CountDownTimer(60000 + 200, 1000) {
                @Override
                public void onTick(long l) {
                    timerView.setText(String.valueOf(time[0]--) + 's');
                    oldNewSwitch = true;
                }

                @Override
                public void onFinish() {
                    timerView.setText(String.valueOf(time[0]) + 's');

                    fetchLastlocation();
                }
            }.start();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldView = findViewById(R.id.oldView);
        currView = findViewById(R.id.currentView);
        resultView = findViewById(R.id.resultView);
        mapButton = findViewById(R.id.mapButton);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        fetchLastlocation();





    }

    public void fetchLastlocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        Task task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object location) {
                Location currentLocation = null;
                if (location != null)
                    currentLocation = (Location) location;
                final Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> addressList =   geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(),1);
                    if(addressList != null && addressList.size() > 0){

                        if(!oldNewSwitch){
                            if(addressList.get(0).hasLatitude() && addressList.get(0).hasLongitude()){
                                mapButton.setVisibility(View.INVISIBLE);
                                startPoint = currentLocation;
                                oldView.setText("Latitude: " + addressList.get(0).getLatitude() + "\n" + "Longitude: " + addressList.get(0).getLongitude());
                            }
                        }else {
                            if (addressList.get(0).hasLatitude() && addressList.get(0).hasLongitude()) {
                                taskComplete = true;
                                mapButton.setVisibility(View.VISIBLE);
                                endPoint = currentLocation;
                                currView.setText("Latitude: " + addressList.get(0).getLatitude() + "\n" + "Longitude: " + addressList.get(0).getLongitude());
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }


        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastlocation();
                }
                break;
        }
    }



}