package fi.metropolia.teemuerk.webstoreapi.service;

import fi.metropolia.teemuerk.webstoreapi.dto.OrderDto;
import fi.metropolia.teemuerk.webstoreapi.dto.OrderItemDto;
import fi.metropolia.teemuerk.webstoreapi.entity.*;
import fi.metropolia.teemuerk.webstoreapi.exception.NotFoundException;
import fi.metropolia.teemuerk.webstoreapi.exception.OutOfStockException;
import fi.metropolia.teemuerk.webstoreapi.mapper.OrderItemMapper;
import fi.metropolia.teemuerk.webstoreapi.mapper.OrderMapper;
import fi.metropolia.teemuerk.webstoreapi.repository.CustomerAddressRepository;
import fi.metropolia.teemuerk.webstoreapi.repository.CustomerRepository;
import fi.metropolia.teemuerk.webstoreapi.repository.OrderRepository;
import fi.metropolia.teemuerk.webstoreapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.productRepository = productRepository;
    }

    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        return OrderMapper.toDto(order);
    }

    public List<OrderDto> getOrdersByCustomerId(Integer customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderDto> dtos = new ArrayList<>();
        for(Order order : orders) {
            OrderDto dto = OrderMapper.toDto(order);
            dtos.add(dto);
        }
        return dtos;
    }

    // This method might need better deadlock prevention
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerAddress address = customerAddressRepository.findById(orderDto.getShippingAddressId()).orElseThrow(() -> new RuntimeException("Address not found"));
        List<Integer> productIds = orderDto.getOrderItems().stream().map(OrderItemDto::getProductId).toList();
        List<Product> products = productRepository.findAllById(productIds);

        //Check stock before creating dummy order
        for(OrderItemDto item : orderDto.getOrderItems()) {
            Product product = productRepository.findByIdForUpdate(item.getProductId());
            if(product == null) {
                throw new NotFoundException("Product not found: " + item.getProductId());
            }
            if (product.getStock_quantity() < item.getQuantity()) {
                throw new OutOfStockException("Not enough stock for product: " + product.getName());
            }
            // reduce stock
            // NOTE: API for customerservice/internal needs to return stock on cancelled orders
            product.setStock_quantity(
                    product.getStock_quantity() - item.getQuantity()
            );
        }

        //Save order before creating items to get the order ID for the items
        Order order = OrderMapper.toEntity(orderDto, customer, address);
        Order savedOrder = orderRepository.save(order);
        final Order orderRef = savedOrder;
        //Map items and set order reference
        List<OrderItem> items = orderDto.getOrderItems().stream()
                .map(itemDto -> {

                    Product product = products.stream()
                            .filter(p -> Objects.equals(p.getId(), itemDto.getProductId()))
                            .findFirst()
                            .orElseThrow();

                    return OrderItemMapper.toEntity(itemDto, product, orderRef);

                })
                .collect(Collectors.toList());

        savedOrder.getItems().clear();
        savedOrder.getItems().addAll(items);

        Order finalOrder = orderRepository.save(savedOrder);

        return OrderMapper.toDto(finalOrder);
    }
}
