package com.example.garrett.mycontactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ContactMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_map);
        initListButton();
        initMapButton();
        initSettingsButton();
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
