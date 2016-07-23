package com.workingtitle.makeit.controllers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import com.workingtitle.makeit.R;

/**
 * Created by xiwen on 21/6/16.
 */
public class OptionsPortionsDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        int np = getArguments().getInt("portions");
        if (np ==  -1) {
            np = 1;
        }

        System.out.print(np);

        final NumberPicker portionPicker = new NumberPicker(getActivity());
        portionPicker.setMaxValue(100); // max value 100
        portionPicker.setMinValue(1);   // min value 1
        portionPicker.setValue(np);
        portionPicker.setWrapSelectorWheel(true);

        final FrameLayout parentNP = new FrameLayout(getActivity());
        parentNP.addView(portionPicker, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER));

        builder.setTitle(R.string.portions)
                .setView(parentNP)
                .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int value = portionPicker.getValue();
                        SearchOptions callingActivity = (SearchOptions) getActivity();
                        callingActivity.onPortionsOK(value);
                        dialog.dismiss();

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
