package com.workingtitle.makeit.controllers;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.workingtitle.makeit.R;

public class SearchOptions extends AppCompatActivity {

    // Time
    int hour;
    int minute;
    int portions;

    NumberPicker hrPicker;
    NumberPicker minPicker;
    NumberPicker portionPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set toolbar back listener to go back to previous activity/fragment
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        int minutesMaxValue = 59;
        int minutesMinValue = 0;
        int hourMaxValue = 24;
        int hourMinValue = 0;
        int portionsMaxValue = 100;
        int portionsMinValue = 1;

        // Get options data
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            // Cook Time values
            hour = b.getInt("ctHour");
            minute = b.getInt("ctMin");

            if (hour == -1 && minute == -1) {
                hour = hourMaxValue;
                minute = minutesMaxValue;
            }
            else{
                if (hour == -1) {
                    hour = 0;
                }
                if (minute == -1) {
                    minute = 0;
                }
            }

            // Portions Values
            portions = b.getInt("portions");
            if (portions == -1){
                portions = portionsMinValue;
            }
        }

        /* Number Pickers */
        // Minutes
        minPicker = (NumberPicker)findViewById(R.id.cookTimePickerMinutes);
        minPicker.setMaxValue(minutesMaxValue); // max value 59
        minPicker.setMinValue(minutesMinValue);   // min value 0
        minPicker.setValue(minute);
        minPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });
        minPicker.setWrapSelectorWheel(true);

        // Hours
        hrPicker = (NumberPicker)findViewById(R.id.cookTimePickerHours);
        hrPicker.setMaxValue(hourMaxValue); // max value 23
        hrPicker.setMinValue(hourMinValue);   // min value 0
        hrPicker.setValue(hour);
        hrPicker.setWrapSelectorWheel(true);

        portionPicker = (NumberPicker)findViewById(R.id.portionsPicker);;
        portionPicker.setMaxValue(portionsMaxValue); // max value 100
        portionPicker.setMinValue(portionsMinValue);   // min value 1
        portionPicker.setValue(portions);
        portionPicker.setWrapSelectorWheel(true);
    }

    @Override
    public void onBackPressed() {
        Intent args = new Intent();

        hour = hrPicker.getValue();
        minute = minPicker.getValue();
        portions = portionPicker.getValue();

        args.putExtra("ctHour", hour);
        args.putExtra("ctMin", minute);
        args.putExtra("portions", portions);
        setResult(RESULT_OK, args);

        super.onBackPressed();
    }
}