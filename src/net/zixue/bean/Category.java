package net.zixue.bean;

/**
 * Created by luckf on 2022/3/17.
 */
public class Category
{
    private int c_id;
    private String c_name;
    private String place;
    private String createtime;
    private int type;

    public int getC_id()
    {
        return c_id;
    }

    public void setC_id(int c_id)
    {
        this.c_id = c_id;
    }

    public String getC_name()
    {
        return c_name;
    }

    public void setC_name(String c_name)
    {
        this.c_name = c_name;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }



}
