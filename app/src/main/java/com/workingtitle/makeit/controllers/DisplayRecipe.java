package com.workingtitle.makeit.controllers;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.workingtitle.makeit.GlobalClass;
import com.workingtitle.makeit.R;
import com.workingtitle.makeit.models.Recipe;

/**
 * Created by Joyce on 6/17/2016.
 */
public class DisplayRecipe extends AppCompatActivity {

    public Recipe recipe;
    public int recipeIndex = -1;

    private int resultCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_recipe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView saveBtn = (ImageView) findViewById(R.id.saveRemoveBtn);
        String saveBtnAction = getIntent().getStringExtra("RECIPE_SAVE_ACTION");

        //If the window that opened this recipe is the Search Results, set the button to SaveRecipe
        if (saveBtnAction.equals(getResources().getString(R.string.saveButton))){
            saveBtn.setImageResource(R.drawable.ic_action_save);
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveRecipe();
                }
            });
        }
        //If the window that opened this recipe is the SavedRecipeFragment, set the button to remove saved recipe
        else{
            saveBtn.setImageResource(R.drawable.ic_action_cancel);
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeSavedRecipe();
                }
            });
        }

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
        recipeIndex = getIntent().getIntExtra("RECIPE_INDEX", -1);
        System.out.println(recipe.getRecipeId());

        setRecipeElements(recipe.getTitle(), R.id.recipeName);
        setRecipeElements(recipe.getCookTime(), R.id.cookingTime);
        setRecipeElements(recipe.getIngredients(), R.id.ingredientsList);
        setRecipeElements(recipe.getDirections(), R.id.directions);
        setRecipeElements(recipe.getAuthor(), R.id.authorName);
        setRecipeElements(recipe.getServingSize().toString() + " Servings", R.id.servingsize);

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

    @Override
    public void onBackPressed() {
        setResult(resultCode);
        finishActivity(1);
        super.onBackPressed();
    }

    private void saveRecipe() {
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        globalVariable.addRecipe(this.recipe);

        Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void removeSavedRecipe() {
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        if (recipeIndex != -1) {
            globalVariable.removeRecipe(recipeIndex);
            resultCode = 2;
            Toast toast = Toast.makeText(getApplicationContext(), "Unsaved", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
