-- MySQL Workbench Synchronization
-- Generated: 2022-07-20 18:33
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Enrique Mingorance Cano

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER SCHEMA `recuerdamelon`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`user` (
  `id` INT(11) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `avatar` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`user_role` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`user_role_has_user` (
  `user_role_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_role_id`, `user_id`),
  INDEX `fk_user_role_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_role_has_user_user_role_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role_has_user_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `recuerdamelon`.`user_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`task` (
  `id` INT(11) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `location_url` VARCHAR(255) NULL DEFAULT NULL,
  `delete` TINYINT(1) NOT NULL DEFAULT 0,
  `owner_id` INT(11) NOT NULL,
  `task_type_id` INT(11) NOT NULL,
  `calendar_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `owner_id`, `task_type_id`, `calendar_id`),
  INDEX `fk_task_user1_idx` (`owner_id` ASC) VISIBLE,
  INDEX `fk_task_task_type1_idx` (`task_type_id` ASC) VISIBLE,
  INDEX `fk_task_calendar1_idx` (`calendar_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_user1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `recuerdamelon`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_task_type1`
    FOREIGN KEY (`task_type_id`)
    REFERENCES `recuerdamelon`.`task_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_calendar1`
    FOREIGN KEY (`calendar_id`)
    REFERENCES `recuerdamelon`.`calendar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`task_has_user` (
  `task_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`task_id`, `user_id`),
  INDEX `fk_task_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_task_has_user_task1_idx` (`task_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_has_user_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `recuerdamelon`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`task_type` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `icon` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`calendar` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `calendarcol` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`calendar_has_user` (
  `calendar_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`calendar_id`, `user_id`),
  INDEX `fk_calendar_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_calendar_has_user_calendar1_idx` (`calendar_id` ASC) VISIBLE,
  CONSTRAINT `fk_calendar_has_user_calendar1`
    FOREIGN KEY (`calendar_id`)
    REFERENCES `recuerdamelon`.`calendar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_calendar_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recuerdamelon`.`notification` (
  `id` INT(11) NOT NULL,
  `notification_time` INT(11) NOT NULL,
  `notified` TINYINT(1) NOT NULL DEFAULT 0,
  `user_id` INT(11) NOT NULL,
  `task_id` INT(11) NOT NULL,
  `task_owner_id` INT(11) NOT NULL,
  `task_task_type_id` INT(11) NOT NULL,
  `task_calendar_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `task_id`, `task_owner_id`, `task_task_type_id`, `task_calendar_id`),
  INDEX `fk_notification_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_notification_task1_idx` (`task_id` ASC, `task_owner_id` ASC, `task_task_type_id` ASC, `task_calendar_id` ASC) VISIBLE,
  CONSTRAINT `fk_notification_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `recuerdamelon`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_task1`
    FOREIGN KEY (`task_id` , `task_owner_id` , `task_task_type_id` , `task_calendar_id`)
    REFERENCES `recuerdamelon`.`task` (`id` , `owner_id` , `task_type_id` , `calendar_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


--INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(1,'calendario1', '2022-07-07 09:48:00.0');
--INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(2,'calendario2', '2022-07-07 09:48:00.0');
--INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(3,'calendario3', '2022-07-07 09:48:00.0');
--INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(4,'calendario4', '2022-07-07 09:48:00.0');
--
--INSERT INTO Notification (ID,notification_Time,notified) VALUES(1,2,false);
--INSERT INTO Notification (ID,notification_Time,notified) VALUES(3,4,false);
--INSERT INTO Notification (ID,notification_Time,notified) VALUES(5,6,true);
--INSERT INTO Notification (ID,notification_Time,notified) VALUES(7,8,false);
--
--INSERT INTO Task_Type (ID,NAME) VALUES(1,'taskType1');
--INSERT INTO Task_Type (ID,NAME) VALUES(2,'taskType2');
--INSERT INTO Task_Type (ID,NAME) VALUES(3,'taskType3');
--INSERT INTO Task_Type (ID,NAME) VALUES(4,'taskType4');
--
--INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
--VALUES(1,'task1','2007-12-03','2007-12-11','description1','url1',false);
--INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
--VALUES(2,'task2','2007-12-03','2007-12-11','description2','url2',false);
--INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
--VALUES(3,'task3','2007-12-03','2007-12-09','description3','url3',false);
--INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
--VALUES(4,'task4','2007-12-03','2007-12-09','description4','url4',false);
--
--INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
--VALUES(1,'user1','name1','surname1','email1','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);
--INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
--VALUES(2,'user2','name2','surname2','email2','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);
--INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
--VALUES(3,'user3','name3','surname3','email3','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);
--INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
--VALUES(4,'admin','name4','surname4','email4','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);
--
--INSERT INTO User_Role (ID, NAME) VALUES(1,'PLAIN_USER');
--INSERT INTO User_Role (ID, NAME) VALUES(2,'ADMIN');
--
--INSERT INTO `USER_HAS_ROLE` (`USER_ID`, `ROLE_ID`)
--VALUES
--    (1,1),
--    (2,1),
--    (3,1),
--    (4,1),
--    (4,2);
