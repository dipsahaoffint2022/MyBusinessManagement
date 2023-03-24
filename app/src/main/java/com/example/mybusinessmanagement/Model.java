package com.example.mybusinessmanagement;

public class Model {
    String a_p_name, a_p_price;

    Model(){

    }

    public Model(String a_p_name, String a_p_price) {
        this.a_p_name = a_p_name;
        this.a_p_price = a_p_price;
    }

    public String getA_p_name() {
        return a_p_name;
    }

    public void setA_p_name(String a_p_name) {
        this.a_p_name = a_p_name;
    }

    public String getA_p_price() {
        return a_p_price;
    }

    public void setA_p_price(String a_p_price) {
        this.a_p_price = a_p_price;
    }
}
