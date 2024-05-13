-------------------------------PRODUCTS----------------------------------------------

INSERT INTO products(id, brand_name, product_name, product_number, price, type_of_machine, product_warranty, warranty_in_months)
values (101,'test', 'testName',1, 300, 'SCHUURMACHINE',true, 5),
       (102, 'test2', 'testname2', 2,  60, 'ZAAGMACHINE', false, 0);

-------------------------------ORDERS--------------------------------------

INSERT INTO orders(id, user_id, product_number, user_email, user_details, order_number, price, quantity, total_price_order)
VALUES (101,1, 1, 'saskia@hotmail.com', 'saskia noord prinsenstraat 12 2415 XG Edam', 1, 300, 2, 600),
       (102, 2, 2, 'bertus@gmail.com', 'berts beukerhuis bedrijventerrein zichters hollandse rading', 2, 20, 5, 100);


--------------------------------WARRANTIES------------------------------------------------

INSERT INTO warranties(id, product_number, warranty_start, warranty_ends)
VALUES (101, 1, '2024-05-17', '2025-07-17'),
       (102, 2, '2023-12-24', '2026-01-01');

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