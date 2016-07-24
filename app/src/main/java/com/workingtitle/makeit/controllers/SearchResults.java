package com.workingtitle.makeit.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.workingtitle.makeit.FilterSearch;
import com.workingtitle.makeit.R;
import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Joyce on 6/18/2016.
 */
public class SearchResults extends AppCompatActivity {

    /** Declaring an ArrayAdapter to set items to ListView */
    RecipeListAdapter recipeListAdapter;

    ArrayList<Recipe> recipeList;
    // collection holding all recipes returned by search, filtered by user settings
    // need this so that we can change search options after search results is display and still show
    // recipes that were fitered out by previous search options
    RecipeCollection userFilteredCollection = new RecipeCollection();

    // recipe collection after being filtered by user setting and search options
    // the collection that is displayed to the screen
    RecipeCollection filteredCollection = new RecipeCollection();

    private TextView emptyListMsg;
    private ListView listView;

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

        listView = (ListView) findViewById(R.id.list);
        emptyListMsg = (TextView) findViewById(R.id.emptySearchListMsg);

        userFilteredCollection = (RecipeCollection) getIntent().getSerializableExtra("RECIPE_COLLECTION");
        filterCollectionByUserSettings();

        filteredCollection.setRecipeCollection(userFilteredCollection.getRecipeCollection());
        filterCollectionBySearchOptions();

        //recipeList = (ArrayList<Recipe>)filteredCollection.getRecipeCollection().clone();
        recipeList = filteredCollection.getRecipeCollection();

        //Check if the recipe search did not return result
        if (recipeList == null || recipeList.isEmpty()){
            emptyListMsg.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            emptyListMsg.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (menu != null) {
                menu.findItem(R.id.action_settings).setVisible(false);
                menu.findItem(R.id.clearHistory).setVisible(false);
                menu.findItem(R.id.filterButton).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.filterButton){
            openOptionsPage();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openOptionsPage() {

        Intent intent = new Intent(this, SearchOptions.class);
        intent.putExtra("ctHour", FilterSearch.cookTimeHour);
        intent.putExtra("ctMin", FilterSearch.cookTimeMinute);
        intent.putExtra("portions", FilterSearch.numPortions);

        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1: {
                FilterSearch.cookTimeHour = data.getIntExtra("ctHour", -1);
                FilterSearch.cookTimeMinute = data.getIntExtra("ctMin", -1);
                FilterSearch.numPortions = data.getIntExtra("portions", -1);

                resetFilteredCollection();
                filterCollectionBySearchOptions();
                updateList();
                break;
            }
        }
    }

    private void resetFilteredCollection(){
        filteredCollection.setRecipeCollection(userFilteredCollection.getRecipeCollection());
    }

    private void updateList(){
        recipeList.clear();
        recipeList.addAll(filteredCollection.getRecipeCollection());

        if (recipeList.isEmpty()){
            if (emptyListMsg != null && listView != null) {
                emptyListMsg.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }
        } else {
            if (emptyListMsg != null && listView != null) {
                emptyListMsg.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        }

        recipeListAdapter.notifyDataSetChanged();
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
            FilterSearch.filterByVegan(userFilteredCollection);
        }
        else if (prefIsEnabled("pref_diet_vegetarian")){
            FilterSearch.filterByVegetarian(userFilteredCollection);
        }
        else if (prefIsEnabled("pref_diet_pesc")){
            FilterSearch.filterByPescatarian(userFilteredCollection);
        }

        if (prefIsEnabled("pref_allergy_nuts")){
            FilterSearch.filterByNuts(userFilteredCollection);
        }

        if (prefIsEnabled("pref_allergy_shellfish")){
            FilterSearch.filterByShellfish(userFilteredCollection);
        }

        if (prefIsEnabled("pref_allergy_lactose")){
            FilterSearch.filterByLactose(userFilteredCollection);
        }
    }

    public boolean prefIsEnabled(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean enabled = sharedPref.getBoolean(key, false);
        return enabled;
    }

    private void filterCollectionBySearchOptions(){
        if (FilterSearch.numPortions != -1) {
            FilterSearch.filterByNumberOfPortions(filteredCollection);
        }

        if (FilterSearch.cookTimeMinute != -1 || FilterSearch.cookTimeHour != -1){
            FilterSearch.filterByCookTime(filteredCollection);
        }
    }
}
