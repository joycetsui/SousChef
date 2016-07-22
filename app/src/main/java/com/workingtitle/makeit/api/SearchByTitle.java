package com.workingtitle.makeit.api;

import android.os.AsyncTask;

import com.workingtitle.makeit.models.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by byeh on 16-07-22.
 */
public class SearchByTitle extends AsyncTask<String, Void, String> {

    private String RecipeList;

    public String doInBackground(String... args) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            Query query = new Query();
            query.addTerm(args[0]);
            String terms = query.buildSearchTerms();
            String url_builder = "http://159.203.61.63/v1/api/ingredients/search?term=" + terms + "&limit=10&key=cs446";
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
