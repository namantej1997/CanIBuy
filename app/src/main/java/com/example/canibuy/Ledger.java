package com.example.canibuy;

public class Ledger {
    String category;
    String ammount;
    String itemName;
    boolean debited;

    public Ledger(String category, String ammount, String itemName, boolean debited) {
        this.category = category;
        this.ammount = ammount;
        this.itemName = itemName;
        this.debited = debited;
    }
}
