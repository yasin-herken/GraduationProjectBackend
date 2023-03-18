package graduationproject.backend.Order.dto;

import graduationproject.backend.Order.entity.OrderStatus;
import graduationproject.backend.Product.entity.Img;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class OrderDetailsResponseDTO {
    private Long orderDetailsNo;
    private Long productId;
    private String productName;
    private Set<Img> productImage;
    private Double productPrice;
    private Integer quantity;
    private Double subTotal;
    private OrderStatus orderStatus;
}
