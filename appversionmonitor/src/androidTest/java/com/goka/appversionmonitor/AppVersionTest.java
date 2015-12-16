package com.goka.appversionmonitor;

import android.app.Application;
import android.test.ApplicationTestCase;

public class AppVersionTest extends ApplicationTestCase<Application> {

    public AppVersionTest() {
        super(Application.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Equal
     */
    public void testEqualVersion() {
        AppVersion l = new AppVersion("3.0.0");
        AppVersion r = new AppVersion("3.0.0");
        assertEquals(0, l.compareTo(r));
    }


    /**
     * Up
     */
    public void testUpVersion() {
        AppVersion l = new AppVersion("3.1.0");
        AppVersion r = new AppVersion("3.0.0.5");
        assertEquals(1, l.compareTo(r));
    }

    /**
     * Down
     */
    public void testDownVersion() {
        AppVersion l = new AppVersion("2.9.1.0");
        AppVersion r = new AppVersion("3.0.0");
        assertEquals(-1, l.compareTo(r));
    }

}
