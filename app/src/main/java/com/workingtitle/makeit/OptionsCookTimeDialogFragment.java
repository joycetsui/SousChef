package com.workingtitle.makeit;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.app.DialogFragment;
import android.app.Dialog;
import java.util.Calendar;
import android.widget.TimePicker;


public class OptionsCookTimeDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Minutes
        final NumberPicker min = new NumberPicker(getActivity());
        min.setMaxValue(59); // max value 59
        min.setMinValue(0);   // min value 0
        min.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });
        min.setValue(SearchOptions.getMinute());
        min.setWrapSelectorWheel(true);

        // Hours
        final NumberPicker hr = new NumberPicker(getActivity());
        hr.setMaxValue(23); // max value 23
        hr.setMinValue(0);   // min value 0
        hr.setValue(SearchOptions.getHour());
        hr.setWrapSelectorWheel(true);

        // Days
        final NumberPicker day = new NumberPicker(getActivity());
        day.setMaxValue(10); // max value 10
        day.setMinValue(0);   // min value 0
        day.setValue(SearchOptions.getDay());
        day.setWrapSelectorWheel(true);

        LinearLayout LL = new LinearLayout(getActivity());
        LL.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams parentNP = new LinearLayout.LayoutParams(50, 50);
        parentNP.gravity = Gravity.CENTER;

        int margin = 110;
        int width = 200;

        LinearLayout.LayoutParams minParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
     //   minParam.weight = 1;
        minParam.width= width;
        minParam.leftMargin = margin;
        minParam.rightMargin = margin;

        LinearLayout.LayoutParams hrParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
     //   hrParam.weight = 1;
        hrParam.width= width;
        hrParam.leftMargin = margin;
        hrParam.rightMargin = margin;

        LinearLayout.LayoutParams dayParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
       // dayParam.weight = 1;
        dayParam.width= width;
        dayParam.leftMargin = margin;
        dayParam.rightMargin = margin;

        LL.setLayoutParams(parentNP);
        LL.addView(day,dayParam);
        LL.addView(hr,hrParam);
        LL.addView(min,minParam);

        builder.setTitle(R.string.portions)
                .setView(LL)
                .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        SearchOptions.setTime(day.getValue(),hr.getValue(),min.getValue());
                    }
                })
                .setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        final NumberPicker picker = new NumberPicker(getActivity());
        picker.setMinValue(0);
        picker.setMaxValue(5);


        // Create the AlertDialog object and return it
        return builder.create();
    }
}