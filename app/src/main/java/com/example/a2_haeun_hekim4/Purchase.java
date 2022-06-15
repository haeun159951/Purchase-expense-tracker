package com.example.a2_haeun_hekim4;

import java.io.Serializable;

public class Purchase implements Serializable {
    private String nameOfTheStore;
    private double amountOfThePurchase;
    private boolean isPaid;

    public Purchase(String nameOfTheStore, double amountOfThePurchase, boolean isPaid) {
        this.nameOfTheStore = nameOfTheStore;
        this.amountOfThePurchase = amountOfThePurchase;
        this.isPaid = isPaid;
    }

    public Purchase(String nameOfTheStore, String amountOfThePurchase, boolean isPaid) {
        if (nameOfTheStore.isEmpty()) {
            this.nameOfTheStore = "N/A";
        } else {
            this.nameOfTheStore = nameOfTheStore;
        }
        if (amountOfThePurchase.isEmpty()) {
            this.amountOfThePurchase = -1;
        } else {
            this.amountOfThePurchase = Double.parseDouble(amountOfThePurchase);
        }
        this.isPaid = isPaid;
    }

    public String getNameOfTheStore() {
        return nameOfTheStore;
    }

    public void setNameOfTheStore(String nameOfTheStore) {
        this.nameOfTheStore = nameOfTheStore;
    }

    public double getAmountOfThePurchase() {
        return amountOfThePurchase;
    }

    public void setAmountOfThePurchase(double amountOfThePurchase) {
        this.amountOfThePurchase = amountOfThePurchase;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return
                "Store: " + nameOfTheStore +
                        " Amount: " + amountOfThePurchase +
                        " Paid Status: " + isPaid;
    }
}
