package com.workingtitle.makeit;

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

import com.workingtitle.makeit.models.Recipe;

import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-06-16.
 */
public class SavedRecipesFragment extends Fragment {

    /** Declaring an ArrayAdapter to set items to ListView */
    RecipeListAdapter recipeListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_results, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.list);

        //Hide the toolbar defined in the layout since this fragment is already part of the toolbar/tablayout
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        TextView header = (TextView) view.findViewById(R.id.results);
        header.setVisibility(View.GONE);

        /******************************** Hard Coded Recipe ************************************/
        ArrayList<Recipe> recipeList= new ArrayList<Recipe>();

        for (int i = 0; i < 5; i++){
            Recipe recipe = new Recipe();
            recipe.setAuthor("Author");
            recipe.setTitle("Recipe " + i);
            recipe.setRating(i+1);
            recipeList.add(recipe);
        }
        /**************************************************************************************/

        /** Defining the ArrayAdapter to set items to ListView */
        recipeListAdapter = new RecipeListAdapter(getContext(), recipeList);

        /** Setting the adapter to the ListView */
        listView.setAdapter(recipeListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                openRecipeDetailsPage();
            }
        });

        return view;
    }

    private void openRecipeDetailsPage(){
        Intent intent = new Intent(getActivity(), DisplayRecipe.class);
        Bundle b = new Bundle();
        b.putInt("toolbarBackMessage", R.string.saved_recipes);
        intent.putExtras(b);
        startActivity(intent);
    }
}
