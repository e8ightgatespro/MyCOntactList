package com.example.garrett.mycontactlist;


import android.content.ContentValues;
import android.content.Context;
import java.sql.SQLException;
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
}
