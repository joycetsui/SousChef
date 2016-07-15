package com.workingtitle.makeit.api;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by byeh on 16-07-09.
 */
public class SearchByIngredients extends AsyncTask<ArrayList<String>, Void, String>{

    private String RecipeList;

    public String doInBackground(ArrayList<String>... args) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            String query = new QueryBuilder().buildSearchTerms(args[0]);
            String url_builder = "http://159.203.61.63/v1/api/ingredients/search?term=" + query + "&limit=10&key=cs446";
            URL url = new URL(url_builder);
            System.out.println(url_builder);
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
