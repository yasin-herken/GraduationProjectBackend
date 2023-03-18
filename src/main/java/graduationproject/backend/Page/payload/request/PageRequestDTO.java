package graduationproject.backend.Page.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PageRequestDTO {
    private Integer pageSize;
    private String sortBy;
    private String direction;
    private Integer page;
    private Map<String, Map<String,Object>> criteria;
}
