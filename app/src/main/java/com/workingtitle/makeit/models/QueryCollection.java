package com.workingtitle.makeit.models;

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

    public void loadQueryCollection() {
        // do something
    }

    public void saveQueryCollectin() {
        // do something
    }



}
