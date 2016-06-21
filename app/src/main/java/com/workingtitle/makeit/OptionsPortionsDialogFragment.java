package com.workingtitle.makeit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by xiwen on 21/6/16.
 */
public class OptionsPortionsDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final NumberPicker np = new NumberPicker(getActivity());
        np.setMaxValue(100); // max value 100
        np.setMinValue(1);   // min value 1
        np.setValue(SearchOptions.getPortions());
        np.setWrapSelectorWheel(true);

        final FrameLayout parentNP = new FrameLayout(getActivity());
        parentNP.addView(np, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER));

        builder.setTitle(R.string.portions)
                .setView(parentNP)
                .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        SearchOptions.setPortions(np.getValue());

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
