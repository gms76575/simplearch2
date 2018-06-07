package com.gengms.simplearch.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.gengms.simplearch.model.RealName;
import com.gengms.simplearch.model.User;

/**
 * <p>项目名称：MyDogs
 * <p>包   名： com.gengms.mydogs.data.db.entity
 * <p>版   权： 深圳宝德信科技术有限公司 2018
 * <p>描   述：
 * <p>创 建 人： gengmingshan
 * <p>创建时间： 2018/6/6 17:43
 * <p>当前版本： VERSION
 * <p>修订历史： (版本、修改时间、修改人、修改内容)
 */
@Entity(tableName = "real_name",
    foreignKeys = @ForeignKey(entity = User.class,parentColumns = "uid",childColumns = "user_id"))
public class RealNameEntity implements RealName
{
    @PrimaryKey
    private String uid;
    @ColumnInfo(name = "user_id")
    private String userId;
    @ColumnInfo(name = "id_card")
    private String idCard;
    @ColumnInfo(name = "real_name")
    private String realName;

    @Override
    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    @Override
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Override
    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    @Override
    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public RealNameEntity(String uid, String userId, String idCard, String realName)
    {
        this.uid = uid;
        this.userId = userId;
        this.idCard = idCard;
        this.realName = realName;
    }

    public RealNameEntity(RealName realName)
    {
        this.uid = realName.getUid();
        this.userId = realName.getUserId();
        this.idCard = realName.getIdCard();
        this.realName = realName.getRealName();
    }
}
