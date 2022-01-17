-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema untappeddb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `untappeddb` ;

-- -----------------------------------------------------
-- Schema untappeddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `untappeddb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `untappeddb` ;

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `created_at` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `beer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `beer` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `beer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` VARCHAR(400) NULL DEFAULT NULL,
  `ingredients` TEXT(500) NULL DEFAULT NULL,
  `alcoholic` TINYINT NOT NULL DEFAULT 0,
  `contains_alcohol` TINYINT NOT NULL DEFAULT 0,
  `calories` INT NOT NULL DEFAULT 160,
  `volume` DOUBLE NOT NULL DEFAULT 16,
  `active` TINYINT NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  `alcohol` DOUBLE NOT NULL DEFAULT 0.0,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_beer_beer_tracker_idx` (`user_id` ASC),
  CONSTRAINT `fk_beer_beer_tracker`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
SET SQL_MODE = '';
DROP USER IF EXISTS untappeduser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
SHOW WARNINGS;
CREATE USER 'untappeduser'@'localhost' IDENTIFIED BY 'untappeduser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'untappeduser'@'localhost';
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `User`
-- -----------------------------------------------------
START TRANSACTION;
USE `untappeddb`;
INSERT INTO `User` (`id`, `first_name`, `last_name`, `created_at`) VALUES (1, 'Johnny', 'Bobbert', '2022-01-10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `beer`
-- -----------------------------------------------------
START TRANSACTION;
USE `untappeddb`;
INSERT INTO `beer` (`id`, `name`, `description`, `ingredients`, `alcoholic`, `contains_alcohol`, `calories`, `volume`, `active`, `user_id`, `alcohol`, `updated_at`, `created_at`) VALUES (1, 'Lagreat Don\'t Hate', 'Lager', 'barley, malt, water', 0, DEFAULT, 88, 16, DEFAULT, 1, 0, DEFAULT, '2022-01-10');
INSERT INTO `beer` (`id`, `name`, `description`, `ingredients`, `alcoholic`, `contains_alcohol`, `calories`, `volume`, `active`, `user_id`, `alcohol`, `updated_at`, `created_at`) VALUES (2, 'Oswalt', 'Pale Ale', 'barley, malt, hops, water, orange', 1, DEFAULT, 200, 16, DEFAULT, 1, 5.6, DEFAULT, '2022-01-10');
INSERT INTO `beer` (`id`, `name`, `description`, `ingredients`, `alcoholic`, `contains_alcohol`, `calories`, `volume`, `active`, `user_id`, `alcohol`, `updated_at`, `created_at`) VALUES (3, 'Trees For Me', 'India Pale Ale', 'water, hops, pinnapple, malt', 1, DEFAULT, 250, 16, DEFAULT, 1, 7.8, DEFAULT, '2022-01-10');
INSERT INTO `beer` (`id`, `name`, `description`, `ingredients`, `alcoholic`, `contains_alcohol`, `calories`, `volume`, `active`, `user_id`, `alcohol`, `updated_at`, `created_at`) VALUES (4, 'Pils Mils', 'Pilsner', 'Water, malt, beer', 1, DEFAULT, 150, 16, DEFAULT, 1, 5.0, DEFAULT, '2022-01-10');
INSERT INTO `beer` (`id`, `name`, `description`, `ingredients`, `alcoholic`, `contains_alcohol`, `calories`, `volume`, `active`, `user_id`, `alcohol`, `updated_at`, `created_at`) VALUES (5, 'Night Hawk', 'Chocolate Stout', 'barley, malt, chocolate, water', 1, DEFAULT, 250, 10, DEFAULT, 1, 12.0, DEFAULT, '2022-01-10');

COMMIT;

