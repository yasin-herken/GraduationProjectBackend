package graduationproject.backend.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrdersDTO {
    private Long id;

    private Integer quantity;
}