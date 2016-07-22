package com.workingtitle.makeit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.workingtitle.makeit.api.GetLookupTable;
import com.workingtitle.makeit.api.SearchByIngredients;
import com.workingtitle.makeit.api.SearchByTitle;
import com.workingtitle.makeit.models.IngredientsLookupTable;
import com.workingtitle.makeit.models.QueryCollection;
import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.RecipeCollection;

import org.w3c.dom.Text;

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
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Initialize data for activity
        query = new Query(getResources().getString(R.string.search_by_recipe));
        terms = query.getTerms();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_by_name, container, false);

        /** Reference to the button of the layout main.xml */
        keyword = (EditText) view.findViewById(R.id.recipeNameTextItem);
        Button optionsBtn = (Button) view.findViewById(R.id.byNameOptionsBtn);
        Button searchBtn = (Button) view.findViewById(R.id.byNameFeedMeBtn);

        /** Defining a click event listener for the button "FeedMe" */
        View.OnClickListener goSearchListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage();
            }
        };

        /** Setting the event listener for the add button */
        searchBtn.setOnClickListener(goSearchListener);

        // Go to Search Options Page
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchOptions.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void openResultsPage(){
        Intent intent = new Intent(getActivity(), SearchResults.class);
        Bundle b = new Bundle();

        String results = "";

        try{
            query.addTerm(keyword.getText().toString());
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
    }
}
