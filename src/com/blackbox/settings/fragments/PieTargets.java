package com.blackbox.settings.fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.ListPreference;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;

import com.android.internal.logging.MetricsProto.MetricsEvent;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class PieTargets extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String PA_PIE_MENU = "pa_pie_menu";
    private static final String PA_PIE_LASTAPP = "pa_pie_lastapp";
    private static final String PA_PIE_KILLTASK = "pa_pie_killtask";
    private static final String PA_PIE_NOTIFICATIONS = "pa_pie_notifications";
    private static final String PA_PIE_SETTINGS_PANEL = "pa_pie_settings_panel";
    private static final String PA_PIE_SCREENSHOT = "pa_pie_screenshot";

    private SwitchPreference mPieMenu;
    private SwitchPreference mPieLastApp;
    private SwitchPreference mPieKillTask;
    private SwitchPreference mPieNotifications;
    private SwitchPreference mPieQsPanel;
    private SwitchPreference mPieScreenshot;

    private ContentResolver mResolver;

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.BLACKBOX_SETTINGS;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pa_pie_targets);

        PreferenceScreen prefSet = getPreferenceScreen();

        Context context = getActivity();
        mResolver = context.getContentResolver();

        mPieMenu = (SwitchPreference) prefSet.findPreference(PA_PIE_MENU);
        mPieMenu.setChecked(Settings.System.getInt(mResolver,
                Settings.System.PA_PIE_MENU, 0) != 0);

        mPieLastApp = (SwitchPreference) prefSet.findPreference(PA_PIE_LASTAPP);
        mPieLastApp.setChecked(Settings.System.getInt(mResolver,
                Settings.System.PA_PIE_LAST_APP, 0) != 0);

        mPieKillTask = (SwitchPreference) prefSet.findPreference(PA_PIE_KILLTASK);
        mPieKillTask.setChecked(Settings.System.getInt(mResolver,
                Settings.System.PA_PIE_KILL_TASK, 0) != 0);

        mPieNotifications = (SwitchPreference) prefSet.findPreference(PA_PIE_NOTIFICATIONS);
        mPieNotifications.setChecked(Settings.System.getInt(mResolver,
                Settings.System.PA_PIE_NOTIFICATIONS, 0) != 0);

        mPieQsPanel = (SwitchPreference) prefSet.findPreference(PA_PIE_SETTINGS_PANEL);
        mPieQsPanel.setChecked(Settings.System.getInt(mResolver,
                Settings.System.PA_PIE_SETTINGS_PANEL, 0) != 0);

        mPieScreenshot = (SwitchPreference) prefSet.findPreference(PA_PIE_SCREENSHOT);
        mPieScreenshot.setChecked(Settings.System.getInt(mResolver,
                Settings.System.PA_PIE_SCREENSHOT, 0) != 0);

    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mPieMenu) {
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.PA_PIE_MENU,
                    mPieMenu.isChecked() ? 1 : 0);
        } else if (preference == mPieLastApp) {
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.PA_PIE_LAST_APP,
                    mPieLastApp.isChecked() ? 1 : 0);
        } else if (preference == mPieKillTask) {
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.PA_PIE_KILL_TASK,
                    mPieKillTask.isChecked() ? 1 : 0);
        } else if (preference == mPieNotifications) {
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.PA_PIE_NOTIFICATIONS,
                    mPieNotifications.isChecked() ? 1 : 0);
        } else if (preference == mPieQsPanel) {
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.PA_PIE_SETTINGS_PANEL,
                    mPieQsPanel.isChecked() ? 1 : 0);
        } else if (preference == mPieScreenshot) {
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.PA_PIE_SCREENSHOT,
                    mPieScreenshot.isChecked() ? 1 : 0);
        }
        return super.onPreferenceTreeClick(preference);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
