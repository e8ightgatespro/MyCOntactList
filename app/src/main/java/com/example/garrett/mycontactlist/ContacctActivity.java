package com.example.garrett.mycontactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.ToggleButton;


public class ContacctActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacct);
        initListButton(); // initializes the imageButton so that it can be clicked
        initMapButton();
        initSettingsButton();
        initToggleButton();
        setForEditing(false);

    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.ImageButtonList); //create a variable to hold the imageButton object
        ibList.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContacctActivity.this, ContactListActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });
    }
    private void initMapButton() {
        ImageButton ibMap = (ImageButton) findViewById(R.id.ImageButtonMap); //create a variable to hold the imageButton object
        ibMap.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContacctActivity.this, ContactMapActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });
    }
    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.ImageButtonSettings); //create a variable to hold the imageButton object
        ibSettings.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContacctActivity.this, ContactSettingsActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });
    }

    private void initToggleButton() {
        final ToggleButton editToggle = (ToggleButton) findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
               setForEditing(editToggle.isChecked());
            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editName = (EditText) findViewById(R.id.editName);
        EditText editAddress = (EditText) findViewById(R.id.editAddress);
        EditText editCity = (EditText) findViewById(R.id.editCity);
        EditText editState = (EditText) findViewById(R.id.editState);
        EditText editZipCode = (EditText) findViewById(R.id.editZipcode);
        EditText editPhone = (EditText) findViewById(R.id.editHome);
        EditText editCell = (EditText) findViewById(R.id.editCell);
        EditText editEmail = (EditText) findViewById(R.id.editEMail);
        Button buttonChange = (Button) findViewById(R.id.btnBirthday);
        Button buttonSave = (Button) findViewById(R.id.buttonSave);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipCode.setEnabled(enabled);
        editPhone.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonSave.setEnabled(enabled);
        buttonChange.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
        else {
            ScrollView s = (ScrollView) findViewById(R.id.scrollView);
            s.clearFocus();
        }


    }
}
