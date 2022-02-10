package com.Ershisi.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MMain
{
    public  JFrame frame;
    private JPanel panel1;
    public  void showFrame()
    {
        MMain m=new MMain();
        m.frame = new JFrame("商品管理系统");
        m.frame.setContentPane(new MMain().panel1);
        m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.frame.pack();
        m.frame.setVisible(true);

        //设置标题、大小和起始位置
        m.frame.setSize(965,751);
        m.frame.setLocation(455,155);
        m.frame.setResizable(false);

        //设置图标
        ImageIcon titleImg=new ImageIcon("src/img/titleImg.png");
        m.frame.setIconImage(titleImg.getImage());

        //背景图片
        ImageIcon bkIcon=new ImageIcon("src/img/bk.png");
        JLabel bk=new JLabel(bkIcon);
        bk.setBounds(0,0,bkIcon.getIconWidth(),bkIcon.getIconHeight());
        m.frame.getLayeredPane().add(bk, new Integer(-1));

        //标题图片
        ImageIcon titleIMG=new ImageIcon("src/img/title.png");
        JButton title=new JButton(titleIMG);
        title.setBorderPainted(false);
        title.setBounds(bkIcon.getIconWidth()/2-titleIMG.getIconWidth()/2-3,215,titleIMG.getIconWidth(),titleIMG.getIconHeight());
        m.frame.getLayeredPane().add(title, new Integer(10));

        //点击标题出现的关于界面
        title.addActionListener(new ActionListener()
        {
            class INFORMATION_MESSAGE
            {
            }

            @Override
            public void actionPerformed(ActionEvent e)
            {//关于
               JOptionPane.showMessageDialog(null,"商品管理系统     \n素材来自游戏仙剑奇侠传三   经过一定的修改","关于",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //退出系统按钮
        JButton exitSystem=new JButton();
        ImageIcon exitImg=new ImageIcon("src/img/exit.png");
        exitSystem.setIcon(exitImg);
        exitSystem.setBorderPainted(false);
        exitSystem.setBounds(bkIcon.getIconWidth()/2-181,bkIcon.getIconHeight()/2+80,exitImg.getIconWidth(),exitImg.getIconHeight());
        m.frame.getLayeredPane().add(exitSystem, new Integer(10));

        //类别添加按钮
        JButton addCategory=new JButton();
        ImageIcon LAddImg=new ImageIcon("src/img/LAdd.png");
        addCategory.setIcon(LAddImg);
        addCategory.setBorderPainted(false);
        addCategory.setBounds(203,12,LAddImg.getIconWidth(),LAddImg.getIconHeight());
        m.frame.getLayeredPane().add(addCategory, new Integer(10));

        //类别查询按钮
        JButton searchCategory=new JButton();
        ImageIcon LSearchImg=new ImageIcon("src/img/LSearch.png");
        searchCategory.setIcon(LSearchImg);
        searchCategory.setBorderPainted(false);
        searchCategory.setBounds(519,13,LSearchImg.getIconWidth(),LSearchImg.getIconHeight());
        m.frame.getLayeredPane().add(searchCategory, new Integer(10));

        //类别修改按钮
        JButton reviseCategory=new JButton();
        ImageIcon LReviseImg=new ImageIcon("src/img/LRevise.png");
        reviseCategory.setIcon(LReviseImg);
        reviseCategory.setBorderPainted(false);
        reviseCategory.setBounds(88,225,LReviseImg.getIconWidth(),LReviseImg.getIconHeight());
        m.frame.getLayeredPane().add(reviseCategory, new Integer(10));

        //商品修改按钮
        JButton reviseGood=new JButton();
        ImageIcon SReviseImg=new ImageIcon("src/img/SRevise.png");
        reviseGood.setIcon(SReviseImg);
        reviseGood.setBorderPainted(false);
        reviseGood.setBounds(721,235,SReviseImg.getIconWidth(),SReviseImg.getIconHeight());
        m.frame.getLayeredPane().add(reviseGood, new Integer(10));

        //商品添加按钮
        JButton addGood=new JButton();
        ImageIcon SAddImg=new ImageIcon("src/img/SAdd.png");
        addGood.setIcon(SAddImg);
        addGood.setBorderPainted(false);
        addGood.setBounds(190,555,SAddImg.getIconWidth(),SAddImg.getIconHeight());
        m.frame.getLayeredPane().add(addGood, new Integer(10));

        //商品查询按钮
        JButton searchGood=new JButton();
        ImageIcon SSearchImg=new ImageIcon("src/img/SSearch.png");
        searchGood.setIcon(SSearchImg);
        searchGood.setBorderPainted(false);
        searchGood.setBounds(509,555,SSearchImg.getIconWidth(),SSearchImg.getIconHeight());
        m.frame.getLayeredPane().add(searchGood, new Integer(10));

        //类别添加界面跳转
        addCategory.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                CategoryAdd cate=new CategoryAdd();
                cate.showFrame();
                m.frame.dispose();
            }
        });

        //类别查询界面跳转
        searchCategory.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CategorySearch cate=new CategorySearch();
                cate.showFrame();
                m.frame.dispose();
            }
        });

        //类别修改界面跳转
        reviseCategory.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CategorySearch cate=new CategorySearch();
                cate.showFrame();
                m.frame.dispose();
            }
        });

        //商品修改界面跳转
        reviseGood.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                GoodSearch good=new GoodSearch();
                good.showFrame();
                m.frame.dispose();
            }
        });

        //商品查找界面跳转
        searchGood.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                GoodSearch good=new GoodSearch();
                good.showFrame();
                m.frame.dispose();
            }
        });

        addGood.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                m.frame.dispose();
                GoodAdd goodAdd=new GoodAdd();
                goodAdd.showFrame();
            }
        });


        //退出系统
        exitSystem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //添加确认是否退出系统的对话框
                int ret=JOptionPane.showConfirmDialog(null,"即将退出系统！","警告",JOptionPane.YES_NO_OPTION);
                if(ret==0)
                {
                    m.frame.dispose(); //关闭当前窗口
                    UserLogInFrame um=new UserLogInFrame();//创建用户登录窗口
                    um.showFrame();//展示用户登录窗口
                }
            }
        });
    }
}
