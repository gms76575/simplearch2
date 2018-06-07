package com.gengms.simplearch.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.gengms.simplearch.model.User;

import java.util.Date;

/**
 * <p>项目名称：MyDogs
 * <p>包   名： com.gengms.mydogs.data.db.entity
 * <p>版   权： 深圳宝德信科技术有限公司 2018
 * <p>描   述：
 * <p>创 建 人： gengmingshan
 * <p>创建时间： 2018/6/6 17:15
 * <p>当前版本： VERSION
 * <p>修订历史： (版本、修改时间、修改人、修改内容)
 */
@Entity(tableName = "user")
public class UserEntity implements User
{
    @NonNull
    @PrimaryKey
    private String uid;
    private String name;
    private String phone;
    private int gender;
    @ColumnInfo(name = "update_at")
    private Date updateAt;

    @NonNull
    @Override
    public String getUid()
    {
        return uid;
    }

    public void setUid(@NonNull String uid)
    {
        this.uid = uid;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    @Override
    public Date getUpdateAt()
    {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt)
    {
        this.updateAt = updateAt;
    }

    public UserEntity(@NonNull String uid, String name, String phone, int gender, Date updateAt)
    {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.updateAt = updateAt;
    }

    //如果有多个构造方法，使用 @Ignore注解告诉Room哪个用，哪个不用。
    @Ignore
    public UserEntity(User user)
    {
        this.uid = user.getUid();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.updateAt = user.getUpdateAt();
    }
}
