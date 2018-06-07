package com.gengms.simplearch.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gengms.simplearch.data.db.entity.UserEntity;

/**
 * <p>项目名称：MyDogs
 * <p>包   名： com.gengms.mydogs.data.db.dao
 * <p>版   权： 深圳宝德信科技术有限公司 2018
 * <p>描   述：
 * <p>创 建 人： gengmingshan
 * <p>创建时间： 2018/6/6 17:18
 * <p>当前版本： VERSION
 * <p>修订历史： (版本、修改时间、修改人、修改内容)
 */
@Dao
public interface UserDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(UserEntity user);

    @Query("select uid,name,phone,gender,update_at from user where uid=:uid")
    LiveData<UserEntity> getUserInfo(String uid);

    @Query("select count(uid) from user where uid=:uid and date('now') - update_at<:outTime")
    int notOutTime(String uid, long outTime);
}
