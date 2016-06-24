package com.workingtitle.makeit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-22.
 */
public class IngredientsAddedAdapter extends ArrayAdapter<String> {

    ArrayList<String> ingredientList;

    public IngredientsAddedAdapter(Context context, ArrayList<String> ingredients) {
        super(context, 0, ingredients);

        this.ingredientList = ingredients;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // Get the data item for this position
        String ingredient = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_added_layout, parent, false);
        }
        // Lookup view for data population
        TextView ingredientTextView = (TextView) view.findViewById(R.id.ingredientAddedText);

        // Populate the data into the template view using the data object
        ingredientTextView.setText(ingredient);

        Button removeBtn = (Button) view.findViewById(R.id.removeIngredientBtn);
        View.OnClickListener removeBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientList.remove(position);
                notifyDataSetChanged();
            }
        };

        removeBtn.setOnClickListener(removeBtnListener);

        // Return the completed view to render on screen
        return view;
    }
}
