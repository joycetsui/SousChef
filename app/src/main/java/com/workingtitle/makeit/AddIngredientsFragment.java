package com.workingtitle.makeit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maricarla on 2016-06-16.
 */
public class AddIngredientsFragment extends Fragment {

    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> recipeListAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cookTimeDay = -1;
        cookTimeHour = -1;
        cookTimeMinute = -1;
        numPortions = -1;

        final View view = inflater.inflate(R.layout.activity_add_ingredients, container, false);

        //Auto-complete Suggestions for Ingridients TextView

        // Get the string array
        final String[] suggestions = new String[]{
                "all purpose flour",
                "allspice",
                "almond butter",
                "almond extract",
                "almond meal",
                "almond milk",
                "almond paste",
                "almonds",
                "anise seed",
                "apple juice",
                "apples",
                "applesauce",
                "apricot",
                "apricot nectar",
                "archer farms",
                "artichokes",
                "arugula",
                "asafoetida powder",
                "asparagus",
                "avocado",
                "bacon",
                "bagels"
        };

        final AutoCompleteTextView ingredientTextView = (AutoCompleteTextView) view.findViewById(R.id.txtItem);

        // Create the adapter and set it to the AutoCompleteTextView
        final ArrayAdapter<String> suggestionsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, suggestions);
        ingredientTextView.setAdapter(suggestionsAdapter);

        // Unset the var whenever the user types. Validation will
        // then fail. This is how we enforce selecting from the list
        ingredientTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //List View to display the entered ingredients
        ListView listView = (ListView) view.findViewById(R.id.ingredientsList);

        /** Defining the ArrayAdapter to set items to ListView */
        recipeListAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);

        /** Reference to the button of the layout main.xml */
        Button addBtn = (Button) view.findViewById(R.id.btnAdd);
        Button searchBtn = (Button) view.findViewById(R.id.btnFeedMe);

        /** Defining a click event listener for the button "Add" */
        View.OnClickListener itemAddedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredientAdded = ingredientTextView.getText().toString();

                if (!ingredientAdded.isEmpty() && Arrays.asList(suggestions).indexOf(ingredientAdded) != -1) {
                    list.add(0, ingredientAdded);
                    recipeListAdapter.notifyDataSetChanged();
                    ingredientTextView.setText("");
                }
                else {
                    ingredientTextView.setError(getResources().getString(R.string.invalidIngredientMsg));
                }
            }
        };

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
        Button optionsBtn = (Button) view.findViewById(R.id.btnOptions);
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchOptions.class);
                intent.putExtra("ctDay", cookTimeDay);
                intent.putExtra("ctHour", cookTimeHour);
                intent.putExtra("ctMin", cookTimeMinute);
                intent.putExtra("portions", numPortions);
                startActivity(intent);
            }
        });

        //Search Bar
//        SearchView searchView = (SearchView) view.findViewById(R.id.ingredient_search);
//        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Do something
//                System.out.println("HERE2 " + newText);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Do something
//
//                System.out.println("HERE " + query);
//                return true;
//            }
//        };
//
//        searchView.setOnQueryTextListener(queryTextListener);

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void openResultsPage(){
        Intent intent = new Intent(getActivity(), SearchResults.class);
        Bundle b = new Bundle();
        b.putInt("toolbarBackMessage", R.string.search_ingredients_tab);
        intent.putExtras(b);
        startActivity(intent);
    }

    // Search Options info
    int cookTimeDay;
    int cookTimeHour;
    int cookTimeMinute;
    int numPortions;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        cookTimeDay = data.getIntExtra("ctDay", -1);
        cookTimeHour = data.getIntExtra("ctHour", -1);
        cookTimeMinute = data.getIntExtra("ctMin", -1);
        numPortions = data.getIntExtra("portions", -1);

        System.out.print(cookTimeDay);
        System.out.print(cookTimeHour);
        System.out.print(cookTimeMinute);
        System.out.print(numPortions);
    }

//    private void openSearchFragment(){
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        PlaceHolderFragment fragment = new PlaceHolderFragment();
////        PageFragment fragment = PageFragment.newInstance(4);
//        fragmentTransaction.replace(R.id.frame_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
}
