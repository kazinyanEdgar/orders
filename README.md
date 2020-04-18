# Orders

Для unmarshalling использовал JAXB.

## Предварительные условия
В MySQL необходимо выполнить скрипт из **createAndInsert.sql**
и в файле **hibernate.cfg.xml** поменять соответствующие properties.

``` sql
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
```

## Запуск
* В командной строке перейти в папку проекта
(где присутствует файл pom.xml) и выполнить команду

*mvn spring-boot:run*

и перейти в браузере по ссылке localhost:8080/orders.xhtml
