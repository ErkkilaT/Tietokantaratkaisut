package fi.metropolia.teemuerk.webstoreapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "order_date", nullable = false, updatable = false, insertable = false)
    private LocalDateTime order_date;
    private LocalDateTime delivery_date;
    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private CustomerAddress shipping_address;

    private String status;
    @OneToMany(mappedBy="order",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public LocalDateTime getOrder_date() {
        return order_date;
    }
    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }
    public LocalDateTime getDelivery_date() {
        return delivery_date;
    }
    public void setDelivery_date(LocalDateTime delivery_date) {
        this.delivery_date = delivery_date;
    }
    public CustomerAddress getShipping_address() {
        return shipping_address;
    }
    public void setShipping_address(CustomerAddress shipping_address) {
        this.shipping_address = shipping_address;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
