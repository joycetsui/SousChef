package com.workingtitle.makeit;

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

import com.workingtitle.makeit.Recipe.Recipe;

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

        View rowView = inflater.inflate(R.layout.results_row_layout, parent, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        RatingBar ratingbar =(RatingBar) rowView.findViewById(R.id.recipe_rating);

        titleTextView.setText(recipes.get(position).getTitle());
        ratingbar.setRating(recipes.get(position).getRating());

        Bitmap image;

        /*if (position%3 == 0){
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.crepes);
        }
        else if (position%3 == 1){
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.salad_small);
        }
        else{
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.grilled_fish_small);
        }*/
        if (position == 0) {
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.fatoosh);
        }
        else if (position ==1){
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.caesar_salad);
        }
        else if (position == 2){
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.strawberry_romaine_salad);
        }
        else if (position == 3){
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.garden_salad);
        }
        else {
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.salad_small);
        }

        imageView.setImageBitmap(image);

        return rowView;
    }
}
