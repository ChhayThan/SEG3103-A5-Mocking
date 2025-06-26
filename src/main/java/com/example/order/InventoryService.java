package com.example.order;

public interface InventoryService {
    /**
     * Try to reserve the given quantity of item. 
     * Return true if reserved, false if out of stock.
     */
    boolean reserveItem(String itemId, int quantity);
}
