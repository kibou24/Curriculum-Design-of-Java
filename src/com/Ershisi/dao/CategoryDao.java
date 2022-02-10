package com.Ershisi.dao;

import java.sql.ResultSet;
import java.sql.Connection;
import com.Ershisi.Tools.Manager;
import com.Ershisi.model.Category;
import java.sql.PreparedStatement;

//连接数据库
public class CategoryDao extends Manager
{
    //用于类别添加
    public int addNewCate(Connection con, Category cate)throws Exception
    {
        String addSql="insert into category values(null,?,?)";

        String preSql="select * from category where categoryName=?";
        PreparedStatement pps0=con.prepareStatement(preSql);
        pps0.setString(1,cate.getCategoryName());

        ResultSet retS=pps0.executeQuery();
        if(retS.next())
        {//-1表示有同名的类别存在
            return -1;
        }
        else
        {
            PreparedStatement pps=con.prepareStatement(addSql);
            pps.setString(1,cate.getCategoryName());
            pps.setString(2,cate.getCategoryDesc());

            //方法executeUpdate()用于执行INSERT语句
            //执行成功返回1
            return pps.executeUpdate();
        }


    }

    //用于类别查询 返回结果集
    public ResultSet retSet(Connection con,String str)throws Exception
    {
        String searchSql="select * from Category where id like ? or categoryName like ? or categoryDesc like ?";
        PreparedStatement pps=con.prepareStatement(searchSql);
        String str0="%"+str+"%";
        pps.setString(1,str0);
        pps.setString(2,str0);
        pps.setString(3,str0);

        ResultSet retS=pps.executeQuery();
        return  retS;
    }

    //更新(修改)类别
    public int updateCategory(Connection con,Category cate)throws Exception
    {
        //在修改之前查找到该行类别
        String findOldCate="select * from Category where id=?";
        PreparedStatement pps2=con.prepareStatement(findOldCate);
        pps2.setInt(1,cate.getId());
        ResultSet retS=pps2.executeQuery();

        //保存旧的类别名称
        String namePre="";
        if(retS.next())
        {
            namePre = retS.getString("categoryName");
        }

        //注意先更新商品信息中所属类别名
        String updateGoodCategory="update good set goodCategory=? where cateGoryId=?";
        PreparedStatement pps3=con.prepareStatement(updateGoodCategory);
        pps3.setString(1,cate.getCategoryName());
        pps3.setInt(2,cate.getId());
        pps3.executeUpdate();

        //再修改当前类别名称
        String updateSql="update Category set categoryName=?,categoryDesc=? where id=?";
        PreparedStatement pps=con.prepareStatement(updateSql);
        pps.setString(1,cate.getCategoryName());
        pps.setString(2,cate.getCategoryDesc());
        pps.setInt(3,cate.getId());


        return pps.executeUpdate();
    }

    public int deleteCategory(Connection con,String id)throws Exception
    {
        //因为在Category表中，id是主键，所以用它来决定删除哪一个类别
        String deleteSql="delete from Category where id=?";
        PreparedStatement pps=con.prepareStatement(deleteSql);
        pps.setString(1,id);

        //返回成功删除的条数
        return pps.executeUpdate();
    }

}
