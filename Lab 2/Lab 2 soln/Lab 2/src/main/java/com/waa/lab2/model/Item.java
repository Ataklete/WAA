package com.waa.lab2.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;


public class Item {
    @Min(value = 2, message = "size must be between 2 and 5")
    @Max(value = 5, message = "size must be between 2 and 5")
    @NotNull
    private String productNumber;
    @Min(value = 2, message = "size must be between 2 and 20")
    @Max(value = 20, message = "size must be between 2 and 20")
    @NotNull
    private String name;
    private double price;

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
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

    @Override
    public String toString() {
        return "Item{" +
                "productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 &&
                Objects.equals(productNumber, item.productNumber) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNumber, name, price);
    }
}
