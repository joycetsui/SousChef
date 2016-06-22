package com.workingtitle.makeit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Joyce on 6/18/2016.
 */
public class SearchResults extends AppCompatActivity {

    /** Items entered by the user is stored in this ArrayList variable */
//    ArrayList<TempRecipe> list = new ArrayList<TempRecipe>();

    /** Declaring an ArrayAdapter to set items to ListView */
    RecipeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set toolbar back listener to go back to previous activity/fragment
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Get parameters passed when activity was created
        Bundle b = getIntent().getExtras();
        if (b != null){
            int backMessage = b.getInt("toolbarBackMessage");
            getSupportActionBar().setTitle(backMessage);
        }

        final ListView listView = (ListView) findViewById(R.id.list);

        /*TempRecipe recipe = new TempRecipe(1, "title", "author", 1.0f, 5, 200, 30, 2, "Directions", "Ingredients");

        TempRecipe[] recipeList = new TempRecipe[]{
                recipe, recipe, recipe, recipe, recipe, recipe, recipe, recipe, recipe
        };

        for (int i = 0; i < recipeList.length; i++){
            TempRecipe recipe2 = new TempRecipe(1, "Recipe " + i, "Author", i + 0.0f, 5, 200, 30, 2, "Directions", "Ingredients");
            recipeList[i] = recipe2;
        }

        String[] values = new String[]{
                recipe.title, recipe.title, recipe.title, recipe.title, recipe.title, recipe.title, recipe.title, recipe.title, recipe.title
        };*/

        /******************************** Hard Coded Recipe ************************************/
        ArrayList<Recipe> recipeList= new ArrayList<Recipe>();

        for (int i = 0; i < 10; i++){
            Recipe recipe = new Recipe();
            recipe.setAuthor("Author");
            recipe.setTitle("Recipe " + i);
            recipe.setRating(1);
            recipeList.add(recipe);
        }
        /**************************************************************************************/

        //Get values
        ArrayList<String> values = new ArrayList<String>();
        for (int i = 0; i < recipeList.size(); i++){
            values.add(recipeList.get(i).title);
        }

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new RecipeListAdapter(this, recipeList, values);

        /** Setting the adapter to the ListView */
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
//                        .show();
                openRecipeDetailsPage();
            }
        });
    }

    private void openRecipeDetailsPage(){
        Intent intent = new Intent(this, DisplayRecipe.class);
        Bundle b = new Bundle();
        b.putInt("toolbarBackMessage", R.string.search_results);
        intent.putExtras(b);
        startActivity(intent);
    }
}
