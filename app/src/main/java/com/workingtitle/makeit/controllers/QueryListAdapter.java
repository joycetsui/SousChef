package com.workingtitle.makeit.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.workingtitle.makeit.R;
import com.workingtitle.makeit.models.Query;

import java.util.ArrayList;

/**
 * Created by Maricarla on 2016-07-22.
 */
public class QueryListAdapter extends ArrayAdapter<Query> {
    private final Context context;
    ArrayList<Query> queries = new ArrayList<Query>();

    public QueryListAdapter(Context context, ArrayList <Query> queries) {
        super(context, -1, queries);

        this.context = context;
        this.queries = queries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.search_history_row_layout, parent, false);

        TextView searchTypeTextView = (TextView) rowView.findViewById(R.id.searchType);
        TextView searchParamsTextView = (TextView) rowView.findViewById(R.id.searchParams);

        searchTypeTextView.setText(queries.get(position).getQueryType());
        searchParamsTextView.setText(queries.get(position).buildSearchTerms());

        return rowView;
    }
}
