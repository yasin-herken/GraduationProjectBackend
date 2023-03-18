package graduationproject.backend.PurchasingProduct.mapper;

import graduationproject.backend.Order.dto.OrderDetailsResponseDTO;
import graduationproject.backend.Order.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    List<OrderDetailsResponseDTO> toOrderDetailResponseDTOs(List<OrderDetails> orderDetails);

    @Mapping(expression = "java(orderDetails.getProduct().getImages())", target = "productImage")
    @Mapping(expression = "java(Double.valueOf(orderDetails.getProduct().getPrice().getPrice()))", target = "productPrice")
    @Mapping(expression = "java(orderDetails.getProduct().getTitle())", target = "productName")
    @Mapping(expression = "java(orderDetails.getProduct().getId())", target = "productId")
    OrderDetailsResponseDTO toOrderDetailResponseDTO(OrderDetails orderDetails);

}
