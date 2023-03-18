package graduationproject.backend.Order.service;

import graduationproject.backend.Order.entity.Orders;
import graduationproject.backend.Order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }
}
