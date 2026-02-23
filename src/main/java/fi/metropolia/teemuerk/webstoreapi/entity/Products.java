package fi.metropolia.teemuerk.webstoreapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="Products")
public class Products {
    @Id
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock_quantity;
    private int category_id;
    private int supplier_id;

    public BigDecimal getPrice() {
        return price;
    }
    public int getCategory_id() {
        return category_id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getStock_quantity() {
        return stock_quantity;
    }
    public int getSupplier_id() {
        return supplier_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }
}
