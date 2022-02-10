package com.Ershisi.model;

//这是类别的实例化
public class Category
{
    private int id; //编号
    private String categoryName; //类别名
    private String categoryDesc; //类别描述

    public Category(String categoryName, String categoryDesc)
    {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    //因为有时候需要填入id，有时候不需要，所以现在这里有两个构造函数
    public Category(int id, String categoryName, String categoryDesc)
    {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public Category()
    {}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc()
    {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc)
    {
        this.categoryDesc = categoryDesc;
    }

    //重写方法 返回名称 否则返回的是地址
    @Override
    public String toString()
    {
        return this.categoryName;
    }
}
