CREATE TABLE `noqbites`.`food` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `item_category` INT NOT NULL,
  `item_name` VARCHAR(255) NOT NULL,
  `item_type` INT NOT NULL,
  `price` FLOAT NOT NULL,
  `restaurant_id` INT NOT NULL,
  PRIMARY KEY (`item_id`));