-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema recuerdamelon
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema recuerdamelon
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `recuerdamelon` DEFAULT CHARACTER SET utf8mb3 ;
USE `recuerdamelon` ;

-- -----------------------------------------------------
-- Table `recuerdamelon`.`business`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`business` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `avatar` TINYBLOB NULL DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `nif` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`business_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`business_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `tipo_trabajador` VARCHAR(255) NOT NULL,
  `business_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_gj1g9dpsahrnb3bhsgilp0oy0` (`business_id` ASC) VISIBLE,
  CONSTRAINT `FKas2f9lyhs15dxvdscebtqt1fh`
    FOREIGN KEY (`business_id`)
    REFERENCES `recuerdamelon`.`business` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`calendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`calendar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `task_date` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`chat_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`chat_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `sender` VARCHAR(255) NULL DEFAULT NULL,
  `type` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`community`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`community` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `admin` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  `avatar` BLOB NULL DEFAULT NULL,
  `business_user_id` INT NULL DEFAULT NULL,
  `business_id` INT NULL DEFAULT NULL,
  `business_avatar` TINYBLOB NULL DEFAULT NULL,
  `birthday` VARCHAR(255) NULL DEFAULT NULL,
  `business` BIT(1) NOT NULL,
  `nationality` VARCHAR(255) NULL DEFAULT NULL,
  `nif` VARCHAR(255) NULL DEFAULT NULL,
  `team` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_1f2f71or7sv0e8w979ay7a4sn` (`business_user_id` ASC) VISIBLE,
  INDEX `FK4xigqfhyelwj2n5psmxapdr0o` (`business_id` ASC) VISIBLE,
  CONSTRAINT `FK4xigqfhyelwj2n5psmxapdr0o`
    FOREIGN KEY (`business_id`)
    REFERENCES `recuerdamelon`.`business` (`id`),
  CONSTRAINT `FKcg32bm0th1r6ia5fjt77t1gnt`
    FOREIGN KEY (`business_user_id`)
    REFERENCES `recuerdamelon`.`business_user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`community_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`community_has_user` (
  `community_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`community_id`, `user_id`),
  INDEX `FK36cho5qymvb4ujbex7ytm23vj` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK36cho5qymvb4ujbex7ytm23vj`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`),
  CONSTRAINT `FK552vwdklgatmuu49k5kg1euoe`
    FOREIGN KEY (`community_id`)
    REFERENCES `recuerdamelon`.`community` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`confirmation_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`confirmation_token` (
  `token_id` BIGINT NOT NULL,
  `confirmation_token` VARCHAR(255) NULL DEFAULT NULL,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`token_id`),
  INDEX `FKhjrtky9wbd6lbk7mu9tuddqgn` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKhjrtky9wbd6lbk7mu9tuddqgn`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`mensajes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`mensajes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `texto` VARCHAR(255) NOT NULL,
  `date` VARCHAR(255) NULL DEFAULT NULL,
  `sender` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `deleted` TINYINT(1) NULL DEFAULT '0',
  `saved` TINYINT(1) NULL DEFAULT '0',
  `sent` TINYINT(1) NULL DEFAULT '0',
  `recieved` TINYINT(1) NULL DEFAULT '0',
  `invitation` TINYINT(1) NULL DEFAULT '0',
  `community` VARCHAR(45) NULL DEFAULT NULL,
  `acepted` BIT(1) NOT NULL,
  `reciever` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`mensajes_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`mensajes_has_user` (
  `mensajes_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`mensajes_id`, `user_id`),
  INDEX `fk_mensajes_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_mensajes_has_user_mensajes1_idx` (`mensajes_id` ASC) VISIBLE,
  CONSTRAINT `fk_mensajes_has_user_mensajes1`
    FOREIGN KEY (`mensajes_id`)
    REFERENCES `recuerdamelon`.`mensajes` (`id`),
  CONSTRAINT `fk_mensajes_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(255) NOT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKb3y6etti1cfougkdr0qiiemgv` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKb3y6etti1cfougkdr0qiiemgv`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`task_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`task_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `icon` BLOB NULL DEFAULT NULL,
  `image` TINYBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `location_url` VARCHAR(255) NULL DEFAULT NULL,
  `task_type_id` INT NOT NULL,
  `end_date` DATETIME(6) NOT NULL,
  `start_date` DATETIME(6) NOT NULL,
  `business_id` INT NULL DEFAULT NULL,
  `deleted` BIT(1) NOT NULL,
  `horario` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `task_type_id`),
  INDEX `fk_task_task_type1_idx` (`task_type_id` ASC) VISIBLE,
  INDEX `FK31351ddav78eao7ca7v3l4j3d` (`business_id` ASC) VISIBLE,
  CONSTRAINT `FK31351ddav78eao7ca7v3l4j3d`
    FOREIGN KEY (`business_id`)
    REFERENCES `recuerdamelon`.`business` (`id`),
  CONSTRAINT `fk_task_task_type1`
    FOREIGN KEY (`task_type_id`)
    REFERENCES `recuerdamelon`.`task_type` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `notification_time` INT NOT NULL,
  `notified` BIT(1) NOT NULL,
  `task_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKg6e8dcyvu9qdcfds2o3pj9qen` (`task_id` ASC) VISIBLE,
  INDEX `FKb0yvoep4h4k92ipon31wmdf7e` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKb0yvoep4h4k92ipon31wmdf7e`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`),
  CONSTRAINT `FKg6e8dcyvu9qdcfds2o3pj9qen`
    FOREIGN KEY (`task_id`)
    REFERENCES `recuerdamelon`.`task` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`task_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`task_has_user` (
  `task_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`task_id`, `user_id`),
  INDEX `fk_task_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_task_has_user_task1_idx` (`task_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_has_user_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `recuerdamelon`.`task` (`id`),
  CONSTRAINT `fk_task_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`user_has_calendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`user_has_calendar` (
  `user_id` INT NOT NULL,
  `calendar_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `calendar_id`),
  INDEX `FKsgdjv2ijwqis76dvh01vfunyi` (`calendar_id` ASC) VISIBLE,
  CONSTRAINT `FK59a4d6y8n0t78aw35flpcrb9c`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`),
  CONSTRAINT `FKsgdjv2ijwqis76dvh01vfunyi`
    FOREIGN KEY (`calendar_id`)
    REFERENCES `recuerdamelon`.`calendar` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`user_role` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `recuerdamelon`.`user_role_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `recuerdamelon`.`user_role_has_user` (
  `user_role_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`user_role_id`, `user_id`),
  INDEX `fk_user_role_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_role_has_user_user_role_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`),
  CONSTRAINT `fk_user_role_has_user_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `recuerdamelon`.`user_role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
