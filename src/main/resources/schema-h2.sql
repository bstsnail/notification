CREATE TABLE user (
  id int not null auto_increment primary key,
  username varchar(255) not null,
  password varchar(255) not null,
  email varchar(255) not null UNIQUE
);

CREATE TABLE notification_event (
  id int not null auto_increment primary key,
  user_id int,
  date bigint,
  isLunar tinyint(1),
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);

CREATE TABLE notification (
  id int not null auto_increment primary key,
  event_id int not null,
  emails text,
  sendBeforeEvent int, -- minutes
  polling int, -- minutes

  CONSTRAINT FOREIGN KEY (event_id) REFERENCES notification_event(id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);