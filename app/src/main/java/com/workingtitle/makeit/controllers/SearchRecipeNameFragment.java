package com.workingtitle.makeit.controllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.workingtitle.makeit.GlobalClass;
import com.workingtitle.makeit.R;
import com.workingtitle.makeit.api.GetRandomRecipe;
import com.workingtitle.makeit.api.SearchByTitle;
import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-13.
 */
public class SearchRecipeNameFragment extends Fragment {

    /** Items entered by the user is stored in this ArrayList variable */
    Query query;
    ArrayList<String> terms;
    EditText keyword;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.activity_search_by_recipe_name, container, false);

        resetView();

        /** Reference to the button of the layout main.xml */
        keyword = (EditText) view.findViewById(R.id.recipeNameTextItem);
        Button searchBtn = (Button) view.findViewById(R.id.byNameFeedMeBtn);
        Button supriseMeBtn = (Button) view.findViewById(R.id.byNameSurpriseMeBtn);


        /** Defining a click event listener for the button "FeedMe" */
        View.OnClickListener goSearchListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!keyword.getText().toString().isEmpty()) {
                    openResultsPage(keyword.getText().toString());
                }
                else{
                    Toast msg = Toast.makeText(getContext(), "Please enter a keyword.", Toast.LENGTH_LONG);
                    msg.show();
                }
            }
        };

        View.OnClickListener surpriseMeListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaySurpriseRecipe();
            }
        };

        /** Setting the event listener for the add button */
        searchBtn.setOnClickListener(goSearchListener);

        supriseMeBtn.setOnClickListener(surpriseMeListener);

        return view;
    }

    private void openResultsPage(String searchKeyword){
        Intent intent = new Intent(getActivity(), SearchResults.class);
        Bundle b = new Bundle();

        String results = "";

        try{
            query.addTerm(searchKeyword);
            saveQuery(query);
            results  = new SearchByTitle().execute(query).get();
        }catch(Exception e) {
            e.printStackTrace();
        }

        b.putInt("toolbarBackMessage", R.string.search_recipe_tab);

        RecipeCollection recipes = new RecipeCollection();
        recipes.populateRecipeCollection(results);

        intent.putExtra("RECIPE_COLLECTION",recipes);
        intent.putExtras(b);
        startActivity(intent);

        //startActivityForResult(intent, 0);

        resetView();
    }

    private void displaySurpriseRecipe() {
        Intent intent = new Intent(getActivity(), DisplayRecipe.class);
        Bundle b = new Bundle();
        Recipe recipe = new Recipe();

        String results = "";

        try {
            results = new GetRandomRecipe().execute(recipe).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (results.isEmpty() || results == null){
            Toast msg = Toast.makeText(getContext(), "Sorry, our suprise box is empty.", Toast.LENGTH_LONG);
            msg.show();
            return;
        }

        JsonElement element = new JsonParser().parse(results);
        JsonObject object = element.getAsJsonObject();
        object = object.getAsJsonObject("data");
        recipe.populate(object);

        b.putInt("toolbarBackMessage", R.string.search_by_recipe);
        intent.putExtras(b);
        intent.putExtra("RECIPE",recipe);
        intent.putExtra("RECIPE_INDEX",0);
        intent.putExtra("RECIPE_SAVE_ACTION",getResources().getString(R.string.saveButton));
        startActivity(intent);

        resetView();
    }

    public void resetView(){
        // Initialize data for activity
        query = new Query(getResources().getString(R.string.search_by_recipe));
        terms = query.getTerms();
         if (keyword != null) {
             keyword.setText("");
         }
    }

    public void saveQuery(Query query){
        final GlobalClass globalVariable = (GlobalClass) getActivity().getApplicationContext();
        globalVariable.addQuery(query);
    }
}
