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

    public QueryCollection getQueryCollection() {
        return queryCollection;
    }

    public void addQuery(Query q) {
        queryCollection.addQuery(q);
    }

    private RecipeCollection recipeCollection = new RecipeCollection();

    public RecipeCollection getRecipeCollection() {
        return recipeCollection;
    }

    public void addRecipe(Recipe r) {
        recipeCollection.addRecipe(r);
        recipeCollection.saveRecipeCollection(getApplicationContext());
    }
}
