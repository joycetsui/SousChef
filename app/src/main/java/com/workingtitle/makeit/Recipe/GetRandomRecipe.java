package com.workingtitle.makeit.Recipe;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Brandon on 2016-06-24.
 */
public class GetRandomRecipe extends AsyncTask<Recipe,Void,String> {

    private Recipe recipe;

    public String doInBackground(Recipe... args) {
        recipe = args[0];
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            URL url = new URL("http://159.203.61.63/v1/api/recipes/random?key=cs446");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(reader);
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void onPostExecute(String s) {
    }

}
