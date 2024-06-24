-------------------------------USERS-------------------------------------------------

-- password password
INSERT INTO users ( username, password, email, enabled, api_key, user_details)
VALUES ( 'client', '$2a$12$YDXdroti6FVOoZe8mThefe5MWSJ6iBLHp5oT7DyOcDP6lLbjNx8Ly','user@test.nl',
        TRUE, 'Key_Api_User', 'client Bertus Spoorweglaan 4 Bunnik');

INSERT INTO users ( username, password, email, enabled, api_key, user_details)
VALUES ( 'admin', '$2a$12$02hpDdW3Nbh0lN6RPVgz3utGcvyKCppow/vst5UlDypzyBkAMJjNW', 'admin@test.nl', TRUE,
        'Key_Api_Admin', 'admin1 hoofdkantoor afdeling inkoop');

INSERT INTO authorities (id, authority)
VALUES (1, 'ROLE_CLIENT');

INSERT INTO authorities (id, authority)
VALUES (2, 'ROLE_ADMIN');

SELECT * FROM users;


------------------------------PRODUCTS----------------------------------------------

INSERT INTO products(id, brand_name, product_name, product_number, price, type_of_machine, product_warranty, warranty_in_months)
values (201,'testMetabo', 'testNameProduct',1, 300, 'SCHUURMACHINE',true, 5),
       (202, 'test2Makito', 'testnameProduct2', 2,  60, 'ZAAGMACHINE', false, 0),
       (203, 'test3', 'testnameProduct3', 3, 1500, 'BOORMACHINE', true, 2);

--------------------------------WARRANTIES------------------------------------------------

INSERT INTO warranties(id, warranty_start, warranty_ends, product_model_id)
VALUES (301,  '2024-05-17', '2025-07-17', 201),
       (302,  '2023-12-24', '2026-01-01', 202);


-- Voeg de kolom "product_id" toe aan de "orders" tabel
ALTER TABLE orders
    ADD COLUMN product_id INTEGER;

-- Voeg de kolom "invoice_id" toe aan de "orders" tabel
ALTER TABLE orders
    ADD COLUMN invoice_id INTEGER;


-------------------------------ORDERS--------------------------------------


INSERT INTO orders(id, order_number, price, quantity, total_price_order, order_user_id, invoice_id)
VALUES
    (401,  1, 300, 2, 600,1, 601),
    (402,  2, 20, 5, 100,2, 701);

-------------------------kolom toevoegen van product en orders-------------


UPDATE orders
SET product_id = (
    CASE
        WHEN order_number = 1 THEN 101
        WHEN order_number = 2 THEN 102
        END
    )
WHERE order_number IN (1, 2);


-- -- Orders koppelen aan invoices
-- UPDATE orders
-- SET invoice_id = (
--     CASE
--         WHEN order_number = 1 THEN 101
--         WHEN order_number = 2 THEN 102
--         END
--     )
-- WHERE order_number IN (1, 2);

-- Update orders met product_id en invoice_id
UPDATE orders
SET product_id = 201, invoice_id = 501
WHERE id = 401;

UPDATE orders
SET product_id = 202, invoice_id = 502
WHERE id = 402;


-- Invoices koppelen aan orders
UPDATE invoices
SET order_id = (
    CASE
        WHEN invoice_id = 'INV123' THEN 501
        WHEN invoice_id = 'inv321' THEN 102
        END
    )
WHERE invoice_id IN ('INV123', 'inv321');

----------------------------------STOCKS--------------------------------------------------
INSERT INTO stocks(id, order_placed_date, weeks_to_delivery, product_sold, quantity_in_stock, out_of_stock)
VALUES (401, '2024-03-12', 0, 5, 25, FALSE),
        (402, '2024-01-01', 2, 15, 7,'True');

---------------------------------INVOICE----------------------------------------------------


INSERT INTO invoices ( id,
    invoice_id, total_price, vat21product_price,
    vat9product_price, net_price_without_vat, vat_rate,  user_id,
    user_address, product_warranty,  date_of_purchase
) VALUES (
           501, 'INV123',  500.00, 100.00, 50.00, 350.00, 21.0, 101,
             'Burgemeester Reigerstraat 14 Utrecht', true, '2024-03-08'
         ),
            (502, 'inv321', 120.00, 200.00, 50.00, 350.00, 9.0, 102,
             'Stadsring 12 3512 WX Zaandam', false, '2024-03-01'
         );

-- Update de orders tabel om de invoice_id's bij te werken
UPDATE orders
SET invoice_id = CASE
                     WHEN id = 401 THEN 501
                     WHEN id = 402 THEN 502
    END
WHERE id IN (401, 402);


-- -------------------------------INVOICES & WARRANTIES----------------------------------------


-- -- Factuur 101 koppelen aan garantie 101
-- UPDATE invoices
-- SET warranty_id = 101
-- WHERE id = 101;
--
-- -- Factuur 102 koppelen aan garantie 102
-- UPDATE invoices
-- SET warranty_id = 102
-- WHERE id = 102;



-- voeg order aan product toe
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
