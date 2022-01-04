package com.example.MoneyXMoney.model;

public class Buyer
{

    private String name;
    private String address;
    private String productName;
    private String clothingSize;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setlClothingSize(String clothingSize) {
        this.clothingSize = clothingSize;
    }
    public String getClothingSize() {
        return clothingSize;
    }

    public void setClothingSize(String clothingSize) {
        this.clothingSize = clothingSize;
    }





    public Buyer(String name, String address, String productName, String clothingSize) {
        this.name = name;
        this.address = address;
        this.productName = productName;
        this.clothingSize=clothingSize;
    }








    @Override
    public String toString()
    {
        return "User [name=" + name + ", address=" + address + ", productName=" + productName + ", clothingSize=" + clothingSize + "]";



    }

    }

