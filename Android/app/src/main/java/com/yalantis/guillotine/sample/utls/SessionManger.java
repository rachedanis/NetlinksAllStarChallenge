package com.yalantis.guillotine.sample.utls;

/**
 * Created by WiKi on 21/02/2016.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.android.gms.maps.model.LatLng;
import com.yalantis.guillotine.sample.LoginActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SessionManger {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_ID = "id";
    public static final String KEY_LAT = "loc";
    public static final String KEY_LON = "lDSc";


    // Constructor
    public SessionManger(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String id) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_ID, id);


        // commit changes
        editor.commit();
    }

    public void setLoc(String a, String b) {
        // Storing login value as TRUE
        // Storing name in pref
        editor.putString(KEY_LAT, a);
        editor.putString(KEY_LON, b);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public boolean checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
//            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            // Staring Login Activity
            _context.startActivity(i);
            return false;
        }
        return true;
    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_LAT, pref.getString(KEY_LAT, null));
        user.put(KEY_LON, pref.getString(KEY_LON, null));

        // return user
        return user;
    }


    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        IRepository rep = new Repository();
        List<History> histories = rep.getAll();
        for (History h : histories)
            h.delete();
        Intent intentstop = new Intent(_context, MyReceiver.class);
        PendingIntent senderstop = PendingIntent.getBroadcast(_context,
                234324243, intentstop, 0);
        AlarmManager alarmManagerstop = (AlarmManager) _context.getSystemService(_context.ALARM_SERVICE);
        alarmManagerstop.cancel(senderstop);
        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}