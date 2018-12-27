create database shopping;

use shopping;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(40),
    password VARCHAR(16),
    phone VARCHAR(40),
    address VARCHAR(255),
    register_date DATETIME
);

CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description VARCHAR(255),
    parent_id INT,
    isleaf INT,	# 0表示叶子结点，1表示非叶子节点
    grade INT
);

CREATE TABLE production (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description VARCHAR(255),
    price DOUBLE,
    member_price DOUBLE,
    production_date DATETIME,
    category_id INT REFERENCES category (id)
);

CREATE TABLE order_form (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userid INT,
    address VARCHAR(255),
    order_date DATETIME,
    status INT
);

CREATE TABLE salesitem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    production_id INT,
    unit_price DOUBLE,
    amount INT,
    orderid INT
);


