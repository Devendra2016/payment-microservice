package com.company.payment.domain.port;

import com.company.payment.domain.model.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(UUID id);
    // Other methods as needed
}