package com.example.lib.Model;

import java.io.Serializable;

public class MenuofLuan implements Serializable {
    int Pic;
    String Name;
    String Price;

    public MenuofLuan() {

    }

    public MenuofLuan(int pic, String name, String price) {
        Pic = pic;
        Name = name;
        Price = price;
    }

    public int getPic() {
        return Pic;
    }

    public void setPic(int pic) {
        Pic = pic;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
