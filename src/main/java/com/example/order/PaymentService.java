package com.example.order;

public interface PaymentService {
    /**
     * Charge the given card. Return true if successful.
     * Throws PaymentException on network or gateway errors.
     */
    boolean charge(String cardNumber, double amount) throws PaymentException;
}
