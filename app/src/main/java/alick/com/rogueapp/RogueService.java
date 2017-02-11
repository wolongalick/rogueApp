package alick.com.rogueapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by cxw on 2017/2/10.
 */

public class RogueService extends Service {
    public static final String TAG = RogueService.class.getSimpleName();

    private boolean isRunning;
    private int index;


    @Nullable
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        BLog.i(TAG, "--->onStartCommand()");
        startIM();
        return Service.START_STICKY;
    }

    private void startIM() {
        if (!isRunning) {
            isRunning = true;
            new Thread() {
                @Override
                public void run() {
                    while (isRunning){
                        BLog.i("正在运行:"+(index++));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BLog.i(TAG, "--->onDestroy()");
        startService(new Intent(this, RogueService.class));
    }
}
