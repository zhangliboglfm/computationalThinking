package com.myself.JAVA_8_New_Character.customFuntionalInterface;

public class OrderItem {
    private final int id;
    private final int price;

    public OrderItem(int theId, int thePrice) {
        id = theId;
        price = thePrice;
    }

    public int getId() { return id; }
    public int getPrice() { return price; }

    public String toString() { return String.format("id: %d price: %d", id, price); }
}