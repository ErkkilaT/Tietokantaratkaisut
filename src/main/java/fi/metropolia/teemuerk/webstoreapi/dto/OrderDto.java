package fi.metropolia.teemuerk.webstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class OrderDto {
    private Integer id;
    @JsonProperty("customer_id")
    private Integer customerId;
    @JsonProperty("order_date")
    private LocalDateTime orderDate;
    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;
    private String status;
    @JsonProperty("shipping_address_id")
    private Integer shippingAddressId;
    private List<OrderItemDto> orderItems = new ArrayList<>();

    public OrderDto() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDateTime deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getShippingAddressId() { return shippingAddressId; }
    public void setShippingAddressId(Integer shippingAddressId) { this.shippingAddressId = shippingAddressId; }

    public List<OrderItemDto> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemDto> orderItems) { this.orderItems = orderItems; }
}
