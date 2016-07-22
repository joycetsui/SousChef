package com.workingtitle.makeit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.workingtitle.makeit.api.GetLookupTable;
import com.workingtitle.makeit.api.SearchByIngredients;
import com.workingtitle.makeit.models.IngredientsLookupTable;
import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.RecipeCollection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maricarla on 2016-06-16.
 */
public class AddIngredientsFragment extends Fragment {

    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> ingredientList = new ArrayList<String>();
    private Query query;

    /** Declaring an ArrayAdapter to set items to ListView */
    IngredientsAddedAdapter recipeListAdapter;

    // textview used to add ingredients to the list
    AutoCompleteTextView ingredientTextView;

    // ingredients List
    IngredientsLookupTable lookupTable;

    // Search Options info
    private int cookTimeDay;
    private int cookTimeHour;
    private int cookTimeMinute;
    private int numPortions;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_add_ingredients, container, false);

        //Auto-complete Suggestions for Ingredients TextView

        ingredientTextView = (AutoCompleteTextView) view.findViewById(R.id.txtItem);

        // Create the adapter and set it to the AutoCompleteTextView
        final ArrayAdapter<String> suggestionsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lookupTable.getLookupTable());
        ingredientTextView.setAdapter(suggestionsAdapter);

        //List View to display the entered ingredients
        ListView listView = (ListView) view.findViewById(R.id.ingredientsList);

        /** Defining the ArrayAdapter to set items to ListView */
        recipeListAdapter = new IngredientsAddedAdapter(view.getContext(), ingredientList);

        /** Reference to the button of the layout main.xml */
        Button addBtn = (Button) view.findViewById(R.id.btnAdd);
        Button searchBtn = (Button) view.findViewById(R.id.btnFeedMe);

        /** Defining a click event listener for the button "Add" */
        View.OnClickListener itemAddedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredientToList();
            }
        };

        /** Define a listener to accept text input when pressing enter on keyboard => simulate clicking "Add button"*/
        ingredientTextView.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    switch (keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            addIngredientToList();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        /** Define a listener to add an item directly when clicked from the suggestion list */
        ingredientTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                addIngredientToList();
            }
        });

        /** Defining a click event listener for the button "Feed me" */
        View.OnClickListener goSearchListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage();
            }
        };

        /** Setting the event listener for the add button */
        addBtn.setOnClickListener(itemAddedListener);
        searchBtn.setOnClickListener(goSearchListener);

        /** Setting the adapter to the ListView */
        listView.setAdapter(recipeListAdapter);

        // Go to Search Options Page
        //Button optionsBtn = (Button) view.findViewById(R.id.btnOptions);
        //optionsBtn.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
             //   openOptionsPage();
           // }
        //});

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Initialize data for activity
        query = new Query(getResources().getString(R.string.search_by_ingredients));

        // Initialize data for activity
        lookupTable  = new IngredientsLookupTable();
        try{
            String s  = new GetLookupTable().execute().get();
            lookupTable.populate(s);
        }catch(Exception e) {
            e.printStackTrace();
        }

        cookTimeDay = -1;
        cookTimeHour = -1;
        cookTimeMinute = -1;
        numPortions = -1;

    }

    private void openOptionsPage() {
        Intent intent = new Intent(getActivity(), SearchOptions.class);
        intent.putExtra("ctDay", cookTimeDay);
        intent.putExtra("ctHour", cookTimeHour);
        intent.putExtra("ctMin", cookTimeMinute);
        intent.putExtra("portions", numPortions);

        startActivityForResult(intent, 1);
    }

    private void openResultsPage(){

        String results = "";
        System.out.println(ingredientList.size());

        query.setTerms((ingredientList));

        try{
            SearchHistoryFragment.saveQuery(query);
            results  = new SearchByIngredients().execute(query).get();
        }catch(Exception e) {
            e.printStackTrace();
        }
        RecipeCollection recipes = new RecipeCollection();
        recipes.populateRecipeCollection(results);
        final GlobalClass globalVariable = (GlobalClass) getActivity().getApplicationContext();
        globalVariable.addQuery(query);

        Intent intent = new Intent(getActivity(), SearchResults.class);
        intent.putExtra("RECIPE_COLLECTION",recipes);

        Bundle b = new Bundle();
        b.putInt("toolbarBackMessage", R.string.search_ingredients_tab);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void addIngredientToList(){
        String ingredientAdded = ingredientTextView.getText().toString();

        if (!ingredientAdded.isEmpty() && Arrays.asList(lookupTable.getLookupTable()).indexOf(ingredientAdded) != -1) {
            ingredientList.add(0, ingredientAdded);
            recipeListAdapter.notifyDataSetChanged();
            ingredientTextView.setText("");
        }
        else {
            ingredientTextView.setError(getResources().getString(R.string.invalidIngredientMsg));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                cookTimeDay = data.getIntExtra("ctDay", -1);
                cookTimeHour = data.getIntExtra("ctHour", -1);
                cookTimeMinute = data.getIntExtra("ctMin", -1);
                numPortions = data.getIntExtra("portions", -1);
            default:
        }
    }
}
