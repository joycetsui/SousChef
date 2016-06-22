package com.workingtitle.makeit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-16.
 */
public class SavedRecipesFragment extends Fragment {

    /** Declaring an ArrayAdapter to set items to ListView */
    RecipeListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_results, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.list);

        //Hide the toolbar defined in the layout since this fragment is already part of the toolbar/tablayout
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        TextView header = (TextView) view.findViewById(R.id.results);
        header.setVisibility(View.GONE);

        /*Recipe recipe = new Recipe();

        TempRecipe[] recipeList = new TempRecipe[]{
                recipe, recipe, recipe, recipe, recipe, recipe, recipe, recipe, recipe
        };*/

        /******************************** Hard Coded Recipe ************************************/
        ArrayList<Recipe> recipeList= new ArrayList<Recipe>();

        for (int i = 0; i < 10; i++){
            Recipe recipe = new Recipe();
            recipe.setAuthor("Author");
            recipe.setTitle("Recipe " + i);
            recipe.setRating(1);
            recipeList.add(recipe);
        }
        /**************************************************************************************/

        //Get values
        ArrayList<String> values = new ArrayList<String>();
        for (int i = 0; i < recipeList.size(); i++){
            values.add(recipeList.get(i).title);
        }


        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new RecipeListAdapter(getContext(), recipeList, values);

        /** Setting the adapter to the ListView */
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

        return view;
    }
}
