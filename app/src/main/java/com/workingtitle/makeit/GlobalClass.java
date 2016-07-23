package com.workingtitle.makeit;

import android.app.Application;

import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.QueryCollection;
import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

/**
 * Created by byeh on 16-07-22.
 */
public class GlobalClass extends Application {

    private QueryCollection queryCollection = new QueryCollection();
    private RecipeCollection recipeCollection = new RecipeCollection();

    public void initialize() {
        queryCollection.loadQueryCollection(getApplicationContext());
        recipeCollection.loadRecipeCollection(getApplicationContext());
    }

    public void clearSearchHistory(){
        queryCollection.emptyQueryCollection(getApplicationContext());
    }

    public QueryCollection getQueryCollection() {
        return queryCollection;
    }

    public void addQuery(Query q) {
        int queryIndex = queryCollection.queryExists(q);
        if(queryIndex == -1) {
            queryCollection.addQuery(q);
            queryCollection.saveQueryCollection(getApplicationContext());
        }
        else{
            updateQueryCollection(queryIndex);
        }
    }

    public void updateQueryCollection(int position){

        Query query = queryCollection.getQueryCollection().get(position);
        queryCollection.removeQuery(position);
        addQuery(query);
    }

    public RecipeCollection getRecipeCollection() {
        return recipeCollection;
    }

    public void addRecipe(Recipe r) {
        if(!recipeCollection.recipeExists(r)) {
            recipeCollection.addRecipe(r);
            recipeCollection.saveRecipeCollection(getApplicationContext());
        }
    }
}
