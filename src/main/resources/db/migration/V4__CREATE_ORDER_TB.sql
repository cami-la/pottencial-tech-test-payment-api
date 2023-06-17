CREATE TABLE "order"
(
    id        BIGINT NOT NULL,
    seller_id BIGINT,
    date      date,
    status    INT,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE order_itens
(
    order_id BIGINT NOT NULL,
    itens_id BIGINT NOT NULL
);

ALTER TABLE order_itens
    ADD CONSTRAINT uc_order_itens_itens UNIQUE (itens_id);

ALTER TABLE "order"
    ADD CONSTRAINT FK_ORDER_ON_SELLER FOREIGN KEY (seller_id) REFERENCES seller (id);

ALTER TABLE order_itens
    ADD CONSTRAINT fk_ordite_on_item FOREIGN KEY (itens_id) REFERENCES item (id);

ALTER TABLE order_itens
    ADD CONSTRAINT fk_ordite_on_order FOREIGN KEY (order_id) REFERENCES "order" (id);