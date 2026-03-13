

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.customeraddresses, firma.customers
TO 'webstore'@'localhost';

GRANT SELECT, INSERT
ON firma.orderitems, firma.orders
TO 'webstore'@'localhost';

GRANT SELECT
ON firma.productcategories, firma.products
TO 'webstore'@'localhost';


CREATE USER storemanagement;

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.productcategories, firma.products, firma.suppliers, firma.supplieraddresses
TO 'storemanagement'@'localhost';

GRANT SELECT
ON firma.dailyordercount, firma.orders, firma.orderitems
TO 'storemanagement'@'localhost';


SHOW USERS;



CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'viljo123';

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.*
TO 'dbuser'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.customers
TO 'dbuser'@'localhost';

GRANT SELECT, INSERT
ON firma.orderitems
TO 'webstore'@'localhost';

GRANT SELECT, INSERT
ON firma.orders
TO 'webstore'@'localhost';

GRANT SELECT
ON firma.productcategories
TO 'webstore'@'localhost';

GRANT SELECT
ON firma.products
TO 'webstore'@'localhost';

CREATE USER 'storemanagement'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.productcategories
TO 'storemanagement'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.products
TO 'storemanagement'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.suppliers
TO 'storemanagement'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE
ON firma.supplieraddresses
TO 'storemanagement'@'localhost';

GRANT SELECT
ON firma.dailyordercount
TO 'storemanagement'@'localhost';

GRANT SELECT
ON firma.orders
TO 'storemanagement'@'localhost';

GRANT SELECT
ON firma.orderitems
TO 'storemanagement'@'localhost';