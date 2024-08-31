CREATE TABLE `noqbites`.`food_category_enum` (
  `id` INT NOT NULL,
  `value` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `noqbites`.`food_category_enum` (`id`, `value`) VALUES ('1', 'BEVERAGE');
INSERT INTO `noqbites`.`food_category_enum` (`id`, `value`) VALUES ('2', 'NON_BEVERAGE');