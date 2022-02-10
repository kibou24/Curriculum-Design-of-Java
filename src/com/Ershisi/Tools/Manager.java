package com.Ershisi.Tools;

import java.sql.Connection;
import java.sql.DriverManager;

//用于操作数据库的类
public class Manager
{
    //数据库url   jdbc   3306端口   database_homework表
    private String Url ="jdbc:mysql://localhost:3306/database_homework";
    private String UserName ="root";//数据库用户名
    private String Pwd ="123456"; //数据库密码
    private String jdbcName="com.mysql.cj.jdbc.Driver"; //数据库驱动

    //连接数据库
    //抛出异常
    public Connection getConnection()throws Exception
    {
        //加载驱动
        Class.forName(jdbcName);

        //连接数据库
        Connection con=DriverManager.getConnection(Url, UserName, Pwd);

        //返回数据库连接状态
        return con;
    }

    //关闭数据库连接
    public void closeDataBase(Connection con)throws Exception
    {
        //如果不为空，说明已经连接上了，可以断开
        if(con!=null)
        {
            con.close();
        }
    }

}
