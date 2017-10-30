package me.dev.bkk.hivsurvey;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by BKK on 9/10/2560.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initialFont();
        initialPrefs();
    }

    private void initialPrefs() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    private void initialFont(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DroidSans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
