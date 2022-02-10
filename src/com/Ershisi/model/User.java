package com.Ershisi.model;

//用户用户用户用户
//这是一个存放用户信息的类 可以用于比较用户名和密码和数据库中的是否相符
public class User
{
    private int id;
    private String userName;
    private String password;

    public User()
    {//默认构造函数
        super();
    }

    public User(String userName, String password)
    {//有参构造函数
        super();
        this.userName = userName;
        this.password = password;
    }

    //获得私有变量的值
    public int getId()
    {
        return id;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }


    //给私有变量赋值
    public void setId(int id)
    {
        this.id = id;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
