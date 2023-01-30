DROP DATABASE audiroDB;
CREATE DATABASE audiroDB;
USE audiroDB;
CREATE TABLE USER(
    USER_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(20) NOT NULL,
    TOKEN VARCHAR(200) NOT NULL,
    NICKNAME VARCHAR(20) NOT NULL UNIQUE,
    PROFILE_MESSAGE VARCHAR(100) NULL,
    PROFILE_IMG VARCHAR(200) NOT NULL DEFAULT 'https://t1.daumcdn.net/cfile/tistory/2513B53E55DB206927',
	EMAIL VARCHAR(50),
    PASSWORD VARCHAR(100),
    ROLE VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
desc user;
select * from user;
