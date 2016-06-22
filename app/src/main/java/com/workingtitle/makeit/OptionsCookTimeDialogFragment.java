package com.workingtitle.makeit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.app.DialogFragment;
import android.app.Dialog;


public class OptionsCookTimeDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
      //  LayoutInflater inflater = getActivity().getLayoutInflater();

        /* Number Pickers */
        // Minutes
        final NumberPicker minPicker = new NumberPicker(getActivity());
        minPicker.setMaxValue(59); // max value 59
        minPicker.setMinValue(0);   // min value 0
        minPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });
        minPicker.setValue(SearchOptions.getMinute());
        minPicker.setWrapSelectorWheel(true);

        // Hours
        final NumberPicker hrPicker = new NumberPicker(getActivity());
        hrPicker.setMaxValue(23); // max value 23
        hrPicker.setMinValue(0);   // min value 0
        hrPicker.setValue(SearchOptions.getHour());
        hrPicker.setWrapSelectorWheel(true);

        // Days
        final NumberPicker dayPicker = new NumberPicker(getActivity());
        dayPicker.setMaxValue(10); // max value 10
        dayPicker.setMinValue(0);   // min value 0
        dayPicker.setValue(SearchOptions.getDay());
        dayPicker.setWrapSelectorWheel(true);


        /* Building the layout of the dialog */

        int margin = 110;
        int width = 200;

        // Layout of number pickers
        LinearLayout pickerLL = new LinearLayout(getActivity());
        pickerLL.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LayoutParams.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams LLParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
     //   LLParam.weight = 1;
        LLParam.width= width;
        LLParam.leftMargin = margin;
        LLParam.rightMargin = margin;
        LLParam.gravity = Gravity.CENTER;

        pickerLL.setLayoutParams(LayoutParams);
        pickerLL.addView(dayPicker,LLParam);
        pickerLL.addView(hrPicker,LLParam);
        pickerLL.addView(minPicker,LLParam);

        Log.d("np", "np made");
        // Titles

        final TextView minText = new TextView(getActivity());
        minText.setText("MINS");
        minText.setGravity(Gravity.CENTER);
       // minText.setLayoutParams(LLParam);

        final TextView hrText = new TextView(getActivity());
        hrText.setText("HOURS");
        hrText.setGravity(Gravity.CENTER);
     //   hrText.setLayoutParams(LLParam);

        final TextView dayText = new TextView(getActivity());
        dayText.setText("DAYS");
        dayText.setGravity(Gravity.CENTER);
      //  dayText.setLayoutParams(LLParam);

        LinearLayout textLL = new LinearLayout(getActivity());
        textLL.setOrientation(LinearLayout.HORIZONTAL);

        textLL.setLayoutParams(LayoutParams);
        textLL.addView(dayText,LLParam);
        textLL.addView(hrText,LLParam);
        textLL.addView(minText,LLParam);


        LinearLayout LLParent = new LinearLayout(getActivity());
        LLParent.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams parent = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        parent.gravity = Gravity.CENTER;

        LLParent.setLayoutParams(parent);
        LLParent.addView(textLL);
        LLParent.addView(pickerLL);

        builder.setTitle(R.string.cookTime)
                .setView(LLParent)

                .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        SearchOptions.setTime(dayPicker.getValue(),hrPicker.getValue(),minPicker.getValue());
                    }
                })
                .setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}