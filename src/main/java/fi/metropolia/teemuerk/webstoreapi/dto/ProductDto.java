package fi.metropolia.teemuerk.webstoreapi.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock_quantity;
    private int category_id;

    public ProductDto(){}
    public ProductDto(Integer id, String name, String description, BigDecimal price, int stock_quantity, int category_id){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.category_id = category_id;

    }

    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public int getCategory_id() {
        return category_id;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getStock_quantity() {
        return stock_quantity;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

}
