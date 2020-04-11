package com.project.Image_Based_Ordering.Model;

public class Predictions {


    String name;
    Float value;
    public Predictions(String name, Float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Predictions{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }


}
