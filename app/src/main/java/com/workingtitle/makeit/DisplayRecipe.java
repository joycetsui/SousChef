package com.workingtitle.makeit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Joyce on 6/17/2016.
 */
public class DisplayRecipe extends AppCompatActivity{

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
        if (b != null){
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

        //Create Recipe
        /************************************************Hard Coded******************************************************/
        Recipe recipe = new Recipe();
        recipe.setTitle("Fatoosh");
        recipe.setCookTime("30 m");
        recipe.setRating(4.3f);
        recipe.setAuthor("WINNIFRED");
        recipe.setIngredients("2 pita breads\n8 leaves romaine lettuce, torn into bite-size pieces\n" +
                "2 green onions, chopped\n1 cucumber, chopped\n3 tomatoes, cut into wedges\n1/4 cup chopped fresh " +
                "parsley\n1 clove garlic, peeled and chopped\n2 tablespoons sumac powder\n1/4 cup lemon juice\n1/4 cup " +
                "olive oil\n1 teaspoon salt\n1/4 teaspoon ground black pepper\n1/4 cup chopped fresh mint leaves");
        recipe.setDirections("1) Preheat oven to 350 degrees F (175 degrees C).\n2) Toast pitas 5 to 10 minutes in " +
                "the preheated oven, until crisp. Remove from heat, and break into bite size pieces.\n3) In a large bowl, " +
                "toss together toasted pita pieces, romaine lettuce, green onions, cucumber, and tomatoes.\n4) In a small bowl, " +
                "mix the parsley, garlic, sumac powder, lemon juice, olive oil, salt, pepper, and mint. Pour over the pita mixture, " +
                "and toss just before serving.\n");
        /****************************************************************************************************************************/

        setRecipeElements(recipe.title, R.id.recipeName);
        setRecipeElements(recipe.cookTime, R.id.cookingTime);
        setRecipeElements(recipe.ingredients, R.id.ingredientsList);
        setRecipeElements(recipe.directions, R.id.directions);
        setRecipeElements(recipe.author, R.id.authorName);

        // Set rating
        RatingBar ratingBar = (RatingBar) findViewById(R.id.recipe_rating);
        ratingBar.setRating(recipe.rating);
    }

    public void setRecipeElements(String text, int id){
        TextView tv = new TextView (this);
        tv = (TextView)findViewById(id);
        tv.setText(text);
    }
}
