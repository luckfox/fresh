package net.zixue.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by luckf on 2022/3/18.
 */
//透過繼承的方式,

@WebServlet(name = "BaseServlet")

public class BaseServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("service@BaseServlet");

        //super.service(req, resp);
        //取得參數的method是login還是register
        String method=req.getParameter("method");
        System.out.println("method="+method+"@BaseServlet");
        if(method==null) return;
        //取得此物件的Class
        Class clazz=this.getClass();
        try
        {
            //透過此物件,取得指定的method,參數為method name,此method所使用到的參數型態串
            Method clazzMethod= clazz.getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            try
            {
                //執行此method
                if(clazzMethod!=null) clazzMethod.invoke(this,req,resp);

            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            } catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
