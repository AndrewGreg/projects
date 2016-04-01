-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema alumnitracker
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema alumnitracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `alumnitracker` DEFAULT CHARACTER SET utf8 ;
USE `alumnitracker` ;

-- -----------------------------------------------------
-- Table `alumnitracker`.`college`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`college` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`title`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`title` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `bnumber` INT(11) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `personal_email` VARCHAR(100) NULL DEFAULT NULL,
  `password` VARCHAR(300) NOT NULL,
  `salt` VARCHAR(300) NOT NULL,
  `title_id` INT(11) NULL DEFAULT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `role` INT(11) NOT NULL,
  `graduation_year` INT(11) NULL DEFAULT NULL,
  `occupation` VARCHAR(200) NULL DEFAULT NULL,
  `company` VARCHAR(200) NULL DEFAULT NULL,
  `suffix` VARCHAR(45) NULL DEFAULT NULL,
  `biography` VARCHAR(1000) NULL DEFAULT NULL,
  `experience` VARCHAR(1000) NULL DEFAULT NULL,
  `hidden` TINYINT(1) NOT NULL DEFAULT '0',
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  `created` DATETIME NULL DEFAULT NULL,
  `last_active` DATETIME NULL DEFAULT NULL,
  `last_modified` DATETIME NULL DEFAULT NULL,
  `last_login` DATETIME NULL DEFAULT NULL,
  `social_media` VARCHAR(200) NULL DEFAULT NULL,
  `phone_number` INT(11) NULL DEFAULT NULL,
  `work_number` INT(11) NULL DEFAULT NULL,
  `user_verified` TINYINT(1) NULL DEFAULT '0',
  `admin_verified` TINYINT(1) NULL DEFAULT '0',
  `graduate_verified` TINYINT(1) NULL DEFAULT '0',
  `current_graduate_verified` TINYINT(1) NULL DEFAULT '0',
  `graduate_school` VARCHAR(200) NULL DEFAULT NULL,
  `public` INT(11) NULL DEFAULT '1',
  `reference` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `personal_email_UNIQUE` (`personal_email` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_title1_idx` (`title_id` ASC),
  CONSTRAINT `fk_user_title1`
    FOREIGN KEY (`title_id`)
    REFERENCES `alumnitracker`.`title` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(1000) NULL DEFAULT NULL,
  `phone_number` VARCHAR(11) NULL DEFAULT NULL,
  `message` VARCHAR(1000) NULL DEFAULT NULL,
  `user_id` INT(11) NOT NULL,
  `recipient_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `recipient_id`),
  INDEX `fk_contact_user1_idx` (`user_id` ASC),
  INDEX `fk_contact_user2_idx` (`recipient_id` ASC),
  CONSTRAINT `fk_contact_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contact_user2`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1000) NOT NULL,
  `college_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_department_college1_idx` (`college_id` ASC),
  CONSTRAINT `fk_department_college1`
    FOREIGN KEY (`college_id`)
    REFERENCES `alumnitracker`.`college` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`event` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `user_id` INT(11) NOT NULL,
  `hidden` TINYINT(1) NOT NULL DEFAULT '0',
  `start_time` VARCHAR(10) NULL DEFAULT NULL,
  `end_time` VARCHAR(10) NULL DEFAULT NULL,
  `public` INT(11) NOT NULL DEFAULT '1',
  `longitude` FLOAT NULL DEFAULT NULL,
  `latitude` FLOAT NULL DEFAULT NULL,
  `role` INT(11) NOT NULL DEFAULT '0',
  `reference` VARCHAR(45) NULL DEFAULT NULL,
  `location` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`event_department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`event_department` (
  `event_id` INT(11) NOT NULL,
  `department_id` INT(11) NOT NULL,
  PRIMARY KEY (`event_id`, `department_id`),
  INDEX `fk_event_has_department_department1_idx` (`department_id` ASC),
  INDEX `fk_event_has_department_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_event_has_department_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `alumnitracker`.`department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_has_department_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `alumnitracker`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`interest` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `hidden` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`event_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`event_interest` (
  `interest_id` INT(11) NOT NULL,
  `event_id` INT(11) NOT NULL,
  PRIMARY KEY (`interest_id`, `event_id`),
  INDEX `fk_interest_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_interest_has_event_interest1_idx` (`interest_id` ASC),
  CONSTRAINT `fk_interest_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `alumnitracker`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interest_has_event_interest1`
    FOREIGN KEY (`interest_id`)
    REFERENCES `alumnitracker`.`interest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`file` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`hours`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`hours` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `classification` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`image` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file` BLOB NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_image_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_image_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`job`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`job` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `company` VARCHAR(100) NULL DEFAULT NULL,
  `user_id` INT(11) NOT NULL,
  `hidden` TINYINT(1) NOT NULL DEFAULT '0',
  `end_date` VARCHAR(10) NULL DEFAULT NULL,
  `start_date` VARCHAR(10) NULL DEFAULT NULL,
  `reference` VARCHAR(45) NULL DEFAULT NULL,
  `public` INT(11) NULL DEFAULT '1',
  `hours_id` INT(11) NOT NULL,
  `salary` TINYINT(1) NOT NULL DEFAULT '0',
  `start_wage` FLOAT NULL DEFAULT NULL,
  `end_wage` FLOAT NULL DEFAULT NULL,
  `start_salary` INT(11) NULL DEFAULT NULL,
  `end_salary` INT(11) NULL DEFAULT NULL,
  `link` VARCHAR(1000) NULL DEFAULT NULL,
  `location` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `hours_id`),
  INDEX `fk_job_user_idx` (`user_id` ASC),
  INDEX `fk_job_hours1_idx` (`hours_id` ASC),
  CONSTRAINT `fk_job_hours1`
    FOREIGN KEY (`hours_id`)
    REFERENCES `alumnitracker`.`hours` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`job_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`job_interest` (
  `interest_id` INT(11) NOT NULL,
  `job_id` INT(11) NOT NULL,
  PRIMARY KEY (`interest_id`, `job_id`),
  INDEX `fk_interest_has_job_job1_idx` (`job_id` ASC),
  INDEX `fk_interest_has_job_interest1_idx` (`interest_id` ASC),
  CONSTRAINT `fk_interest_has_job_interest1`
    FOREIGN KEY (`interest_id`)
    REFERENCES `alumnitracker`.`interest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interest_has_job_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `alumnitracker`.`job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`mailing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`mailing` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`major`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`major` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `concentration` TINYINT(1) NOT NULL DEFAULT '0',
  `major_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_major_major1_idx` (`major_id` ASC),
  CONSTRAINT `fk_major_major1`
    FOREIGN KEY (`major_id`)
    REFERENCES `alumnitracker`.`major` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`reason`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`reason` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`transfer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `verified` TINYINT(1) NOT NULL DEFAULT '0',
  `reason_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `verified_id` INT(11) NULL DEFAULT NULL,
  `hidden` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_transfer_reason1_idx` (`reason_id` ASC),
  INDEX `fk_transfer_user1_idx` (`user_id` ASC),
  INDEX `fk_transfer_user2_idx` (`verified_id` ASC),
  CONSTRAINT `fk_transfer_reason1`
    FOREIGN KEY (`reason_id`)
    REFERENCES `alumnitracker`.`reason` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transfer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transfer_user2`
    FOREIGN KEY (`verified_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`user_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user_file` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `file_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `file_id`),
  INDEX `fk_user_has_file_file1_idx` (`file_id` ASC),
  INDEX `fk_user_has_file_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_file_file1`
    FOREIGN KEY (`file_id`)
    REFERENCES `alumnitracker`.`file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_file_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`user_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user_interest` (
  `user_id` INT(11) NOT NULL,
  `interest_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `interest_id`),
  INDEX `fk_user_has_interest_interest1_idx` (`interest_id` ASC),
  INDEX `fk_user_has_interest_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_interest_interest1`
    FOREIGN KEY (`interest_id`)
    REFERENCES `alumnitracker`.`interest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_interest_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `alumnitracker`.`user_major`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user_major` (
  `user_id` INT(11) NOT NULL,
  `major_id` INT(11) NOT NULL,
  `minor` TINYINT(1) NOT NULL DEFAULT '0',
  `hidden` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `major_id`),
  INDEX `fk_user_has_major_major1_idx` (`major_id` ASC),
  INDEX `fk_user_has_major_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_major_major1`
    FOREIGN KEY (`major_id`)
    REFERENCES `alumnitracker`.`major` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_major_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
