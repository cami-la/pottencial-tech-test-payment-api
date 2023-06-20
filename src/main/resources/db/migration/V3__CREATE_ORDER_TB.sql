CREATE TABLE "order"
(
    id        BIGINT NOT NULL,
    seller_id BIGINT,
    date      date,
    status    INT,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

ALTER TABLE "order"
    ADD CONSTRAINT FK_ORDER_ON_SELLER FOREIGN KEY (seller_id) REFERENCES seller (id);