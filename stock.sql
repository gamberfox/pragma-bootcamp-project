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
    brand_id BIGINT,
    FOREIGN KEY (brand_id) REFERENCES brand(id)
);
-- article_category table
DROP TABLE IF EXISTS article_category;
CREATE TABLE article_category (
    article_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (article_id, category_id),
    FOREIGN KEY (article_id) REFERENCES article(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
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
INSERT INTO article(
        name,
        description,
        stock_quantity,
        price,
        brand_id
    )
VALUES('article1', "article description", 2, 30.00, 1);
INSERT INTO article(
        name,
        description,
        stock_quantity,
        price,
        brand_id
    )
VALUES('article2', "article description", 5, 10.00, 1);
--
INSERT INTO article_category(article_id, category_id)
VALUES(1, 1);
INSERT INTO article_category(article_id, category_id)
VALUES(2, 2);