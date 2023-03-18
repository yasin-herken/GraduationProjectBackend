package graduationproject.backend.Product.payload.request;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Product.entity.Color;
import graduationproject.backend.Product.entity.Gender;
import graduationproject.backend.Product.entity.Price;
import graduationproject.backend.Product.entity.ProductSize;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductDTO {

    private String title;
    private Price price;
    private Category category;
    private Color color;
    private ProductSize size;
    private Set<String> images;
    private String description;
    private Integer stock;
    private Gender gender;
}
