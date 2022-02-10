package com.Ershisi.view;

import com.Ershisi.model.Good;
import com.Ershisi.dao.GoodDao;
import com.Ershisi.Tools.Manager;
import com.Ershisi.model.Category;
import com.Ershisi.dao.CategoryDao;
import com.Ershisi.Tools.StringIsValid;


import java.awt.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GoodAdd extends MMain
{
    private JPanel panel1;
    public JFrame frame;

    public void showFrame()
    {
        GoodAdd goodAdd=new GoodAdd();
        goodAdd.frame = new JFrame("商品添加");
        goodAdd.frame.setContentPane(new GoodAdd().panel1);
        goodAdd.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goodAdd.frame.pack();

        goodAdd.frame.setSize(965,751);
        goodAdd.frame.setLocation(455,155);
        goodAdd.frame.setResizable(false);

        goodAdd.frame.setVisible(true);

        //设置图标
        ImageIcon titleImg=new ImageIcon("src/img/titleImg.png");
        goodAdd.frame.setIconImage(titleImg.getImage());

        //背景图片
        ImageIcon bkIcon=new ImageIcon("src/img/bkG.png");
        JLabel bk=new JLabel(bkIcon);
        bk.setBounds(0,0,bkIcon.getIconWidth(),bkIcon.getIconHeight());
        goodAdd.frame.getLayeredPane().add(bk, new Integer(-1));

        //商品名称文字
        JLabel goodName=new JLabel("商品名称：");
        goodAdd.frame.getLayeredPane().add(goodName, new Integer(10));
        goodName.setFont(new Font("", Font.BOLD, 25));
        goodName.setBounds(bkIcon.getIconWidth()/2-128,bkIcon.getIconHeight()/2-190,150,50);

        //商品名称输入框
        JTextField nameJTF=new JFormattedTextField();
        nameJTF.setFont(new Font("", Font.BOLD, 24));
        nameJTF.setBounds(bkIcon.getIconWidth()/2+20,bkIcon.getIconHeight()/2-185,200,40);
        goodAdd.frame.getLayeredPane().add(nameJTF, new Integer(10));

        //商品数量文字
        JLabel goodNumber=new JLabel("商品数量：");
        goodAdd.frame.getLayeredPane().add(goodNumber, new Integer(10));
        goodNumber.setFont(new Font("", Font.BOLD, 25));
        goodNumber.setBounds(bkIcon.getIconWidth()/2-128,bkIcon.getIconHeight()/2-30,150,50);

        //商品数量输入框
        JTextField numberJTF=new JFormattedTextField();
        numberJTF.setFont(new Font("", Font.BOLD, 24));
        numberJTF.setBounds(bkIcon.getIconWidth()/2+20,bkIcon.getIconHeight()/2-25,100,40);
        goodAdd.frame.getLayeredPane().add(numberJTF, new Integer(10));


        //商品价格文字
        JLabel goodPrice=new JLabel("商品价格：");
        goodAdd.frame.getLayeredPane().add(goodPrice, new Integer(10));
        goodPrice.setFont(new Font("", Font.BOLD, 25));
        goodPrice.setBounds(bkIcon.getIconWidth()/2-128,bkIcon.getIconHeight()/2-110,150,50);

        //商品价格输入框
        JTextField priceJTF=new JFormattedTextField();
        priceJTF.setFont(new Font("", Font.BOLD, 24));
        priceJTF.setBounds(bkIcon.getIconWidth()/2+20,bkIcon.getIconHeight()/2-105,100,40);
        goodAdd.frame.getLayeredPane().add(priceJTF, new Integer(10));


        //商品类别选择文字
        JLabel goodCategory=new JLabel("商品类别：");
        goodAdd.frame.getLayeredPane().add(goodCategory, new Integer(10));
        goodCategory.setFont(new Font("", Font.BOLD, 25));
        goodCategory.setBounds(bkIcon.getIconWidth()/2-128,bkIcon.getIconHeight()/2+50,150,50);

        //一个选项
        JComboBox categoryJCB=new JComboBox();
        categoryJCB.setBounds(bkIcon.getIconWidth()/2+20,bkIcon.getIconHeight()/2+50,190,50);
        categoryJCB.setFont(new Font("楷体",Font.BOLD,25));
        Connection con=null;
        try
        {
            //连接数据库
            //把每个数据放到选项里面
            con=new Manager().getConnection();
            CategoryDao categoryDao=new CategoryDao();
            ResultSet retS=categoryDao.retSet(con,"");
            while(retS.next())
            {
                Category cate=new Category();
                cate.setId(retS.getInt("id"));
                cate.setCategoryName(retS.getString("categoryName"));
                cate.setCategoryDesc(retS.getString("categoryDesc"));
                categoryJCB.addItem(cate);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                if(con!=null)
                {
                    new Manager().closeDataBase(con);
                }
            } catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        goodAdd.frame.getLayeredPane().add(categoryJCB,new Integer(10));

        //商品简介文字
        JLabel goodDesc=new JLabel("商品简介：");
        goodAdd.frame.getLayeredPane().add(goodDesc, new Integer(10));
        goodDesc.setFont(new Font("", Font.BOLD, 25));
        goodDesc.setBounds(bkIcon.getIconWidth()/2-128,bkIcon.getIconHeight()/2+130,150,50);

        //简介输入框
        JTextArea descJTA=new JTextArea();
        descJTA.setLineWrap(true);
        descJTA.setBounds(bkIcon.getIconWidth()/2+20,bkIcon.getIconHeight()/2+130,300,180);
        descJTA.setFont(new Font("楷体",Font.BOLD,25));
        goodAdd.frame.getLayeredPane().add(descJTA,new Integer(10));

        JScrollPane JSP=new JScrollPane(descJTA);
        JSP.setBounds(bkIcon.getIconWidth()/2+20,bkIcon.getIconHeight()/2+130,300,180);
        goodAdd.frame.getLayeredPane().add(JSP,new Integer(10));

        //添加按钮
        JButton addGood=new JButton("添  加");
        addGood.setFont(new Font("", Font.BOLD, 28));
        addGood.setBounds(bkIcon.getIconWidth()/2-375,bkIcon.getIconHeight()/2+150,150,50);
        goodAdd.frame.getLayeredPane().add(addGood, new Integer(10));

        //返回按钮
        JButton goback=new JButton("主菜单");
        goback.setFont(new Font("", Font.BOLD, 28));
        goback.setBounds(bkIcon.getIconWidth()/2-375,bkIcon.getIconHeight()/2-150,150,50);
        goodAdd.frame.getLayeredPane().add(goback, new Integer(10));

        //返回按钮
        goback.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                goodAdd.frame.dispose();
                new MMain().showFrame();
            }
        });

        //添加商品
        addGood.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = nameJTF.getText();
                String desc=descJTA.getText();
                String price=priceJTF.getText();
                String number=numberJTF.getText();

                String goodCategory="";int id = 0;
                if(categoryJCB.getItemCount()>0)
                {
                    Category temp=null;
                    temp=(Category) categoryJCB.getSelectedItem();
                    goodCategory=temp.getCategoryName();
                    id=temp.getId();
                }

                if(StringIsValid.isEmpty(name)||StringIsValid.isEmpty(desc)||StringIsValid.isEmpty(price)||StringIsValid.isEmpty(number)||StringIsValid.isEmpty(goodCategory))
                {
                    JOptionPane.showMessageDialog(null,"请输入完整信息！","警告",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    if(StringIsValid.isNumber(price)==false||StringIsValid.isNumber(number)==false)
                    {
                        JOptionPane.showMessageDialog(null,"请输入正确的数字！","警告",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        Good good = new Good();
                        good.setCateGoryId(id);
                        good.setGoodName(name);
                        good.setGoodPrice(Integer.parseInt(price));
                        good.setGoodNumber(Integer.parseInt(number));
                        good.setGoodCategory(goodCategory);
                        good.setGoodDesc(desc);

                        Connection con = null;
                        GoodDao goodDao = new GoodDao();
                        try
                        {
                            con = new Manager().getConnection();
                            int ret = goodDao.addGood(con, good);
                            if (ret == 1)
                            {
                                nameJTF.setText("");
                                priceJTF.setText("");
                                numberJTF.setText("");
                                descJTA.setText("");

                                JOptionPane.showMessageDialog(null, "添加商品成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else if (ret == -1)
                            {
                                JOptionPane.showMessageDialog(null, "已经有同名的类别存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "添加商品失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception exception)
                        {
                            JOptionPane.showMessageDialog(null, "添加商品失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            exception.printStackTrace();
                        }finally
                        {
                            try
                            {
                                if(con!=null)
                                {
                                    new Manager().closeDataBase(con);
                                }
                            } catch (Exception exception)
                            {
                                exception.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }
}
