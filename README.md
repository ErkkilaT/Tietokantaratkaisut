# Online store database API
This a school project API-solution for a webstore database by Teemu Erkkilä. This API includes endpoints required for the consumer-side usage of a webstore so it doesn't include endpoints/indexing etc. that is needed for managing the store. This API also does not allow deletion of user accounts or deleting/updating orders, but does allow for deleting user addresses. The thought was that removing accounts or canceling/deleting orders would be done through customer service.

# Database Preparation
The database was edited in the following way for security, referential integrity and performance reasons.
#### Permissions
The following permission are granted to the API. 
```
CREATE USER 'dbuser'@'localhost' IDENTIFIED BY '**********';

GRANT SELECT, INSERT, UPDATE
ON firma.customers
TO 'dbuser'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.customeraddresses
TO 'dbuser'@'localhost';

GRANT SELECT, INSERT
ON firma.orderitems
TO 'dbuser'@'localhost';

GRANT SELECT, INSERT
ON firma.orders
TO 'dbuser'@'localhost';

GRANT SELECT
ON firma.productcategories
TO 'dbuser'@'localhost';

GRANT SELECT, UPDATE
ON firma.products
TO 'dbuser'@'localhost';
```
#### Indexing
These indexes were added to improve performance. Of note is that since in the orderitems-table primary key, order_id comes first, there is no need to manually create an index for it.
```
CREATE INDEX idx_customer_id
ON customeraddresses(customer_id);

CREATE INDEX idx_customer_id
ON orders(customer_id);

CREATE INDEX idx_address_id
ON orders(shipping_address_id);

CREATE INDEX idx_category_id
ON products(category_id);

CREATE INDEX idx_order_id
ON orderitems(order_id);
```
#### Cleanup/Logging
A small log to keep track of incoming orders was added.
```
-- Log for checking incoming order volume
CREATE TABLE orderlog (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
order_id INT,
order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER neworder
AFTER INSERT ON orders
FOR EACH ROW
INSERT INTO orderlog (order_id) VALUES (NEW.id);
```
Also an event that removes cancelled orders that were created over two years ago, it runs monthly
```
SET GLOBAL event_scheduler = ON;
-- delete old cancelled orders and their items
DELIMITER $$

CREATE EVENT cleanup_old_orders
ON SCHEDULE EVERY 1 MONTH
STARTS '2026-03-31 00:00:00'
DO
BEGIN
    
    DELETE oi
    FROM order_items oi
    INNER JOIN orders o ON oi.order_id = o.id
    WHERE o.status = 'CANCELLED'
      AND o.order_date < NOW() - INTERVAL 2 YEAR;

    DELETE FROM orders
    WHERE status = 'CANCELLED'
      AND order_date < NOW() - INTERVAL 2 YEAR;
END $$

DELIMITER ;
```

#### Other
The orders-table was altered to allow for deleting customeraddresses. This is acceptable because in the API there is a block to deleting addresses that are used in any active orders (For the purpose of this exercise it was determined that status 'SHIPPED' meant shipped and delivered since there was no 'Delivered' -type status)
```
-- Allow deletion of customeraddresses. Blocking deletion of addresses that have active orders is blocked in API
ALTER TABLE orders
DROP FOREIGN KEY `fk_order_shipping_address`;
ALTER TABLE orders
ADD CONSTRAINT `fk_order_shipping_address`
FOREIGN KEY (`shipping_address_id`)
REFERENCES   `customeraddresses` (`id`)
ON DELETE SET NULL;
```

# Endpoints
## Products
### Get all products
- **Endpoint**: `/products/`
- **Method**: GET
- **Description**: Retrieve product information for all products in the store
- **Parameters**:
  - `none` 
- **Response**:JSON format
### Get products by category id
- **Endpoint**: `/products/category/{id}`
- **Method**: GET
- **Description**: Retrieve product information for all items in a category
- **Parameters**:
  - `id` (int) - category ID number
- **Response**:JSON format
- ### Get product information by product id
- **Endpoint**: `/products/product/{id}`
- **Method**: GET
- **Description**: Retrieve product information for single item
- **Parameters**:
  - `id` (int) - product ID number
- **Response**:JSON format

## Customers
### Get customer information
- **Endpoint**: `/customer/{id}`
- **Method**: GET
- **Description**: Retrieve user information
- **Parameters**:
  - `id` (int) - customer id
- **Response**:JSON format
### Register new customer
- **Endpoint**: `/customer/`
- **Method**: POST
- **Description**: Register new user
- **Parameters**:
  - body type: `Content-Type: application/json`
  - body example:
  ```
  {
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@test.com",
    "phone": "123456789"
  }
  ```
### Update customer information
- **Endpoint**: `/customer/{id}`
- **Method**: PATCH
- **Description**: Edit user information.
- **Parameters**:
  - `id` (int) - customer id
  - body type: `Content-Type: application/json`
  - body example: (include only changed ones)
  ```
  {
    "email": "john.smith@test.com",
  }
  ```


## Orders
### Get order information by id
- **Endpoint**: `/orders/{id}`
- **Method**: GET
- **Description**: Retrieve order information, including all products ordered
- **Parameters**:
  - `id` (int) - order id
- **Response**:JSON format
### Get order information by customer id
- **Endpoint**: `/orders/customer/{id}`
- **Method**: GET
- **Description**: Retrieve order information, including all products ordered for single customer
- **Parameters**:
  - `id` (int) - customer id
- **Response**:JSON format
### Add new order
- **Endpoint**: `/orders`
- **Method**: POST
- **Description**: Add new order including list of items included in the order
- **Parameters**:
  - body type: `Content-Type: application/json`
  - body example:
  ```
  {
    "customer_id": 1,
    "shipping_address_id": 10,
    "status": "NEW",
    "orderItems": [
      {
        "product_id": 101,
        "quantity": 3,
        "price": 29.99
      },
      {
        "product_id": 102,
        "quantity": 4,
        "price": 49.99
      }
    ]
  }
  ```

## Customer Addresses
### Get address information by customer id
- **Endpoint**: `/address/{customer_id}`
- **Method**: GET
- **Description**: Retrieve address information for all addresses of user
- **Parameters**:
  - `id` (int) - customer id
- **Response**:JSON format
### Add new address
- **Endpoint**: `/address`
- **Method**: POST
- **Description**: Add a new address
- **Parameters**:
  - body type: `Content-Type: application/json`
  - body example:
  ```
  {
    "customer_id": 100001,
    "street": "bas",
    "city": "asd",
    "postal_code": "123",
    "country": "asd"
  }
  ```
### Update address
- **Endpoint**: `/address/{id}`
- **Method**: PATCH
- **Description**: Update address
- **Parameters**:
  - `id` (int) - address id    
  - body type: `Content-Type: application/json`
  - body example: (only include changed fields)
  ```
  {
    "customer_id": 100001,
    "street": "bas",
    "city": "asd",
    "postal_code": "123",
    "country": "asd"
  }
  ```
### Delete Address
- **Endpoint**: `/address/{address_id}`
- **Method**: DELETE
- **Description**: Delete address
- **Parameters**:
  - `address_id` (int) - address id
- **Response**:JSON format

## Product categories
- **Endpoint**: `/categories`
- **Method**: GET
- **Description**: Retrieve list of product categories
- **Response**:JSON format
