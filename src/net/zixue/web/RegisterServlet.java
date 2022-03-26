package net.zixue.web;

import com.mchange.v2.beans.BeansUtils;
import com.mchange.v2.codegen.bean.BeangenUtils;
import net.zixue.bean.Category;
import net.zixue.bean.User;
import net.zixue.service.UserService;
import net.zixue.webUtils.WebUtil1;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * Created by luckf on 2022/3/17.
 */
@WebServlet(name = "RegiisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }



}
