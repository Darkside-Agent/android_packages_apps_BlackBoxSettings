package com.blackbox.settings.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.SwitchPreference;
import android.provider.Settings;

import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.settings.DevelopmentSettings;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class MiscSettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String KEY_LOCK_CLOCK = "lock_clock";
    private static final String KEY_LOCK_CLOCK_PACKAGE_NAME = "com.cyanogenmod.lockclock";
    private static final String SCREENSHOT_TYPE = "screenshot_type";

    private ListPreference mScreenshotType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.blackbox_settings_misc);

        // mLockClock
        if (!DevelopmentSettings.isPackageInstalled(getActivity(), KEY_LOCK_CLOCK_PACKAGE_NAME)) {
            getPreferenceScreen().removePreference(findPreference(KEY_LOCK_CLOCK));
        }

        mScreenshotType = (ListPreference) findPreference(SCREENSHOT_TYPE);
        int mScreenshotTypeValue = Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.SCREENSHOT_TYPE, 0);
        mScreenshotType.setValue(String.valueOf(mScreenshotTypeValue));
        mScreenshotType.setSummary(mScreenshotType.getEntry());
        mScreenshotType.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if  (preference == mScreenshotType) {
            int mScreenshotTypeValue = Integer.parseInt(((String) newValue).toString());
            mScreenshotType.setSummary(
                    mScreenshotType.getEntries()[mScreenshotTypeValue]);
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREENSHOT_TYPE, mScreenshotTypeValue);
            mScreenshotType.setValue(String.valueOf(mScreenshotTypeValue));
            return true;
        }
        return false;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.BLACKBOX_SETTINGS;
    }

}
