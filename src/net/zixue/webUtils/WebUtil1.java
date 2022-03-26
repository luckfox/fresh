package net.zixue.webUtils;

import net.zixue.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by luckf on 2022/3/19.
 */
public class WebUtil1
{
    public User getParamters1(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException
    {
        User user=new User();
        Map<String,String[]> parameterMap=request.getParameterMap();
        BeanUtils.populate(user,parameterMap);
        return user;
    }

    public User getParamters2(HttpServletRequest request)
    {
        User user=new User();
        String stringID=request.getParameter("id");
        if(stringID!=null) user.setId(Integer.parseInt(stringID));
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        return user;
    }
}
