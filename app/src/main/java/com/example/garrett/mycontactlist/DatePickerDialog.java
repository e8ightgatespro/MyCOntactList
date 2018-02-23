package com.example.garrett.mycontactlist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import java.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerDialog extends DialogFragment {

    public interface SaveDateListener {
        void didFinishDatePickerDialog(Calendar selectedTime);
    }

    public DatePickerDialog() {
        //empty contrsuctor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
        final View view = inflater.inflate(R.layout.select_date, container);

        getDialog().setTitle("Select Date");

        final DatePicker dp = (DatePicker) view.findViewById(R.id.birthdayPicker);

        Button saveButton = (Button) view.findViewById(R.id.buttonSelect);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar selectedTime = Calendar.getInstance();
                selectedTime.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
                saveItem(selectedTime);
            }
        });
        Button cancelButton = (Button) view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }
    private void saveItem(Calendar selectedTime) {
        SaveDateListener activity = (SaveDateListener) getActivity();
        activity.didFinishDatePickerDialog((selectedTime));
        getDialog().dismiss();
    }
}