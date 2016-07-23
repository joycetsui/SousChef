package com.workingtitle.makeit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.workingtitle.makeit.api.SearchByIngredients;
import com.workingtitle.makeit.api.SearchByTitle;
import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.QueryCollection;
import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Maricarla on 2016-07-22.
 */
public class SearchHistoryFragment extends Fragment {

    private QueryListAdapter queryListAdapter;

    private ArrayList<Query> queryList = new ArrayList<Query>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.activity_search_history, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.searchHistoryList);

        //Hide the toolbar defined in the layout since this fragment is already part of the toolbar/tablayout
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        
        final GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();
        queryList = globalClass.getQueryCollection().getQueryCollection();
        //Collections.reverse(queryList);

        /** Defining the ArrayAdapter to set items to ListView */
        queryListAdapter = new QueryListAdapter(getContext(), queryList);

        /** Setting the adapter to the ListView */
        listView.setAdapter(queryListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Query query = queryList.get(position);
                if (query.getQueryType().equals(getResources().getString(R.string.search_by_ingredients))){
                    openSearchByIngredientsTabPopulated(query);
                }
                else{
                    openResultsPage(query);
                }

                updateSearchHistory(position);
            }
        });

        return view;
    }

    private void openSearchByIngredientsTabPopulated(Query query){

        Intent intent = new Intent(getActivity(), SearchResults.class);
        ArrayList<String> ingredients = query.getTerms();
        intent.putExtra("IngredientList", ingredients);

        ((MainActivity)getActivity()).setIntent(intent);
        ((MainActivity)getActivity()).viewPager.setCurrentItem(0);
    }

    private void updateSearchHistory(int position){
        final GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();

        Query query = globalClass.getQueryCollection().getQueryCollection().get(position);
        globalClass.getQueryCollection().removeQuery(position);
        globalClass.getQueryCollection().addQuery(query);

        //queryList = globalClass.getQueryCollection().getQueryCollection();
        queryListAdapter.notifyDataSetChanged();
    }

    private void openResultsPage(Query query){
        Intent intent = new Intent(getActivity(), SearchResults.class);
        Bundle b = new Bundle();

        String results = "";

        try{
            results  = new SearchByTitle().execute(query).get();
        }catch(Exception e) {
            e.printStackTrace();
        }

        b.putInt("toolbarBackMessage", R.string.search_history);
        RecipeCollection recipes = new RecipeCollection();
        recipes.populateRecipeCollection(results);
        intent.putExtra("RECIPE_COLLECTION",recipes);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void saveQuery(Query query){
        final GlobalClass globalVariable = (GlobalClass) getActivity().getApplicationContext();
        globalVariable.addQuery(query);
    }
}
