package net.zixue.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.zixue.bean.Category;
import net.zixue.dbUtil.dbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by luckf on 2022/3/19.
 */
public class CategoryDao
{
    public boolean addCategory(Category category) throws SQLException
    {
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="insert into category value(null,?,?,?,?)";
        LocalDate now = LocalDate.now();
        category.setCreatetime(now.toString());
        int iret = queryRunner.update(sql, category.getC_name(), category.getPlace(), category.getCreatetime(), category.getType());
        if(iret>0) return true;
        return false;
    }

    public List<Category> queryCategoryList() throws SQLException
    {
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from category";

        List<Category> categoryList=queryRunner.query(sql,new BeanListHandler<Category >(Category.class));
        return categoryList;

    }

    /**
     * @method:accountExist 確認帳戶是否存在
     * @date: 2017/7/7
     * @params:[User]
     * @return: bool=true 帳戶存在,bool=false 帳戶不存在
     */

    public List<Category> queryPageCategoryList(int startPosition,int currentCount) throws SQLException
    {
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from category limit ?,?";

        List<Category> categoryList=queryRunner.query(sql,new BeanListHandler<Category >(Category.class),startPosition,currentCount);
        return categoryList;

    }
    public int queryCount() throws SQLException
    {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select count(*) from category";
        Long ret=queryRunner.query(sql, new ScalarHandler<>());
        return ret.intValue();
    }
    public boolean updateCategory(Category category) throws SQLException, ClassNotFoundException
    {

        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="update category set c_name=?,place=?,type=? where c_id=?";
        int iret=queryRunner.update(sql,category.getC_name(),category.getPlace(),category.getType(),category.getC_id());

        if(iret>0) return true;
        return false;
    }

    public boolean deleteCategory(Category category) throws SQLException
    {
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="delete from  category where c_id=?";
        int iret=queryRunner.update(sql,category.getC_id());
        if(iret>0) return true;
        return false;

    }
}

