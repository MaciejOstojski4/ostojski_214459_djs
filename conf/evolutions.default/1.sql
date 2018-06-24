# User schema

# --- !Ups

create table `user` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`first_name`
 TEXT NOT NULL,`last_name` TEXT NOT NULL,
 `email` TEXT NOT NULL)

# --- !Downs

drop table `user`

CREATE TABLE `dsl_project`.`crypto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `name` VARCHAR(45) NULL,
  `price` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id`
    FOREIGN KEY ()
    REFERENCES `dsl_project`.`user` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;

