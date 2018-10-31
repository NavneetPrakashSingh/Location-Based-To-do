Add the following code snippet if you want to call ChooseLocation.java to select location using Google Api
MainActivity.xml code

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="navigate"
        android:text="Select Location"
        tools:layout_editor_absoluteX="109dp"
        tools:layout_editor_absoluteY="96dp" />

    <TextView
        android:id="@+id/LocationText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Selected Location Displayed here" />

MainActivity.java code

    public void navigate(View view){
        startActivity(new Intent(this,ChooseLocation.class));
    }

Use the following code if you are trying to take the values from an activity

        Boolean checkPermission = Location.CheckPermission(this);
        if (!checkPermission){
            Location.requestLocation(this);
        }else{
            Location locationValues = new Location();
            locationValues.getLastKnownLocation(this);

            String longitudeValues = locationValues.getLongitude();

            TextView lastLocation = findViewById(R.id.current_location);
            lastLocation.setText(String.valueOf(longitudeValues));
        }
