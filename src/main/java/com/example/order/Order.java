package com.example.order;

public class Order {
    private final String itemId;
    private final int quantity;
    private final String cardNumber;
    private final double amount;

    public Order(String itemId, int quantity, String cardNumber, double amount) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getItemId() { return itemId; }
    public int getQuantity() { return quantity; }
    public String getCardNumber() { return cardNumber; }
    public double getAmount() { return amount; }
}
