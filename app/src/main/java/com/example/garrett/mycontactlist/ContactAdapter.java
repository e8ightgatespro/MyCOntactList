package com.example.garrett.mycontactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Garrett.Voss on 3/6/2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    private ArrayList<Contact> items;
    private Context adapterContext;

    public ContactAdapter(Context context, ArrayList<Contact> items) {
        super(context, R.layout.list_item, items);
        adapterContext = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        try {
            Contact contact = items.get(position);

            if(v == null) {
                LayoutInflater vi = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item, null);
            }

            TextView contactName = (TextView) v.findViewById(R.id.textContactName);
            TextView contactPhone = (TextView) v.findViewById(R.id.textPhoneNumber);
            Button b = (Button) v.findViewById(R.id.buttonDeleteContact);
            contactName.setText(contact.getContactName());
            contactPhone.setText(contact.getPhonenumber());
            b.setVisibility(View.INVISIBLE);
        }
        catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return v;
    }
}
