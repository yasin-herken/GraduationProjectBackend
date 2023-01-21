package graduationproject.backend.Admin.payload.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SellerDTO {

    private Long id;

    private String companyName;

    private String address;

    private String city;

    private String phone;
}
