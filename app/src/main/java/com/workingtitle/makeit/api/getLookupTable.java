package com.workingtitle.makeit.api;

import android.os.AsyncTask;

import com.workingtitle.makeit.models.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by byeh on 16-07-09.
 */
public class getLookupTable extends AsyncTask<Recipe,Void,ArrayList<String>> {

    private ArrayList<String> ingredientsList;

    public ArrayList<String> doInBackground(Recipe... args) {

        ingredientsList = new ArrayList<String>();

        String line;
        try {
            URL url = new URL("http://159.203.61.63/v1/api/ingredients/lookup?key=cs446");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(reader);
            while((line = br.readLine()) != null) {
                ingredientsList.add(line);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return ingredientsList;
    }

    public void onPostExecute(String s) {
    }

}
