package graduationproject.backend.User.payload.response;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Product.entity.Img;
import graduationproject.backend.Product.entity.Price;
import graduationproject.backend.Product.entity.ProductDetail;
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
    private Set<ProductDetail> productDetails;
    private Integer stock;
}
