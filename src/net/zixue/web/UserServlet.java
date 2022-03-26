package net.zixue.web;

import net.zixue.bean.User;
import net.zixue.service.UserService;
import net.zixue.webUtils.WebUtil1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by luckf on 2022/3/18.
 */


//說明:使用login2.jsp
//將兩個form的action都設定為user,並且在兩個form內都增加一個method 的paramter,
//value分別為login,register,因而當此servlet被喚起時候,再透過request所攜帶的paramter
//去判斷是哪個from所傳過來的post
@WebServlet(name = "UserServlet",urlPatterns = "/user")


//public class UserServlet extends HttpServlet
public class UserServlet extends BaseServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("doPost@UserServlet");
        String method=request.getParameter("method");
        if(method!=null && method.equals("login"))
        {
            login(request,response);
        }else if(method!=null && method.equals("register"))
        {
            register(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("doGet@UserServlet");
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("login@UserServlet");
        User user=null;
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        UserService userService=new UserService();
        try
        {
            user = userService.login(name, password);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        if(user!=null)
        {

            String remember=request.getParameter("remember");
            if(remember!=null && remember.equals("yes"))
            {
                //1.將帳號和密碼加到cookie
                Cookie nameCookie=new Cookie("name",name);
                Cookie passwordCookie=new Cookie("password",password);
                //2.設置cookie的效期
                nameCookie.setMaxAge(60*60*24*7);
                passwordCookie.setMaxAge(60*60*24*7);
                //3.透過cookie發送到前端保存
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
                //4.後續由前端的jsp部分擷取此cookie,並且處理
            }
            request.getSession().setAttribute("user",user);
            //response.sendRedirect(request.getContextPath()+"category-list.jsp");
            response.sendRedirect(request.getContextPath()+"/category?method=getCategoryList");
        }
        else
        {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用戶登錄失敗");
        }
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        System.out.println("register@UserServlet");
        User user= null;
        // User user=getParamters2(request);
        WebUtil1 w=new WebUtil1();
        try
        {
            user = w.getParamters1(request);
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        UserService userService=new UserService();

        if(userService.register(user))
        {
            response.sendRedirect(request.getContextPath()+"login.jsp");
        }else
        {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("註冊失敗");
        }

    }
}
