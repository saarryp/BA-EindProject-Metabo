-------------------------------PRODUCTS----------------------------------------------

INSERT INTO products(id, brand_name, product_name, product_number, price, type_of_machine, product_warranty, warranty_in_months)
values (101,'test', 'testName',1, 300, 'SCHUURMACHINE',true, 5),
       (102, 'test2', 'testname2', 2,  60, 'ZAAGMACHINE', false, 0);


----------------------------meerdere images aan product koppelen-----------------------------------


INSERT INTO product_images (product_id, image_url)
VALUES
    (101, 'url_naar_afbeelding_1.jpg'),
    (101, 'url_naar_afbeelding_2.jpg');


-- Voeg de kolom "product_id" toe aan de "orders" tabel
ALTER TABLE orders
    ADD COLUMN product_id INTEGER;

-- Voeg de kolom "invoice_id" toe aan de "orders" tabel
ALTER TABLE orders
    ADD COLUMN invoice_id INTEGER;


-------------------------------ORDERS--------------------------------------


INSERT INTO orders(id, product_id, invoice_id, product_number, user_email, user_details, order_number, price, quantity, total_price_order)
VALUES
    (101, 10, 101, 123456, 'saskia@hotmail.com', 'saskia noord prinsenstraat 12 2415 XG Edam', 1, 300, 2, 600),
    (102, 11, 102, 202030, 'bertus@gmail.com', 'berts beukerhuis bedrijventerrein zichters hollandse rading', 2, 20, 5, 100);

-------------------------kolom toevoegen van product en orders-------------


UPDATE orders
SET product_id = (
    CASE
        WHEN order_number = 1 THEN 101
        WHEN order_number = 2 THEN 102
        END
    )
WHERE order_number IN (1, 2);


-- Orders koppelen aan invoices
UPDATE orders
SET invoice_id = (
    CASE
        WHEN order_number = 1 THEN 101
        WHEN order_number = 2 THEN 102
        END
    )
WHERE order_number IN (1, 2);

-- Invoices koppelen aan orders
UPDATE invoices
SET order_id = (
    CASE
        WHEN invoice_id = 'INV123' THEN 101
        WHEN invoice_id = 'inv321' THEN 102
        END
    )
WHERE invoice_id IN ('INV123', 'inv321');

----------------------------------STOCKS--------------------------------------------------
INSERT INTO stocks(id,brand_name, product_name, product_number, type_of_machine,
                   product_in_stock, order_placed_date, weeks_to_delivery, product_sold, quantity_in_stock, out_of_stock)
VALUES (101, 'Metabo', 'Metabo Schuurmachine Z234', 987654, 'SCHUURMACHINE',
        15, '2024-03-12', 0, 5, 25, FALSE),
        (102, 'Metabo', 'Metabo schroefmachine S123', 12345, 'SCHROEFMACHINE',
         5, '2024-01-01', 2, 15, 7,'True');

---------------------------------INVOICE----------------------------------------------------


INSERT INTO invoices ( id,
    invoice_id, product_number, product_name, total_price, vat21product_price,
    vat9product_price, net_price_without_vat, vat_rate, vat_amount, user_id,
    user_address, product_warranty, warranty_in_months, date_of_purchase
) VALUES (
           101, 'INV123', 1, 'Metabo zaagmachine', 500.00, 100.00, 50.00, 350.00, 21.0, 150.00, 101,
             'Burgemeester Reigerstraat 14 Utrecht', true, 12, '2024-03-08'
         ),
            (102, 'inv321', 2, 'Metabo', 120.00, 200.00, 50.00, 350.00, 9.0, 140.00, 102,
             'Stadsring 12 3512 WX Zaandam', false, 0, '2024-03-01'
         );


--------------------------------WARRANTIES------------------------------------------------

INSERT INTO warranties(id, product_number, warranty_start, warranty_ends)
VALUES (101, 1, '2024-05-17', '2025-07-17'),
       (102, 2, '2023-12-24', '2026-01-01');

-- -------------------------------INVOICES & WARRANTIES----------------------------------------


-- Factuur 101 koppelen aan garantie 101
UPDATE invoices
SET warranty_id = 101
WHERE id = 101;

-- Factuur 102 koppelen aan garantie 102
UPDATE invoices
SET warranty_id = 102
WHERE id = 102;



-------------------------------USERS-------------------------------------------------

-- password password
INSERT INTO users (username, password, email, enabled, api_key, user_details)
VALUES ('client', '$2a$12$YDXdroti6FVOoZe8mThefe5MWSJ6iBLHp5oT7DyOcDP6lLbjNx8Ly','user@test.nl',
        TRUE, 'Key_Api_User', 'client Bertus Spoorweglaan 4 Bunnik');

INSERT INTO users (username, password, email, enabled, api_key, user_details)
VALUES ('admin', '$2a$12$02hpDdW3Nbh0lN6RPVgz3utGcvyKCppow/vst5UlDypzyBkAMJjNW', 'admin@test.nl', TRUE,
        'Key_Api_Admin', 'admin1 hoofdkantoor afdeling inkoop');

INSERT INTO authorities (id, authority)
VALUES (1, 'ROLE_CLIENT');

INSERT INTO authorities (id, authority)
VALUES (2, 'ROLE_ADMIN');

SELECT * FROM users;
