package com.Ershisi.Tools;

//用来判断字符串是否合法(符号我想要的条件)
public class StringIsValid extends Manager
{
    //使用静态方法 可以直接调用

    //判断字符串是不是空字符串
    public static boolean isEmpty(String str)
    {
        String str2=str.trim();//去除字符串头尾的空格
        if(str==null||"".equals(str2))
        {
            return true; //说明为空
        }
        else
        {
            return false;//说明不为空
        }
    }

    //判断一个字符串是不是数字
    public static boolean isNumber(String str)
    {
        try
        {
            int num=Integer.parseInt(str);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
