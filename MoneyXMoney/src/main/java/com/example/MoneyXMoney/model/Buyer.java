package com.example.MoneyXMoney.model;

public class Buyer
{

    private String name;
    private String address;
    private String productName;




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

    public void setlProductName(String productName) {
        this.productName = productName;
    }






    public Buyer(String name, String address, String productName) {
        this.name = name;
        this.address = address;
        this.productName = productName;
    }








    @Override
    public String toString()
    {
        return "User [name=" + name + ", address=" + address +" , productName= "+productName+ "]";



    }

}