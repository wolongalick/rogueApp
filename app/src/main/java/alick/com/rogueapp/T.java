package alick.com.rogueapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast统一管理类
 */
public class T {
    // Toast
    private static Toast toast;


    public static void showShort(Context context, int messageRes){
        showShort(context, context.getString(messageRes));
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (context==null) {
            return;
        }
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }


    public static void showLong(Context context, int  messageRes){
        showLong(context,context.getString(messageRes));
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /** Hide the toast, if any. */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
