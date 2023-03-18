package graduationproject.backend.Customer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String city;
    private String address;
    private String zipCode;
    private String phone;
    private String company;
}
