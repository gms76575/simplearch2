package com.gengms.simplearch.data;

import android.arch.lifecycle.LiveData;

import com.gengms.simplearch.data.db.AppDatabase;
import com.gengms.simplearch.data.db.entity.UserEntity;

/**
 * Repository handling the work with products and comments.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public void insertOrUpdate(UserEntity user){
        mDatabase.userDao().insertOrUpdate(user);
    }

    public LiveData<UserEntity> getUserInfo(String uid){
        return mDatabase.userDao().getUserInfo(uid);
    }

    public boolean notOutTime(String uid, long outTime){
        return mDatabase.userDao().notOutTime(uid, outTime)>0;
    }

}
