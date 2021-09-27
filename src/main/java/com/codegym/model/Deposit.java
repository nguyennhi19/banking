package com.codegym.model;

public class Deposit {
    private int id;
    private int id_customer;
    private float amount;
    private String datetime;

    public Deposit(int id, int id_customer, float amount, String datetime) {
        this.id = id;
        this.id_customer = id_customer;
        this.amount = amount;
        this.datetime = datetime;
    }

    public Deposit(int id_customer, float amount, String datetime) {
        this.id_customer = id_customer;
        this.amount = amount;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }
}
