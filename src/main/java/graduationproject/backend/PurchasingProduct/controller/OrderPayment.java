package graduationproject.backend.PurchasingProduct.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import graduationproject.backend.Customer.entity.Customer;
import graduationproject.backend.Customer.mapper.CustomerMapper;
import graduationproject.backend.Customer.repository.CustomerRepository;
import graduationproject.backend.Order.entity.OrderDetails;
import graduationproject.backend.Order.entity.OrderStatus;
import graduationproject.backend.Order.entity.Orders;
import graduationproject.backend.Order.repository.OrderDetailRepository;
import graduationproject.backend.Order.repository.OrderRepository;
import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.Product.service.ProductService;
import graduationproject.backend.PurchasingProduct.dto.OrderPaymentEntity;
import graduationproject.backend.PurchasingProduct.service.PurchasingProductService;
import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderPayment {
    private final ProductService productService;
    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final PurchasingProductService purchasingProductService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveOrderPayment(@RequestBody OrderPaymentEntity orderPaymentDTO) {
        Customer customerToSave = CustomerMapper.INSTANCE.customerDTOToCustomer(orderPaymentDTO.getCustomer());

        Customer customerDb = customerRepository.save(customerToSave);

        Orders ordersToSave = new Orders();
        ordersToSave.setCustomer(customerDb);
        ordersToSave.setTotalAmount(orderPaymentDTO.getTotalAmount());
        List<OrderDetails> orderDetailsList = orderPaymentDTO.getOrders().stream().map(order -> {
            Product product = productService.getProductById(order.getId());
            Optional<Seller> seller = sellerRepository.findById(product.getSeller().getId());
            if (seller.isPresent()) {
                OrderDetails orderDetailsToSave = OrderDetails.builder().orders(ordersToSave).quantity(order.getQuantity()).product(product).subTotal(Double.parseDouble(product.getPrice().getPrice()) * order.getQuantity()).seller(seller.get()).orderStatus(OrderStatus.PENDING).build();
                ordersToSave.addOrderDetails(orderDetailsToSave);
                orderDetailRepository.save(orderDetailsToSave);
                seller.get().getOrderDetails().add(orderDetailsToSave);
                sellerRepository.save(seller.get());
                return orderDetailsToSave;
            }
            return null;
        }).toList();
        ordersToSave.setOrderDetails(orderDetailsList);
       // Orders savedOrder = orderRepository.save(ordersToSave);
        return ResponseEntity.ok(ordersToSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @JsonProperty("status") @RequestBody String status) {
        Optional<OrderDetails> order = orderDetailRepository.findById(id);
        if (order.isPresent()) {
            order.get().setOrderStatus(OrderStatus.valueOf(status));
            OrderDetails savedOrder = orderDetailRepository.save(order.get());
            return ResponseEntity.ok(savedOrder);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getOrders(
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ResponseEntity.ok(purchasingProductService.getOrders(pageSize, sortBy, direction, page));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductForOrder(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
