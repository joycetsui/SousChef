package com.workingtitle.makeit.models;

import android.content.Context;
import android.content.res.Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by byeh on 16-07-13.
 */
public class Query implements Serializable{

    private ArrayList<String> terms;
    private Integer limit;
    private String sortOptions;
    private String type;

    public Query(String type) {
        terms = new ArrayList<String>();
        limit = 10;
        sortOptions = "";
        this.type = type;
    }

    public boolean queryExists(Query q) {
        return this.equals(q);
    }

    public String buildSearchTerms() {
        String query = terms.toString();
        query = query.replace("[","").replace("]","");
        return query;
    }

    public void setTerms(ArrayList<String> terms){ this.terms = terms; }

    public void updateLimit(int l) {
        limit = l;
    }

    public Integer getLimit() {
        return limit;
    }

    public void updateSortOptions(String s) {
        sortOptions = s;
    }

    public String getSortOptions() {
        return sortOptions;
    }

    public void addTerm(String s) {
        terms.add(s);
    }

    public void addTerm(ArrayList<String> s) {terms = s;}

    public void removeTerm(int i) {
        terms.remove(i);
    }

    public void clearTerms() {
        terms.removeAll(terms);
    }

    public ArrayList<String> getTerms() {
        return terms;
    }

    public void setQueryType(String type){ this.type = type; }

    public String getQueryType(){ return this.type; }
}
