package com.workingtitle.makeit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joyce on 6/18/2016.
 */
public class SearchResults extends AppCompatActivity {

    /** Declaring an ArrayAdapter to set items to ListView */
    RecipeListAdapter recipeListAdapter;

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


        /******************************** Hard Coded Recipe ************************************/
        RecipeCollection collection = (RecipeCollection) getIntent().getSerializableExtra("RECIPE_COLLECTION");
        ArrayList<Recipe> recipeList = collection.getRecipeCollection();

        /**************************************************************************************/

        /** Defining the ArrayAdapter to set items to ListView */
        recipeListAdapter = new RecipeListAdapter(this, recipeList);

        /** Setting the adapter to the ListView */
        listView.setAdapter(recipeListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
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
