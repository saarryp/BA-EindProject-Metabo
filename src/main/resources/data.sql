-------------------------------PRODUCTS----------------------------------------------

INSERT INTO products(id, brand_name, product_name, price, type_of_machine, product_warranty, warranty_in_months)
values (101,'test', 'testName',300, 'SCHUURMACHINE',true, 5 );

-------------------------------ORDERS--------------------------------------

INSERT INTO orders(id, user_id, product_number, user_email, user_details, order_number, price, quantity, total_price_order)
VALUES (101,1, 1, 'saskia@hotmail.com', 'saskia noord prinsenstraat 12 2415 XG Edam', 1, 300, 2, 600);


--------------------------------WARRANTIES------------------------------------------------

INSERT INTO warranties(id, product_number, warranty_start, warranty_ends)
VALUES (101, 1, '2024-05-17', '2025-07-17');



---------------------------------INVOICE----------------------------------------------------


INSERT INTO invoices ( id,
    invoice_id, product_number, product_name, total_price, vat21product_price,
    vat9product_price, net_price_without_vat, vat_rate, vat_amount, user_id,
    user_address, product_warranty, warranty_in_months, date_of_purchase
) VALUES (
           101, 'INV123', 1, 'Product ABC', 500.00, 100.00, 50.00, 350.00, 21.0, 150.00, 101,
             '123 Main Street, Cityville', true, 12, '2024-03-08'
         );


-------------------------------USERS---------------------------------------------------