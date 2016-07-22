package com.workingtitle.makeit.models;

import android.content.Context;

import java.io.File;
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

    public void loadQueryCollection(Context context){
        String fileName = context.getFilesDir().getPath().toString() + "SearchHistory";

        try {
            FileInputStream fis = new FileInputStream(new File(fileName));
            ObjectInputStream is = new ObjectInputStream(fis);
            queryCollection = (ArrayList<Query>) is.readObject();
            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
