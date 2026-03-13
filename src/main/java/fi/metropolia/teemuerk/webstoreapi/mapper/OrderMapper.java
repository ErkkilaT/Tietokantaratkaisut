package fi.metropolia.teemuerk.webstoreapi.mapper;

import fi.metropolia.teemuerk.webstoreapi.dto.OrderDto;
import fi.metropolia.teemuerk.webstoreapi.dto.OrderItemDto;
import fi.metropolia.teemuerk.webstoreapi.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    // DTO → Entity
    public static Order toEntity(OrderDto dto, Customer customer, CustomerAddress address) {
        if (dto == null || customer == null) return null;

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrder_date(dto.getOrderDate());
        order.setDelivery_date(dto.getDeliveryDate());
        order.setStatus(dto.getStatus());
        order.setShipping_address(address);

        // Map OrderItems


        return order;
    }

    // Entity → DTO
    public static OrderDto toDto(Order order) {
        if (order == null) return null;

        OrderDto dto = new OrderDto();
        dto.setId((Integer) order.getId());
        dto.setCustomerId((Integer) order.getCustomer().getId());
        dto.setOrderDate(order.getOrder_date());
        dto.setDeliveryDate(order.getDelivery_date());
        dto.setStatus(order.getStatus());
        dto.setShippingAddressId((Integer) order.getShipping_address().getId());

        List<OrderItemDto> itemDtos = order.getItems().stream()
                .map(OrderItemMapper::toDto)
                .collect(Collectors.toList());
        dto.setOrderItems(itemDtos);

        return dto;
    }
}