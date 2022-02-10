package com.Ershisi.view;

import com.Ershisi.model.User;
import com.Ershisi.dao.UserDao;
import com.Ershisi.Tools.Manager;
import com.Ershisi.Tools.StringIsValid;

import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogInFrame extends MMain
{
    private JPanel MAIN;
    private JFrame frame;

    public static void main(String[] args)
    {
        new UserLogInFrame().showFrame();
    }
    public  void showFrame()
    {
        UserLogInFrame um=new UserLogInFrame();

        um.frame = new JFrame("商品管理系统——登录界面");
        um.frame.setContentPane(new UserLogInFrame().MAIN);
        um.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        um.frame.pack();
        um.frame.setVisible(true);
        um.frame.setResizable(false);

        //设置标题、大小和起始位置
        um.frame.setSize(965,751);
        um.frame.setLocation(455,155);

        //设置图标
        ImageIcon titleImg=new ImageIcon("src/img/titleImg.png");
        um.frame.setIconImage(titleImg.getImage());


        //背景图片
        ImageIcon bkIcon=new ImageIcon("src/img/bk.png");
        JLabel bk=new JLabel(bkIcon);
        bk.setBounds(0,0,bkIcon.getIconWidth(),bkIcon.getIconHeight());
        um.frame.getLayeredPane().add(bk, new Integer(-1));

        //标题图片
        ImageIcon titleIMG=new ImageIcon("src/img/title.png");
        JLabel title=new JLabel(titleIMG);
        title.setBounds(bkIcon.getIconWidth()/2-titleIMG.getIconWidth()/2-3,215,titleIMG.getIconWidth(),titleIMG.getIconHeight());
        um.frame.getLayeredPane().add(title, new Integer(10));

        //用户名文字
        JLabel userName=new JLabel("用户名");
        um.frame.getLayeredPane().add(userName, new Integer(10));
        userName.setFont(new Font("", Font.BOLD, 25));
        userName.setBounds(bkIcon.getIconWidth()/2-150,bkIcon.getIconHeight()/2-40,100,50);

        //密码文字
        JLabel pwd=new JLabel("密码");
        um.frame.getLayeredPane().add(pwd, new Integer(10));
        pwd.setFont(new Font("", Font.BOLD, 25));
        pwd.setBounds(bkIcon.getIconWidth()/2-150,bkIcon.getIconHeight()/2+51,100,50);

        //用户名输入框
        JTextField inputName=new JFormattedTextField();
        inputName.setFont(new Font("", Font.BOLD, 20));
        inputName.setBounds(bkIcon.getIconWidth()/2-30,bkIcon.getIconHeight()/2-30,180,35);
        um.frame.getLayeredPane().add(inputName, new Integer(10));

        //密码输入框
        JPasswordField inputPwd=new JPasswordField();
        inputPwd.setFont(new Font("", Font.BOLD, 20));
        inputPwd.setBounds(bkIcon.getIconWidth()/2-30,bkIcon.getIconHeight()/2+60,180,35);
        um.frame.getLayeredPane().add(inputPwd, new Integer(10));

        //登录按钮
        JButton login=new JButton("登录");
        login.setFont(new Font("", Font.BOLD, 20));
        login.setBounds(bkIcon.getIconWidth()/2-150,bkIcon.getIconHeight()/2+130,100,35);
        um.frame.getLayeredPane().add(login, new Integer(10));

        //清空按钮
        JButton reset=new JButton("清空");
        reset.setFont(new Font("", Font.BOLD, 20));
        reset.setBounds(bkIcon.getIconWidth()/2+50,bkIcon.getIconHeight()/2+130,100,35);
        um.frame.getLayeredPane().add(reset, new Integer(10));

        //点击登录按钮的事件
        login.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //登录事件
                //获取输入框中的账号密码
                String userNameTxt=inputName.getText();
                String pwdTxt= new String(inputPwd.getPassword());

                //使用StringIsValid中的isEmpty()方法判断得到的是不是合法(非空)字符串
                if(StringIsValid.isEmpty(userNameTxt)&& StringIsValid.isEmpty(pwdTxt))
                {
                    JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else if(StringIsValid.isEmpty(pwdTxt))
                {
                    JOptionPane.showMessageDialog(null,"密码不能为空！","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else if(StringIsValid.isEmpty(userNameTxt))
                {//这里的消息提示框没有父控件 说明是置顶的
                    JOptionPane.showMessageDialog(null,"用户名不能为空！","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                //能执行到这一步说明输入的用户名和密码全部合法(非空)
                User user=new User(userNameTxt,pwdTxt);
                //准备进行数据库连接
                Connection con=null;
                try//抛出异常处理
                {
                    //调用Manager类中的方法，进行数据库连接
                    con=new Manager().getConnection();

                    //判断用户名密码是否正确
                    //如果错误，就返回null
                    User cur=new UserDao().login(con,user);

                    if(cur!=null)
                    {//说明登陆成功
                        JOptionPane.showMessageDialog(null,"登陆成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                        MMain m=new MMain();
                        m.showFrame();
                        um.frame.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"用户名或密码错误！","提示",JOptionPane.INFORMATION_MESSAGE);
                        return;
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
        });


        //点击清空按钮的事件
        reset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //清空输入框
                //将文本设置为空字符串即可
                inputName.setText("");
                inputPwd.setText("");
                JOptionPane.showMessageDialog(null,"输入框已清空！");
            }
        });
    }
}
