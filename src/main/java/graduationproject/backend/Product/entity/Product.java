package graduationproject.backend.Product.entity;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Common.entity.AuditableDate;
import graduationproject.backend.User.entity.Seller;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AuditableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Embedded
    private Price price;

    @OneToOne
    private Category category;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Set<Img> images;

    @Column(name = "descriptipn")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<ProductDetail> productDetails;

    public Product(String title, Price price, Category category, Set<Img> images, String description, Set<ProductDetail> productDetails) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.images = images;
        this.description = description;
        this.productDetails = productDetails;
    }


    public void update(Product product) {
        this.category = product.getCategory();
        this.images = product.getImages();
        this.description = product.getDescription();
        this.productDetails = product.getProductDetails();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
