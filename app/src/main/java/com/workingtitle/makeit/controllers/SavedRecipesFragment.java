package com.workingtitle.makeit.controllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.workingtitle.makeit.GlobalClass;
import com.workingtitle.makeit.R;
import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-16.
 */
public class SavedRecipesFragment extends Fragment {

    /** Declaring an ArrayAdapter to set items to ListView */
    RecipeListAdapter recipeListAdapter;

    ArrayList<Recipe> recipeList;

    private TextView emptyListMsg;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.activity_search_results, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        emptyListMsg = (TextView) view.findViewById(R.id.emptySearchListMsg);
        emptyListMsg.setText("No saved recipes found.");

        //Hide the toolbar defined in the layout since this fragment is already part of the toolbar/tablayout
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        TextView header = (TextView) view.findViewById(R.id.results);
        header.setVisibility(View.GONE);

        // Get recipes
        final GlobalClass globalVariable = (GlobalClass) getActivity().getApplicationContext();
        recipeList = globalVariable.getRecipeCollection().getRecipeCollection();

        if (recipeList == null || recipeList.isEmpty()){
            emptyListMsg.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            emptyListMsg.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

        /** Defining the ArrayAdapter to set items to ListView */
        recipeListAdapter = new RecipeListAdapter(getContext(), recipeList);

        /** Setting the adapter to the ListView */
        listView.setAdapter(recipeListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                openRecipeDetailsPage(position);
            }
        });

        return view;
    }

    private void openRecipeDetailsPage(int position){
        Intent intent = new Intent(getActivity(), DisplayRecipe.class);
        Bundle b = new Bundle();
        b.putInt("toolbarBackMessage", R.string.saved_recipes);

        intent.putExtras(b);
        intent.putExtra("RECIPE",recipeList.get(position));
        intent.putExtra("RECIPE_INDEX", position);
        intent.putExtra("RECIPE_SAVE_ACTION",getResources().getString(R.string.unsaveButton));

        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 1: {
                if (resultCode == 2) {
                    // Recipe was removed
                    updateList();
                }
                break;
            }
        }
    }

    public void updateList(){
        final GlobalClass globalVariable = (GlobalClass) getActivity().getApplicationContext();
        recipeList = globalVariable.getRecipeCollection().getRecipeCollection();

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
}
