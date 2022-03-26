package net.zixue.service;

import net.zixue.bean.User;
import net.zixue.dao.UserDao;

import java.sql.SQLException;

/**
 * Created by luckf on 2022/3/17.
 */
public class UserService
{

    /**
     * @method:register 用戶註冊
     * @date: 2017/7/7
     * @params:[name, password, email]
     * @return: boolean
     */

    public boolean register(User user)
    {
        boolean ret=false;
        UserDao userDao=new UserDao();
        //1.取得用戶資料
        boolean checkUser=userDao.accountExist(user.getName());

        if(!checkUser)
        {
            //2.如果不存在,則將使用者資料加入到資料庫
            ret=userDao.register(user);
        }
        return ret;
    }

    /**
     * @method:login 用戶登錄
     * @date: 2017/7/7
     * @params:[name, password]
     * @return: User
     */

    public User login(String name,String password) throws SQLException
    {
        UserDao userDao=new UserDao();
        User user=userDao.login(name,password);
        return user;
    }

}
