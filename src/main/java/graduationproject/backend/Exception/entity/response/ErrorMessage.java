package graduationproject.backend.Exception.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private int status;
    private Date timestamp;
    private String message;
}
