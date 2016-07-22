package com.workingtitle.makeit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.workingtitle.makeit.models.RecipeCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

    private int[] imageResId = {
            R.drawable.ic_apple,
            R.drawable.ic_chef2,
            R.drawable.ic_saved,
            R.drawable.ic_list
    };

    // Viewpager to manage the tabs in the main page
    ViewPager viewPager;

    public static RecipeCollection savedRecipeCollection;

    //public static File savedRecipesFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        final MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        viewPager.setAdapter(adapter);

        //Set default tab
        viewPager.setCurrentItem(0);
        setToolbarTitle(0);

        //Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        setupTabLayout(tabLayout);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                setToolbarTitle(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        savedRecipeCollection = new RecipeCollection();
        savedRecipeCollection.loadRecipeCollection();

    }

    private void setToolbarTitle(int position){
        switch (position){
            case 0:
                setTitle(R.string.search_ingredients_tab);
                break;
            case 1:
                setTitle(R.string.search_recipe_tab);
                break;
            case 2:
                setTitle(R.string.saved_recipes);
                break;
            case 3:
                setTitle(R.string.search_history);
                break;
            default:
                setTitle("");
                break;
        }
    }

    private void setupTabLayout(TabLayout tabLayout) {

//        tabLayout.addTab(tabLayout.newTab().setIcon(imageResId[0]));
//        tabLayout.addTab(tabLayout.newTab().setIcon(imageResId[1]));
//        tabLayout.addTab(tabLayout.newTab().setIcon(imageResId[2]));
//        tabLayout.addTab(tabLayout.newTab().setIcon(imageResId[3]));

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < imageResId.length; i++){
            TextView tab = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
            tab.setCompoundDrawablesWithIntrinsicBounds(0, imageResId[i], 0, 0);
            tabLayout.getTabAt(i).setCustomView(tab);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
