package com.mvopo.mytune;

import android.app.Application;

import com.mvopo.mytune.models.DaoMaster;
import com.mvopo.mytune.models.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DBApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "tune_db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
