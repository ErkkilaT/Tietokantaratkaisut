SELECT * FROM customers
LIMIT 10;

SELECT first_name, customeraddresses.city, orders.`status`
FROM customers 
INNER JOIN customeraddresses 
ON customers.id = customeraddresses.customer_id
INNER JOIN orders
ON customers.id = orders.customer_id
LIMIT 10;

SELECT first_name, customeraddresses.city
FROM customers
LEFT JOIN customeraddresses
ON customers.id = customeraddresses.customer_id
LIMIT 10;

SELECT country, COUNT(*)
FROM customeraddresses
GROUP BY country
HAVING COUNT(*) > 500;

SELECT first_name, customeraddresses.city, customeraddresses.country
FROM  customers
INNER JOIN customeraddresses
ON customers.id = customeraddresses.customer_id
WHERE customeraddresses.city = (
	SELECT city
	FROM customers INNER JOIN customeraddresses
	ON customers.id = customeraddresses.customer_id
	WHERE first_name = 'Dustin' AND last_name = 'Carey'
);