package graduationproject.backend.PurchasingProduct.mapper;

import graduationproject.backend.Order.entity.Orders;
import graduationproject.backend.PurchasingProduct.dto.OrderResponseDTO;
import graduationproject.backend.PurchasingProduct.dto.PaymentMethod;
import graduationproject.backend.PurchasingProduct.dto.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PaymentStatus.class, PaymentMethod.class, OrderDetailMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(expression = "java(order.getCustomer().getNameAndSurname())", target = "customerFullName")
    @Mapping(expression = "java(PaymentStatus.PAID)", target = "paymentStatus")
    @Mapping(expression = "java(PaymentMethod.VISA)", target = "paymentMethod")
    OrderResponseDTO toOrderResponseDTO(Orders order);
}
