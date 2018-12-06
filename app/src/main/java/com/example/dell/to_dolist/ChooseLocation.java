package com.example.dell.to_dolist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class ChooseLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);
        
          if(checkNetworkAvailability(getApplicationContext())) {

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {
//                Intent previousIntent = new Intent(getApplicationContext(),MainActivity.class);
                Log.i("Location coordinates",String.valueOf(place.getLatLng()));
//                previousIntent.putExtra("Location",String.valueOf(place.getName()));
                //TODO : Add longitude and latitude here and send it to the previous activity
                finish();
            }

            @Override
            public void onError(Status status) {
                Log.i("Error in place",String.valueOf(status));
            }
        });
          }

        else
        {
            Toast.makeText(getApplicationContext(), "No Internet Connectivity for Location Feature", Toast.LENGTH_LONG).show();
        }
    }


    //Checking Internet connectivity is there or not
    public static boolean checkNetworkAvailability(Context context)
    {
        //return if connected
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}

