package com.workingtitle.makeit.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.workingtitle.makeit.GlobalClass;
import com.workingtitle.makeit.R;

public class MainActivity extends AppCompatActivity {

    private int[] imageResId = {
            R.drawable.ic_action_cart,
            R.drawable.ic_action_restaurant,
            R.drawable.ic_action_book,
            R.drawable.ic_action_document
    };

    // Viewpager to manage the tabs in the main page
    ViewPager viewPager;

    // Options Menu
    public Menu optionsMenu;

    // Search Options info
    private int cookTimeDay;
    private int cookTimeHour;
    private int cookTimeMinute;
    private int numPortions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        globalVariable.initialize();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                onPrepareOptionsMenu(optionsMenu);
                setToolbarTitle(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
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
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
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
        optionsMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, optionsMenu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (menu != null) {
            if (viewPager.getCurrentItem() == 3) {
                menu.findItem(R.id.filterButton).setVisible(false);
                menu.findItem(R.id.clearHistory).setVisible(true);
            } else {
                menu.findItem(R.id.filterButton).setVisible(true);
                menu.findItem(R.id.clearHistory).setVisible(false);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openUserSettings();
        }
        else if (id == R.id.filterButton){
            openOptionsPage();
        }
        else if (id == R.id.clearHistory){
            clearSearchHistory();
        }

        return super.onOptionsItemSelected(item);
    }

<<<<<<< Updated upstream:app/src/main/java/com/workingtitle/makeit/controllers/MainActivity.java
    private void clearSearchHistory(){
        ((SearchHistoryFragment)((MyFragmentPagerAdapter)viewPager.getAdapter()).getItem(3)).clearSearchHistory();
=======
    private void openUserSettings(){
        Intent intent = new Intent(this, UserSettingsActivity.class);
        startActivity(intent);
>>>>>>> Stashed changes:app/src/main/java/com/workingtitle/makeit/MainActivity.java
    }

    private void openOptionsPage() {
        cookTimeDay = -1;
        cookTimeHour = -1;
        cookTimeMinute = -1;
        numPortions = -1;
        Intent intent = new Intent(this, SearchOptions.class);
        intent.putExtra("ctDay", cookTimeDay);
        intent.putExtra("ctHour", cookTimeHour);
        intent.putExtra("ctMin", cookTimeMinute);
        intent.putExtra("portions", numPortions);

        startActivityForResult(intent, 1);
    }
}
