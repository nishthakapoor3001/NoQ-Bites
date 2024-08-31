CREATE TABLE `noqbites`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `wat_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`));