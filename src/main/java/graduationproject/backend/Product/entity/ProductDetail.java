package graduationproject.backend.Product.entity;


import graduationproject.backend.User.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_detail_id")
    private Set<DetailMap> nameMap;

    public ProductDetail() {

    }
}
