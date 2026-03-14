ALTER TABLE products
ADD startdate DATE DEFAULT CURRENT_DATE,
ADD enddate DATE DEFAULT '2106-12-31',
ADD PERIOD FOR valid_time (startdate,enddate);


SELECT * FROM products LIMIT 10;

SELECT *  FROM products WHERE name = "Super Bug 360";

SELECT * FROM products WHERE NOW() BETWEEN startdate AND enddate AND name = "Super Bug 360";


SELECT * FROM products WHERE "2026-02-16" BETWEEN startdate AND enddate AND name = "Super Bug 360";



UPDATE products
FOR PORTION OF valid_time FROM "2026-02-15" TO '2106-12-31'
SET price = 30
WHERE id = 1;





CREATE TABLE Products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0,
    category_id INT,
    supplier_id INT,
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id) REFERENCES ProductCategories(id),
    CONSTRAINT fk_product_supplier
        FOREIGN KEY (supplier_id) REFERENCES Suppliers(id)
);