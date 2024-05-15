DROP DATABASE IF EXISTS beersinventoryervice;
DROP USER IF EXISTS `beersinventoryervice`@`%`;
CREATE DATABASE IF NOT EXISTS beersinventoryervice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `beersinventoryervice`@`%` IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `beersinventoryervice`.* TO `beersinventoryervice`@`%`;
FLUSH PRIVILEGES;
