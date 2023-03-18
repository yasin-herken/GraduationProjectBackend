package graduationproject.backend.PurchasingProduct.dto;

import graduationproject.backend.Customer.dto.CustomerDTO;
import graduationproject.backend.Order.dto.OrdersDTO;
import graduationproject.backend.Payment.entity.Payment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderPaymentEntity {
    CustomerDTO customer;
    List<OrdersDTO> orders;
    Payment payment;
    Double totalAmount;
}
