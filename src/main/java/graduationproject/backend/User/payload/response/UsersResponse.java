package graduationproject.backend.User.payload.response;

import graduationproject.backend.Role.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersResponse {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
    private String companyName;
    private String address;
    private String city;
    private String phone;
}
