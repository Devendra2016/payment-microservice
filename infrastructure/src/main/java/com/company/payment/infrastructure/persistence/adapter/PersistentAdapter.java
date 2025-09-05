package com.company.payment.infrastructure.persistence.adapter;

import com.company.payment.domain.model.Payment;
import com.company.payment.domain.port.PaymentRepository;
import com.company.payment.infrastructure.persistence.entity.PaymentEntity;
import com.company.payment.infrastructure.persistence.repository.JpaPaymentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PersistentAdapter implements PaymentRepository {

    private JpaPaymentRepository jpaPaymentRepository;

    public PersistentAdapter(JpaPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentEntity = toEntity(payment);
        PaymentEntity savedEntity = jpaPaymentRepository.save(paymentEntity);
        return toDomain(savedEntity);
    }


    @Override
    public Optional<Payment> findById(UUID id) {
        return jpaPaymentRepository.findById(id).map(this::toDomain);
    }

    private PaymentEntity toEntity(Payment payment) {
        return new PaymentEntity(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getPayerId(),
                payment.getPayeeId(),
                payment.getDescription(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }

    private Payment toDomain(PaymentEntity entity) {
        return new Payment(
                entity.getId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getStatus(),
                entity.getPayerId(),
                entity.getPayeeId(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
