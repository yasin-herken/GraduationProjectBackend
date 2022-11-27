package graduationproject.backend.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private String token;

    private List<String> roles;

    public UserInfoResponse(String accessToken,Long id, String username, String email, List<String> roles) {
        this.id = id;
        this.token = accessToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}
