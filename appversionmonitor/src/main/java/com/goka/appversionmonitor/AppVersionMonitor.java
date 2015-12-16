/**
 Copyright (c) 2015 muukii <m@muukii.me>
 Copyright (c) 2015 gotokatsuya

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */

package com.goka.appversionmonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class AppVersionMonitor {

    public static final String TAG = AppVersionMonitor.class.getSimpleName();

    public enum State {
        NotChanged,
        Upgraded,
        Downgraded;
    }

    private static AppVersionMonitor instance = new AppVersionMonitor();

    public static AppVersionMonitor getInstance() {
        return instance;
    }

    public State state;
    private AppVersion installedAppVersion;

    public void init(Context context) {
        String manifestVersionName = getManifestVersionName(context);
        installedAppVersion = new AppVersion(manifestVersionName);
        state = State.NotChanged;
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        String preInstalledVersion = preference.getString(TAG, "");
        if (!TextUtils.isEmpty(preInstalledVersion)) {
            state = getStateByPreviousVersion(preInstalledVersion);
        }
        preference.edit().putString(TAG, installedAppVersion.version).apply();
    }

    public State getStateByPreviousVersion(String preVersion) {
        int compare = installedAppVersion.compareTo(new AppVersion(preVersion));
        switch (compare) {
            case 0:
                return State.NotChanged;
            case 1:
                return State.Upgraded;
            case -1:
                return State.Downgraded;
        }
        return null;
    }

    private String getManifestVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        String versionName = "";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    // You should read manifest version
    @Deprecated
    public void setInstalledAppVersion(String installedAppVersion) {
        this.installedAppVersion = new AppVersion(installedAppVersion);
    }

}
