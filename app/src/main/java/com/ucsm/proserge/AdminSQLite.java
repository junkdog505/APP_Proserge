package com.ucsm.proserge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class AdminSQLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "ProsergeDB.db";
    private static final int DB_VERSION = 1;
    private final Context mContext;
    public AdminSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void copyDatabase() {
        try {
            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            String outFileName = mContext.getDatabasePath(DB_NAME).getPath();

            OutputStream outputStream = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}