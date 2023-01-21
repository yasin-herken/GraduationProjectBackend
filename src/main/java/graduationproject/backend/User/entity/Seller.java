package graduationproject.backend.User.entity;

import graduationproject.backend.Product.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "ID")
    private CustomUser user;

    @Column(name="company_name")
    private String companyName;

    @Column(name= "address")
    private String address;

    @Column(name= "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    public Seller(CustomUser user, String companyName, String address, String city, String phone) {
        this.user = user;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }
}