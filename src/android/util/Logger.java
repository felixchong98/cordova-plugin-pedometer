package org.apache.cordova.pedometer.util;

import android.database.Cursor;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

//import android.util.Log;

//import org.apache.cordova.BuildConfig;
import org.apache.cordova.pedometer.util.Util;

public abstract class Logger {

    private static FileWriter fw;
    private static final Date date = new Date();
    private final static String APP = "Pedometer";
    private static final String TAG = "cordova-plugin-pedometer";

    public static void log(Throwable ex) {
        log(ex.getMessage());
        for (StackTraceElement ste : ex.getStackTrace()) {
            log(ste.toString());
        }
    }

    public static void log(final Cursor c) {
        if (!Util.isDebug()) return;
        //if (!BuildConfig.DEBUG) return;
        c.moveToFirst();
        String title = "";
        for (int i = 0; i < c.getColumnCount(); i++)
            title += c.getColumnName(i) + "\t| ";
        log(title);
        while (!c.isAfterLast()) {
            title = "";
            for (int i = 0; i < c.getColumnCount(); i++)
                title += c.getString(i) + "\t| ";
            log(title);
            c.moveToNext();
        }
    }

    @SuppressWarnings("deprecation")
    public static void log(String msg) {
         if (!Util.isDebug()) return;
        //if (!BuildConfig.DEBUG) return;
        //Log.i(TAG, msg);
        //android.util.Log.i(TAG, msg);
        android.util.Log.d(APP, msg);

        //String newPath = mContext.getExternalFilesDir(null).getAbsolutePath();
        /*
        try {
            if (fw == null) {
                fw = new FileWriter(new File(
                        Environment.getExternalStorageDirectory().toString() + "/" + APP + ".txt"),
                        true);
            }
            date.setTime(System.currentTimeMillis());
            fw.write(date.toLocaleString() + " - " + msg + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    protected void finalize() throws Throwable {
        try {
            if (fw != null) fw.close();
        } finally {
            super.finalize();
        }
    }

}
