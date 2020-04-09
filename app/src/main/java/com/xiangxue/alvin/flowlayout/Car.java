package com.xiangxue.alvin.flowlayout;

import android.graphics.Color;

/**
 * Created by zhuliang on 2019/11/26.
 */

public class Car {

    Color color;
    double price;
    String brand;
    String displacement;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }


    private Car(Builder builder) {
        this.color = builder.color;
        this.price = builder.price;
        this.brand = builder.brand;
        this.displacement = builder.displacement;
    }

    public static class Builder {

        Color color;
        double price;
        String brand;
        String displacement;

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder displacement(String displacement) {
            this.displacement = displacement;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

}
