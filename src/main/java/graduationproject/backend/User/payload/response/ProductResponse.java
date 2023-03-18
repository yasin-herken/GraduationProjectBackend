package graduationproject.backend.User.payload.response;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Product.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String title;
    private Price price;
    private Category category;
    private Set<Img> images;
    private String description;
    private Integer stock;
    private Color color;
    private ProductSize size;
    private Gender gender;
}
