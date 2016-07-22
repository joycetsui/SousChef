package com.workingtitle.makeit.models;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public QueryCollection() {
        queryCollection = new ArrayList<Query>();
    }

    public void addQuery(Query q) {
        queryCollection.add(q);
    }

    public ArrayList<Query> getQueryCollection() {
        return queryCollection;
    }

    public void moveToFront(int index) {
        Collections.swap(queryCollection,0,index);
    }

    public void removeQuery(int index) {
        queryCollection.remove(index);
    }

    public void saveQueryCollection(Context context){
        //String fileName = context.getResources().getString(R.string.search_history_file_name);
        String fileName = "SearchHistory";

        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadQueryCollection(Context context){
        String fileName = "SearchHistory";

        ArrayList<Query> collection = new ArrayList<Query>();

        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            collection = (ArrayList<Query>) is.readObject();
            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.queryCollection = collection;
    }

    public boolean queryExists(Query q) {
        for(Query query : queryCollection) {
            if(query.queryExists(q)) {
                return true;
            }
        }
        return false;
    }
}
