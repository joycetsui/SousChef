package com.workingtitle.makeit.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.workingtitle.makeit.FilterSearch;
import com.workingtitle.makeit.R;
import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import java.util.ArrayList;

/**
 * Created by Joyce on 6/18/2016.
 */
public class SearchResults extends AppCompatActivity {

    /** Declaring an ArrayAdapter to set items to ListView */
    RecipeListAdapter recipeListAdapter;

    ArrayList<Recipe> recipeList;
    RecipeCollection collection = new RecipeCollection();

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


        collection = (RecipeCollection) getIntent().getSerializableExtra("RECIPE_COLLECTION");

        filterCollectionByUserSettings();

        recipeList = collection.getRecipeCollection();

        /** Defining the ArrayAdapter to set items to ListView */
        recipeListAdapter = new RecipeListAdapter(this, recipeList);

        /** Setting the adapter to the ListView */
        listView.setAdapter(recipeListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                openRecipeDetailsPage(position);
            }
        });
    }

    private void openRecipeDetailsPage(int position){
        Intent intent = new Intent(this, DisplayRecipe.class);
        Bundle b = new Bundle();
        b.putInt("toolbarBackMessage", R.string.search_results);
        intent.putExtras(b);
        intent.putExtra("RECIPE",recipeList.get(position));
        intent.putExtra("RECIPE_INDEX",position);
        intent.putExtra("RECIPE_SAVE_ACTION",getResources().getString(R.string.saveButton));
        startActivity(intent);
    }

    private void filterCollectionByUserSettings(){
        if (prefIsEnabled("pref_diet_vegan")){
            FilterSearch.filterByVegan(collection);
        }
        else if (prefIsEnabled("pref_diet_vegetarian")){
            FilterSearch.filterByVegetarian(collection);
        }
        else if (prefIsEnabled("pref_diet_pesc")){
            FilterSearch.filterByPescatarian(collection);
        }

        if (prefIsEnabled("pref_allergy_nuts")){
            FilterSearch.filterByNuts(collection);
        }

        if (prefIsEnabled("pref_allergy_shellfish")){
            FilterSearch.filterByShellfish(collection);
        }

        if (prefIsEnabled("pref_allergy_lactose")){
            FilterSearch.filterByLactose(collection);
        }
    }

    public boolean prefIsEnabled(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean enabled = sharedPref.getBoolean(key, false);
        return enabled;
    }

    private void filterCollectionBySearchOptions(){

    }
}
