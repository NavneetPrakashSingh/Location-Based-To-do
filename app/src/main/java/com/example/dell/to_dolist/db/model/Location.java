package com.example.dell.to_dolist.db.model;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/*
* Sample test case:
* Input : source, context
* Output: returns longitude and latitude of current location
* */

public class Location extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    public String longitudeValue=null;
    public String latitudeValue=null;


    public static Boolean CheckPermission(Context contextFromActivity){
        Boolean decision = true;
        if (ActivityCompat.checkSelfPermission(contextFromActivity, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            decision = false;
        }else{
            decision = true;
        }
        return decision;
    }

    public static void requestLocation(Activity contextFromActivity) {
        ActivityCompat.requestPermissions(contextFromActivity,new String[]{ACCESS_FINE_LOCATION},1);
    }

    public String getLongitude(){
        return longitudeValue;
    }
    public String getLatitude(){
        return latitudeValue;
    }

    public void setLongitude(String longitude){
        longitudeValue = longitude;
    }
    public void setLatitude(String latitude){
        latitudeValue = latitude;
    }


    public void getLastKnownLocation(Activity contextFromActivity){
        try{

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(contextFromActivity);
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {

                @Override
                public void onSuccess(android.location.Location location) {
                    if (location != null) {
                        String returnValue = String.valueOf(location.getLongitude()+","+location.getLatitude());
                        setLatitude(String.valueOf(location.getLatitude()));
                        setLongitude(String.valueOf(location.getLatitude()));
                        //store the values in database and retrieve it whenever you want
                    }
                }
            });

        }catch (SecurityException ex){

        }
    }
}
