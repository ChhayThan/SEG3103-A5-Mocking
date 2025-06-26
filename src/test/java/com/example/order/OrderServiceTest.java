package com.example.order;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderServiceTest {

    private InventoryService inventoryServiceMock;
    private PaymentService   paymentServiceMock;
    private OrderService     orderService;

    @Before
    public void setUp() {
        // 1) Create the mocks
        inventoryServiceMock = EasyMock.createMock(InventoryService.class);
        paymentServiceMock = EasyMock.createMock(PaymentService.class);
        // 2) Inject into SUT
        orderService = new OrderService(inventoryServiceMock, paymentServiceMock);
    }

    @Test
    public void testPlaceOrderSuccess() throws PaymentException {
        Order order = new Order("item123", 2, "4111111111111111", 49.99);

        EasyMock.expect(inventoryServiceMock.reserveItem("item123", 2))
                .andReturn(true);
        EasyMock.expect(paymentServiceMock.charge("4111111111111111", 49.99))
                .andReturn(true);

        EasyMock.replay(inventoryServiceMock, paymentServiceMock);

        String result = orderService.placeOrder(order);
        assertEquals("Order placed", result);

        EasyMock.verify(inventoryServiceMock, paymentServiceMock);
    }

    @Test
    public void testPlaceOrderPaymentFailure() throws PaymentException {
        Order order = new Order("item456", 1, "5555555555554444", 19.95);

        EasyMock.expect(inventoryServiceMock.reserveItem("item456", 1))
                .andReturn(true);
        EasyMock.expect(paymentServiceMock.charge("5555555555554444", 19.95))
                .andReturn(false);

        EasyMock.replay(inventoryServiceMock, paymentServiceMock);

        String result = orderService.placeOrder(order);
        assertEquals("Payment failed", result);

        EasyMock.verify(inventoryServiceMock, paymentServiceMock);
    }

    @Test
    public void testPlaceOrderOutOfStock() throws PaymentException {
        Order order = new Order("item789", 5, "378282246310005", 99.95);

        EasyMock.expect(inventoryServiceMock.reserveItem("item789", 5))
                .andReturn(false);

        EasyMock.replay(inventoryServiceMock, paymentServiceMock);

        String result = orderService.placeOrder(order);
        assertEquals("Out of stock", result);

        // Only inventoryMock should have been called
        EasyMock.verify(inventoryServiceMock, paymentServiceMock);
    }
}
