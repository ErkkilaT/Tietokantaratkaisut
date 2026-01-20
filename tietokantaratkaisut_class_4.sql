SELECT * FROM orders;

START TRANSACTION;

UPDATE products
JOIN orderitems
ON orderitems.product_id = products.id
JOIN orders ON orders.id = orderitems.order_id
SET products.stock_quantity = products.stock_quantity + orderitems.quantity
WHERE orderitems.order_id = 7 AND orders.status != 'CANCELLED';

UPDATE orders
SET orders.status = 'CANCELLED'
WHERE id = 7 AND orders.status != 'CANCELLED';

COMMIT;

ROLLBACK;

SELECT id, status FROM orders WHERE orders.id=7;

SELECT * FROM orderitems WHERE order_id = 7;

SELECT id, stock_quantity FROM products
JOIN orderitems ON products.id = orderitems.product_id
WHERE orderitems.order_id = 7;


UPDATE orders
SET orders.status = "NEW"
WHERE id = 7;
