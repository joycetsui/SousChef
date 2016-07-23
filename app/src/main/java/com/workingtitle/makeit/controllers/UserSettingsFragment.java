package com.workingtitle.makeit.controllers;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.widget.Toast;


import com.workingtitle.makeit.R;

/**
 * Created by xiwen on 22/7/16.
 */
public class UserSettingsFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

    public boolean prefIsEnabled(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPref.getBoolean(key, true);
    }
}
