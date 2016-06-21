package com.workingtitle.makeit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Maricarla on 2016-06-13.
 */
public class SearchRecipeNameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_by_name, container, false);

        /** Reference to the button of the layout main.xml */
        Button optionsBtn = (Button) view.findViewById(R.id.byNameOptionsBtn);
        Button searchBtn = (Button) view.findViewById(R.id.byNameFeedMeBtn);

        /** Defining a click event listener for the button "Add" */
        View.OnClickListener goSearchListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage();
            }
        };

        /** Setting the event listener for the add button */
        searchBtn.setOnClickListener(goSearchListener);

        return view;
    }

    private void openResultsPage(){
        Intent intent = new Intent(getActivity(), SearchResults.class);
        Bundle b = new Bundle();
        b.putInt("toolbarBackMessage", R.string.search_recipe_tab);
        intent.putExtras(b);
        startActivity(intent);
    }
}
