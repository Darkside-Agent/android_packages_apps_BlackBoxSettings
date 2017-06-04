package com.blackbox.settings.fragments;

import com.android.internal.logging.MetricsProto.MetricsEvent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import com.android.settings.R;
import android.support.annotation.NonNull;

import com.android.settings.SettingsPreferenceFragment;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class PowerMenuSettings extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.blackbox_settings_power);
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.BLACKBOX_SETTINGS;
    }


}
