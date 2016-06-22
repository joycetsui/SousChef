package com.workingtitle.makeit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-16.
 */
public class AddIngredientsFragment extends Fragment {

    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cookTimeDay = -1;
        cookTimeHour = -1;
        cookTimeMinute = -1;
        numPortions = -1;

        final View view = inflater.inflate(R.layout.activity_add_ingredients, container, false);

        /** Reference to the button of the layout main.xml */
        Button addBtn = (Button) view.findViewById(R.id.btnAdd);
        Button searchBtn = (Button) view.findViewById(R.id.btnFeedMe);

        ListView listView = (ListView) view.findViewById(R.id.ingredientsList);

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);

        /** Defining a click event listener for the button "Add" */
        View.OnClickListener itemAddedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) view.findViewById(R.id.txtItem);
                String ingredient = edit.getText().toString();
                if (!ingredient.isEmpty()){
                    list.add(0, ingredient);
                    edit.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        };

        /** Defining a click event listener for the button "Add" */
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
        listView.setAdapter(adapter);

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
