CREATE DATABASE audiroDB;

USE audiroDB;

/* User */
DROP TABLE IF EXISTS USER;

CREATE TABLE USER(
    USER_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(20) NOT NULL,
    TOKEN VARCHAR(200) NOT NULL,
    NICKNAME VARCHAR(20) NOT NULL UNIQUE,
    PROFILE_MESSAGE VARCHAR(100) NULL,
    PROFILE_IMG VARCHAR(200) NOT NULL DEFAULT 'https://t1.daumcdn.net/cfile/tistory/2513B53E55DB206927'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO USER (NAME, TOKEN, NICKNAME) VALUES ('sohee', 'abv12efwsedfwerg', 'sohee');
INSERT INTO USER (NAME, TOKEN, NICKNAME) VALUES ('gaok', 'abv12eqwqdfwerg', 'gaok');
INSERT INTO USER (NAME, TOKEN, NICKNAME) VALUES ('sungwhan', 'webv12ef1231', 'sungwhan');
INSERT INTO USER (NAME, TOKEN, NICKNAME) VALUES ('hosung', '12wrtsedddfwerg', 'hosung');


/* Song */
DROP TABLE IF EXISTS SONG;

CREATE TABLE SONG(
    SONG_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    SONG_IMG VARCHAR(200) NOT NULL,
    SONG_TITLE VARCHAR(50) NOT NULL,
    SINGER VARCHAR(50) NOT NULL,
    SONG_URL VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO SONG (SONG_IMG, SONG_TITLE, SINGER, SONG_URL) VALUES('http://image_path.~~~', 'Ditto', 'New Jeans', 'https://yutube.Ditto~~~');


/* Spot */
DROP TABLE IF EXISTS SPOT;

CREATE TABLE SPOT(
    SPOT_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    SPOT_NAME VARCHAR(200) NOT NULL,
    SPOT_ADDR VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT SPOT (SPOT_NAME, SPOT_ADDR) VALUES('역삼점', '서울시 강남구 역삼동~');


/* Gift */
DROP TABLE IF EXISTS GIFT;

CREATE TABLE GIFT(
    GIFT_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    USER_ID BIGINT NOT NULL,
    SPOT_ID BIGINT NOT NULL,
    SONG_ID BIGINT NOT NULL,
    GIFT_IMG VARCHAR(200) NOT NULL,
    IS_OPEN BOOLEAN NOT NULL DEFAULT TRUE,
    GIFT_TAG VARCHAR(100) NULL,
    REG_TIME TIMESTAMP NOT NULL DEFAULT NOW(),
    GIFT_FEEDBACK1 INT NOT NULL DEFAULT 0,
    GIFT_FEEDBACK2 INT NOT NULL DEFAULT 0,
    GIFT_FEEDBACK3 INT NOT NULL DEFAULT 0,
    GIFT_FEEDBACK4 INT NOT NULL DEFAULT 0,
    IS_MANITO BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID) ON DELETE CASCADE,
    FOREIGN KEY (SPOT_ID) REFERENCES SPOT(SPOT_ID) ON DELETE CASCADE,
    FOREIGN KEY (SONG_ID) REFERENCES SONG(SONG_ID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO GIFT (GIFT_IMG, USER_ID, SPOT_ID, SONG_ID) VALUES ('https://www.naver.com/gift1', 2, 1, 1);
INSERT INTO GIFT (GIFT_IMG, USER_ID, SPOT_ID, SONG_ID, GIFT_TAG) VALUES ('https://www.naver.com/gift1', 2, 1, 1, "SUNNY");


/* Postcard */
DROP TABLE IF EXISTS POSTCARD;

CREATE TABLE POSTCARD(
    POSTCARD_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    SEND_ID BIGINT NOT NULL,
    SPOT_ID BIGINT NOT NULL,
    SONG_ID BIGINT NOT NULL,
    POSTCARD_IMG VARCHAR(200) NOT NULL,
    REG_TIME TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (SEND_ID) REFERENCES USER(USER_ID) ON DELETE CASCADE,
    FOREIGN KEY (SPOT_ID) REFERENCES SPOT(SPOT_ID) ON DELETE CASCADE,
    FOREIGN KEY (SONG_ID) REFERENCES SONG(SONG_ID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO POSTCARD (SEND_ID, SPOT_ID, SONG_ID, POSTCARD_IMG) VALUES (2, 1, 1, 'IMGE PATH ~~~');


/* Musicmate */
DROP TABLE IF EXISTS MUSICMATE;

CREATE TABLE MUSICMATE(
    MUSICMATE_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    USER_ID BIGINT NOT NULL,
    MATE_ID BIGINT NOT NULL,
    IS_MATE BOOLEAN NOT NULL DEFAULT TRUE,
    IS_BLOCK BOOLEAN NOT NULL DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO MUSICMATE (USER_ID, MATE_ID) VALUES (2, 3);