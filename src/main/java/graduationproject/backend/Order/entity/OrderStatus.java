package graduationproject.backend.Order.entity;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    PENDING,
    CANCELLED_FROM_SELLER,
    CANCELLED_FROM_CUSTOMER,
    ON_WAY,
    DELIVERED
}
