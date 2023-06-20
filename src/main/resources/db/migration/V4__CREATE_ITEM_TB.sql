CREATE TABLE item
(
    id         BIGINT NOT NULL,
    product_id BIGINT,
    quantity   INT    NOT NULL,
    order_id   BIGINT,
    CONSTRAINT pk_item PRIMARY KEY (id)
);

ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES "order" (id);

ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);