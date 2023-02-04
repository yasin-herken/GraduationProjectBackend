package graduationproject.backend.User.mapper;

import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.payload.response.UsersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {

    SellerMapper INSTANCE = Mappers.getMapper( SellerMapper.class );

    @Mapping(source = "user.username", target= "username")
    @Mapping(source = "user.email", target= "email")
    @Mapping(source = "user.roles", target= "roles")
    UsersResponse userToResponse(Seller seller);

}
