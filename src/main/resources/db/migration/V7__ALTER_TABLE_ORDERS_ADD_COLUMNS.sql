ALTER TABLE ORDERS
    ADD COLUMN NAME VARCHAR(250);

ALTER TABLE ORDERS
    ADD COLUMN DATE DATE;

ALTER TABLE ORDERS
    RENAME COLUMN ALTERNATIVE_MOBILE_NUMBER TO MOBILE_NO;

ALTER TABLE ORDERS
    ADD COLUMN ORDERED_BOOKS TEXT[];

ALTER TABLE ORDERS
    ADD COLUMN ORDER_VALUE VARCHAR(50);