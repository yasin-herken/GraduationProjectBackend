package graduationproject.backend.Product.entity;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Common.entity.AuditableDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Product")
@Getter
@Setter
@AllArgsConstructor
public class Product extends AuditableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "stock", nullable = false)
    @Size(min = 0, message = "Stock must be greater than or equal to 0")
    private Integer stock;

    @Embedded
    private Price price;

    @OneToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Set<Img> images;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;
    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private ProductSize size;

    @Column(name = "descriptipn")
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Set<ProductDetail> productDetails;

    public Product(String title, Price price, Category category, Set<Img> images, String description, Set<ProductDetail> productDetails, ProductSize size, Color color) {
        this.title = title;
        this.color = color;
        this.price = price;
        this.category = category;
        this.images = images;
        this.description = description;
        this.productDetails = productDetails;
        this.stock = 0;
        this.size = size;
    }

    public Product() {

    }

    public void update(Product product) {
        this.category = product.getCategory();
        this.images = product.getImages();
        this.description = product.getDescription();
        this.productDetails = product.getProductDetails();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.color = product.getColor();
        this.size = product.getSize();
        this.stock = product.getStock();
    }
}
