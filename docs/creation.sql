-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema alumnitracker
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema alumnitracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `alumnitracker` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `alumnitracker` ;

-- -----------------------------------------------------
-- Table `alumnitracker`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bnumber` INT NULL,
  `email` VARCHAR(100) NULL,
  `personal_email` VARCHAR(100) NULL,
  `password` VARCHAR(45) NOT NULL,
  `salt` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
  `graduation_year` INT NULL,
  `occupation` VARCHAR(45) NULL,
  `title` VARCHAR(5) NULL,
  `suffix` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `personal_email_UNIQUE` (`personal_email` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`job`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `company` VARCHAR(100) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_job_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NULL,
  `description` VARCHAR(1000) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`interest` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`user_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user_interest` (
  `user_id` INT NOT NULL,
  `interest_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `interest_id`),
  INDEX `fk_user_has_interest_interest1_idx` (`interest_id` ASC),
  INDEX `fk_user_has_interest_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_interest_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_interest_interest1`
    FOREIGN KEY (`interest_id`)
    REFERENCES `alumnitracker`.`interest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `file` BLOB NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_image_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_image_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `file` BLOB NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`user_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user_file` (
  `user_id` INT NOT NULL,
  `file_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `file_id`),
  INDEX `fk_user_has_file_file1_idx` (`file_id` ASC),
  INDEX `fk_user_has_file_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_file_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_file_file1`
    FOREIGN KEY (`file_id`)
    REFERENCES `alumnitracker`.`file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`major`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`major` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `concentration` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`job_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`job_interest` (
  `interest_id` INT NOT NULL,
  `job_id` INT NOT NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`event_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`event_interest` (
  `interest_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`interest_id`, `event_id`),
  INDEX `fk_interest_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_interest_has_event_interest1_idx` (`interest_id` ASC),
  CONSTRAINT `fk_interest_has_event_interest1`
    FOREIGN KEY (`interest_id`)
    REFERENCES `alumnitracker`.`interest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interest_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `alumnitracker`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alumnitracker`.`user_major`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alumnitracker`.`user_major` (
  `user_id` INT NOT NULL,
  `major_id` INT NOT NULL,
  `minor` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`, `major_id`),
  INDEX `fk_user_has_major_major1_idx` (`major_id` ASC),
  INDEX `fk_user_has_major_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_major_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `alumnitracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_major_major1`
    FOREIGN KEY (`major_id`)
    REFERENCES `alumnitracker`.`major` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
