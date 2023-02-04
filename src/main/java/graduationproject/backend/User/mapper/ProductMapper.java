package graduationproject.backend.User.mapper;

import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.User.payload.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductResponse mapToProductResponse(Product product);
}
