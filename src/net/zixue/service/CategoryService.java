package net.zixue.service;

import net.zixue.bean.Category;
import net.zixue.bean.Page;
import net.zixue.dao.CategoryDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by luckf on 2022/3/19.
 */
public class CategoryService
{
    public boolean addCategory(Category category) throws SQLException
    {
        CategoryDao dao=new CategoryDao();
        return dao.addCategory(category);
    }

    public List<Category> findCategory() throws SQLException
    {
        CategoryDao dao=new CategoryDao();
        int totalCount=dao.queryCount();
        List<Category> categories=dao.queryCategoryList();
        return categories;
    }

    public Page findPageCategory(int currentPage,int currentCount) throws SQLException
    {
        Page page=new Page();
        CategoryDao dao=new CategoryDao();
        int totalCount=dao.queryCount();
        int totalPage=(int)Math.ceil(1.0*totalCount/currentCount);

        page.setCurrentCount(currentCount);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);

        page.setTotalPage(totalPage);

        int startPosition=(currentPage-1)*currentCount;
        List<Category> categories=dao.queryPageCategoryList(startPosition,currentCount);
        page.setList(categories);
        return page;
    }
    public boolean updateCategory(Category category) throws SQLException, ClassNotFoundException
    {
        CategoryDao dao=new CategoryDao();
        boolean updateCategory;
        if (dao.updateCategory(category)) updateCategory = true;
        else updateCategory = false;
        return updateCategory;
    }

    public boolean deleteCategory(Category category) throws SQLException
    {
        CategoryDao dao=new CategoryDao();
        boolean deleteCategory;
        if(dao.deleteCategory(category)) deleteCategory = true;
        else deleteCategory=false;
        return deleteCategory;
    }
}

