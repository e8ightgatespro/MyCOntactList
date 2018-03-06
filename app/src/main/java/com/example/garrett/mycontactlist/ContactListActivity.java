package com.example.garrett.mycontactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initListButton();
        initMapButton();
        initSettingsButton();
        initItemClick();
        ContactDataSource ds = new ContactDataSource(this);


        try {
            ds.open();
            contacts = ds.getContacts();
            ds.close();
            ListView listView = (ListView) findViewById(R.id.lvContacts);
            listView.setAdapter(new ContactAdapter(this, contacts));
        }
        catch(Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
    }

    private void initItemClick() {
        ListView listView = (ListView) findViewById(R.id.lvContacts);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Contact selectedContact = contacts.get(position);
                Intent intent = new Intent(ContactListActivity.this, ContacctActivity.class);
                intent.putExtra("contactid", selectedContact.getContactID());
                startActivity(intent);
            }
        });
    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.ImageButtonList); //create a variable to hold the imageButton object
        ibList.setEnabled(false);
//        ibList.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ContactListActivity.this, ContactListActivity.class); // sets an intent that comes from ContactActivity, and sends
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
//                startActivity(intent); // executes the intent
//            }
//        });
    }
    private void initMapButton() {
        ImageButton ibMap = (ImageButton) findViewById(R.id.ImageButtonMap); //create a variable to hold the imageButton object
        ibMap.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, ContactMapActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });
    }
    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.ImageButtonSettings); //create a variable to hold the imageButton object
//        ibSettings.setEnabled(false);
        ibSettings.setOnClickListener(new View.OnClickListener() { // set a listener to wait for a click on the image to occur
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, ContactSettingsActivity.class); // sets an intent that comes from ContactActivity, and sends
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // the user to the ContactListActivity, and also clears any pre-existing version of that page
                startActivity(intent); // executes the intent
            }
        });
    }
}
