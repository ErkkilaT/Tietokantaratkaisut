SELECT first_name, customeraddresses.country, orders.`status`
FROM customers 
INNER JOIN customeraddresses 
ON customers.id = customeraddresses.customer_id
INNER JOIN orders
ON customers.id = orders.customer_id
WHERE orders.`status` = "NEW" AND customeraddresses.country = "France";

CREATE INDEX idx_country
ON customeraddresses(country);

SELECT price, products.name, productcategories.name
FROM products
INNER JOIN productcategories
ON products.category_id = productcategories.id
WHERE productcategories.`name` = "Elektroniikka";



SELECT customers.first_name, orders.order_date
FROM customers
INNER JOIN orders
ON customers.id = orders.customer_id
WHERE customers.id = 12;

SELECT orders.id, customeraddresses.postal_code, orders.order_date, products.`name`, orderitems.unit_price, orderitems.quantity
FROM customers
INNER JOIN customeraddresses
ON customeraddresses.customer_id = customers.id
INNER JOIN orders
ON orders.customer_id = customers.id
INNER JOIN orderitems
ON orders.id = orderitems.order_id
INNER JOIN products
ON products.id = orderitems.product_id
WHERE customers.id = 12;

explain SELECT customers.first_name, contacts.email
FROM contacts
INNER JOIN customers
ON customers.email = contacts.email;

CREATE INDEX idx_email_cust
ON customers(email);

