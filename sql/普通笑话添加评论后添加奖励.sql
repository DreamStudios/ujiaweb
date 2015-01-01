DELIMITER $$

USE `ujiajoke`$$

DROP TRIGGER /*!50032 IF EXISTS */ `jokecomment_AINS`$$

CREATE
    /*!50017 DEFINER = 'root'@'localhost' */
    TRIGGER `jokecomment_AINS` AFTER INSERT ON `jokecomment` 
    FOR EACH ROW 
BEGIN
    -- 添加评论，奖励5个经验
    UPDATE ujiajoke.`userinfo` SET experience = experience + 5 WHERE uid = new.uid;
END;
$$

DELIMITER ;