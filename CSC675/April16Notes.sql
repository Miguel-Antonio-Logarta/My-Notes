Use MusicDB;

-- DELETE FROM <table> WHERE <condition>
-- CAUTION: If you forget the WHERE then you will clean up the table if you don't use the safe mode that by default is on
-- CAUTION: If you try to delete a value that doesn't exist in the table, you WON'T get an error.
DELETE FROM Invoice WHERE customer = 1;
SELECT Customer.customer_id AS "Customer_id from Customer table", Invoice.customer AS "Customer_id from Invoice"
FROM Invoice
INNER JOIN Customer ON Customer.customer_id = Invoice.customer

-- UPDATE <TABLE> SET <attribute1 = new value> WHERE <attribute2 = another value>
UPDATE Customer SET customer_id = 100 WHERE name = "Jose";

-- Triggers, Procedures, and Functions
Use MusicDB;
DELIMITER $$
FUNCTION SHSHSH
  SELECT KSKSKS;
END FUNCTION $$

DELIMITER ;


CREATE TRIGGER trg_compute_customer_total_sales AFTER INSERT ON Invoice
FOR EACH ROW
    BEGIN
        -- Initialize a variable to hold the temporal sales of the invoice
        DECLARE sales DEC(8,0)
        -- Initialize a variable to check if the customer exists
        DECLARE customer_exists TINYINT;
        -- Logic
        SET sales  = (new.quantity * new.unit_price);
        SET customer_exists = (SELECT COUNT(customer) FROM Sales WHERE customer = new.customer)

        IF customer_exists > 0 THEN
          UPDATE Sales SET total_sales  = total_sales + sales WHERE customer = new.customer;
        ELSE
          INSERT INTO Sales (customer, total_sales) VALUES (new.customer, sales);
        END IF;
END$$

DROP TRIGGER IF EXISTS trg_backup_customer_data


CREATE TRIGGER trg_backup_customer_data AFTER DELETE ON Customer
  
-- When a customer is removed from the database, the associated data from this customer is 
-- backed up for future reference, such as for analyzing company revenue statistics.
