package com.workingtitle.makeit;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

public class SearchOptions extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);
    }

    // I think ideally this should be a listener but it works ¯\_(ツ)_/¯
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCookTime:
                DialogFragment newTPFragment = new OptionsCookTimeDialogFragment();
                newTPFragment.show(getFragmentManager(),"TimePicker");
                break;
            case R.id.tvPortions:
                DialogFragment dialog = new OptionsPortionsDialogFragment();
                dialog.show(getFragmentManager(), "Portions");
        }
    }

    // TextView with context menu
    private TextView tvName;

    // Time
    private static int hour = 0;
    private static int minute = 0;
    private static int days = 0;

    public static void setTime(int d, int h, int m){
        hour = h;
        minute = m;
        days = d;
    }
    public static int getDay(){
        return days;
    }
    public static int getHour(){
        return hour;
    }
    public static int getMinute(){
        return minute;
    }

    // Number Picker
    private static int portions = 1;
    public static void setPortions(int p){
        portions = p;
    }
    public static int getPortions(){
        return portions;
    }
}