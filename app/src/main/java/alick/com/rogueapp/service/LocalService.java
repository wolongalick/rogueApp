package alick.com.rogueapp.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import alick.com.rogueapp.BLog;
import alick.com.rogueapp.IMControl;
import alick.com.rogueapp.IMyAidlInterface;
import alick.com.rogueapp.T;

public class LocalService extends Service {
    private static final java.lang.String TAG = LocalService.class.getSimpleName();
    MyBinder binder;
    MyConn conn;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        conn = new MyConn();
    }

    class MyBinder extends IMyAidlInterface.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return LocalService.class.getSimpleName();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        T.showShort(this,"本地服务started");
        BLog.i(TAG,"本地服务started");

        this.bindService(new Intent(this, RomoteService.class), conn, Context.BIND_IMPORTANT);

        IMControl.getInstance().startIM();

        return START_STICKY;
    }

    class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            T.showShort(LocalService.this,"远程服务killed");
            BLog.i("远程服务killed");
            //开启远程服务
            startService(new Intent(LocalService.this, RomoteService.class));
            //绑定远程服务
            bindService(new Intent(LocalService.this, RomoteService.class), conn, Context.BIND_IMPORTANT);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //开启远程服务
        LocalService.this.startService(new Intent(LocalService.this, RomoteService.class));
        //绑定远程服务
        LocalService.this.bindService(new Intent(LocalService.this, RomoteService.class), conn, Context.BIND_IMPORTANT);

    }
}