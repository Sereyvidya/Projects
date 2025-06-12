CREATE DATABASE IF NOT EXISTS OFS;
USE OFS;

CREATE TABLE IF NOT EXISTS user (
  userID     INT AUTO_INCREMENT PRIMARY KEY,
  firstName  VARCHAR(50)  NOT NULL,
  lastName   VARCHAR(50)  NOT NULL,
  email      VARCHAR(320) NOT NULL,
  phone      VARCHAR(10)  NOT NULL,
  password   VARCHAR(256) NOT NULL,
  isAdmin    BOOLEAN       NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS product (
  productID   INT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(50)  NOT NULL,
  description VARCHAR(255) NOT NULL,
  price       DECIMAL(5,2) NOT NULL,
  quantity    INT          NOT NULL,
  category    VARCHAR(50)  NOT NULL,
  weight      DECIMAL(5,3) NOT NULL,
  image       LONGBLOB     NOT NULL
);

CREATE TABLE IF NOT EXISTS cart_item (
  cartItemID INT AUTO_INCREMENT PRIMARY KEY,
  userID     INT NOT NULL,
  productID  INT NOT NULL,
  quantity   INT DEFAULT 1,
  FOREIGN KEY (userID)    REFERENCES user(userID)    ON DELETE CASCADE,
  FOREIGN KEY (productID) REFERENCES product(productID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `order` (
  orderID   INT AUTO_INCREMENT PRIMARY KEY,
  userID    INT NOT NULL,
  orderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
  street    VARCHAR(100) NOT NULL,
  city      VARCHAR(50)  NOT NULL,
  state     VARCHAR(50)  NOT NULL,
  zip       VARCHAR(10)  NOT NULL,
  total     DECIMAL(7,2) NOT NULL,
  weight    DECIMAL(6,3) NOT NULL,  
  status    VARCHAR(20)  NOT NULL DEFAULT 'awaiting',
  lat       FLOAT DEFAULT NULL,       
  lng       FLOAT DEFAULT NULL,
  FOREIGN KEY (userID) REFERENCES user(userID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_item (
  orderItemID      INT AUTO_INCREMENT PRIMARY KEY,
  orderID          INT NOT NULL,
  productID        INT NOT NULL,
  quantity         INT NOT NULL,
  priceAtPurchase  DECIMAL(5,2) NOT NULL,
  FOREIGN KEY (orderID)   REFERENCES `order`(orderID)    ON DELETE CASCADE,
  FOREIGN KEY (productID) REFERENCES product(productID) ON DELETE CASCADE
);