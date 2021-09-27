package com.codegym.model;

public class Transfer {
    private int id;
    private int idSender;
    private String name_Sender;
    private int idReceiver;
    private String name_Receiver;
    private float amount;
    private int fee = 5;
    private float amount_fee ;
    private float total_amount;
    private String datetime;

    public Transfer() {
    }

    public Transfer(int id, int idSender, String name_Sender, int idReceiver, String name_Receiver, float amount,int fee,String datetime, float amount_fee, float total_amount) {
        this.id = id;
        this.idSender = idSender;
        this.name_Sender = name_Sender;
        this.idReceiver = idReceiver;
        this.name_Receiver = name_Receiver;
        this.amount = amount;
        this.datetime = datetime;
        this.fee = fee;
        this.amount_fee = amount_fee;
        this.total_amount = total_amount;
    }
    public Transfer(int id, int idSender, String name_Sender, int idReceiver, String name_Receiver, float amount,String datetime, float amount_fee, float total_amount) {
        this.id = id;
        this.idSender = idSender;
        this.name_Sender = name_Sender;
        this.idReceiver = idReceiver;
        this.name_Receiver = name_Receiver;
        this.amount = amount;
        this.datetime = datetime;
        this.amount_fee = amount_fee;
        this.total_amount = total_amount;
    }

    public Transfer(int id, int idSender, int idReceiver, float amount,int fee,String datetime, float amount_fee, float total_amount) {
        this.id = id;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.amount = amount;
        this.datetime = datetime;
        this.fee = fee;
        this.amount_fee = amount_fee;
        this.total_amount = total_amount;
    }

    public Transfer(int idSender, int idReceiver, float amount,String datetime, float amount_fee, float total_amount) {
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.amount = amount;
        this.datetime = datetime;
        this.amount_fee = amount_fee;
        this.total_amount = total_amount;
    }

    public Transfer(int idSender, String name_Sender, int idReceiver, String name_Receiver, float amount,String datetime, float amount_fee, float total_amount) {
        this.idSender = idSender;
        this.name_Sender = name_Sender;
        this.idReceiver = idReceiver;
        this.name_Receiver = name_Receiver;
        this.amount = amount;
        this.datetime = datetime;
        this.amount_fee = amount_fee;
        this.total_amount = total_amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public String getName_Sender() {
        return name_Sender;
    }

    public void setName_Sender(String name_Sender) {
        this.name_Sender = name_Sender;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getName_Receiver() {
        return name_Receiver;
    }

    public void setName_Receiver(String name_Receiver) {
        this.name_Receiver = name_Receiver;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    public float getAmount_fee() {
        return amount_fee;
    }

    public void setAmount_fee(float amount_fee) {
        this.amount_fee = amount_fee;
    }

}
