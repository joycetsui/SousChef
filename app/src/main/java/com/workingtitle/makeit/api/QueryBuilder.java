package com.workingtitle.makeit.api;

import java.util.ArrayList;

/**
 * Created by byeh on 16-07-13.
 */
public class QueryBuilder {

    public String buildSearchTerms(ArrayList<String> input) {
        String query = input.toString();
        query = query.replace("[","").replace("]","");
        return query;
    }
}
