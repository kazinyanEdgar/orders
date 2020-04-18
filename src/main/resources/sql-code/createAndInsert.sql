CREATE SCHEMA `database`;

CREATE TABLE `database`.`orders` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(45) NOT NULL,
  `customer_Address` VARCHAR(45) NOT NULL,
  `total_amount` INT NOT NULL,
  `date_manufacture` DATE NOT NULL,
  PRIMARY KEY (`id_order`));
INSERT INTO `database`.`orders` (`customer_name`, `customer_Address`, `total_amount`, `date_manufacture`) VALUES ('Рунет', 'Москва', '20000', '2015.12.05');
INSERT INTO `database`.`orders` (`customer_name`, `customer_Address`, `total_amount`, `date_manufacture`) VALUES ('Стройгруп', 'Воронеж', '30000', '2020.02.02');
INSERT INTO `database`.`orders` (`customer_name`, `customer_Address`, `total_amount`, `date_manufacture`) VALUES ('Лайв', 'Бегород', '10000', '2019.03.5');


CREATE TABLE `database`.`product_description` (
  `id_serial_number` VARCHAR(45) NOT NULL,
  `product_name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `date_serial_production` DATE NOT NULL,
  PRIMARY KEY (`id_serial_number`));

  CREATE TABLE `database`.`order_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `serial_number` VARCHAR(45) NOT NULL,
  `number` INT NOT NULL,
  `id_order_details` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_idx` (`id_order_details` ASC) VISIBLE,
  INDEX `fk_product_description_idx` (`serial_number` ASC) VISIBLE,
  CONSTRAINT `fk_orders`
    FOREIGN KEY (`id_order_details`)
    REFERENCES `database`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_description`
    FOREIGN KEY (`serial_number`)
    REFERENCES `database`.`product_description` (`id_serial_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


    /*Эти скрипты для заполнение таблиц `product_description` и `order_details`. Их использовать не надо. При нажатии на кнопку "Обновить данные по товарам", они сами заполнятся.

INSERT INTO `database`.`product_description` (`id_serial_number`, `product_name`, `description`, `date_serial_production`) VALUES ('1q2e1z611', 'Краска', 'Темных оттенков', '2015.10.02');
INSERT INTO `database`.`product_description` (`id_serial_number`, `product_name`, `description`, `date_serial_production`) VALUES ('23dw292', 'Текстиль', 'Для дома', '2018.01.10');
INSERT INTO `database`.`product_description` (`id_serial_number`, `product_name`, `description`, `date_serial_production`) VALUES ('3s1a027s', 'Лампы', 'Дневного света', '2019.05.6');
INSERT INTO `database`.`product_description` (`id_serial_number`, `product_name`, `description`, `date_serial_production`) VALUES ('4s452d34', 'Ключи', 'Разных видов', '2020.01.30');

INSERT INTO `database`.`order_details` (`serial_number`, `number`, `id_order_details`) VALUES ('1q2e1z611', '10', '1');
INSERT INTO `database`.`order_details` (`serial_number`, `number`, `id_order_details`) VALUES ('23dw292', '20', '1');
INSERT INTO `database`.`order_details` (`serial_number`, `number`, `id_order_details`) VALUES ('23dw292', '5', '2');
INSERT INTO `database`.`order_details` (`serial_number`, `number`, `id_order_details`) VALUES ('3s1a027s', '2', '3');
INSERT INTO `database`.`order_details` (`serial_number`, `number`, `id_order_details`) VALUES ('4s452d34', '35', '3');*/
