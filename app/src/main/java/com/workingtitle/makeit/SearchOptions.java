package com.workingtitle.makeit;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
            day = b.getInt("ctDay");
            hour = b.getInt("ctHour");
            minute = b.getInt("ctMin");

            // Set TextView of values
            TextView tvCookTime = (TextView) findViewById(R.id.tvCookTimeDisplay);
            if (day == -1 && hour == -1 && minute == -1) {
                tvCookTime.setText("-");
            }
            else {
                if (day == -1) {
                    day = 0;
                }
                if (hour == -1) {
                    hour = 0;
                }
                if (minute == -1) {
                    minute = 0;
                }
                tvCookTime.setText(Integer.toString(day) + " d, " + Integer.toString(hour) + " hr, " + Integer.toString(minute) + " min");
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

    // Doesn't work, need to figure out how to pass values to fragment on back button
    @Override
    public void onBackPressed() {
        Intent args = new Intent();
        args.putExtra("ctDay", day);
        args.putExtra("ctHour", hour);
        args.putExtra("ctMin", minute);
        args.putExtra("portions", portions);
        setResult(RESULT_OK, args);

        super.onBackPressed();
    }

    // I think ideally this should be a listener but it works ¯\_(ツ)_/¯
    public void onClick(View v) {
        Bundle args = new Bundle();
        switch (v.getId()){
            case R.id.tvCookTimeText:
                DialogFragment cookTimeDialog = new OptionsCookTimeDialogFragment();
                args.putInt("day", day);
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
        day = Integer.parseInt(tokens[0]);
        hour = Integer.parseInt(tokens[1]);
        minute = Integer.parseInt(tokens[2]);
        TextView tvCookTime = (TextView) findViewById(R.id.tvCookTimeDisplay);
        tvCookTime.setText(tokens[0] + " d, " + tokens[1] + " hr, " + tokens[2] + " min");
    }

    // Number Picker

    int portions;
    public void onPortionsOK(int p) {
        portions = p;
        TextView tvPortion = (TextView) findViewById(R.id.tvPortionsDisplay);
        tvPortion.setText(Integer.toString(p));
    }

}