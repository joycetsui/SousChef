package com.workingtitle.makeit.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by byeh on 16-07-13.
 */
public class Query implements Serializable{

    private ArrayList<String> terms;
    private Integer limit;
    private String sortOptions;

    public Query() {
        terms = new ArrayList<String>();
        limit = 10;
        sortOptions = "";
    }

    public String buildSearchTerms(ArrayList<String> input) {
        String query = input.toString();
        query = query.replace("[","").replace("]","");
        return query;
    }

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

    public void removeTerm(int i) {
        terms.remove(i);
    }

    public void clearTerms() {
        terms.removeAll(terms);
    }

    public ArrayList<String> getTerms() {
        return terms;
    }








}
