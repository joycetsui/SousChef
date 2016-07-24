package com.workingtitle.makeit.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.workingtitle.makeit.GlobalClass;
import com.workingtitle.makeit.R;
import com.workingtitle.makeit.api.SearchByTitle;
import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.RecipeCollection;

import java.util.ArrayList;

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
        queryList = globalClass.getQueryCollection().getReverseQueryCollection();

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

                int size = globalClass.getQueryCollection().getQueryCollection().size();
                updateSearchHistory(size - position - 1);
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

        globalClass.updateQueryCollection(position);
        queryListAdapter.notifyDataSetChanged();
    }

    public void clearSearchHistory(){
        final GlobalClass globalVariable = (GlobalClass) getActivity().getApplicationContext();
        globalVariable.clearSearchHistory();
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
