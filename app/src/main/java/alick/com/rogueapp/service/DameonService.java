package alick.com.rogueapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import alick.com.rogueapp.BLog;

/**
 * Created by cxw on 2017/2/12.
 */

public class DameonService extends Service {

    private static final String TAG = DameonService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        BLog.i(TAG, "--->onBind()");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BLog.i(TAG, "--->onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BLog.i(TAG, "--->onDestroy()");
        startService(new Intent(this, DameonService.class));

    }
}
