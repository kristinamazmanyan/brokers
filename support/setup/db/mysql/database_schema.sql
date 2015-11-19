/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for brokerexam
CREATE DATABASE IF NOT EXISTS `brokerexam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `brokerexam`;

# -------------- User management -----------------

DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(12) NOT NULL,
  `name` varchar(70) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  INDEX `permission_name_idx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`id` INT(12) NOT NULL,
	`name` VARCHAR(70) NOT NULL,
	`description` TEXT NULL,
	`created_by` INT(12) NOT NULL,
	`created_at` DATETIME NOT NULL,
	`changed_by` INT(12) NOT NULL,
	`changed_at` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `role_name_idx` (`name`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions` (
	`role_id` INT(12) NOT NULL,
	`permission_id` INT(12) NOT NULL,
	UNIQUE INDEX `role_id_permission_id` (`role_id`, `permission_id`),
	INDEX `role_permissions_permission_idx` (`permission_id`),
	CONSTRAINT `role_permissions_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
	CONSTRAINT `role_permissions_permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` INT(12) NOT NULL,
	`login` VARCHAR(100) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	`full_name` VARCHAR(1000) NULL,
	`email` VARCHAR(128) NULL DEFAULT NULL,
	`phone` VARCHAR(128) NULL DEFAULT NULL,
	`last_login` TIMESTAMP NULL DEFAULT NULL,
	`deleted` TINYINT(3) NOT NULL DEFAULT '0',
	`created_by` INT(12) NOT NULL,
	`created_at` DATETIME NOT NULL,
	`changed_by` INT(12) NOT NULL,
	`changed_at` DATETIME NOT NULL,	
	PRIMARY KEY (`id`),
	UNIQUE INDEX `user_login_uidx` (`login`),
	INDEX `user_deleted_idx` (`deleted`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;


DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
	`user_id` INT(12) NOT NULL,
	`role_id` INT(12) NOT NULL,
	UNIQUE INDEX `user_id_role_id_uidx` (`user_id`, `role_id`),
	INDEX `user_roles_role_id_idx` (`role_id`),
	CONSTRAINT `user_roles_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
	CONSTRAINT `user_roles_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

# -------------- Events -----------------

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
	`id` INT(12) NOT NULL AUTO_INCREMENT,
	`event_type` INT(4) NOT NULL,
	`message` TEXT NULL,
	`performed_by` INT(12),
	`performed_on` VARCHAR(40),	
	`created_at` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `event_event_type_idx` (`event_type`),
	INDEX `event_performed_by_idx` (`performed_by`),
	INDEX `event_created_at_idx` (`created_at`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `rf_exam_time` (
  `id` int(2) NOT NULL,
  `time` int(3) NOT NULL,
  `q_count` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rf_exam_condition` (
  `id` int(2) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `rf_examinator_position` (
	`id` INT(1) NOT NULL,
	`name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `exams` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(400) NOT NULL,
	`start_date` DATE NOT NULL,
	`status` INT(1) NOT NULL,
	`archived` INT(1) NOT NULL,
	`usermod` VARCHAR(50) NOT NULL,
	`datemod` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `exam_members` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(100) NOT NULL,
	`last_name` VARCHAR(100) NOT NULL,
	`surname` VARCHAR(100) NULL DEFAULT NULL,
	`exam_end_time` TIMESTAMP NULL DEFAULT NULL,
	`passport` VARCHAR(14) NOT NULL,
	`exam_id` INT(12) NOT NULL,
	`status` INT(1) NOT NULL,
	`session_id` CHAR(9) NOT NULL,
	`usermod` VARCHAR(50) NOT NULL,
	`datemod` DATETIME NOT NULL,
	`permission` INT(1) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `committee_members` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(100) NOT NULL,
	`last_name` VARCHAR(100) NOT NULL,
	`position_type` INT(1) NOT NULL,
	`exam_id` INT(12) NOT NULL,
	`usermod` VARCHAR(50) NOT NULL,
	`datemod` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `exam_questions` (
	`id` INT(12) NOT NULL AUTO_INCREMENT,
	`exam_id` INT(12) NOT NULL,
	`question` VARCHAR(4000) NOT NULL,
	`answer_a` VARCHAR(4000) NOT NULL,
	`answer_b` VARCHAR(4000) NOT NULL,
	`answer_c` VARCHAR(4000) NOT NULL,
	`answer_d` VARCHAR(4000) NOT NULL,
	`right_answer` INT(1) NOT NULL,
	`quest_index` INT(10) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `quiz_results` (
	`id` INT(12) NOT NULL AUTO_INCREMENT,
	`exam_id` INT(12) NOT NULL,
	`right_answer` INT(1) NOT NULL,
	`em_id` INT(12) NOT NULL,
	`question_id` INT(12) NOT NULL,
	`answer` INT(1) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `quiz_results_1` (
	`id` INT(12) NOT NULL AUTO_INCREMENT,
	`exam_id` INT(12) NOT NULL,
	`right_answer` INT(1) NOT NULL,
	`em_id` INT(12) NOT NULL,
	`question_id` INT(12) NOT NULL,
	`answer` INT(1) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `quiz_final_result` (
	`id` INT(12) NOT NULL AUTO_INCREMENT,
	`exam_id` INT(12) NOT NULL,
	`em_id` INT(12) NOT NULL,
	`percent` INT(12) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `constr2` (`exam_id`, `em_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;






