package com.Ershisi.view;

import com.Ershisi.Tools.Manager;
import com.Ershisi.model.Category;
import com.Ershisi.dao.CategoryDao;
import com.Ershisi.Tools.StringIsValid;

import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Connection;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class CategorySearch extends MMain
{
    private JPanel panel1;
    public  JFrame frame;

    public void showFrame()
    {
        CategorySearch cateSearch = new CategorySearch();
        cateSearch.frame = new JFrame("类别查询与修改");
        cateSearch.frame.setContentPane(new CategorySearch().panel1);
        cateSearch.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cateSearch.frame.pack();
        cateSearch.frame.setVisible(true);

        cateSearch.frame.setSize(965, 751);
        cateSearch.frame.setLocation(455, 155);
        cateSearch.frame.setResizable(false);

        //设置图标
        ImageIcon titleImg = new ImageIcon("src/img/titleImg.png");
        cateSearch.frame.setIconImage(titleImg.getImage());

        //背景图片
        ImageIcon bkIcon = new ImageIcon("src/img/bkLR.png");
        JLabel bk = new JLabel(bkIcon);
        bk.setBounds(0, 0, bkIcon.getIconWidth(), bkIcon.getIconHeight());
        cateSearch.frame.getLayeredPane().add(bk, new Integer(-1));

        //输入框
        JTextField inputInform=new JFormattedTextField();
        inputInform.setFont(new Font("", Font.BOLD, 24));
        inputInform.setBounds(bkIcon.getIconWidth()/2-337,bkIcon.getIconHeight()/2-170,180,40);
        cateSearch.frame.getLayeredPane().add(inputInform, new Integer(10));

        //搜索按钮
        JButton searchCate=new JButton("搜索");
        searchCate.setFont(new Font("", Font.BOLD, 25));
        searchCate.setBounds(bkIcon.getIconWidth()/2-300,bkIcon.getIconHeight()/2+230,120,40);
        cateSearch.frame.getLayeredPane().add(searchCate, new Integer(10));

        //退出按钮
        JButton goBack=new JButton("主菜单");
        goBack.setFont(new Font("", Font.BOLD, 28));
        goBack.setBounds(bkIcon.getIconWidth()/2-85,bkIcon.getIconHeight()/2-330,150,50);
        cateSearch.frame.getLayeredPane().add(goBack, new Integer(10));

        //编号文字
        JLabel categoryID=new JLabel("编号：");
        cateSearch.frame.getLayeredPane().add(categoryID, new Integer(10));
        categoryID.setFont(new Font("", Font.BOLD, 25));
        categoryID.setBounds(bkIcon.getIconWidth()/2+150,bkIcon.getIconHeight()/2-160,100,50);

        //编号显示框
        JTextField idJTF=new JFormattedTextField();
        idJTF.setFont(new Font("", Font.BOLD, 24));
        idJTF.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2-155,70,40);
        idJTF.setEditable(false);
        cateSearch.frame.getLayeredPane().add(idJTF, new Integer(10));

        //类别名称文字
        JLabel categoryName=new JLabel("类别名称：");
        cateSearch.frame.getLayeredPane().add(categoryName, new Integer(10));
        categoryName.setFont(new Font("", Font.BOLD, 25));
        categoryName.setBounds(bkIcon.getIconWidth()/2+98,bkIcon.getIconHeight()/2-80,150,50);

        //类别名称显示框
        JTextField nameJTF=new JFormattedTextField();
        nameJTF.setFont(new Font("", Font.BOLD, 24));
        nameJTF.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2-75,200,40);
        cateSearch.frame.getLayeredPane().add(nameJTF, new Integer(10));

        //类别简介文字
        JLabel categoryDesc=new JLabel("类别简介：");
        cateSearch.frame.getLayeredPane().add(categoryDesc, new Integer(10));
        categoryDesc.setFont(new Font("", Font.BOLD, 25));
        categoryDesc.setBounds(bkIcon.getIconWidth()/2+98,bkIcon.getIconHeight()/2,150,50);

        //类别简介显示框
        JTextArea descJTF=new JTextArea();
        descJTF.setFont(new Font("", Font.BOLD, 25));
        descJTF.setLineWrap(true);//可以让文本框自动换行
        descJTF.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2+5,200,180);
        cateSearch.frame.getLayeredPane().add(descJTF, new Integer(10));

        //可以让简介文本框上下滚动
        JScrollPane descScroll=new JScrollPane(descJTF);
        descScroll.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2+5,200,180);
        cateSearch.frame.getLayeredPane().add(descScroll, new Integer(10));

        //修改按钮
        JButton categoryRevise=new JButton("修改");
        categoryRevise.setFont(new Font("", Font.BOLD, 25));
        categoryRevise.setBounds(bkIcon.getIconWidth()/2+65,bkIcon.getIconHeight()/2+230,120,40);
        cateSearch.frame.getLayeredPane().add(categoryRevise, new Integer(10));

        //删除按钮
        JButton categoryDelete=new JButton("删除");
        categoryDelete.setFont(new Font("", Font.BOLD, 25));
        categoryDelete.setBounds(bkIcon.getIconWidth()/2+275,bkIcon.getIconHeight()/2+230,120,40);
        cateSearch.frame.getLayeredPane().add(categoryDelete, new Integer(10));


        //创建表格
        DefaultTableModel tableModel=new DefaultTableModel();
        JTable jT=new JTable(tableModel)
        {
            public boolean isCellEditable(int row,int column)
            {
                return false;
            }
        };//创建表格 并且让表格只能被选中 不能被更改

        jT.setBounds(40,250,400,300);
        //创建表头
        jT.setModel(new DefaultTableModel(new Object[][] {}, new String[]
                {
                "编号", "类别名称", "类别简介"
                }));
        //设置列宽
        jT.getColumnModel().getColumn(0).setMaxWidth(50);
        jT.getColumnModel().getColumn(1).setMaxWidth(80);
        jT.getColumnModel().getColumn(2).setMaxWidth(270);



        //让表头能上下滚动
        JScrollPane jSP=new JScrollPane(jT);
        jSP.setBounds(40,250,400,330);
        jSP.setVisible(true);
        jSP.setViewportView(jT);
        cateSearch.frame.getLayeredPane().add(jSP, new Integer(10));


        //连接数据库
        //并且把数据放到JTable控件上展示
        //这里调用函数的目的是 用户点开查询界面，就能首先看到全部的类别
        updateCateGory(jT,inputInform);


        //点击搜索按键的事件(其实和上面是类似的)
        //但是因为函数主体有改动，所以不能直接使用上面的函数
        searchCate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                Connection con=null;
                try
                {
                    con=new Manager().getConnection();
                    ResultSet retS= new CategoryDao().retSet(con,inputInform.getText());
                    DefaultTableModel retTable= (DefaultTableModel) jT.getModel();
                    retTable.setRowCount(0);//清空表格内所有数据，防止重复数据堆叠
                    int retCount=0;
                    CategorySearch cS=new CategorySearch();
                    while(retS.next())
                    {
                        Vector v=new Vector();
                        v.add(retS.getString("id"));
                        v.add(retS.getString("categoryName"));
                        v.add(retS.getString("categoryDesc"));
                        retTable.addRow(v);
                        retCount++;//记录结果的条数
                    }

                    if(retCount==0)
                    {
                        JOptionPane.showMessageDialog(null,"没有查询到相关结果！","查询完毕！",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"共查找到"+retCount+"条相关结果！","查询完毕！",JOptionPane.INFORMATION_MESSAGE);
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
            }
        });

        //点击左侧表格的某一行时，需要把它的信息显示在界面右侧
        //然后进行删除或者修改的操作
        jT.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                int r=jT.getSelectedRow();
                idJTF.setText((String)jT.getValueAt(r,0));
                nameJTF.setText((String)jT.getValueAt(r,1));
                descJTF.setText((String)jT.getValueAt(r,2)+"\r");
            }
        });

        //点击修改按钮
        categoryRevise.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String id = idJTF.getText();
                String name=nameJTF.getText();
                String desc=descJTF.getText();

                if(StringIsValid.isEmpty(id))
                {
                    JOptionPane.showMessageDialog(null,"还未选择类别！","警告",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(StringIsValid.isEmpty(name)||StringIsValid.isEmpty(desc))
                {
                    JOptionPane.showMessageDialog(null,"请输入完整的信息！","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    Connection con=null;
                    Category cate=new Category(Integer.parseInt(id),name,desc);
                    try
                    {
                        con=new Manager().getConnection();
                        int ret=new CategoryDao().updateCategory(con,cate);

                        if(ret==1)
                        {
                            JOptionPane.showMessageDialog(null,"修改成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                            idJTF.setText("");
                            nameJTF.setText("");
                            descJTF.setText("");
                            updateCateGory(jT,inputInform);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"修改失败！","提示",JOptionPane.INFORMATION_MESSAGE);
                        }



                    } catch (Exception exception)
                    {
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
        });

        //点击删除按钮事件
        categoryDelete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String id=idJTF.getText();
                if(StringIsValid.isEmpty(id))
                {
                    JOptionPane.showMessageDialog(null,"请选择要删除的类别！","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    int ret=JOptionPane.showConfirmDialog(null,"确定要删除该类别吗","提示",JOptionPane.YES_NO_OPTION);
                    if(ret==0)
                    {
                        Connection con=null;
                        try
                        {
                            con=new Manager().getConnection();
                            int m=new CategoryDao().deleteCategory(con,id);
                            if(m==1)
                            {
                                idJTF.setText("");
                                nameJTF.setText("");
                                descJTF.setText("");
                                updateCateGory(jT,inputInform);
                                JOptionPane.showMessageDialog(null,"删除成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                            {
                                updateCateGory(jT,inputInform);
                                JOptionPane.showMessageDialog(null,"删除失败！","提示",JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (Exception exception)
                        {
                            updateCateGory(jT,inputInform);
                            //要删除的类别下如果存在商品的话就会保存！
                            JOptionPane.showMessageDialog(null,"删除失败！可能是因为该类别下还存在商品！","提示",JOptionPane.INFORMATION_MESSAGE);
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


        //返回主界面事件
        goBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                inputInform.setText("");
                MMain m=new MMain();
                m.showFrame();
                cateSearch.frame.dispose();
            }
        });



    }

    //这是一个更新表格的方法
    //需要更新表格的时候只需要调用它即可
    public void updateCateGory(JTable jT,JTextField inputInform)
    {
        DefaultTableModel retTable= (DefaultTableModel) jT.getModel();
        retTable.setRowCount(0);
        Connection con=null;
        try
        {
            con=new Manager().getConnection();
            ResultSet retS= new CategoryDao().retSet(con,inputInform.getText());

            while(retS.next())
            {
                //使用Vector容器储存每一行的信息
                Vector v=new Vector();
                v.add(retS.getString("id"));
                v.add(retS.getString("categoryName"));
                v.add(retS.getString("categoryDesc"));
                //添加新的行
                retTable.addRow(v);
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
    }
}
