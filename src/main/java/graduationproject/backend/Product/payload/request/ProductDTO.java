package graduationproject.backend.Product.payload.request;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Product.entity.Img;
import graduationproject.backend.Product.entity.Price;
import graduationproject.backend.Product.entity.ProductDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {

    private String title;
    private Price price;
    private Category category;
    private Set<String> images;
    private String description;
    private Set<ProductDetail> productDetails;
    private Long sellerId;
}
