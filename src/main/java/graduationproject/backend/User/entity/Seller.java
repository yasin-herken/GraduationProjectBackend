package graduationproject.backend.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import graduationproject.backend.Common.entity.AuditableDate;
import graduationproject.backend.Order.entity.OrderDetails;
import graduationproject.backend.Product.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Seller extends AuditableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private CustomUser user;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @OneToMany
    @ToString.Exclude
    @JsonManagedReference
    private List<Product> products;

    @OneToMany
    @ToString.Exclude
    @JsonManagedReference
    private List<OrderDetails> orderDetails;

    public Seller(CustomUser user, String companyName, String address, String city, String phone) {
        this.user = user;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public void add(Product product) {
        this.products.add(product);
    }
}