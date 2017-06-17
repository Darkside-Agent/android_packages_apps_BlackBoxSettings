/*
 * Copyright (C) 2017 BlackBox Hybrid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blackbox.settings.fragments.doze;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DozeReceiver extends BroadcastReceiver {

    private static final boolean DEBUG = false;
    private static final String TAG = "BlackBoxDoze";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            if (Utils.isDozeEnabled(context)) {
                if (DEBUG) Log.d(TAG, "Starting service");
                Utils.startService(context);
            }
        }
    }
}
