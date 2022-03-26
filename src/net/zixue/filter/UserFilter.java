package net.zixue.filter;

import net.zixue.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by luckf on 2022/3/23.
 */
//定義此Filter針對/category的servlet
@WebFilter(filterName = "UserFilter",urlPatterns ="/category")
public class UserFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {

        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;

        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if(user==null)
        {
            //指出目前狀態尚未登錄
            response.sendRedirect(request.getContextPath()+"login2.jsp");
            //攔截
            return;
        }


        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
