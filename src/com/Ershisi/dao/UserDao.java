package com.Ershisi.dao;


import java.sql.ResultSet;
import java.sql.Connection;
import com.Ershisi.model.User;
import com.Ershisi.Tools.Manager;
import java.sql.PreparedStatement;

//用户登录验证
//整个程序最关键的部分
public class UserDao extends Manager
{
    //这是一个用于判断密码是否正确的方法
    //原理是以用户输入的信息(即参数中的user对象)为依据对数据库进行检索(SELECT指令)
    //如果不存在相应的结果，返回null
    public User login(Connection con, User user)throws Exception
    {
        User retUser=null;//先设置为空 如果之后能够读取不到数据 方便直接返回空值

        //准备好sql语句
        //通过对比数据库中user表中的username和password两个字段查询出每一行的所有数据
        String sql="select * from user where userName=? and password=?";

        //使用PreparedStatement可以阻止由于sql注入引发的各种问题
        //但是直接使用Statement则不能
        PreparedStatement pps= con.prepareStatement(sql);

        //使用PreparedStatement类的setString()方法，使用user对象的两个成员变量的值给上方sql语句中的?赋值
        //1和2代表?出现的位次
        pps.setString(1,user.getUserName());
        pps.setString(2,user.getPassword());

        //使用executeQuery()方法来执行替换?之后的SELECT语句
        //并且把筛选到的结果返回到结果集中
        ResultSet retS=pps.executeQuery();

        if(retS.next())
        {
            //如果结果集不为空，说明用户名和密码都输入正确，已经筛选到了相应的值
            //retUser
            retUser=new User();//实例化对象

            //使用ResultSet的get(用String来对应varchar即可)方法，通过查找索引和列名
            //来获得结果集中当前行(因为结果集中只能有一对用户名密码，所以是第一行)的指定列的值
            retUser.setId(retS.getInt("id"));//设置ID
            retUser.setUserName(retS.getString("userName"));//设置用户名
            retUser.setPassword(retS.getString("password"));//设置密码

        }
        //返回值
        return retUser;
    }
}
