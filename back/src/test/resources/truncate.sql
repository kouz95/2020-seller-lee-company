SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE ARTICLE;
TRUNCATE TABLE CHAT_ROOM;

ALTER TABLE ARTICLE ALTER COLUMN article_id RESTART WITH 1;
ALTER TABLE CHAT_ROOM ALTER COLUMN chat_room_id RESTART WITH 1;

SET FOREIGN_KEY_CHECKS = 1;