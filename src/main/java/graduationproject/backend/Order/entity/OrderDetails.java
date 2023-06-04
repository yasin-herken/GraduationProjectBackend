package graduationproject.backend.Order.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import graduationproject.backend.Common.entity.AuditableDate;
import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.User.entity.Seller;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails extends AuditableDate {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailsNo;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonBackReference
    private Seller seller;

    private Integer quantity;

    private OrderStatus orderStatus;

    private Double subTotal;
}
