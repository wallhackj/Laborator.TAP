1.	Comenzi și clienți cu produse cumpărate;
SELECT o.order_id, c.first_name, c.last_name, p.product_name
FROM sales.orders o
         JOIN sales.customers c on c.customer_id = o.customer_id
         JOIN sales.order_items oi on o.order_id = oi.order_id
         JOIN production.products p using (product_id)
    FETCH FIRST 50 ROWS ONLY;
2.	Stocuri disponibile în magazine pentru fiecare categorie de produse;
SELECT s.store_name, pc.category_name, SUM(p.list_price * ps.quantity) total_stock_value
FROM sales.stores s
         JOIN production.products p ON s.store_id = p.product_id
         JOIN production.categories pc ON p.category_id = pc.category_id
         LEFT JOIN sales.order_items ps ON ps.product_id = p.product_id
group by pc.category_name, s.store_name
3.	Produse comandate, reduceri aplicate și stocuri disponibile;
SELECT p.product_name,oi.quantity ordered_quantity,oi.discount,(oi.quantity * p.list_price * (1 - oi.discount)) net_price
FROM sales.order_items oi
         JOIN production.products p ON oi.product_id = p.product_id;
4.	Angajați responsabili de comenzi și detalii ale clienților;
SELECT o.order_id, c.first_name client_first_name,c.last_name client_last_name,s.first_name staff_first_name,
       s.last_name staff_last_name
FROM sales.orders o
         JOIN sales.customers c USING (customer_id)
         JOIN sales.staffs s ON o.staff_id = s.staff_id;
5.	Produse comandate împreună cu magazinele unde sunt disponibile;
SELECT p.product_name,s.store_name,oi.quantity ordered_quantity
FROM sales.order_items oi
         JOIN production.products p USING (product_id)
         JOIN sales.stores s ON s.store_id = oi.order_id;
6.	Produse, categorii și angajații care au gestionat vânzările;
SELECT p.product_name,pc.category_name,s.first_name staff_first_name,s.last_name staff_last_name
FROM production.products p
         JOIN production.categories pc ON p.category_id = pc.category_id
         JOIN sales.order_items oi ON p.product_id = oi.product_id
         JOIN sales.orders o ON oi.order_id = o.order_id
         JOIN sales.staffs s ON o.staff_id = s.staff_id;
7.	Magazine și numărul total de produse vândute;
SELECT s.store_name,COUNT(oi.product_id) total_products_sold
FROM sales.stores s
         JOIN sales.orders o ON s.store_id = o.store_id
         JOIN sales.order_items oi ON o.order_id = oi.order_id
GROUP BY s.store_name;
8.	Clienți și comenzile lor (inclusiv clienții fără comenzi);
SELECT c.first_name, c.last_name,o.order_id
FROM sales.customers c
         LEFT JOIN sales.orders o ON c.customer_id = o.customer_id;
9.	Produse și stocuri disponibile (inclusiv produse fără stocuri);
SELECT p.product_name,COALESCE(SUM(oi.quantity), 0) available_stock
FROM production.products p
         LEFT JOIN sales.order_items oi USING (product_id)
GROUP BY p.product_name;
10.	Angajați și comenzile gestionate (inclusiv angajații fără comenzi);
SELECT s.first_name,s.last_name,o.order_id
FROM sales.staffs s
         LEFT JOIN sales.orders o ON s.staff_id = o.staff_id;
11.	Produse și categoriile lor (inclusiv categorii fără produse);
SELECT c.category_name,p.product_name
FROM production.categories c
         LEFT JOIN production.products p ON c.category_id = p.category_id;
12.	Comenzi și magazinele aferente (inclusiv magazine fără comenzi);
SELECT s.store_name,o.order_id
FROM sales.stores s
         LEFT JOIN sales.orders o ON s.store_id = o.store_id;
13.	Produse comandate și stocuri aferente (inclusiv stocuri fără produse comandate);
SELECT p.product_name,COALESCE(SUM(oi.quantity), 0) total_stock
FROM production.products p
         LEFT JOIN sales.order_items oi ON p.product_id = oi.product_id
GROUP BY p.product_name;
14.	Managerii angajaților;
SELECT s1.first_name employee_first_name,s1.last_name employee_last_name,
       s2.first_name manager_first_name,s2.last_name manager_last_name
FROM sales.staffs s1
         LEFT JOIN sales.staffs s2 ON s1.manager_id = s2.staff_id;
15.	Magazine din același oraș;
SELECT s1.store_name store1,s2.store_name store2,s1.city
FROM sales.stores s1
         JOIN sales.stores s2 ON s1.city = s2.city AND s1.store_id != s2.store_id;

