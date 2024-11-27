CREATE SCHEMA IF NOT EXISTS sales;
CREATE SCHEMA IF NOT EXISTS production;

CREATE TABLE IF NOT EXISTS sales.customers (
    customer_id serial PRIMARY KEY,
    first_name VARCHAR (255) NOT NULL,
    last_name VARCHAR (255) NOT NULL,
    phone VARCHAR (25),
    email VARCHAR (255) NOT NULL,
    street VARCHAR (255),
    city VARCHAR (50),
    state VARCHAR (25),
    zip_code VARCHAR (5)
);

CREATE TABLE IF NOT EXISTS sales.orders (
    order_id serial PRIMARY KEY,
    customer_id INT,
    order_status smallint NOT NULL,
    -- Order status: 1 = Pending; 2 = Processing; 3 = Rejected; 4 = Completed
    order_date DATE NOT NULL,
    required_date DATE NOT NULL,
    shipped_date DATE,
    store_id INT NOT NULL,
    staff_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES sales.customers (customer_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (store_id) REFERENCES sales.stores (store_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (staff_id) REFERENCES sales.staffs (staff_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS sales.staffs (
    staff_id serial PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    email VARCHAR (255) NOT NULL UNIQUE,
    phone VARCHAR (25),
    active smallint NOT NULL,
    store_id INT NOT NULL,
    manager_id INT,
    FOREIGN KEY (store_id) REFERENCES sales.stores (store_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (manager_id) REFERENCES sales.staffs (staff_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS sales.stores (
    store_id serial PRIMARY KEY,
    store_name VARCHAR (255) NOT NULL,
    phone VARCHAR (25),
    email VARCHAR (255),
    street VARCHAR (255),
    city VARCHAR (255),
    state VARCHAR (10),
    zip_code VARCHAR (5)
);

CREATE TABLE IF NOT EXISTS sales.order_items(
    order_id INT,
    item_id INT,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    list_price DECIMAL (10, 2) NOT NULL,
    discount DECIMAL (4, 2) NOT NULL DEFAULT 0,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES sales.orders (order_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES production.products (product_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS production.categories (
    category_id serial PRIMARY KEY,
    category_name VARCHAR (255) NOT NULL
);

CREATE TABLE IF NOT EXISTS production.brands (
    brand_id serial PRIMARY KEY,
    brand_name VARCHAR (255) NOT NULL
);

CREATE TABLE IF NOT EXISTS production.products (
    product_id serial PRIMARY KEY,
    product_name VARCHAR (255) NOT NULL,
    brand_id INT NOT NULL,
    category_id INT NOT NULL,
    model_year SMALLINT NOT NULL,
    list_price DECIMAL (10, 2) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES production.categories (category_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (brand_id) REFERENCES production.brands (brand_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS production.stocks(
        store_id INT,
        product_id INT,
        quantity INT,
        PRIMARY KEY (store_id, product_id),
        FOREIGN KEY (store_id) REFERENCES sales.stores (store_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (product_id) REFERENCES production.products (product_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- 1.	Ce clienți au domiciliul în statul 'CA'?
 Select * from sales.customers
 where state = 'CA'
-- 2.	Ce produse nu aparțin brandului cu ID-ul 3?
Select * from production.products
where brand_id != 3
-- 3.	Care sunt articolele din comenzi cu o cantitate mai mare de 5?
Select * from sales.order_items
where order_items.quantity > 5
-- 4.	Care sunt clienții care au codul poștal mai mic de 50000?
Select * from sales.customers
where CAST(zip_code as INT) < 50000
-- 5.	Care sunt angajații cu ID-ul managerului egal cu 1?
Select * from sales.staffs
where manager_id = 1
-- 6.	Ce comenzi au fost plasate în anul 2023?
Select * from sales.orders
where EXTRACT(YEAR FROM order_date) = 2023;
-- 7.	Ce angajați au email-ul ce se termină în '@bikestores.com'?
Select * from sales.staffs
where email like '%@bikestores.com'
-- 8.	Ce clienți au numele de familie care începe cu litera 'S'?
Select * from sales.customers
where last_name like 'S%'
-- 9.	Care sunt magazinele cu numele ce conține 'Bike'?
Select * from sales.stores
where store_name like '%Bike%'
-- 10.	Ce comenzi sunt în stare 'Shipped' și provin din statul 'NY'?
SELECT * from sales.orders o
                  Join sales.stores s on s.store_id = o.store_id
where o.order_status = 4 AND s.state = 'NY'
-- 11.	Care sunt stocurile cu cantități mai mici de 10 sau mai mari de 50?
SELECT * from production.stocks
where quantity < 10 or quantity > 50

-- 12.	Ce clienți nu locuiesc în statul 'TX'?
SELECT * from sales.customers
where state != 'TX'
-- 13.	Care este numărul total de comenzi?
SELECT COUNT(o.order_id) from sales.orders o
-- 13.	Care este cantitatea totală de produse în stocuri?
SELECT sum(s.quantity) from production.stocks s
-- 14.	Care este prețul mediu al produselor?
SELECT avg(p.list_price) from production.products p
-- 15.	Care este prețul minim al produselor?
SELECT min(p.list_price) from production.products p
-- 16.	Care este prețul maxim al produselor?
SELECT max(p.list_price) from production.products p

-- 17.	Care este numărul total de clienți din statul 'CA'?
SELECT count(c.customer_id) from sales.customers c
where state = 'CA'
-- 19.	Care sunt numele clienților scrise cu majuscule?
SELECT upper(c.last_name), upper(c.first_name) from sales.customers c

-- 20.	Care sunt orașele magazinelor scrise cu minuscule?
SELECT upper(s.city) from sales.stores s

-- 21.	Care este lungimea numărului de telefon pentru fiecare client?
SELECT length(c.phone) from sales.customers c
where phone IS NOT NULL
-- 22.	Care este lungimea numelui fiecărui produs?
SELECT length(p.product_name) from production.products p


-- 23.	Care sunt prenumele clienților scrise cu minuscule?
SELECT lower(c.last_name) from sales.customers c
-- 25.	În ce an au fost plasate comenzile?
SELECT extract(year from o.order_date) from sales.orders o
-- 26.	În ce lună au fost expediate comenzile?
SELECT EXTRACT(MONTH from o.shipped_date) from sales.orders o
-- 27.	În ce zi au fost plasate comenzile?
SELECT extract(day from o.order_date) from sales.orders o

-- 28.	În ce lună au fost înregistrate produsele cu ID mai mare de 100?
SELECT p.model_year FROM production.products p
where product_id > 100
-- 29.	Care sunt comenzile din anul 2018?
SELECT * FROM sales.orders o
where EXTRACT(year from order_date) = 2018
-- 30.	Care sunt angajații activi cu ID-ul mai mic de 50?
SELECT * FROM sales.staffs s
where s.active = 1 AND s.staff_id < 50
