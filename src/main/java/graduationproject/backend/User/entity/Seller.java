package graduationproject.backend.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import graduationproject.backend.Common.entity.AuditableDate;
import graduationproject.backend.Product.entity.Product;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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
    private CustomUser user;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "seller_id")
    private List<Product> products;

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