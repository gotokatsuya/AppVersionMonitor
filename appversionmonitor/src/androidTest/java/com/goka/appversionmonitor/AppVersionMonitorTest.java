package com.goka.appversionmonitor;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.ApplicationTestCase;

public class AppVersionMonitorTest extends ApplicationTestCase<Application> {

    public AppVersionMonitorTest() {
        super(Application.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMonitor() {
        // init
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getContext());
        preference.edit().clear().apply();

        // Installed
        AppVersionMonitor monitor = AppVersionMonitor.getInstance();
        monitor.init(getContext());
        assertSame(AppVersionMonitor.State.NotChanged, monitor.state);

        // Installed Version
        monitor.setInstalledAppVersion("4.0.0");
        // Compare installed with prev
        assertSame(AppVersionMonitor.State.Upgraded, monitor.getStateByPreviousVersion("3.0.0"));
    }

}
