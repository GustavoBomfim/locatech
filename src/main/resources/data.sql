CREATE TABLE veiculos
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca        VARCHAR(255),
    modelo       VARCHAR(255),
    placa        VARCHAR(25),
    ano          INT,
    cor          VARCHAR(255),
    valor_diaria DECIMAL(10, 2)
);

CREATE TABLE pessoas
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(255),
    cpf      VARCHAR(255),
    telefone VARCHAR(25),
    email    VARCHAR(255)

);

INSERT INTO veiculos(marca, modelo, placa, ano, cor, valor_diaria)
VALUES ('Chevrolet', 'Celta', 'ABC-1234', 2010, 'preto', 100.00);

INSERT INTO pessoas(nome, cpf, telefone, email)
VALUES ('Gustavo', '123998875434', '8927568291', 'gustavo@gmail.com');