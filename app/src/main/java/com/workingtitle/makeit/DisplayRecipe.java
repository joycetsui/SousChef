package com.workingtitle.makeit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Joyce on 6/17/2016.
 */
public class DisplayRecipe extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void setRecipeElements(String text, int id){
        TextView tv = new TextView (this);
        tv = (TextView)findViewById(id);
        tv.setText(text);
    }

}
