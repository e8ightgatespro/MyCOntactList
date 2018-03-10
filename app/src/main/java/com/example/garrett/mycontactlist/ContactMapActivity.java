package com.example.garrett.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class ContactMapActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener gpsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_map);
        initListButton();
        initMapButton();
        initSettingsButton();
        initGetLocationButton();
    }

    @Override
    public void onPause() {
        super.onPause();
        try{
            locationManager.removeUpdates(gpsListener);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void initGetLocationButton() {
        Button locationButton = findViewById(R.id.buttonGetLocation);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*EditText editAddress = findViewById(R.id.editAddress);
                EditText editCity = findViewById(R.id.editCity);
                EditText editState = findViewById(R.id.editState);
                EditText editZipcode = findViewById(R.id.editZipcode);

                String address = editAddress.getText().toString() +", " +
                        editCity.getText().toString() + ", " +
                        editState.getText().toString() + ", " +
                        editZipcode.getText().toString();

                List<Address> addresses = null;
                Geocoder geo = new Geocoder(ContactMapActivity.this);
                try{
                    addresses = geo.getFromLocationName(address, 1);
                }
                catch(IOException e) {
                    e.printStackTrace();
                }

                TextView txtLatitude = findViewById(R.id.textLatitude);
                TextView txtLongitude = findViewById(R.id.textLongitude);

                txtLatitude.setText("Latitude: " + String.valueOf(addresses.get(0).getLatitude()));
                txtLongitude.setText("Longitude: " + String.valueOf(addresses.get(0).getLongitude()));*/

                /* the above code is for grabbing coordinates using geocoding and an address */

                try {
                    locationManager = (LocationManager) getBaseContext().getSystemService(Context.LOCATION_SERVICE);
                    gpsListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            TextView txtLatitude = findViewById(R.id.textLatitude);
                            TextView txtLongitude = findViewById(R.id.textLongitude);
                            TextView txtAccuracy = findViewById(R.id.textAccuracy);

                            txtLatitude.setText("Latitude: " + String.valueOf(location.getLatitude()));
                            txtLongitude.setText("Longitude: " + String.valueOf(location.getLongitude()));
                            txtAccuracy.setText("Accuracy: " +  String.valueOf(location.getAccuracy()));
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {}

                        @Override
                        public void onProviderEnabled(String s) {}

                        @Override
                        public void onProviderDisabled(String s) {}
                    };

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,gpsListener);
                }
                catch(Exception e) {
                    Toast.makeText(getBaseContext(), "Error, location not available", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.ImageButtonList); //create a variable to hold the imageButton object
        ibList.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactMapActivity.this, ContactListActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });
    }
    private void initMapButton() {
        ImageButton ibMap = (ImageButton) findViewById(R.id.ImageButtonMap);//create a variable to hold the imageButton object
        ibMap.setEnabled(false);
        /*ibMap.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactMapActivity.this, ContactMapActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });*/
    }
    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.ImageButtonSettings); //create a variable to hold the imageButton object
        ibSettings.setEnabled(false);
        ibSettings.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactMapActivity.this, ContactSettingsActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });
    }
}
