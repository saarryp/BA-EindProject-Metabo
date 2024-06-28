
-------------------------------USERS-------------------------------------------------

-- password password
INSERT INTO users ( username, password, email, enabled, api_key, user_details)
VALUES ('client', '$2a$12$YDXdroti6FVOoZe8mThefe5MWSJ6iBLHp5oT7DyOcDP6lLbjNx8Ly', 'user@test.nl',
        TRUE, 'Key_Api_User', 'client Bertus Spoorweglaan 4 Bunnik');

INSERT INTO users ( username, password, email, enabled, api_key, user_details)
VALUES ('admin', '$2a$12$02hpDdW3Nbh0lN6RPVgz3utGcvyKCppow/vst5UlDypzyBkAMJjNW', 'admin@test.nl', TRUE,
        'Key_Api_Admin', 'admin1 hoofdkantoor afdeling inkoop');

INSERT INTO authorities (id, authority)
VALUES (1, 'ROLE_CLIENT');

INSERT INTO authorities (id, authority)
VALUES (2, 'ROLE_ADMIN');

SELECT * FROM users;


------------------------------PRODUCTS----------------------------------------------

INSERT INTO products(id, brand_name, product_name, product_number, price, type_of_machine, product_warranty, warranty_in_months)
VALUES (201, 'testMetabo', 'testNameProduct', 1, 300, 'SCHUURMACHINE', true, 5),
       (202, 'test2Makito', 'testnameProduct2', 2, 60, 'ZAAGMACHINE', false, 0),
       (203, 'test3', 'testnameProduct3', 3, 1500, 'BOORMACHINE', true, 2);

--------------------------------WARRANTIES------------------------------------------------

INSERT INTO warranties(id, warranty_start, warranty_ends, product_model_id)
VALUES (301, '2024-05-17', '2025-07-17', 201),
       (302, '2023-12-24', '2026-01-01', 202);

-------------------------------ORDERS--------------------------------------

-- Voeg de kolom "product_id" en "invoice_id" toe aan de "orders" tabel
ALTER TABLE orders ADD COLUMN product_id INTEGER;
ALTER TABLE orders ADD COLUMN invoice_id INTEGER;

INSERT INTO orders(id, order_number, price, quantity, total_price_order, order_user_id)
VALUES
    (401, 1, 300, 2, 600, 1),
    (402, 2, 20, 5, 100, 2);



-------------------------kolom toevoegen van product en orders-------------

-- Voeg producten aan orders toe
INSERT INTO order_product (order_id, product_id)
VALUES (401, 201),
       (402, 202);

-- Update de orders tabel om de product_id's bij te werken
UPDATE orders
SET product_id = CASE
                     WHEN id = 401 THEN 201
                     WHEN id = 402 THEN 202
    END
WHERE id IN (401, 402);

---------------------------------STOCKS--------------------------------------------------
INSERT INTO stocks(id, order_placed_date, weeks_to_delivery, product_sold, quantity_in_stock, out_of_stock)
VALUES (401, '2024-03-12', 0, 5, 25, FALSE),
       (402, '2024-01-01', 2, 15, 7, TRUE);

---------------------------------INVOICE----------------------------------------------------

INSERT INTO invoices (id, invoice_id, total_price, vat21product_price, vat9product_price, net_price_without_vat, vat_rate, user_id, user_address, product_warranty, date_of_purchase, order_id)
VALUES (501, 'INV123', 500.00, 100.00, 50.00, 350.00, 21.0, 101, 'Burgemeester Reigerstraat 14 Utrecht', true, '2024-03-08',401),
       (502, 'inv321', 120.00, 200.00, 50.00, 350.00, 9.0, 102, 'Stadsring 12 3512 WX Zaandam', false, '2024-03-01',402);

-- Update de orders tabel om de invoice_id's bij te werken
UPDATE orders
SET invoice_id = CASE
                     WHEN id = 401 THEN 501
                     WHEN id = 402 THEN 502
    END
WHERE id IN (401, 402);



