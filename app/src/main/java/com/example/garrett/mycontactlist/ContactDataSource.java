package com.example.garrett.mycontactlist;


import android.content.ContentValues;
import android.content.Context;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactDataSource {
    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    public ContactDataSource(Context context) {
        dbHelper = new ContactDBHelper((context));
    }

    public void open()throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertContact(Contact c) {
        boolean didSucceed = false;
        try {
            ContentValues initalValues = new ContentValues();
            initalValues.put("contactname", c.getContactName());
            initalValues.put("streetaddress", c.getStreetaddress());
            initalValues.put("city", c.getCity());
            initalValues.put("state", c.getState());
            initalValues.put("zipcode", c.getZipcode());
            initalValues.put("phonenumber", c.getPhonenumber());
            initalValues.put("cellnumber", c.getCellnumber());
            initalValues.put("email", c.geteMail());
            initalValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            didSucceed = database.insert("contact",null,initalValues) > 0;
        }
        catch(Exception e) {
            //Do nothing - will return false if there is an exception
        }

        return didSucceed;
    }

    public boolean updateContact(Contact c) {
        boolean didSucceed = false;

        try {
            long rowId = (long) c.getContactID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("contactname", c.getContactName());
            updateValues.put("streetaddress", c.getStreetaddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode",c.getZipcode());
            updateValues.put("phonennumber", c.getPhonenumber());
            updateValues.put("cellnumber", c.getCellnumber());
            updateValues.put("email", c.geteMail());
            updateValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            didSucceed = database.update("contact", updateValues, "_id=" + rowId,null) > 0;
        }
        catch(Exception e) {
            //Do nothing - will return false if there is an exception
        }

        return didSucceed;
    }

    public int getLastContactId() {
        int lastId = -1;
        try {
            String query = "Select MAX(_id) from contact";
            Cursor cursor = database.rawQuery(query,null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch(Exception e) {
            lastId = -1;
        }

        return lastId;
    }

    public ArrayList<String> getContactName() {
        ArrayList<String> contactNames = new ArrayList<>();
        try {
            String query = "Select contactname from contact";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                contactNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();

        }
        catch(Exception e) {
            contactNames = new ArrayList<String>();
        }

        return contactNames;
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try {
            String query = "SELECT *FROM contact";
            Cursor cursor = database.rawQuery(query,null);

            Contact newContact;
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                newContact = new Contact();
                newContact.setContactID(cursor.getInt(0));
                newContact.setContactName(cursor.getString(1));
                newContact.setStreetaddress(cursor.getString(2));
                newContact.setCity(cursor.getString(3));
                newContact.setState(cursor.getString(4));
                newContact.setZipcode(cursor.getString(5));
                newContact.setPhonenumber(cursor.getString(6));
                newContact.setCellnumber(cursor.getString(7));
                newContact.seteMail(cursor.getString(8));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.valueOf(cursor.getString(9)));
                newContact.setBirthday(calendar);
                contacts.add(newContact);
                cursor.moveToNext();
            }
            cursor.close();

        }
        catch(Exception e) {
            contacts = new ArrayList<Contact>();
        }
        return contacts;
    }
}
