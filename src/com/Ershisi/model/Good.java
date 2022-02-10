package com.Ershisi.model;


public class Good
{
    private int id;
    private String goodName;
    private String goodDesc;
    private int goodPrice;
    private int goodNumber;
    private String goodCategory;
    private int cateGoryId;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getGoodName()
    {
        return goodName;
    }

    public void setGoodName(String goodName)
    {
        this.goodName = goodName;
    }

    public String getGoodDesc()
    {
        return goodDesc;
    }

    public void setGoodDesc(String goodDesc)
    {
        this.goodDesc = goodDesc;
    }

    public int getGoodPrice()
    {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice)
    {
        this.goodPrice = goodPrice;
    }

    public int getGoodNumber()
    {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber)
    {
        this.goodNumber = goodNumber;
    }

    public String getGoodCategory()
    {
        return goodCategory;
    }

    public void setGoodCategory(String goodCategory)
    {
        this.goodCategory = goodCategory;
    }

    public int getCateGoryId()
    {
        return cateGoryId;
    }

    public void setCateGoryId(int cateGoryId)
    {
        this.cateGoryId = cateGoryId;
    }
}
