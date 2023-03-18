package graduationproject.backend.Order.controller;

import graduationproject.backend.Order.entity.Orders;
import graduationproject.backend.Order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @GetMapping
    ResponseEntity<List<Orders>> getOrders(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Orders> saveOrder(@RequestBody Orders orders){
        return new ResponseEntity<>(orderService.save(orders), HttpStatus.OK);
    }
}
