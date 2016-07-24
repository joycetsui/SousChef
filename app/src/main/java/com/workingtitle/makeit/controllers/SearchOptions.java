package com.workingtitle.makeit.controllers;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.workingtitle.makeit.R;

public class SearchOptions extends AppCompatActivity {
    
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

        // Get options data
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {

            // Cook Time values
            hour = b.getInt("ctHour");
            minute = b.getInt("ctMin");

            // Set TextView of values
            TextView tvCookTime = (TextView) findViewById(R.id.tvCookTimeDisplay);
            if (hour == -1 && minute == -1) {
                tvCookTime.setText("-");
            }
            else {
                if (hour == -1) {
                    hour = 0;
                }
                if (minute == -1) {
                    minute = 0;
                }
                tvCookTime.setText(Integer.toString(hour) + " hr, " + Integer.toString(minute) + " min");
            }

            // Portions Values
            portions = b.getInt("portions");
            TextView tvPortion = (TextView) findViewById(R.id.tvPortionsDisplay);
            if (portions == -1) {
                tvPortion.setText("-");
            }
            else {
                tvPortion.setText(Integer.toString(portions));
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent args = new Intent();
        args.putExtra("ctHour", hour);
        args.putExtra("ctMin", minute);
        args.putExtra("portions", portions);
        setResult(RESULT_OK, args);

        super.onBackPressed();
    }

    public void onClick(View v) {
        Bundle args = new Bundle();
        switch (v.getId()){
            case R.id.tvCookTimeText:
                DialogFragment cookTimeDialog = new OptionsCookTimeDialogFragment();
                args.putInt("hour", hour);
                args.putInt("min", minute);
                cookTimeDialog.setArguments(args);
                cookTimeDialog.show(getFragmentManager(),"TimePicker");
                break;
            case R.id.tvPortionsText:
                DialogFragment portionsDialog = new OptionsPortionsDialogFragment();
                args.putInt("portions", portions);
                portionsDialog.setArguments(args);
                portionsDialog.show(getFragmentManager(), "Portions");
                break;
        }
    }

    // TextView with context menu
    private TextView tvName;

    // Time
    int hour;
    int minute;
    int day;

    public void onCookTimeOK(String cookTime) {
        String delims = "[:]";
        String[] tokens = cookTime.split(delims);
        hour = Integer.parseInt(tokens[0]);
        minute = Integer.parseInt(tokens[1]);
        TextView tvCookTime = (TextView) findViewById(R.id.tvCookTimeDisplay);
        tvCookTime.setText(tokens[0] + " h " + tokens[1] + " m");
    }

    // Number Picker

    int portions;
    public void onPortionsOK(int p) {
        portions = p;
        TextView tvPortion = (TextView) findViewById(R.id.tvPortionsDisplay);
        tvPortion.setText(Integer.toString(p));
    }

}