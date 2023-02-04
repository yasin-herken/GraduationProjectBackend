package graduationproject.backend.Page.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {

    Long totalRecords;

    List<T> data;
}
