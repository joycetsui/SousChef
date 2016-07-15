package com.workingtitle.makeit.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by byeh on 16-07-09.
 */
public class RecipeCollection implements Serializable{

    private ArrayList<Recipe> recipeCollection;

    public RecipeCollection() {
        recipeCollection = new ArrayList<Recipe>();
    }

    public void addRecipe(Recipe r) {
        recipeCollection.add(r);
    }

    public void populateRecipeCollection(String JsonResponse) {
        JsonElement element = new JsonParser().parse(JsonResponse);

        // full API Response body
        JsonObject object = element.getAsJsonObject();
        Integer numRecipes = Integer.parseInt(object.getAsJsonObject().getAsJsonPrimitive("length").toString());

        JsonArray ingredientList = object.getAsJsonArray("data");

        for(JsonElement e : ingredientList) {
            Recipe recipe = new Recipe();
            recipe.populate(e.getAsJsonObject());
            recipeCollection.add(recipe);
        }
    }

    public ArrayList<Recipe> getRecipeCollection() {
        return recipeCollection;
    }

}
