package net.zixue.web;

import net.zixue.bean.User;
import net.zixue.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by luckf on 2022/3/18.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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

            response.sendRedirect(request.getContextPath()+"category-list.jsp");
        }
        else
        {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用戶登錄失敗");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
