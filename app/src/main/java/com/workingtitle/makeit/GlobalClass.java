package com.workingtitle.makeit;

import android.app.Application;

import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.QueryCollection;

/**
 * Created by byeh on 16-07-22.
 */
public class GlobalClass extends Application {

    private QueryCollection queryCollection = new QueryCollection();

    public QueryCollection getQueryCollection() {
        return queryCollection;
    }

    public void addQuery(Query q) {
        queryCollection.addQuery(q);
    }


}
