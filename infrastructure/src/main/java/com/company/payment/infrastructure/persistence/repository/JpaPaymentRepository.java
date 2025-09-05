package com.company.payment.infrastructure.persistence.repository;

import com.company.payment.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
