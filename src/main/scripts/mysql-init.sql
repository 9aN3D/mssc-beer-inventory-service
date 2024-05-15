DROP DATABASE IF EXISTS beerinventoryservice;
DROP USER IF EXISTS `beer_inventory_service`@`%`;
CREATE DATABASE IF NOT EXISTS beerinventoryservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `beer_inventory_service`@`%` IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `beerinventoryservice`.* TO `beer_inventory_service`@`%`;
FLUSH PRIVILEGES;
