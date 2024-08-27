USE stock;
DROP TABLE IF EXISTS article_category;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS brand;
DROP TABLE IF EXISTS category;
---------------- stock database for the emazon sotre--------------------------------
USE stock;
-- brand table
DROP TABLE IF EXISTS brand;
CREATE TABLE brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    description VARCHAR(120)
);
-- category table
DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) unique,
    description VARCHAR(90) NOT NULL
);
-- article table
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name TEXT,
    description TEXT,
    stock_quantity BIGINT,
    price decimal(10, 2),
    id_brand BIGINT,
    FOREIGN KEY (id_brand) REFERENCES brand(id)
);
-- article_category table
DROP TABLE IF EXISTS article_category;
CREATE TABLE article_category (
    id_article BIGINT NOT NULL,
    id_category BIGINT NOT NULL,
    PRIMARY KEY (id_article, id_category),
    FOREIGN KEY (id_article) REFERENCES article(id),
    FOREIGN KEY (id_category) REFERENCES category(id)
);
INSERT INTO brand(name, description)
VALUES('brand1', 'brand1 description');
INSERT INTO brand(name, description)
VALUES('brand2', 'brand2 description');
--
INSERT INTO category(name, description)
VALUES('category1', 'category1 description');
INSERT INTO category(name, description)
VALUES('category2', 'category2 description');
--
INSERT INTO article(name, stock_quantity, price, id_brand)
VALUES('article1', 2, 30.00, 1);
INSERT INTO article(name, stock_quantity, price, id_brand)
VALUES('article2', 5, 10.00, 1);
--
INSERT INTO article_category(id_article, id_category)
VALUES(1, 1);
INSERT INTO article_category(id_article, id_category)
VALUES(2, 2);