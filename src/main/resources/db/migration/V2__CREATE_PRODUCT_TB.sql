CREATE TABLE product
(
    id          INT NOT NULL,
    description VARCHAR(255),
    price       DECIMAL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);