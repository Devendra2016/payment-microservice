import com.company.payment.domain.model.Payment;
import com.company.payment.infrastructure.persistence.adapter.PersistentAdapter;
import com.company.payment.payment_service.PaymentServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

@SpringBootTest(classes = PaymentServiceApplication.class)
public class PersistenceAdapterTest {

    @Autowired
    private PersistentAdapter persistentAdapter;

    @Test
    public void testSavePayment() {
        Payment payment = new Payment();
        payment.setAmount(new BigDecimal("100.00"));
        payment.setCurrency("USD");
        payment.setStatus("PENDING");
        payment.setPayerId("payer123");
        payment.setPayeeId("payee456");
        payment.setDescription("Test payment");

        Payment savedPayment = persistentAdapter.save(payment);

        Assertions.assertNotNull(savedPayment.getId());
        Assertions.assertEquals("USD", savedPayment.getCurrency());
    }
}
