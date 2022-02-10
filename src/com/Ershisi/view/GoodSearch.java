package com.Ershisi.view;

import com.Ershisi.model.Good;
import com.Ershisi.dao.GoodDao;
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

public class GoodSearch extends MMain
{
    private JPanel panel1;
    public JFrame frame;

    public void showFrame()
    {
        GoodSearch goodSearch=new GoodSearch();
        goodSearch.frame = new JFrame("商品查询与修改");
        goodSearch.frame.setContentPane(new GoodSearch().panel1);
        goodSearch.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goodSearch.frame.pack();
        goodSearch.frame.setVisible(true);

        goodSearch.frame.setSize(965, 751);
        goodSearch.frame.setLocation(455, 155);
        goodSearch.frame.setResizable(false);

        //一些变量的定义
        ImageIcon titleImg = new ImageIcon("src/img/titleImg.png");
        ImageIcon bkIcon = new ImageIcon("src/img/bkGR.png");
        JTextArea descJTF=new JTextArea();//类别描述显示框
        JTextField inputInform=new JFormattedTextField();//输入框
        JTextField idJTF=new JFormattedTextField();//编号显示框
        JTextField nameJTF=new JFormattedTextField();//商品名称显示框
        JTextField priceJTF=new JFormattedTextField();//商品价格显示框
        JTextField numberJTF=new JFormattedTextField();//商品名称输入框
        JButton searchCate=new JButton("搜索");//搜索按钮
        JButton goBack=new JButton("主菜单");//退出按钮
        JButton goodRevise=new JButton("修改");//修改按钮
        JButton goodDelete=new JButton("删除");//删除按钮
        JLabel goodID=new JLabel("编号：");//编号文字
        JLabel goodName=new JLabel("商品名称：");//商品名称文字
        JLabel goodPrice=new JLabel("价格：");//商品价格文字
        JLabel goodNumber=new JLabel("数量：");//商品数量文字
        JLabel goodCategory=new JLabel("类别：");//商品价格文字
        JLabel goodDesc=new JLabel("商品简介：");//商品描述文字
        JLabel bk = new JLabel(bkIcon);//背景图片
        JComboBox goodJCB=new JComboBox();//搜索时的选项
        JComboBox categoryReviseJCB=new JComboBox();//修改时的选项

        DefaultTableModel tableModel=new DefaultTableModel();
        JTable jT=new JTable(tableModel)
        {
          public boolean isCellEditable(int row,int column)
          {
              return false;
          }
        };//创建表格 并且让表格只能被选中 不能被更改



        //设置图标
        goodSearch.frame.setIconImage(titleImg.getImage());

        //背景图片
        bk.setBounds(0, 0, bkIcon.getIconWidth(), bkIcon.getIconHeight());
        goodSearch.frame.getLayeredPane().add(bk, new Integer(-1));

        jT.setBounds(20,250,600,300);
        jT.setModel(new DefaultTableModel(new Object[][] {}, new String[]//创建表头
                {
                        "编号", "名称", "价格","数量","类别","简介"
                }));
        //设置列宽
        jT.getColumnModel().getColumn(0).setMaxWidth(30);
        jT.getColumnModel().getColumn(1).setMaxWidth(80);
        jT.getColumnModel().getColumn(2).setMaxWidth(50);
        jT.getColumnModel().getColumn(3).setMaxWidth(50);
        jT.getColumnModel().getColumn(4).setMaxWidth(100);
        jT.getColumnModel().getColumn(5).setMaxWidth(290);

        //商品搜索输入框
        inputInform.setFont(new Font("", Font.BOLD, 24));
        inputInform.setBounds(bkIcon.getIconWidth()/2-437,bkIcon.getIconHeight()/2-170,180,40);
        goodSearch.frame.getLayeredPane().add(inputInform, new Integer(10));

        //搜索按钮
        searchCate.setFont(new Font("", Font.BOLD, 25));
        searchCate.setBounds(bkIcon.getIconWidth()/2-300,bkIcon.getIconHeight()/2+230,120,40);
        goodSearch.frame.getLayeredPane().add(searchCate, new Integer(10));

        //返回按钮
        goBack.setFont(new Font("", Font.BOLD, 28));
        goBack.setBounds(bkIcon.getIconWidth()/2-85,bkIcon.getIconHeight()/2-330,150,50);
        goodSearch.frame.getLayeredPane().add(goBack, new Integer(10));

        //商品id文字
        goodSearch.frame.getLayeredPane().add(goodID, new Integer(10));
        goodID.setFont(new Font("", Font.BOLD, 25));
        goodID.setBounds(bkIcon.getIconWidth()/2+150,bkIcon.getIconHeight()/2-180,100,50);

        //id输入框文字
        idJTF.setFont(new Font("", Font.BOLD, 24));
        idJTF.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2-175,70,40);
        idJTF.setEditable(false);
        goodSearch.frame.getLayeredPane().add(idJTF, new Integer(10));

        //商品名称文字
        goodSearch.frame.getLayeredPane().add(goodName, new Integer(10));
        goodName.setFont(new Font("", Font.BOLD, 25));
        goodName.setBounds(bkIcon.getIconWidth()/2+98,bkIcon.getIconHeight()/2-130,150,50);

        //名称输入框
        nameJTF.setFont(new Font("", Font.BOLD, 24));
        nameJTF.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2-125,200,40);
        goodSearch.frame.getLayeredPane().add(nameJTF, new Integer(10));

        //商品价格文字
        goodSearch.frame.getLayeredPane().add(goodPrice, new Integer(10));
        goodPrice.setFont(new Font("", Font.BOLD, 25));
        goodPrice.setBounds(bkIcon.getIconWidth()/2+98,bkIcon.getIconHeight()/2-80,150,50);

        //商品价格输入框
        priceJTF.setFont(new Font("", Font.BOLD, 24));
        priceJTF.setBounds(bkIcon.getIconWidth()/2+170,bkIcon.getIconHeight()/2-75,70,40);
        goodSearch.frame.getLayeredPane().add(priceJTF, new Integer(10));

        //商品数量文字
        goodSearch.frame.getLayeredPane().add(goodNumber, new Integer(10));
        goodNumber.setFont(new Font("", Font.BOLD, 25));
        goodNumber.setBounds(bkIcon.getIconWidth()/2+288,bkIcon.getIconHeight()/2-80,150,50);

        //商品数量输入框
        numberJTF.setFont(new Font("", Font.BOLD, 24));
        numberJTF.setBounds(bkIcon.getIconWidth()/2+360,bkIcon.getIconHeight()/2-75,70,40);
        goodSearch.frame.getLayeredPane().add(numberJTF, new Integer(10));

        //商品类别文字
        goodSearch.frame.getLayeredPane().add(goodCategory, new Integer(10));
        goodCategory.setFont(new Font("", Font.BOLD, 25));
        goodCategory.setBounds(bkIcon.getIconWidth()/2+98,bkIcon.getIconHeight()/2-30,150,50);

        //类别修改栏
        categoryReviseJCB.setBounds(bkIcon.getIconWidth()/2+170,bkIcon.getIconHeight()/2-30,190,50);
        categoryReviseJCB.setFont(new Font("楷体",Font.BOLD,25));
        Connection con=null;
        try
        {
            //连接数据库
            //把每个数据放到选项里面
            con=new Manager().getConnection();
            CategoryDao categoryDao=new CategoryDao();
            ResultSet retS=categoryDao.retSet(con,"");

            Category cate0=new Category();
            cate0.setId(-1);
            cate0.setCategoryName("请选择");
            categoryReviseJCB.addItem(cate0);
            while(retS.next())
            {
                Category cate=new Category();
                cate.setId(retS.getInt("id"));
                cate.setCategoryName(retS.getString("categoryName"));
                cate.setCategoryDesc(retS.getString("categoryDesc"));
                categoryReviseJCB.addItem(cate);
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
        goodSearch.frame.getLayeredPane().add(categoryReviseJCB,new Integer(10));

        //商品简介文字
        goodSearch.frame.getLayeredPane().add(goodDesc, new Integer(10));
        goodDesc.setFont(new Font("", Font.BOLD, 25));
        goodDesc.setBounds(bkIcon.getIconWidth()/2+98,bkIcon.getIconHeight()/2+30,150,50);

        //商品简介输入框
        descJTF.setFont(new Font("", Font.BOLD, 25));
        descJTF.setLineWrap(true);//可以让文本框自动换行
        descJTF.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2+45,200,180);
        goodSearch.frame.getLayeredPane().add(descJTF, new Integer(10));

        //让简介窗口滚动
        JScrollPane descScroll=new JScrollPane(descJTF);//可以让简介文本框上下滚动
        descScroll.setBounds(bkIcon.getIconWidth()/2+230,bkIcon.getIconHeight()/2+45,200,180);
        goodSearch.frame.getLayeredPane().add(descScroll, new Integer(10));

        //修改确定按钮
        goodRevise.setFont(new Font("", Font.BOLD, 25));
        goodRevise.setBounds(bkIcon.getIconWidth()/2+65,bkIcon.getIconHeight()/2+230,120,40);
        goodSearch.frame.getLayeredPane().add(goodRevise, new Integer(10));

        //商品删除按钮
        goodDelete.setFont(new Font("", Font.BOLD, 25));
        goodDelete.setBounds(bkIcon.getIconWidth()/2+275,bkIcon.getIconHeight()/2+230,120,40);
        goodSearch.frame.getLayeredPane().add(goodDelete, new Integer(10));

        //让表头能上下滚动
        JScrollPane jSP=new JScrollPane(jT);//让表能上下滚动
        jSP.setBounds(40,250,400,330);
        jSP.setVisible(true);
        jSP.setViewportView(jT);
        goodSearch.frame.getLayeredPane().add(jSP, new Integer(10));

        //搜索时的类别选项栏
        goodJCB.setBounds(bkIcon.getIconWidth()/2-220,bkIcon.getIconHeight()/2-175,190,50);
        goodJCB.setFont(new Font("楷体",Font.BOLD,25));

        //开始连接数据库
        Connection con0=null;
        ResultSet retS=null;
        try
        {
            //连接数据库
            //把每个数据放到选项里面
            con0=new Manager().getConnection();
            CategoryDao categoryDao=new CategoryDao();
            retS=categoryDao.retSet(con0,"");
            Category cate0=new Category();
            cate0.setId(-1);
            cate0.setCategoryName("全部类别");
            goodJCB.addItem(cate0);
            while(retS.next())
            {
                Category cate=new Category();
                cate.setId(retS.getInt("id"));
                cate.setCategoryName(retS.getString("categoryName"));
                cate.setCategoryDesc(retS.getString("categoryDesc"));
                goodJCB.addItem(cate);
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
        goodSearch.frame.getLayeredPane().add(goodJCB,new Integer(10));


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
                    //传入类别的id
                    Category temp=(Category) goodJCB.getSelectedItem();
                    ResultSet retS= new GoodDao().retSet(con,inputInform.getText(),temp.getId());

                    DefaultTableModel retTable= (DefaultTableModel) jT.getModel();
                    retTable.setRowCount(0);//清空表格内所有数据，防止重复数据堆叠
                    int retCount=0;
                    CategorySearch cS=new CategorySearch();
                    while(retS.next())
                    {
                        Vector v=new Vector();
                        v.add(retS.getString("id"));
                        v.add(retS.getString("goodName"));
                        v.add(retS.getString("goodPrice"));
                        v.add(retS.getString("goodNumber"));
                        v.add(retS.getString("goodCategory"));
                        v.add(retS.getString("goodDesc"));
                        retTable.addRow(v);
                        retCount++;
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
                priceJTF.setText((String)jT.getValueAt(r,2));
                numberJTF.setText((String)jT.getValueAt(r,3));
                descJTF.setText((String)jT.getValueAt(r,5));

                String goodCategory=(String)jT.getModel().getValueAt(r,4);
                int count=categoryReviseJCB.getItemCount();
                for(int i=0;i<count;i++)
                {
                    Category goodCategoryObject=(Category) categoryReviseJCB.getItemAt(i);
                    if(goodCategory.equals(goodCategoryObject.getCategoryName()))
                    {
                        categoryReviseJCB.setSelectedIndex(i);
                        break;
                    }

                }

                categoryReviseJCB.setSelectedItem(new Category(goodCategory,""));
                Connection con=null;
            }
        });


        //点击修改按钮
        goodRevise.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String id = idJTF.getText();
                String name=nameJTF.getText();
                String desc=descJTF.getText();
                String price=priceJTF.getText();
                String number=numberJTF.getText();
                Category goodCategory=(Category)categoryReviseJCB.getItemAt(categoryReviseJCB.getSelectedIndex());

                if(StringIsValid.isEmpty(id))
                {
                    JOptionPane.showMessageDialog(null,"还未选择商品！","警告",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(StringIsValid.isEmpty(name)||StringIsValid.isEmpty(desc)||StringIsValid.isEmpty(price)||StringIsValid.isEmpty(number)||goodCategory.getId()==-1)
                {
                    JOptionPane.showMessageDialog(null,"请输入完整的信息！","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    if(StringIsValid.isNumber(price)==false||StringIsValid.isNumber(number)==false)
                    {
                        JOptionPane.showMessageDialog(null,"请输入正确的数字！","警告",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        Connection con = null;

                        try
                        {
                            Good good = new Good();
                            good.setId(Integer.parseInt(id));
                            good.setGoodName(name);
                            good.setGoodDesc(desc);
                            good.setGoodPrice(Integer.parseInt(price));
                            good.setGoodNumber(Integer.parseInt(number));
                            good.setGoodCategory(goodCategory.getCategoryName());
                            good.setCateGoryId(goodCategory.getId());

                            con = new Manager().getConnection();
                            int ret = new GoodDao().updateGood(con, good);

                            if (ret == 1)
                            {
                                JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                                idJTF.setText("");
                                nameJTF.setText("");
                                descJTF.setText("");
                                priceJTF.setText("");
                                numberJTF.setText("");
                                categoryReviseJCB.setSelectedIndex(0);
                                updateCateGory(jT, inputInform);//更新一下表格
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "修改失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
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
            }
        });

        //点击删除按钮事件
        goodDelete.addActionListener(new ActionListener()
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
                            int m=new GoodDao().deleteGood(con,id);
                            if(m==1)
                            {
                                idJTF.setText("");
                                nameJTF.setText("");
                                descJTF.setText("");
                                priceJTF.setText("");
                                numberJTF.setText("");
                                categoryReviseJCB.setSelectedIndex(0);
                                updateCateGory(jT,inputInform);//再更新一下表格
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
                            JOptionPane.showMessageDialog(null,"删除失败！","提示",JOptionPane.INFORMATION_MESSAGE);
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
                goodSearch.frame.dispose();
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
            ResultSet retS= new GoodDao().retSet(con,inputInform.getText(),-1);

            while(retS.next())
            {
                //这里同样使用Vector容器
                //来储存每一行的信息
                Vector v=new Vector();
                v.add(retS.getString("id"));
                v.add(retS.getString("goodName"));
                v.add(retS.getString("goodPrice"));
                v.add(retS.getString("goodNumber"));
                v.add(retS.getString("goodCategory"));
                v.add(retS.getString("goodDesc"));
                //然后将信息添加到新的行上
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