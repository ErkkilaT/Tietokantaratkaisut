package fi.metropolia.teemuerk.webstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class OrderItemDto {
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("product_id")
    private Integer productId;
    private int quantity;
    private BigDecimal price;

    public OrderItemDto() {}

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}