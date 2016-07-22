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
public class GetLookupTable extends AsyncTask<Void,Void,String>{

    private String ingredientsList;

    public String doInBackground(Void... args) {

        StringBuilder sb = new StringBuilder();
        String line;
        try {
            URL url = new URL("http://159.203.61.63/v1/api/ingredients/lookup?key=cs446");
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
