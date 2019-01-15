package com.chris.androidjetpack.room;

import android.content.Context;
import androidx.room.Room;

/**
 * Created by jianjianhong on 19-1-15
 */
public class AppDatabaseUtil {
    private static AppDatabaseUtil instance = new AppDatabaseUtil();
    private static final String DATABASE_NAME = "android-jetpack";
    private AppDatabase db;
    private AppDatabaseUtil (){}
    public static AppDatabaseUtil getInstance() {
        return instance;
    }

    public AppDatabase getAppDataBase(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return db;
    }
}
