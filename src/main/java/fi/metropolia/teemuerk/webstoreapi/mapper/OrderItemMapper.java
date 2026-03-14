package fi.metropolia.teemuerk.webstoreapi.mapper;

import fi.metropolia.teemuerk.webstoreapi.dto.OrderItemDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Order;
import fi.metropolia.teemuerk.webstoreapi.entity.OrderItem;
import fi.metropolia.teemuerk.webstoreapi.entity.Product;
import fi.metropolia.teemuerk.webstoreapi.key.OrderItemId;

public class OrderItemMapper {


    public static OrderItemDto toDto(OrderItem entity) {
        if (entity == null) return null;

        OrderItemDto dto = new OrderItemDto();
        dto.setOrderId(entity.getId().getOrderId());
        dto.setProductId(entity.getId().getProductId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getUnitPrice());
        return dto;
    }

    public static OrderItem toEntity(OrderItemDto dto, Product product, Order order) {
        if (dto == null || product == null) return null;

        OrderItem entity = new OrderItem();
        OrderItemId id = new OrderItemId();

        id.setOrderId(order.getId());
        id.setProductId(product.getId());
        entity.setId(id);
        entity.setOrder(order);          // set parent
        entity.setProduct(product);      // set product
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getPrice());
        return entity;
    }
}