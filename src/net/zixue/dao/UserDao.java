package net.zixue.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.zixue.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;


public class UserDao
{
    /**
     * @method:accountExist 確認帳戶是否存在
     * @date: 2017/7/7
     * @params:[User]
     * @return: bool=true 帳戶存在,bool=false 帳戶不存在
     */
    public boolean accountExist(String name)
    {
        boolean ret = false;
        try
        {
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="select name from user where name=?";
            User user=queryRunner.query(sql,new BeanHandler<User>(User.class),name);
            if(user!=null) ret=true;//帳號已經存在
            else ret=false;
        } catch (SQLException e)
        {
            e.printStackTrace();
            ret=false;
        }finally
        {
            return ret;
        }
    }
    /**
     * @method:register 註冊使用者
     * @date: 2017/7/7
     * @params:[User]
     * @return: bool=true 註冊成功,bool=false 註冊失敗
     */

    public boolean register(User user)
    {
        try
        {
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="insert into user values(null,?,?,?)";
            int iRet=queryRunner.update(sql,user.getName(),user.getPassword(),user.getEmail());
            if(iRet>0)
            {
                //註冊成功
                return true;
            }else return false;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @method:login 根據帳號密碼進行登錄
     * @date: 2017/7/7
     * @params:[name, password]
     * @return: User
     */

    public User login(String name,String password) throws SQLException
    {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from user where name=? and password=?";
        User user=queryRunner.query(sql,new BeanHandler<User>(User.class),name,password);
        return user;
    }

}
