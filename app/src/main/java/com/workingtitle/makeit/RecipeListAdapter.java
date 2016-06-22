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
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-20.
 */
public class RecipeListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<Recipe> recipes;
    private final ArrayList<String> values;

    public RecipeListAdapter(Context context, ArrayList <Recipe> recipes, ArrayList<String> values) {
        super(context, -1, values);
        this.context = context;
        this.recipes = recipes;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.results_row_layout, parent, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        RatingBar ratingbar =(RatingBar) rowView.findViewById(R.id.recipe_rating);

//        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
//
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating,
//                                        boolean fromUser) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getContext(),Float.toString(rating),Toast.LENGTH_LONG).show();
//            }
//
//        });

        titleTextView.setText(recipes.get(position).title);
        ratingbar.setRating(recipes.get(position).rating);

        Bitmap image;

        if (position%3 == 0){
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.crepes);
        }
        else if (position%3 == 1){
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.salad_small);
        }
        else{
            image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.grilled_fish_small);
        }

        imageView.setImageBitmap(image);

        return rowView;
    }
}
