package com.workingtitle.makeit.models;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        if (JsonResponse.isEmpty()){
            return;
        }

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

    public void loadRecipeCollection(Context context){
        String fileName = context.getFilesDir().getPath().toString() + "savedRecipes";
        try {
            File savedRecipeFile = new File(fileName);

            if (!savedRecipeFile.exists()) {
                savedRecipeFile.createNewFile();
            }

            FileInputStream fileIn = new FileInputStream(savedRecipeFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.recipeCollection = (ArrayList<Recipe>) in.readObject();

            in.close();
            fileIn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveRecipeCollection(Context context) {
        String filename = context.getFilesDir().getPath().toString() + "savedRecipes";

        try {
//            File savedRecipeFile = new File(filename);
//
//            if (!savedRecipeFile.exists()) {
//                savedRecipeFile.createNewFile();
//            }
            FileOutputStream fileOut = new FileOutputStream(new File(filename));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.getRecipeCollection());
            out.close();
            fileOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean recipeExists(Recipe r) {
        for(Recipe recipe : recipeCollection) {
            if(r.getId().equals(recipe.getId())) {
                return true;
            }
        }
        return false;
    }

}
