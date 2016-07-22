package com.workingtitle.makeit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.workingtitle.makeit.models.Recipe;

/**
 * Created by Joyce on 6/17/2016.
 */
public class DisplayRecipe extends AppCompatActivity {

    public Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_recipe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get parameters passed when activity was created
        Bundle b = getIntent().getExtras();
        if (b != null) {
            int backMessage = b.getInt("toolbarBackMessage");
            getSupportActionBar().setTitle(backMessage);
        }

        // Set toolbar back listener to go back to previous activity/fragment
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recipe = (Recipe) getIntent().getSerializableExtra("RECIPE");
        System.out.println(recipe.getRecipeId());

        setRecipeElements(recipe.getTitle(), R.id.recipeName);
        setRecipeElements(recipe.getCookTime(), R.id.cookingTime);
        setRecipeElements(recipe.getIngredients(), R.id.ingredientsList);
        setRecipeElements(recipe.getDirections(), R.id.directions);
        setRecipeElements(recipe.getAuthor(), R.id.authorName);

        // Set rating
        RatingBar ratingBar = (RatingBar) findViewById(R.id.recipe_rating);
        ratingBar.setRating(recipe.getRating());
    }

    public void setRecipeElements(String text, int id) {
        System.out.println(text);
        TextView tv = new TextView(this);

        tv.setTransformationMethod(null);
        tv.setAllCaps(false);
        tv = (TextView) findViewById(id);

        tv.setText(text);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void saveRecipe() {
        MainActivity.savedRecipeCollection.addRecipe(this.recipe);
    }
}
