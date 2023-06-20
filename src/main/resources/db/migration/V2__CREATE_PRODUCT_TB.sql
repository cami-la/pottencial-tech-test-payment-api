CREATE TABLE product
(
    id          BIGINT NOT NULL,
    description VARCHAR(255),
    price       DECIMAL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

INSERT INTO product (id, description, price) VALUES (1, 'Seguro Auto', 500.00);
INSERT INTO product (id, description, price) VALUES (2, 'Seguro Residencial', 250.00);
INSERT INTO product (id, description, price) VALUES (3, 'Seguro de Vida', 100.00);
INSERT INTO product (id, description, price) VALUES (4, 'Seguro Viagem', 150.00);
INSERT INTO product (id, description, price) VALUES (5, 'Seguro Saúde', 300.00);