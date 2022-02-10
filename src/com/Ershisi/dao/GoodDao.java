package com.Ershisi.dao;

import java.sql.ResultSet;
import java.sql.Connection;
import com.Ershisi.model.Good;
import com.Ershisi.Tools.Manager;
import java.sql.PreparedStatement;

public class GoodDao extends Manager
{
    public int addGood(Connection con , Good good)throws Exception
    {
        String addSql="insert into good values(null,?,?,?,?,?,?)";

        String preSql="select * from good where goodName=?";
        PreparedStatement pps0=con.prepareStatement(preSql);
        pps0.setString(1,good.getGoodName());

        ResultSet retS=pps0.executeQuery();
        if(retS.next())
        {//-1表示有同名的类别存在
            return -1;
        }
        else
        {

            PreparedStatement pps = con.prepareStatement(addSql);
            pps.setString(1, good.getGoodName());
            pps.setString(2, good.getGoodDesc());
            pps.setInt(3, good.getGoodPrice());
            pps.setInt(4, good.getGoodNumber());
            pps.setString(5, good.getGoodCategory());
            pps.setInt(6, good.getCateGoryId());

            //返回添加的行数
            return pps.executeUpdate();
        }
    }

    //商品信息查询
    public ResultSet retSet(Connection con , String str,int cateid)throws Exception
    {
        ResultSet retS=null;
        String str0="%"+str+"%";

        String searchSql="";

        if(cateid!=-1)
        {
            searchSql="select * from good where cateGoryId=? and ( goodName like ? or goodDesc like ? or id like ?) ";

            PreparedStatement pps=con.prepareStatement(searchSql);
            pps.setInt(1,cateid);
            pps.setString(2,str0);
            pps.setString(3,str0);
            pps.setString(4,str0);
            retS=pps.executeQuery();
        }
        else
        {
            searchSql="select * from good where goodName like ? or goodDesc like ? or id like ?";
            PreparedStatement pps=con.prepareStatement(searchSql);
            pps.setString(1,str0);
            pps.setString(2,str0);
            pps.setString(3,str0);

            retS=pps.executeQuery();
        }
        return retS;
    }

    public int deleteGood(Connection con,String id)throws Exception
    {
        String deleteSql="delete from good where id=?";
        PreparedStatement pps=con.prepareStatement(deleteSql);
        pps.setString(1,id);

        return pps.executeUpdate();

    }

    public int updateGood(Connection con,Good good)throws Exception
    {
        String updateSql="update good set goodName=?,goodPrice=?,goodNumber=?,goodDesc=?,cateGoryId=?,goodCategory=? where id=?";
        PreparedStatement pps=con.prepareStatement(updateSql);
        pps.setString(1,good.getGoodName());
        pps.setInt(2,good.getGoodPrice());
        pps.setInt(3,good.getGoodNumber());
        pps.setString(4,good.getGoodDesc());
        pps.setInt(5,good.getCateGoryId());
        pps.setString(6,good.getGoodCategory());
        pps.setInt(7,good.getId());

        return pps.executeUpdate();
    }

}
