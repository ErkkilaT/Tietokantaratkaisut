package fi.metropolia.teemuerk.webstoreapi.controller;

import fi.metropolia.teemuerk.webstoreapi.dto.OrderDto;
import fi.metropolia.teemuerk.webstoreapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer id) {
        OrderDto orderDto = orderService.getOrderById(id);
        if (orderDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDto);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>>getOrdersByCustomerId(@PathVariable Integer customerId) {
        List<OrderDto> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        if (createdOrder == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdOrder);
    }
}
