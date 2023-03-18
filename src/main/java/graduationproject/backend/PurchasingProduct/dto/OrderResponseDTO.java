package graduationproject.backend.PurchasingProduct.dto;

import graduationproject.backend.Order.dto.OrderDetailsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDTO {
    private UUID orderId;
    private String customerFullName;
    private Double totalAmount;
    private Date createdAt;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private List<OrderDetailsResponseDTO> orderDetails;
}
