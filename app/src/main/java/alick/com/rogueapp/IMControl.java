package alick.com.rogueapp;

/**
 * Created by cxw on 2017/2/11.
 */
public class IMControl {
    private boolean isRunning;
    private int index;

    private static IMControl ourInstance = new IMControl();

    public static IMControl getInstance() {
        return ourInstance;
    }

    private IMControl() {
    }

    void startIM() {
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
}
