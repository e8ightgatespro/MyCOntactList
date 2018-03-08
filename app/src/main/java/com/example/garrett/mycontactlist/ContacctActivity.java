package com.example.garrett.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.text.format.DateFormat;
import java.util.Calendar;


public class ContacctActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {

    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacct);
        initListButton(); // initializes the imageButton so that it can be clicked
        initMapButton();
        initSettingsButton();
        initToggleButton();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            initContact(extras.getInt("contactid"));
        }
        else {
            currentContact = new Contact();
        }
        setForEditing(false);
        initChangeDateButton();
        initTextChangedEvents();
        initSaveButton();
        initContact(-1);
    }

    private void initContact(int id) {
        ContactDataSource ds = new ContactDataSource(ContacctActivity.this);
        try {
            ds.open();
            currentContact = ds.getSpecificContact(id);
            ds.close();
        }
        catch(Exception e) {
            Toast.makeText(this, "Load COntact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);
        EditText editPhonenumber = findViewById(R.id.editHome);
        EditText editCellnumber = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEMail);
        TextView birthday = findViewById(R.id.textBirthday);

        editName.setText(currentContact.getContactName());
        editAddress.setText(currentContact.getStreetaddress());
        editCity.setText(currentContact.getCity());
        editState.setText(currentContact.getState());
        editZipcode.setText(currentContact.getZipcode());
        editPhonenumber.setText(currentContact.getPhonenumber());
        editCellnumber.setText(currentContact.getCellnumber());
        editEmail.setText(currentContact.geteMail());
        birthday.setText(DateFormat.format("MM/dd/yyyy", currentContact.getBirthday()).toString());

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = (EditText) findViewById(R.id.editName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = (EditText) findViewById(R.id.editAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText editCity = (EditText) findViewById(R.id.editCity);
        imm.hideSoftInputFromWindow(editCity.getWindowToken(), 0);
        EditText editState = (EditText) findViewById(R.id.editState);
        imm.hideSoftInputFromWindow(editState.getWindowToken(), 0);
        EditText editZipcode = (EditText) findViewById(R.id.editZipcode);
        imm.hideSoftInputFromWindow(editZipcode.getWindowToken(), 0);
        EditText editPhonenumber = (EditText) findViewById(R.id.editHome);
        imm.hideSoftInputFromWindow(editPhonenumber.getWindowToken(), 0);
        EditText editCellnumber = (EditText) findViewById(R.id.editCell);
        imm.hideSoftInputFromWindow(editCellnumber.getWindowToken(), 0);
        EditText editEmail = (EditText) findViewById(R.id.editEMail);
        imm.hideSoftInputFromWindow(editEmail.getWindowToken(), 0);
    }

    private void initSaveButton() {
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccesful = false;
                hideKeyboard();
                ContactDataSource ds = new ContactDataSource(ContacctActivity.this);
                try {

                    ds.open();

                    if(currentContact.getContactID() == -1) {
                        wasSuccesful = ds.insertContact(currentContact);
                        if(wasSuccesful) {
                            int newId = ds.getLastContactId();
                            currentContact.setContactID(newId);
                        }
                    }
                    else {
                        wasSuccesful = ds.updateContact(currentContact);
                    }
                    ds.close();
                }
                catch(Exception e) {
                    wasSuccesful = false;
                }

                if(wasSuccesful) {
                    ToggleButton editToggle = (ToggleButton) findViewById(R.id.toggleButtonEdit);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
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

    private void initTextChangedEvents() {
        final EditText etContactName = (EditText) findViewById(R.id.editName);
         etContactName.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                 currentContact.setContactName(etContactName.getText().toString());

             }
         });

        final EditText etContactStreetAddress = (EditText) findViewById(R.id.editAddress);
        etContactStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setStreetaddress(etContactStreetAddress.getText().toString());

            }
        });

        final EditText etContactCity = (EditText) findViewById(R.id.editCity);
        etContactCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setCity(etContactCity.getText().toString());

            }
        });

        final EditText etContactState = (EditText) findViewById(R.id.editState);
        etContactState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setState(etContactState.getText().toString());

            }
        });

        final EditText etContactZipcode = (EditText) findViewById(R.id.editZipcode);
        etContactZipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setZipcode(etContactZipcode.getText().toString());

            }
        });

        final EditText etContactPhonenumber = (EditText) findViewById(R.id.editHome);
        etContactPhonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setPhonenumber(etContactPhonenumber.getText().toString());

            }
        });

        final EditText etContactCellnumber = (EditText) findViewById(R.id.editCell);
        etContactCellnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setCellnumber(etContactCellnumber.getText().toString());

            }
        });

        final EditText etContactEmail = (EditText) findViewById(R.id.editEMail);
        etContactEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.seteMail(etContactEmail.getText().toString());

            }
        });
         etContactPhonenumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
         etContactCellnumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

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
            s.fullScroll(ScrollView.FOCUS_UP);
            s.clearFocus();
        }


    }

    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        TextView birthDay = (TextView) findViewById(R.id.textBirthday);
        birthDay.setText(DateFormat.format("MM/dd/yyyy", selectedTime.getTimeInMillis()).toString());
        currentContact.setBirthday(selectedTime);
    }

    private void initChangeDateButton() {
        Button changeDate = (Button) findViewById(R.id.btnBirthday);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm, "Date Pick");
            }
        });
    }
}
