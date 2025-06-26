package com.example.order;

public class OrderService {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;

    public OrderService(InventoryService inventoryService,
                        PaymentService paymentService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
    }

    /**
     * Place an order: first reserve the item, then charge payment.
     * Returns:
     *   "Out of stock"     – if reserveItem() returns false
     *   "Payment failed"   – if charge() returns false
     *   "Order placed"     – if both succeed
     * May throw PaymentException if charge() throws.
     */
    public String placeOrder(Order order) throws PaymentException {
        if (!inventoryService.reserveItem(order.getItemId(), order.getQuantity())) {
            return "Out of stock";
        }
        if (!paymentService.charge(order.getCardNumber(), order.getAmount())) {
            return "Payment failed";
        }
        return "Order placed";
    }
}
