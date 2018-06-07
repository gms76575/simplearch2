package com.gengms.simplearch.model;

import java.util.Date;

/**
 * <p>项目名称：MyDogs
 * <p>包   名： com.gengms.mydogs.model
 * <p>版   权： 深圳宝德信科技术有限公司 2018
 * <p>描   述：
 * <p>创 建 人： gengmingshan
 * <p>创建时间： 2018/6/6 17:08
 * <p>当前版本： VERSION
 * <p>修订历史： (版本、修改时间、修改人、修改内容)
 */
public interface User
{
    String getUid();
    String getName();
    String getPhone();
    int getGender();
    Date getUpdateAt();
}
