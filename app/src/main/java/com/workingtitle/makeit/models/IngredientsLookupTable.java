package com.workingtitle.makeit.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;

/**
 * Created by byeh on 16-07-10.
 */
public class IngredientsLookupTable {

    private ArrayList<String> lookupTable;

    public IngredientsLookupTable() {
        lookupTable = new ArrayList<String>();
    }

    public String[] getLookupTable() {
        return lookupTable.toArray(new String[0]);
    }

    public void populate(String JSONObject) {
        JsonElement element = new JsonParser().parse(JSONObject);

        JsonObject object = element.getAsJsonObject();

        // lets get the nested JSON object inside data key which contains a recipe and populate the fields
        JsonArray ingredientList = object.getAsJsonArray("data");

        for(JsonElement e : ingredientList) {
            String ingredient = e.getAsJsonObject().getAsJsonPrimitive("lookup_name").toString();
            ingredient = ingredient.replace("\"", "");
            lookupTable.add(ingredient);
        }


    }

}
