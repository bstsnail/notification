DROP DATABASE IF EXISTS notification;
CREATE DATABASE notification;
USE notification;
CREATE TABLE user (
  id int not null auto_increment primary key,
  username varchar(255) not null,
  password varchar(255) not null,
  email varchar(255) not null UNIQUE,
  timezone varchar(64) not null
);

CREATE TABLE event (
  id int not null auto_increment primary key,
  user_id int,
  date bigint, -- in ms
  isLunar tinyint(1),
  description text,
  emails text,
  sendBeforeEvent int, -- minutes
  polling int, -- minutes
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);
