package net.zixue.web;

import net.zixue.bean.Category;
import net.zixue.bean.Page;
import net.zixue.service.CategoryService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by luckf on 2022/3/19.
 */
@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("doPost@CategoryServlet");
        addCategory(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("doPost@CategoryServlet");
    }
    public void addCategory(HttpServletRequest request, HttpServletResponse response) throws   ServletException, IOException
    {
        System.out.println("addCategory@CategoryServlet");
        //將request所攜帶的paramter以Map的方式存放
        final Map<String, String[]> parameterMap = request.getParameterMap();
        Category category=new Category();
        try
        {
            BeanUtils.populate(category,parameterMap);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }

        CategoryService categoryService=new CategoryService();
        try
        {
            if(categoryService.addCategory(category))
            {
                response.setStatus(201);
                request.getRequestDispatcher("/category-add.jsp").forward(request,response);
            }else
            {
                response.setStatus(600);
                request.getRequestDispatcher("/category-add.jsp").forward(request,response);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public void getCategoryList(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException
    {
        System.out.println("getCategory@CategoryServlet");
        String param;
        int currentPage=1,currentCount=10;

        param=request.getParameter("currentPage");
        if(param!=null) currentPage=Integer.parseInt(param);

        param=request.getParameter("currentCount");
        if(param!=null) currentCount=Integer.parseInt(param);

        //1.透過CategoryService
        CategoryService service=new CategoryService();
       // List<Category> categories=service.findCategory();
        Page page=  service.findPageCategory(currentPage,currentCount);
        if(page!=null)
        {
            request.setAttribute("Page",page);
        }
        request.getRequestDispatcher("/category-list.jsp").forward(request,response);
    }
    public void deleteCategory(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException
    {
        Map<String,String[]>paramterMap= request.getParameterMap();
        Category category=new Category();

        try
        {
            BeanUtils.populate(category,paramterMap);
            CategoryService service=new CategoryService();
            boolean deleteCategory=service.deleteCategory(category);
            if(deleteCategory)
            {
                    response.sendRedirect(request.getContextPath()+"/category?method=getCategoryList");
            }else
            {
                response.setContentType("text/html;charset=UTF-8");
                try
                {
                    response.getWriter().write("刪除失敗");
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }

    }
    public void updateCategory(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException
    {
        Map<String,String[]>paramterMap= request.getParameterMap();
        Category category=new Category();
        try
        {
            BeanUtils.populate(category,paramterMap);
            CategoryService service=new CategoryService();
            boolean updateCategory=service.updateCategory(category);
            if(updateCategory)
            {
                //修改成功後返回產品列表頁面
                response.sendRedirect(request.getContextPath()+"/category?method=getCategoryList");
            }else
            {
                response.setContentType("text/html;charset=UTF-8");
                try
                {
                    response.getWriter().write("修改失敗");
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }

    }
}

