package com.android.testcode.util;

import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceHelper {
    public static final String PREF_FILE_NAME = "angelcall_pref_file";
    private final SharedPreferences preferences;

    @Inject
    public PreferenceHelper(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
    }

    public void putString(String key, String value) {
        this.preferences.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return this.preferences.getString(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        this.preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return this.preferences.getBoolean(key, false);
    }

    public boolean getBooleanDefaultTrue(String key) {
        return this.preferences.getBoolean(key, true);
    }

    public void putInt(String key, boolean value) {
        this.preferences.edit().putBoolean(key, value).apply();
    }

    public int getInt(String key) {
        return this.preferences.getInt(key, -1);
    }

    public void clear() {
        this.preferences.edit().clear().apply();
    }
}
