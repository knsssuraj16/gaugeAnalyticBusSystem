package com.knsssuraj.gaugeanalyticsbussystem.pojo;

public class CardHolder {
    int id;
    double amount = 0.0;
    String name;

    public CardHolder(int id, double amount, String name) {
        this.id = id;
        this.amount = amount;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CardHolder{" +
                "id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
