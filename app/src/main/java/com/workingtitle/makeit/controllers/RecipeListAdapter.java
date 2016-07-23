package com.workingtitle.makeit.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.workingtitle.makeit.R;
import com.workingtitle.makeit.models.Recipe;

import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-20.
 */
public class RecipeListAdapter extends ArrayAdapter<Recipe> {
    private final Context context;
    ArrayList<Recipe> recipes;

    public RecipeListAdapter(Context context, ArrayList <Recipe> recipes) {
        super(context, -1, recipes);

        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.search_results_row_layout, parent, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        RatingBar ratingbar =(RatingBar) rowView.findViewById(R.id.recipe_rating);

        titleTextView.setText(recipes.get(position).getTitle());
        ratingbar.setRating(recipes.get(position).getRating());

        return rowView;
    }
}
