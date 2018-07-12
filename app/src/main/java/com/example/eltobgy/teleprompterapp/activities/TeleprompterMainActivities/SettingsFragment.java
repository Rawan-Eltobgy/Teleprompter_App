package com.example.eltobgy.teleprompterapp.activities.TeleprompterMainActivities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import com.example.eltobgy.teleprompterapp.R;

/**using prefrencefragmentcompat in order to save these preferences
 * will automatically save to SharedPreferences as the user interacts with them.
 */

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener{



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.fragment_settings);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();

        // Go through all of the preferences, and set up their preference summary.
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            // You don't need to set up preference summaries for checkbox preferences because
            // they are already set up in xml using summaryOff and summary On
            if (!(p instanceof SwitchPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }
    }



    /**
     * Updates the summary for the preference
     *
     * @param preference The preference to be updated
     * @param value      The value that the preference was updated to
     */
    private void setPreferenceSummary(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            // For list preferences, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                // Set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        Preference preference = findPreference(s);
        if (null != preference) {
            // Updates the summary for the preference
            if (!(preference instanceof SwitchPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


}
/**
 @Override
public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.fragment_settings);
        }

@Override
public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // To get a preference
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Preference preference = preferenceScreen.findPreference("preference_ key_defined_in_the_xml");

        //You can set a listener
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
@Override
public boolean onPreferenceClick(Preference preference) {
        return false;
        }
        });


        }


}
private void showDiscardDialog() {
 AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
 builder.setMessage("Discard changes?")
 .setCancelable(false)
 .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
 public void onClick(DialogInterface dialog, int id) {
 getActivity().finish();
 startActivity(new Intent(mContext, TeleprompterActivity.class));
 }
 })
 .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
 public void onClick(DialogInterface dialog, int id) {
 //  Action for 'NO' Button
 dialog.cancel();
 }
 });

 AlertDialog alert = builder.create();
 alert.show();
 }
**/