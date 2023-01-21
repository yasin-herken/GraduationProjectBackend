package graduationproject.backend.Product.entity;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.User.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Embedded
    private Price price;

    @OneToOne
    private Category category;

    @ElementCollection // 1
    @CollectionTable(name = "Img_list", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "img") // 3
    private Set<String> images;

    @Column(name ="descriptipn")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id")
    private Set<ProductDetail> productDetails;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="seller_id", referencedColumnName = "ID")
    private Seller sellerId;

    public void update(Product product){
        this.images = product.getImages();
        this.description = product.getDescription();
        this.productDetails = product.getProductDetails();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
