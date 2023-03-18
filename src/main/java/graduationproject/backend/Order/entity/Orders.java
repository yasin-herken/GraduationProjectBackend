package graduationproject.backend.Order.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import graduationproject.backend.Common.entity.AuditableDate;
import graduationproject.backend.Customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders extends AuditableDate {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id")
    private UUID orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany
    @JsonManagedReference
    private List<OrderDetails> orderDetails = new ArrayList<>();
    private Double totalAmount;
    public void addOrderDetails(OrderDetails orderDetails) {
        this.orderDetails.add(orderDetails);
    }
}
