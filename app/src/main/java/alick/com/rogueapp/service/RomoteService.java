package alick.com.rogueapp.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import alick.com.rogueapp.BLog;
import alick.com.rogueapp.IMyAidlInterface;

public class RomoteService extends Service {
    private static final String TAG = RomoteService.class.getSimpleName();
    MyConn conn;
    MyBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        BLog.i(TAG, "--->onCreate()--->RomoteService");
        conn = new MyConn();
        binder = new MyBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        BLog.i(TAG, "--->onBind()--->RomoteService");
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(RomoteService.this, "远程服务started", Toast.LENGTH_SHORT).show();
        BLog.i(TAG, "--->onStartCommand()--->RomoteService");
        //绑定本地服务
        bindService(new Intent(this, LocalService.class), conn, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    class MyBinder extends IMyAidlInterface.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return RomoteService.class.getSimpleName();
        }
    }

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BLog.i(TAG, "--->onServiceConnected()--->RomoteService");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(RomoteService.this, "本地服务killed", Toast.LENGTH_SHORT).show();
            BLog.i(TAG, "--->onServiceDisconnected()--->RomoteService");
            //开启本地服务
            startService(new Intent(RomoteService.this, LocalService.class));
            //绑定本地服务
            bindService(new Intent(RomoteService.this, LocalService.class), conn, Context.BIND_IMPORTANT);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "远程服务销毁", Toast.LENGTH_SHORT).show();
        BLog.i(TAG, "--->onDestroy()--->RomoteService");
//        unbindService(conn);

        //绑定本地服务
//        RomoteService.this.bindService(new Intent(RomoteService.this, LocalService.class), conn, Context.BIND_IMPORTANT);

        //开启本地服务
//        RomoteService.this.startService(new Intent(RomoteService.this, LocalService.class));
    }
}