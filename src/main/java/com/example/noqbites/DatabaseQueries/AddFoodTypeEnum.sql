CREATE TABLE `noqbites`.`food_type_enum` (
  `id` INT NOT NULL,
  `value` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `noqbites`.`food_type_enum` (`id`, `value`) VALUES ('1', 'VEG');
INSERT INTO `noqbites`.`food_type_enum` (`id`, `value`) VALUES ('2', 'NON-VEG');
