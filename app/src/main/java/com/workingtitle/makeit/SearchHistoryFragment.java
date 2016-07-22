package com.workingtitle.makeit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.workingtitle.makeit.models.Query;
import com.workingtitle.makeit.models.Recipe;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-07-22.
 */
public class SearchHistoryFragment extends Fragment {

    QueryListAdapter queryListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_history, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.searchHistoryList);

        //Hide the toolbar defined in the layout since this fragment is already part of the toolbar/tablayout
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        /******************************** Hard Coded Recipe ************************************/
        ArrayList<Query> queryList = new ArrayList<Query>();

        for (int i = 0; i < 5; i++){
            Query query = new Query(getResources().getString(R.string.search_by_recipe));
            queryList.add(query);
        }
        /**************************************************************************************/

        /** Defining the ArrayAdapter to set items to ListView */
        queryListAdapter = new QueryListAdapter(getContext(), queryList);

        /** Setting the adapter to the ListView */
        listView.setAdapter(queryListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                openRecipeDetailsPage();
            }
        });

        return view;
    }

    public static void saveQuery(Query query){
        //query.save(context);
    }
}
