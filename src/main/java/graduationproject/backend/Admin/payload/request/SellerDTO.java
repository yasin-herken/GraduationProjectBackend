package graduationproject.backend.Admin.payload.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class SellerDTO {

    private Long id;

    private String companyName;

    private String address;

    private String city;

    private String phone;
}
