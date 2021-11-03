-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sapo
-- ------------------------------------------------------
-- Server version	8.0.22
--
-- Table structure for table `category`
--
DROP DATABASE if exists `sapo`;
CREATE DATABASE `sapo`;
USE `sapo`;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `code` varchar(45) NOT NULL,
                            `name` varchar(45) NOT NULL,
                            `description` varchar(500) DEFAULT NULL,
                            `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
INSERT INTO `category` VALUES
(1,'C01','Điện thoại','ok','2021-09-10 00:00:00','2021-09-10 00:00:00'),
(2,'C02','Máy tính','ok','2021-09-01 00:00:00','2021-09-10 00:00:00'),
(3,'C03','Laptop','ok','2021-09-11 00:00:00','2021-09-10 00:00:00'),
(4,'C04','Máy giặt','ok','2021-09-11 00:00:00','2021-09-10 00:00:00'),
(5,'C05','Điều hòa','ok','2021-09-12 00:00:00','2021-09-10 00:00:00'),
(6,'C06','ti vi','ok','2021-09-21 00:00:00','2021-09-10 00:00:00'),
(7,'C07','Điện thoại2','ok','2021-09-10 00:00:00','2021-09-10 00:00:00');
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `code` varchar(45) NOT NULL,
                             `name` varchar(45) NOT NULL,
                             `address` varchar(500) DEFAULT NULL,
                             `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
INSERT INTO `inventory` VALUES
(1,'K01','khu 1','ha noi','2021-09-10 00:00:00','2021-09-10 00:00:00'),
(2,'K02','khu 2','ha noi','2021-09-11 00:00:00','2021-09-10 00:00:00'),
(3,'K03','khu 3','ha noi','2021-09-12 00:00:00','2021-09-10 00:00:00'),
(4,'K04','khu 4','ha noi','2021-09-13 00:00:00','2021-09-10 00:00:00'),
(5,'K05','khu 5','ha noi','2021-09-14 00:00:00','2021-09-10 00:00:00'),
(6,'K06','khu 6','ha noi','2021-09-15 00:00:00','2021-09-10 00:00:00');
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `code` varchar(45) NOT NULL,
                           `category_id` int NOT NULL,
                           `inventory_id` int NOT NULL,
                           `name` varchar(45) NOT NULL,
                           `price` decimal(10,0) NOT NULL,
                           `description` varchar(500) DEFAULT NULL,
                           `image` varchar(255) DEFAULT NULL,
                           `quantity` int NOT NULL,
                           `amount_sale` int NOT NULL,
                           `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `fk_category_idx` (`category_id`),
                           KEY `fk_warehouse_idx` (`inventory_id`),
                           CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
                           CONSTRAINT `fk_inventory` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
INSERT INTO `product` VALUES
(1,'P01',1,1,'abc1',123,'chung dai ka','chung dai ka',1000,123,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(2,'P02',3,2,'abdsc2',132,'chung dai ka','chung dai ka',1000,122,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(3,'P03',4,4,'abfc3',122,'chung dai ka','chung dai ka',1000,126,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(4,'P04',6,4,'abdfsc4',2,'chung dai ka','chung dai ka',1000,128,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(5,'P05',2,3,'afddsfbc5',312,'chung dai ka','chung dai ka',1000,124,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(6,'P06',3,3,'ab2ec5',212,'chung dai ka','chung dai ka',1000,14,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(7,'P07',4,5,'ffabc6',132,'chung dai ka','chung dai ka',1000,13,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(8,'P08',2,6,'retabc1',1562,'chung dai ka','chung dai ka',1000,13,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(9,'P09',4,2,'tetabc4',152,'chung dai ka','chung dai ka',1000,12,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(10,'P10',4,3,'tetafdgbc4',1552,'chung dai ka','chung dai ka',1000,12,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(11,'P11',1,1,'abc36',1233,'chung dai ka','chung dai ka',1000,123,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(12,'P12',3,2,'abdsc7',132,'chung dai ka','chung dai ka',1000,122,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(13,'P13',4,4,'abfc8',1223,'chung dai ka','chung dai ka',1000,126,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(14,'P14',6,4,'abdfsc9',24,'chung dai ka','chung dai ka',1000,128,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(15,'P15',2,3,'afddsfbc10',312,'chung dai ka','chung dai ka',1000,124,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(16,'P16',3,3,'ab2ec11',212,'chung dai ka','chung dai ka',1000,14,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(17,'P17',4,5,'ffabc12',132,'chung dai ka','chung dai ka',1000,13,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(18,'P18',2,6,'retabc13',1562,'chung dai ka','chung dai ka',1000,13,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(19,'P19',4,2,'tetabc14',152,'chung dai ka','chung dai ka',1000,12,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(20,'P20',3,3,'ab2ec15',212,'chung dai ka','chung dai ka',1000,14,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(21,'P21',4,5,'ffabc16',132,'chung dai ka','chung dai ka',1000,13,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(22,'P22',2,6,'retabc17',1562,'chung dai ka','chung dai ka',1000,13,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(23,'P23',4,2,'tetabc175',152,'chung dai ka','chung dai ka',1000,12,'2021-09-01 00:00:00','2021-09-10 00:00:00'),
(24,'P24',2,1,'y55abc4',172,'chung dai ka','chung dai ka',1000,132,'2021-09-01 00:00:00','2021-09-10 00:00:00');
UNLOCK TABLES;


-- Procedures & Functions
DELIMITER $$
DROP PROCEDURE IF EXISTS `sapo`.`get_product` $$
CREATE
    PROCEDURE `sapo`.`get_product`(IN in_id INTEGER,
                            OUT out_code VARCHAR(45),
                            OUT out_category_id INTEGER,
                            OUT out_inventory_id INTEGER,
                            OUT out_name VARCHAR(45),
                            OUT out_price DECIMAL,
                            OUT out_description VARCHAR(500),
                            OUT out_image VARCHAR(255),
                            OUT out_quantity INTEGER ,
                            OUT out_amount_sale INTEGER,
                            OUT out_created_at DATE,
                            OUT out_updated_at DATE
                            )
BEGIN
SELECT code, category_id, inventory_id, name, price, description, image, quantity, amount_sale, created_at, updated_at
INTO out_code, out_category_id, out_inventory_id, out_name, out_price, out_description, out_image, out_quantity, out_amount_sale, out_created_at,out_updated_at
FROM product WHERE id = in_id;
END$$
DELIMITER ;


DELIMITER $$
DROP PROCEDURE IF EXISTS `sapo`.`get_all_products` $$
CREATE
    PROCEDURE `sapo`.`get_all_products`()
BEGIN
SELECT * FROM product;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_top_products(IN top_count INT(11))
BEGIN
    SELECT * FROM product
    ORDER BY amount_sale DESC
    LiMIT top_count;
END; $$
DELIMITER ;




-- Spring security

-- -----------------------------------------------------
-- Table `sapo`.`users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                      `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NULL,
                      `email` VARCHAR(45) NULL,
                      `username` VARCHAR(45) NULL,
                      `password` VARCHAR(100) NULL,
                      `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      PRIMARY KEY (`id`),
                      UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
                      UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sapo`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
                      `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(60) NULL,
                      PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sapo`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
                           `user_id` BIGINT(20) NOT NULL,
                           `role_id` BIGINT(20) NOT NULL,
                           PRIMARY KEY (`user_id`, `role_id`),
                           INDEX `fk_user_roles_roles1_idx` (`role_id` ASC) VISIBLE,
                           CONSTRAINT `fk_role`
                               FOREIGN KEY (`role_id`)
                                   REFERENCES `roles` (`id`)
                                   ON DELETE NO ACTION
                                   ON UPDATE NO ACTION,
                           CONSTRAINT `fk_user`
                               FOREIGN KEY (`user_id`)
                                   REFERENCES `users` (`id`)
                                   ON DELETE NO ACTION
                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB;

LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES
(1,'ROLE_USER'),
(2,'ROLE_ADMIN'),
(3,'ROLE_PM');
UNLOCK TABLES;

DROP TABLE IF EXISTS `statistics`;

CREATE TABLE `statistics` (
                         `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                         `inventory_id` BIGINT(20) NOT NULL,
                         `products_number` INT NOT NULL,
                         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`))
    ENGINE = InnoDB;

