package graduationproject.backend.Product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Common.entity.AuditableDate;
import graduationproject.backend.Order.entity.OrderDetails;
import graduationproject.backend.User.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product")
@Getter
@Builder
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

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToOne
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    @JsonBackReference
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Set<Img> images = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;
    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private ProductSize size;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDetails orderDetails;

    @Column(name = "description")
    private String description;

    public Product() {

    }

    public void setImages(Set<Img> images) {
        this.images.clear();
        if (images != null) this.images.addAll(images);
    }

    public void update(Product product) {
        this.category = product.getCategory();
        this.images = product.getImages();
        this.description = product.getDescription();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.color = product.getColor();
        this.size = product.getSize();
        this.stock = product.getStock();
        this.gender = product.getGender();
    }
}
