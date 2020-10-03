
package fr.intech.bacon.projeti;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyApplication","Hello");
        Stetho.initializeWithDefaults(this);
    }
}