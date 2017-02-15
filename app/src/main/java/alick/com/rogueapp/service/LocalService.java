package alick.com.rogueapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import alick.com.rogueapp.BLog;
import alick.com.rogueapp.IMControl;
import alick.com.rogueapp.IMyAidlInterface;

public class LocalService extends Service {
    private static final java.lang.String TAG = LocalService.class.getSimpleName();
    MyBinder binder;
//    MyConn conn;

    class MyBinder extends IMyAidlInterface.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return LocalService.class.getSimpleName();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BLog.i(TAG,"--->onCreate()--->LocalService");
        binder = new MyBinder();
//        conn = new MyConn();
//        this.bindService(new Intent(this, RomoteService.class), conn, Context.BIND_IMPORTANT);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        BLog.i(TAG,"--->onBind()--->LocalService");
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(LocalService.this,"本地服务started",Toast.LENGTH_SHORT).show();
        BLog.i(TAG,"--->onStartCommand()--->LocalService");

        IMControl.getInstance().startIM();
        return START_STICKY;
    }

    /*class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BLog.i(TAG,"--->onServiceConnected()--->LocalService");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(LocalService.this,"远程服务killed",Toast.LENGTH_SHORT).show();
            BLog.i(TAG,"--->onServiceConnected()--->LocalService");
            //开启远程服务
            startService(new Intent(LocalService.this, RomoteService.class));
//            //绑定远程服务
//            bindService(new Intent(LocalService.this, RomoteService.class), conn, Context.BIND_IMPORTANT);
        }
    }*/

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"本地服务解绑",Toast.LENGTH_SHORT).show();
        BLog.i(TAG,"--->onUnbind()--->LocalService");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"本地服务销毁",Toast.LENGTH_SHORT).show();
        BLog.i(TAG,"--->onDestroy()--->LocalService");
//        unbindService(conn);

        //绑定远程服务
//        LocalService.this.bindService(new Intent(LocalService.this, RomoteService.class), conn, Context.BIND_IMPORTANT);

        //开启远程服务
//        LocalService.this.startService(new Intent(LocalService.this, RomoteService.class));
    }
}