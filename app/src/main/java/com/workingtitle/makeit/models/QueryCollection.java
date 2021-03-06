package com.workingtitle.makeit.models;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by byeh on 16-07-20.
 */
public class QueryCollection implements Serializable{

    ArrayList<Query> queryCollection;
    ArrayList<Query> reverseQueryCollection;

    public QueryCollection() {

        queryCollection = new ArrayList<Query>();
        reverseQueryCollection = new ArrayList<>();
    }

    public void addQuery(Query q) {
        queryCollection.add(q);
    }

    public ArrayList<Query> getQueryCollection() {
        return queryCollection;
    }

    public ArrayList<Query> getReverseQueryCollection(){
        //reverseQueryCollection.clear();
        reverseQueryCollection = (ArrayList<Query>) queryCollection.clone();
        Collections.reverse(reverseQueryCollection);

        return reverseQueryCollection;
    }

    public void moveToFront(int index) {
        Collections.swap(queryCollection,0,index);
    }

    public void removeQuery(int index) {
        queryCollection.remove(index);
    }

    public void saveQueryCollection(Context context){
        //String fileName = context.getResources().getString(R.string.search_history_file_name);
        String fileName = context.getFilesDir().getPath().toString() + "SearchHistory";

        try {
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(queryCollection);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emptyQueryCollection(Context context) {
        String fileName = context.getFilesDir().getPath().toString() + "SearchHistory";
        System.out.println(fileName);
        this.queryCollection.clear();
        this.reverseQueryCollection.clear();

        try {
            File searchHistoryFile = new File(fileName);
            // empty the current content
            FileWriter fileOut = new FileWriter(searchHistoryFile);
            fileOut.write("");
            fileOut.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadQueryCollection(Context context){
        String fileName = context.getFilesDir().getPath().toString() + "SearchHistory";

        try {
            File searchHistoryFile = new File(fileName);

            if (!searchHistoryFile.exists()) {
                searchHistoryFile.createNewFile();
            }

            FileInputStream fis = new FileInputStream(searchHistoryFile);
            ObjectInputStream is = new ObjectInputStream(fis);
            queryCollection = (ArrayList<Query>) is.readObject();

            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int queryExists(Query q) {
        int index = 0;
        for(Query query : queryCollection) {
            if(query.queryExists(q)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
