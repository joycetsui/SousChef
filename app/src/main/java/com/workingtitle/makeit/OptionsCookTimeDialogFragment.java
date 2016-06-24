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

        int nd = getArguments().getInt("day");
        if (nd == -1) {
            nd = 0;
        }
        int nh = getArguments().getInt("hour");
        if (nh == -1) {
            nh = 0;
        }
        int nm = getArguments().getInt("min");
        if (nm == -1) {
            nm = 0;
        }
        /** The following bit programmatically creates a view. **/

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
        minPicker.setValue(nm);
        minPicker.setWrapSelectorWheel(true);

        // Hours
        final NumberPicker hrPicker = new NumberPicker(getActivity());
        hrPicker.setMaxValue(23); // max value 23
        hrPicker.setMinValue(0);   // min value 0
        hrPicker.setValue(nh);
        hrPicker.setWrapSelectorWheel(true);

        // Days
        final NumberPicker dayPicker = new NumberPicker(getActivity());
        dayPicker.setMaxValue(10); // max value 10
        dayPicker.setMinValue(0);   // min value 0
        dayPicker.setValue(nd);
        dayPicker.setWrapSelectorWheel(true);

        /* Building the layout of the dialog */
        int margin = 30;
        int width = 150;

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
        LLParam.width= width;
        LLParam.leftMargin = margin;
        LLParam.rightMargin = margin;
        LLParam.gravity = Gravity.CENTER;

        pickerLL.setLayoutParams(LayoutParams);
        pickerLL.addView(dayPicker,LLParam);
        pickerLL.addView(hrPicker,LLParam);
        pickerLL.addView(minPicker,LLParam);

        Log.d("np", "np made");

        // Layout of number picker titles
        final TextView minText = new TextView(getActivity());
        minText.setText("MINS");
        minText.setGravity(Gravity.CENTER);

        final TextView hrText = new TextView(getActivity());
        hrText.setText("HOURS");
        hrText.setGravity(Gravity.CENTER);

        final TextView dayText = new TextView(getActivity());
        dayText.setText("DAYS");
        dayText.setGravity(Gravity.CENTER);

        LinearLayout textLL = new LinearLayout(getActivity());
        textLL.setOrientation(LinearLayout.HORIZONTAL);

        textLL.setLayoutParams(LayoutParams);
        textLL.addView(dayText,LLParam);
        textLL.addView(hrText,LLParam);
        textLL.addView(minText,LLParam);

        /* Putting the title and number picker linear layouts into the same parent linear layout */
        LinearLayout LLParent = new LinearLayout(getActivity());
        LLParent.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams parent = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        parent.gravity = Gravity.CENTER;

        LLParent.setLayoutParams(parent);
        LLParent.addView(textLL);
        LLParent.addView(pickerLL);

        /** View complete **/

        // Building the dialog
        builder.setTitle(R.string.cookTime)
                .setView(LLParent)

                .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String value =
                                Integer.toString(dayPicker.getValue()) + ":" +
                                Integer.toString(hrPicker.getValue()) + ":" +
                                Integer.toString(minPicker.getValue());
                        SearchOptions callingActivity = (SearchOptions) getActivity();
                        callingActivity.onCookTimeOK(value);
                        dialog.dismiss();
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