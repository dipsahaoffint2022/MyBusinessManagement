package com.example.mybusinessmanagement;

public class Model_1 {
    String a_p_name, a_p_price, a_p_qty, a_p_unit;

    Model_1() {

    }

    public Model_1(String a_p_name, String a_p_price, String a_p_qty, String a_p_unit) {
        this.a_p_name = a_p_name;
        this.a_p_price = a_p_price;
        this.a_p_qty = a_p_qty;
        this.a_p_unit = a_p_unit;
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

    public String getA_p_qty() {
        return a_p_qty;
    }

    public void setA_p_qty(String a_p_qty) {
        this.a_p_qty = a_p_qty;
    }

    public String getA_p_unit() {
        return a_p_unit;
    }

    public void setA_p_unit(String a_p_unit) {
        this.a_p_price = a_p_unit;
    }
}