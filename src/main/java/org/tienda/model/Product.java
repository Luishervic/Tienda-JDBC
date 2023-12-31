package org.tienda.model;

public class Product {
    private int code;
    private String name;
    private double price;
    private int fabricantCode;

    public Product() {
    }

    public Product(int code, String name, double price, int fabricantCode) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.fabricantCode = fabricantCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFabricantCode() {
        return fabricantCode;
    }

    public void setFabricantCode(int fabricantCode) {
        this.fabricantCode = fabricantCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", fabricantCode=" + fabricantCode +
                '}';
    }
}
