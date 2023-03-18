package graduationproject.backend.Customer.mapper;

import graduationproject.backend.Customer.dto.CustomerDTO;
import graduationproject.backend.Customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(target = "customerId", ignore = true)
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
