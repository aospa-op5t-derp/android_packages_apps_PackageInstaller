/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.android.packageinstaller.permission.ui.handheld;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

import androidx.annotation.NonNull;
import androidx.preference.Preference;

import com.android.packageinstaller.permission.model.AppPermissionUsage;
import com.android.packageinstaller.permission.ui.AppPermissionActivity;

/**
 * A preference for representing a permission usage by an app.
 */
public class PermissionUsagePreference extends Preference {
    public PermissionUsagePreference(@NonNull Context context, @NonNull AppPermissionUsage usage,
            @NonNull CharSequence title, @NonNull String summary, @NonNull Drawable icon) {
        super(context);
        updateUi(context, usage, title, summary, icon);
    }

    private void updateUi(@NonNull Context context, @NonNull AppPermissionUsage usage,
            @NonNull CharSequence title, @NonNull String summary, @NonNull Drawable icon) {
        setKey(usage.getPackageName() + "," + usage.getPermissionGroupName());
        setTitle(title);
        setSummary(summary);
        setIcon(icon);
        setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(context, AppPermissionActivity.class);
            intent.putExtra(Intent.EXTRA_PACKAGE_NAME, usage.getPackageName());
            intent.putExtra(Intent.EXTRA_PERMISSION_NAME, usage.getPermissionName());
            intent.putExtra(Intent.EXTRA_USER, UserHandle.getUserHandleForUid(usage.getUid()));
            context.startActivity(intent);
            return true;
        });
    }
}
