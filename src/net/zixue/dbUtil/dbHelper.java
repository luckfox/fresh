package net.zixue.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by luckf on 2022/3/16.
 */
public class dbHelper
{
    private static final String driver = "com.mysql.jdbc.Driver";
    //資料庫驅動
    private static final String url = "jdbc:mysql://localhost:3306/mall";
    //要連線到的資料庫地址
    private static final String user = "root";
    //資料庫的使用者名稱
    private static final String password = "root";
    //資料庫的登入密碼

    private static Connection conn = null;//資料庫連線物件，採用單例模式（懶漢模式）

    static
    {
        try
        {
            Class.forName(driver);//載入驅動
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    //用於獲取資料庫連線物件的靜態方法，直接由類呼叫
    public  static Connection getConnection()
    {
        if(conn==null)
        {
            try
            {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return conn;
    }

}
