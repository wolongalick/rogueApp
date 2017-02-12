package alick.com.rogueapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import alick.com.rogueapp.BLog;
import alick.com.rogueapp.IMControl;
import alick.com.rogueapp.MainActivity;
import alick.com.rogueapp.R;

/**
 * Created by cxw on 2017/2/10.
 */

public class RogueService extends Service {
    public static final String TAG = RogueService.class.getSimpleName();

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BLog.i(TAG, "--->onStartCommand()");
        IMControl.getInstance().startIM();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setTicker("有通知到来")
                .setContentTitle("这是通知的标题")
                .setContentText("这是通知的内容")
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .build();
//                .build();
        /*使用startForeground,如果id为0，那么notification将不会显示*/
        startForeground(0, notification);

        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BLog.i(TAG, "--->onDestroy()");
        startService(new Intent(this, RogueService.class));
    }
}
