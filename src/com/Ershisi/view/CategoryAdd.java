package com.Ershisi.view;

import com.Ershisi.Tools.Manager;
import com.Ershisi.model.Category;
import com.Ershisi.dao.CategoryDao;
import com.Ershisi.Tools.StringIsValid;

import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryAdd extends MMain
{
    private JPanel panel1;
    public  JFrame frame;


    public void showFrame()
    {
        CategoryAdd cateAdd=new CategoryAdd();
        cateAdd.frame = new JFrame("类别添加");
        cateAdd.frame.setContentPane(new CategoryAdd().panel1);
        cateAdd.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cateAdd.frame.pack();
        cateAdd.frame.setVisible(true);

        cateAdd.frame.setSize(965,751);
        cateAdd.frame.setLocation(455,155);
        cateAdd.frame.setResizable(false);

        //设置图标
        ImageIcon titleImg=new ImageIcon("src/img/titleImg.png");
        cateAdd.frame.setIconImage(titleImg.getImage());

        //背景图片
        ImageIcon bkIcon=new ImageIcon("src/img/bkL.png");
        JLabel bk=new JLabel(bkIcon);
        bk.setBounds(0,0,bkIcon.getIconWidth(),bkIcon.getIconHeight());
        cateAdd.frame.getLayeredPane().add(bk, new Integer(-1));

        //名称输入框
        JTextField inputCateName=new JFormattedTextField();
        inputCateName.setFont(new Font("", Font.BOLD, 28));
        inputCateName.setBounds(bkIcon.getIconWidth()/2-340,bkIcon.getIconHeight()/2-30,180,60);
        cateAdd.frame.getLayeredPane().add(inputCateName, new Integer(10));

        //简介输入框
        JTextArea inputCateDesc=new JTextArea();
        inputCateDesc.setFont(new Font("", Font.BOLD, 25));
        inputCateDesc.setLineWrap(true);//设置自动换行
        inputCateDesc.setBounds(bkIcon.getIconWidth()/2+30,bkIcon.getIconHeight()/2-30,400,200);
        cateAdd.frame.getLayeredPane().add(inputCateDesc, new Integer(10));

        //可以让简介文本框上下滚动
        JScrollPane descScroll=new JScrollPane(inputCateDesc);
        descScroll.setBounds(bkIcon.getIconWidth()/2+30,bkIcon.getIconHeight()/2-30,400,200);
        cateAdd.frame.getLayeredPane().add(descScroll, new Integer(10));

        //添加按钮
        JButton addCate=new JButton("添  加");
        addCate.setFont(new Font("", Font.BOLD, 28));
        addCate.setBounds(bkIcon.getIconWidth()/2-325,bkIcon.getIconHeight()/2+230,150,50);
        cateAdd.frame.getLayeredPane().add(addCate, new Integer(10));

        //退出按钮
        JButton goBack=new JButton("主菜单");
        goBack.setFont(new Font("", Font.BOLD, 28));
        goBack.setBounds(bkIcon.getIconWidth()/2+160,bkIcon.getIconHeight()/2+230,150,50);
        cateAdd.frame.getLayeredPane().add(goBack, new Integer(10));

        //添加类别事件
        addCate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(StringIsValid.isEmpty(inputCateName.getText())||StringIsValid.isEmpty(inputCateDesc.getText()))
                {
                    JOptionPane.showMessageDialog(null,"请输入完整的信息！","警告",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    Category userInputCate=new Category(inputCateName.getText(),inputCateDesc.getText());
                    Connection con=null;
                    try//抛出异常处理
                    {
                        //连接数据库
                        Manager ma=new Manager();
                        con=ma.getConnection();

                        //执行INSERT语句
                        CategoryDao cateDao=new CategoryDao();
                        int ret=cateDao.addNewCate(con,userInputCate);

                        //如果返回1说明INSERT语句执行成功
                        if(ret==1)
                        {
                            JOptionPane.showMessageDialog(null,"添加类别成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                            inputCateName.setText("");
                            inputCateDesc.setText("");
                        }
                        else if(ret==-1)
                        {
                            JOptionPane.showMessageDialog(null,"已经有同名的类别存在！","提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"添加类别失败！","提示",JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (Exception exception)
                    {
                        //抛出异常
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(null,"添加类别失败！","提示",JOptionPane.INFORMATION_MESSAGE);
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
        });

        //返回主界面事件
        goBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                inputCateName.setText("");
                inputCateDesc.setText("");
                MMain m=new MMain();
                m.showFrame();
                cateAdd.frame.dispose();
            }
        });
    }
}
